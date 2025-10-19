package codegenFragmenter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class FragmentLinkerTest {

    @Test
    void returnsEmptyMapForEmptyInput() {
        Map<String, String> input = new LinkedHashMap<>();

        Map<String, List<String>> result = FragmentLinker.link(input);

        assertTrue(result.isEmpty(), "Expected no entries for empty input");
    }

    @Test
    void headerIsNotLinked() {
        Map<String, String> input = new LinkedHashMap<>();
        input.put("Header", "Header: Common prelude, types, and imports");

        Map<String, List<String>> result = FragmentLinker.link(input);

        assertEquals(1, result.size(), "Only the header key should be present");
        assertTrue(result.containsKey("Header"));
        assertEquals(
                List.of("Header: Common prelude, types, and imports"),
                result.get("Header"),
                "Header list should contain only the header fragment text"
        );
    }

    @Test
    void linksFragmentsByKeyMentionsInIterationOrder() {
        // LinkedHashMap for deterministic entrySet() order: Header, A(, B(, C(, D(
        Map<String, String> input = new LinkedHashMap<>();
        input.put("Header", "Header: Common prelude, types, and imports");
        input.put("A(", "A body calls B( and C(");          // mentions keys for B and C
        input.put("B(", "B body");
        input.put("C(", "C body mentions D(");               // mentions key for D
        input.put("D(", "D body");

        Map<String, List<String>> result = FragmentLinker.link(input);

        // Header: not linked
        assertEquals(
                List.of("Header: Common prelude, types, and imports"),
                result.get("Header")
        );

        // A mentions B then C; because we iterate over the input map in order, expect B then C
        assertEquals(
                List.of("A body calls B( and C(", "B body", "C body mentions D("),
                result.get("A(")
        );

        // B mentions nothing
        assertEquals(List.of("B body"), result.get("B("));

        // C mentions D
        assertEquals(List.of("C body mentions D(", "D body"), result.get("C("));

        // D mentions nothing
        assertEquals(List.of("D body"), result.get("D("));

        // Keys preserved
        assertEquals(Set.of("Header", "A(", "B(", "C(", "D("), result.keySet());
    }

    @Test
    void ignoresDuplicateMentionsAndSelf() {
        Map<String, String> input = new LinkedHashMap<>();
        input.put("Header", "Header: Prelude");
        input.put("X(", "X calls X( and Y( and Y( again");  // self-mention X( and duplicate Y(
        input.put("Y(", "Y body");

        Map<String, List<String>> result = FragmentLinker.link(input);

        // Expect: own fragment first, then Y body once (self-mention doesn't duplicate)
        assertEquals(
                List.of("X calls X( and Y( and Y( again", "Y body"),
                result.get("X(")
        );
        // Y has no outbound mentions
        assertEquals(List.of("Y body"), result.get("Y("));
        // Header stays single
        assertEquals(List.of("Header: Prelude"), result.get("Header"));
    }

    @Test
    void doesNotLinkWhenNoMentions() {
        Map<String, String> input = new LinkedHashMap<>();
        input.put("Header", "Header: Prelude");
        input.put("Alpha(", "Alpha body mentions Z( which is not a key");
        input.put("Beta(", "Beta body");

        Map<String, List<String>> result = FragmentLinker.link(input);

        // Alpha mentions "Z(" which is not a key in the map -> no links added
        assertEquals(List.of("Alpha body mentions Z( which is not a key"), result.get("Alpha("));
        assertEquals(List.of("Beta body"), result.get("Beta("));
        assertEquals(List.of("Header: Prelude"), result.get("Header"));
    }
}

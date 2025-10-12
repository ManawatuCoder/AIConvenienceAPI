package codegenFragmenter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class FragmentLinkerTest {

    @Test
    void linksFragmentsByDetectingFunctionNamesInFragmentBodies() {
        // LinkedHashMap for deterministic iteration: Header, A, B, C, D
        Map<String, String> functionFragments = new LinkedHashMap<>();
        functionFragments.put("Header", "Header: Common prelude, types, and imports");
        functionFragments.put("A(", "A body calls B( and C(");              // mentions keys for B and C
        functionFragments.put("B(", "B body");
        functionFragments.put("C(", "C body mentions D(");                   // mentions key for D
        functionFragments.put("D(", "D body");

        List<List<String>> linked = FragmentLinker.link(functionFragments);

        // One group per input value
        assertEquals(functionFragments.size(), linked.size());

        // Group 0 is the header: should be just the header (no outbound linking)
        assertEquals(List.of("Header: Common prelude, types, and imports"), linked.get(0));

        // Group 1 (A): A + (B, C) in the order discovered via entrySet()
        assertEquals(List.of("A body calls B( and C(", "B body", "C body mentions D("), linked.get(1));

        // Group 2 (B): singleton (no outbound links)
        assertEquals(List.of("B body"), linked.get(2));

        // Group 3 (C): C + (D)
        assertEquals(List.of("C body mentions D(", "D body"), linked.get(3));

        // Group 4 (D): singleton
        assertEquals(List.of("D body"), linked.get(4));
    }

    @Test
    void headerFragmentIsNotLinkedEvenIfItMentionsFunctions() {
        Map<String, String> functionFragments = new LinkedHashMap<>();
        functionFragments.put("Header", "Header: mentions A( and B( but should not link");
        functionFragments.put("A(", "A");
        functionFragments.put("B(", "B");

        List<List<String>> linked = FragmentLinker.link(functionFragments);

        // Header remains single-element
        assertEquals(List.of("Header: mentions A( and B( but should not link"), linked.get(0));
        // A and B remain unmodified (no inbound from header)
        assertEquals(List.of("A"), linked.get(1));
        assertEquals(List.of("B"), linked.get(2));
    }

    @Test
    void doesNotDuplicateWhenFragmentAlreadyContainsItsOwnValue() {
        Map<String, String> functionFragments = new LinkedHashMap<>();
        // Fragment mentions its own key; initial add puts the fragment,
        // and inner loop must NOT add the same value again.
        functionFragments.put("X(", "X( appears here: X(");

        List<List<String>> linked = FragmentLinker.link(functionFragments);

        assertEquals(1, linked.size());
        assertEquals(1, linked.get(0).size()); // no duplicate of itself
        assertEquals("X( appears here: X(", linked.get(0).get(0));
    }

    @Test
    void linksMultipleMatchesWithoutDuplicates() {
        Map<String, String> functionFragments = new LinkedHashMap<>();
        functionFragments.put("Header", "Header: stuff");
        functionFragments.put("Top(", "Top body mentions A( and A( and B("); // duplicate references to A(
        functionFragments.put("A(", "A body");
        functionFragments.put("B(", "B body");

        List<List<String>> linked = FragmentLinker.link(functionFragments);

        // Top group should contain Top, then A, then B (A only once)
        assertEquals(List.of("Top body mentions A( and A( and B(", "A body", "B body"), linked.get(1));
    }

    @Test
    void returnsEmptyListForEmptyInput() {
        Map<String, String> functionFragments = new LinkedHashMap<>();

        List<List<String>> linked = FragmentLinker.link(functionFragments);

        assertNotNull(linked);
        assertTrue(linked.isEmpty());
    }

}

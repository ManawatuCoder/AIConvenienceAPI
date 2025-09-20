package codegenFragmenter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class ChunkLinkerTest {

  @Test
  void linksChunksByDetectingFunctionNamesInChunkBodies() {
    // Use LinkedHashMap so iteration order is deterministic
    Map<String, String> functionChunks = new LinkedHashMap<>();
    functionChunks.put("Header", "Header: Common prelude, types, and imports");
    functionChunks.put("A(", "A body calls B( and C("); // mentions keys for B and C
    functionChunks.put("B(", "B body");
    functionChunks.put("C(", "C body mentions D("); // mentions key for D
    functionChunks.put("D(", "D body");

    List<List<String>> linked = ChunkLinker.link(functionChunks);

    // One group per input value
    assertEquals(functionChunks.size(), linked.size());

    // Group 0 is the header: should be just the header, no linking
    assertEquals(List.of("Header: Common prelude, types, and imports"), linked.get(0));

    // Group 1 is for chunk A: A + (B, C) in the order discovered
    assertEquals(List.of("A body calls B( and C(", "B body", "C body mentions D("), linked.get(1));

    // Group 2 is for chunk B: no outbound links
    assertEquals(List.of("B body"), linked.get(2));

    // Group 3 is for chunk C: C + (D)
    assertEquals(List.of("C body mentions D(", "D body"), linked.get(3));

    // Group 4 is D alone
    assertEquals(List.of("D body"), linked.get(4));
  }

  @Test
  void headerChunkIsNotLinkedEvenIfItMentionsFunctions() {
    Map<String, String> functionChunks = new LinkedHashMap<>();
    functionChunks.put("Header", "Header: mentions A( and B( but should not link");
    functionChunks.put("A(", "A");
    functionChunks.put("B(", "B");

    List<List<String>> linked = ChunkLinker.link(functionChunks);

    // Header stays single-element
    assertEquals(List.of("Header: mentions A( and B( but should not link"), linked.get(0));
    // A and B remain unmodified
    assertEquals(List.of("A"), linked.get(1));
    assertEquals(List.of("B"), linked.get(2));
  }

  @Test
  void doesNotDuplicateWhenChunkAlreadyContainsItsOwnValue() {
    Map<String, String> functionChunks = new LinkedHashMap<>();
    // Chunk mentions its own key; initial add puts the chunk itself,
    // and inner loop should NOT add the same value again.
    functionChunks.put("X(", "X( appears here: X(");

    List<List<String>> linked = ChunkLinker.link(functionChunks);

    assertEquals(1, linked.size());
    assertEquals(1, linked.get(0).size()); // no duplicate
    assertEquals("X( appears here: X(", linked.get(0).get(0));
  }

  @Test
  void linksMultipleMatchesWithoutDuplicates() {
    Map<String, String> functionChunks = new LinkedHashMap<>();
    functionChunks.put("Header", "Header: stuff");
    functionChunks.put("Top(", "Top body mentions A( and A( and B("); // duplicate references to A(
    functionChunks.put("A(", "A body");
    functionChunks.put("B(", "B body");

    List<List<String>> linked = ChunkLinker.link(functionChunks);

    // Top group should contain Top, then A, then B (A only once)
    assertEquals(List.of("Top body mentions A( and A( and B(", "A body", "B body"), linked.get(1));
  }

  @Test
  void returnsEmptyListForEmptyInput() {
    Map<String, String> functionChunks = new LinkedHashMap<>();

    List<List<String>> linked = ChunkLinker.link(functionChunks);

    assertNotNull(linked);
    assertTrue(linked.isEmpty());
  }
}

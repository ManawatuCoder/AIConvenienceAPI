package guidelinesFragmentation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class GuidelineParserTest {

  @Test
  void parsesHeadingsViaJsoup_andWritesJsonWithoutRealNetwork() throws Exception {
    // ---- Arrange: HTML fixture & output path prep ----
    final String url = "https://example.com/guidelines";
    final String html =
        "<html><body>"
            + "<h1>Intro</h1><p>Welcome to the guidelines.</p>"
            + "<h2>Best Practices</h2><p>Use strong typing.</p>"
            + "<p>Avoid raw BinaryData in public APIs.</p>"
            + "<h3>Details</h3><div>Extra detail here.</div>"
            + "</body></html>";

    // Build a Document (no network) that Jsoup would have returned
    Document doc = Jsoup.parse(html, url);

    // The production code writes to this hard-coded relative path:
    Path outputPath =
        Path.of("src\\main\\java\\guidelinesFragmentation\\output\\guidelinesJson.txt");
    Path parent = outputPath.getParent();
    if (parent != null) {
      Files.createDirectories(parent);
    }
    Files.deleteIfExists(outputPath); // force the code path that writes the file

    // ---- Arrange: mock Jsoup.connect(url).get() ----
    Connection mockConn = mock(Connection.class);
    when(mockConn.get()).thenReturn(doc);

    try (MockedStatic<Jsoup> jsoupMock = mockStatic(Jsoup.class)) {
      jsoupMock.when(() -> Jsoup.connect(url)).thenReturn(mockConn);

      // ---- Act ----
      String returnedPath = GuidelineParser.parse(url);

      // ---- Assert: path and file exist ----
      assertEquals(
          outputPath.toString(), returnedPath, "Method should return the output file path");
      assertTrue(Files.exists(outputPath), "Expected the JSON output file to be created");

      // Read and validate JSON shape/content
      String json = Files.readString(outputPath, StandardCharsets.UTF_8);
      assertNotNull(json);
      assertFalse(json.isBlank());

      Type listType = new TypeToken<List<Map<String, String>>>() {}.getType();
      List<Map<String, String>> chunks = new Gson().fromJson(json, listType);

      assertNotNull(chunks);
      assertFalse(chunks.isEmpty());

      Map<String, String> intro =
          chunks.stream().filter(c -> "Intro".equals(c.get("heading"))).findFirst().orElse(null);
      Map<String, String> best =
          chunks.stream()
              .filter(c -> "Best Practices".equals(c.get("heading")))
              .findFirst()
              .orElse(null);
      Map<String, String> details =
          chunks.stream().filter(c -> "Details".equals(c.get("heading"))).findFirst().orElse(null);

      assertNotNull(intro, "Should contain h1: Intro");
      assertTrue(intro.get("content").contains("Welcome to the guidelines."));

      assertNotNull(best, "Should contain h2: Best Practices");
      assertTrue(best.get("content").contains("Use strong typing."));
      assertTrue(best.get("content").contains("Avoid raw BinaryData in public APIs."));

      assertNotNull(details, "Should contain h3: Details");
      assertTrue(details.get("content").contains("Extra detail here."));

      // Also verify we did call Jsoup.connect(url).get()
      jsoupMock.verify(() -> Jsoup.connect(url));
      verify(mockConn).get();
    }
  }
}

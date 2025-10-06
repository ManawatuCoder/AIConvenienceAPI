// Will return null if page not modified since last parsing of design guideline document.
// LastModifiedCheck requests headers and compares response 'last-modified' value with stored value.

// Saves output to a file.
// Returns path to file as String;

package guidelinesFragmentation;

import config.PathConfiguration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;

public class GuidelineParser {
  public static String parse(String url, Logger logger) throws Exception {
    // String url = "https://azure.github.io/azure-sdk/java_introduction.html";
    LastModifiedCheck check = new LastModifiedCheck();
    Path parsedGuidelines = Path.of(PathConfiguration.GUIDELINES_JSON);
    if (check.check(url) || !Files.exists(parsedGuidelines)) {
      Document doc = Jsoup.connect(url).get();

      // Collect all heading tags
      Elements headings = doc.select("h1, h2, h3, h4, h5, h6");
      List<Map<String, String>> fragments = new ArrayList<>();

      for (int i = 0; i < headings.size(); i++) {
        Element heading = headings.get(i);
        String headingText = heading.text();

        StringBuilder contentBuilder = new StringBuilder();
        for (Element sibling = heading.nextElementSibling(); sibling != null
                && !sibling.tagName().matches("h[1-6]"); sibling = sibling.nextElementSibling()) {

          // Include block-level content tags
          if (sibling.isBlock()) {
            contentBuilder.append(sibling.text()).append("\n");
          }
        }

        if (contentBuilder.length() > 0) {
          Map<String, String> fragment = new HashMap<>();
          fragment.put("heading", headingText);
          fragment.put("content", contentBuilder.toString().trim());
          fragments.add(fragment);
        }
      }

      // Convert to JSON using Gson
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String json = gson.toJson(fragments);

      try {
        if (!Files.exists(parsedGuidelines)) {
          Files.writeString(
                  parsedGuidelines, json, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } else {
          Files.writeString(parsedGuidelines, json);
        }
      } catch (IOException e) {
        logger.error(e.getMessage(), e);
        e.printStackTrace();
      }
    }
    return parsedGuidelines.toString();
  }
}

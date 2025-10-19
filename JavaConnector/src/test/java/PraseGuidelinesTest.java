import guidelinesFragmentation.GuidelineParser;
import org.junit.jupiter.api.Test;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ParseGuidelinesTest {

    @Test
    void returns_array_with_heading_and_content_fields() throws Exception {
        Method m = Main.class.getDeclaredMethod("parseGuidelines", GuidelineParser.class);
        m.setAccessible(true);
        JsonArray arr = (JsonArray) m.invoke(null, new GuidelineParser());

        assertTrue(arr.size() >= 1, "Expected at least one guideline");

        // Check structure of the first element
        var first = arr.get(0).getAsJsonObject();
        assertTrue(first.has("heading"), "Guideline should have 'heading'");
        assertTrue(first.has("content"), "Guideline should have 'content'");
        assertTrue(first.get("heading").isJsonPrimitive(), "'heading' should be a string");
        assertTrue(first.get("content").isJsonPrimitive(), "'content' should be a string");
    }
}
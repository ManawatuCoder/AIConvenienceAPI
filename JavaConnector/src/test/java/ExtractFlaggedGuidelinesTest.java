// matches headings

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ExtractFlaggedGuidelinesTest {

    @Test
    void returns_guidelines_by_heading() throws Exception {
        // Build guideline array
        JsonArray guidelineArray = new JsonArray();
        JsonObject g1 = new JsonObject();
        g1.addProperty("heading", "Naming");
        g1.addProperty("content", "Follow naming conventions.");
        guidelineArray.add(g1);

        // group json with "guidelines": ["Naming"]
        String groupJson = "{\"methods\":[\"getA\"],\"guidelines\":[\"Naming\"]}";

        Method m = Main.class.getDeclaredMethod("extractFlaggedGuidelines",
                String.class, com.google.gson.JsonArray.class);
        m.setAccessible(true);

        Map<String,String> out = (Map<String,String>) m.invoke(null, groupJson, guidelineArray);
        assertEquals(1, out.size());
        assertEquals("Follow naming conventions.", out.get("Naming"));
    }
}
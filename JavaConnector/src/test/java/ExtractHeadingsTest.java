import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ExtractHeadingsTest {
    @Test
    void joins_headings_with_newlines() throws Exception {
        JsonArray arr = new JsonArray();
        JsonObject a = new JsonObject(); a.addProperty("heading","H1"); a.addProperty("content","C1");
        JsonObject b = new JsonObject(); b.addProperty("heading","H2"); b.addProperty("content","C2");
        arr.add(a); arr.add(b);

        Method m = Main.class.getDeclaredMethod("extractHeadings", com.google.gson.JsonArray.class);
        m.setAccessible(true);
        String out = (String) m.invoke(null, arr);

        assertTrue(out.contains("H1"));
        assertTrue(out.contains("H2"));
        assertTrue(out.contains("\n"));
    }
}

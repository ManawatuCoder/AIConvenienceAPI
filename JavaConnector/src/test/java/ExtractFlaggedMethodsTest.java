// selects fragments by name

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ExtractFlaggedMethodsTest {

    @Test
    void returns_requested_methods_from_linked_fragments() throws Exception {
        // group json expected by the method
        JsonObject group = new JsonObject();
        group.add("methods", com.google.gson.JsonParser.parseString("[\"getA\"]").getAsJsonArray());
        group.add("guidelines", com.google.gson.JsonParser.parseString("[\"Naming\"]").getAsJsonArray());

        Map<String,String> codeFragments = new LinkedHashMap<>();
        codeFragments.put("getA(", "A()");
        codeFragments.put("getB(", "B()");

        Method m = Main.class.getDeclaredMethod("extractFlaggedMethods", String.class, Map.class);
        m.setAccessible(true);

        Map<String,String> out = (Map<String,String>) m.invoke(null, group.toString(), codeFragments);
        assertTrue(out.containsKey("getA("));
        assertEquals("A()", out.get("getA("));
        assertFalse(out.containsKey("getB("));
    }
}

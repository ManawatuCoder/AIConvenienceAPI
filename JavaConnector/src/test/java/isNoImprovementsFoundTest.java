import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class IsNoImprovementsFoundTest {
    @Test
    void returns_true_only_for_no_ignore_case_trim() throws Exception {
        Method m = Main.class.getDeclaredMethod("isNoImprovementsFound", String.class);
        m.setAccessible(true);

        // normal cases
        assertTrue((boolean) m.invoke(null, "no"));
        assertTrue((boolean) m.invoke(null, " No "));
        assertTrue((boolean) m.invoke(null, "NO"));
        assertFalse((boolean) m.invoke(null, "yes"));
        assertFalse((boolean) m.invoke(null, "nope"));

        // IMPORTANT: to pass a single null String via reflection you must wrap it:
        assertFalse((boolean) m.invoke(null, new Object[]{ null }));
    }
}
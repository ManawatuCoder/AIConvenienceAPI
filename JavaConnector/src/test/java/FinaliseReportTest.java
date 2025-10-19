// Checks that the report text is written to disk and the returned string contains the content.

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FinaliseReportTest {

    @Test
    void writes_report_and_returns_string() throws Exception {
        StringBuilder report = new StringBuilder("Hello report\n");
        Path out = Files.createTempFile("report-", ".txt");

        Method m = Main.class.getDeclaredMethod("finalizeReport", Path.class, StringBuilder.class, String.class);
        m.setAccessible(true);
        String returned = (String) m.invoke(null, out, report, "done");

        assertNotNull(returned);
        String onDisk = Files.readString(out);
        assertTrue(onDisk.contains("Hello report"), "File should contain report content");
    }
}
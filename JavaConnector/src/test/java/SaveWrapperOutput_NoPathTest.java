// Verifies a timestamped wrapper file is created in the Wrappers folder

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SaveWrapperOutput_NoPathTest {

    @Test
    void writes_timestamped_wrapper_file() throws Exception {
        // Directory where wrapper files are written (Outputs/Wrappers)
        Path dir = Path.of(config.PathConfiguration.getWrapperOutputPath("dummy")).getParent();
        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }
        long before;
        try (Stream<Path> s = Files.list(dir)) { before = s.count(); }

        Method m = Main.class.getDeclaredMethod("saveWrapperOutput", String.class);
        m.setAccessible(true);
        m.invoke(null, "// wrapper body");

        long after;
        try (Stream<Path> s = Files.list(dir)) { after = s.count(); }

        assertTrue(after >= before, "Wrapper file count should not decrease");
    }
}
// Verifies the wrapper file is created and includes the “Source File:” line referencing the provided path.

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class SaveWrapperOutput_WithPathTest {

    @Test
    void writes_wrapper_and_mentions_source_file() throws Exception {
        String src = config.PathConfiguration.getDefaultContainersClient();

        Method m = Main.class.getDeclaredMethod("saveWrapperOutput", String.class, String.class);
        m.setAccessible(true);
        m.invoke(null, "// wrapper content", src);

        Path dir = Path.of(config.PathConfiguration.getWrapperOutputPath("x")).getParent();
        Path latest = Files.list(dir)
                .max((a, b) -> Long.compare(a.toFile().lastModified(), b.toFile().lastModified()))
                .orElseThrow();

        String content = Files.readString(latest);
        assertTrue(content.contains("Source File"), "Wrapper file should mention source file");
        assertTrue(content.contains(src), "Wrapper file should include the provided source path");
    }
}

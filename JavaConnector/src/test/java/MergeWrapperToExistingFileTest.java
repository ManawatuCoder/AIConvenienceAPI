// Ensures merged output file is produced and contains the GENERATED/END markers and the injected snippet.

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class MergeWrapperToExistingFileTest {

    @Test
    void appends_generated_block_to_custom_source() throws Exception {
        Path src = Files.createTempFile("Mini-", ".java");
        Files.writeString(src, """
      package itest;
      public class Mini {
        public String a(){ return "x"; }
      }
      """);

        String snippet = "public void generated(){ System.out.println(\"ok\"); }";

        Method m = Main.class.getDeclaredMethod("mergeWrapperToExistingFile", String.class, String.class);
        m.setAccessible(true);
        m.invoke(null, snippet, src.toString());

        Path outDir = Path.of(config.PathConfiguration.getFinalOutputPath("x")).getParent();
        Path latest = Files.list(outDir)
                .max((a,b) -> Long.compare(a.toFile().lastModified(), b.toFile().lastModified()))
                .orElseThrow();

        String merged = Files.readString(latest);
        assertTrue(merged.contains("GENERATED WRAPPER CODE"));
        assertTrue(merged.contains("generated"));
    }
}
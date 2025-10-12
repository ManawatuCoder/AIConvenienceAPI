package codegenFragmenter;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class CodegenFragmenterTest {

    @Test
    void fragmentsHeaderAndEachMethod(@TempDir Path tempDir) throws IOException {
        // Arrange: create a compilable Java file with three methods and Javadoc on each.
        String src =
                "package codegenFragmenter;\n"
                        + "\n"
                        + "public class DemoClass {\n"
                        + "  /** First method javadoc */\n"
                        + "  public void alpha() {\n"
                        + "    int x = 1;\n"
                        + "  }\n"
                        + "\n"
                        + "  /** Second method javadoc */\n"
                        + "  public int beta(int a) {\n"
                        + "    return a + 1;\n"
                        + "  }\n"
                        + "\n"
                        + "  /** Third method javadoc */\n"
                        + "  public String gamma() {\n"
                        + "    return \"g\";\n"
                        + "  }\n"
                        + "}\n";

        Path filePath = tempDir.resolve("DemoClass.java");
        Files.write(filePath, src.getBytes());

        // Act
        Map<String, String> fragments = CodegenFragmenter.fragment(filePath.toFile());

        // Assert: keys exist
        assertTrue(fragments.containsKey("Header"), "Expected a 'Header' fragment");
        assertTrue(fragments.containsKey("alpha("), "Expected first declaration key 'alpha('");
        assertTrue(fragments.containsKey("beta("), "Expected subsequent method key 'beta('");
        assertTrue(fragments.containsKey("gamma("), "Expected subsequent method key 'gamma('");

        // Assert: first-declaration fragment is non-empty (exact content depends on headerExtractor/firstDeclarationExtractor)
        String firstDecl = fragments.get("alpha(");
        assertNotNull(firstDecl, "First declaration fragment should not be null");
        assertFalse(firstDecl.isBlank(), "First declaration fragment should not be blank");

        // Assert: subsequent fragments include their method bodies (loop-built fragments)
        String betaFragment = fragments.get("beta(");
        assertTrue(betaFragment.contains("public int beta(int a)"), "beta fragment should include its signature");
        assertTrue(betaFragment.contains("return a + 1;"), "beta fragment should include its body");
        assertFalse(betaFragment.contains("public String gamma()"), "beta fragment should not include the next method");

        String gammaFragment = fragments.get("gamma(");
        assertTrue(gammaFragment.contains("public String gamma()"), "gamma fragment should include its signature");
        assertTrue(gammaFragment.contains("return \"g\";"), "gamma fragment should include its body");
    }

    @Test
    void fragment_throwsIfSubsequentMethodHasNoComment(@TempDir Path tempDir) throws IOException {
        // Arrange: second method lacks a comment; current implementation calls md.getComment().get()
        // which will throw if absent. Might need to change to md.getComment().orElse(null) for more robust
        String src =
                "package codegenFragmenter;\n"
                        + "\n"
                        + "public class DemoNoComment {\n"
                        + "  /** First method javadoc */\n"
                        + "  public void first() {}\n"
                        + "\n"
                      //  + "  // No Javadoc intentionally for second()\n"
                        + "  public void second() {}\n"
                        + "}\n";

        Path filePath = tempDir.resolve("DemoNoComment.java");
        Files.write(filePath, src.getBytes());

        // Act + Assert: expect NoSuchElementException from md.getComment().get()
        assertThrows(
                java.util.NoSuchElementException.class,
                () -> CodegenFragmenter.fragment(filePath.toFile()),
                "When a subsequent method has no associated comment, current code should throw");
    }

    @Test
    void extractsHeaderStoppingBeforeFirstMethodJavadoc(@TempDir Path tempDir) throws IOException {
        String src =
                "package codegenFragmenter;\n"
                        + "\n"
                        + "public class DemoA {\n"
                        + "\n"
                        + "  /** First method docs */\n"
                        + "  public void first() {}\n"
                        + "}\n";

        Path filePath = tempDir.resolve("DemoA.java");
        Files.writeString(filePath, src);

        Map<String, String> fragments = CodegenFragmenter.fragment(filePath.toFile());
        String header = fragments.get("Header");

        assertNotNull(header, "Header fragment should exist");
        assertTrue(header.startsWith("Header:\n"), "Header should be prefixed as 'Header:\\n'");
        assertTrue(header.contains("package codegenFragmenter;"));
        assertTrue(header.contains("public class DemoA"));
        assertFalse(header.contains("/**"), "Header must exclude the Javadoc of the first method");
        assertFalse(header.contains("public void first()"), "Header must not include the method");
    }

    @Test
    void header_includesFileLevelCommentsAndImports(@TempDir Path tempDir) throws IOException {
        String src =
                "/*\n"
                        + " * License: Demo\n"
                        + " */\n"
                        + "package codegenFragmenter;\n"
                        + "\n"
                        + "import java.util.*;\n"
                        + "\n"
                        + "public class DemoC {\n"
                        + "  /** docs */\n"
                        + "  public void f() {}\n"
                        + "}\n";

        Path filePath = tempDir.resolve("DemoC.java");
        Files.writeString(filePath, src);

        Map<String, String> fragments = CodegenFragmenter.fragment(filePath.toFile());
        String header = fragments.get("Header");

        assertNotNull(header);
        assertTrue(header.contains("License: Demo"), "Header should preserve file-level block comments");
        assertTrue(header.contains("package codegenFragmenter;"));
        assertTrue(header.contains("import java.util.*;"), "Header should include imports");
        assertFalse(header.contains("/**"), "Header should not include the first method's Javadoc");
        assertFalse(header.contains("public void f()"), "Header must end before the first method");
    }

}

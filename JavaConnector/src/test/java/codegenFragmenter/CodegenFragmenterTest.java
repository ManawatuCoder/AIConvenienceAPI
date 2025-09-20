package codegenFragmenter;

import static org.junit.jupiter.api.Assertions.*;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class CodegenFragmenterTest {

  @TempDir File tempDir;

  @Test
  void fragmentsHeaderAndEachMethodIntoChunks() throws IOException {
    // Each method has a comment so md.getComment().get() is present in your current implementation.
    String source =
        "package demo;\n"
            + "\n"
            + "/** Class Javadoc */\n"
            + "public class Sample {\n"
            + "  private int x = 1;\n"
            + "\n"
            + "  /** First method javadoc */\n"
            + "  public String first(int a) {\n"
            + "    // body of first\n"
            + "    return \"first:\" + a;\n"
            + "  }\n"
            + "\n"
            + "  // Second method line comment\n"
            + "  public void second() {\n"
            + "    System.out.println(\"second\");\n"
            + "  }\n"
            + "\n"
            + "  /** Third method javadoc */\n"
            + "  private static int third(String s) {\n"
            + "    return s.length();\n"
            + "  }\n"
            + "}\n";

    File src = new File(tempDir, "Sample.java");
    Files.writeString(src.toPath(), source);

    Map<String, String> chunks = CodegenFragmenter.fragment(src);

    // Keys we expect: "Header", and one per method using name+"("
    assertTrue(chunks.containsKey("Header"), "Missing Header chunk");
    assertTrue(chunks.containsKey("first("), "Missing first( chunk");
    assertTrue(chunks.containsKey("second("), "Missing second( chunk");
    assertTrue(chunks.containsKey("third("), "Missing third( chunk");

    // Header should include package/class area (implementation-specific format, so we assert
    // presence)
    String header = chunks.get("Header");
    assertNotNull(header);
    assertTrue(header.contains("package demo;"), "Header should contain package declaration");
    assertTrue(header.contains("public class Sample"), "Header should contain class declaration");

    // Method chunks should include their own declarations/bodies
    String first = chunks.get("first(");
    assertTrue(
        first.contains("public String first(int a)"), "first( chunk should include its signature");
    assertTrue(first.contains("return \"first:\" + a;"), "first( chunk should include its body");

    String second = chunks.get("second(");
    assertTrue(
        second.contains("public void second()"), "second( chunk should include its signature");
    assertTrue(
        second.contains("System.out.println(\"second\");"),
        "second( chunk should include its body");

    String third = chunks.get("third(");
    assertTrue(
        third.contains("private static int third(String s)"),
        "third( chunk should include its signature");
    assertTrue(third.contains("return s.length();"), "third( chunk should include its body");
  }

  @Test
  void overloadedMethodsCurrentlyClashOnKey_namePlusParen() throws IOException {
    // With current key = name + "(", later overload overwrites earlier one.
    String source =
        "package demo;\n"
            + "public class Overloaded {\n"
            + "  /** v1 */ public void doIt() { }\n"
            + "  /** v2 */ public void doIt(int x) { }\n"
            + "}\n";

    File src = new File(tempDir, "Overloaded.java");
    Files.writeString(src.toPath(), source);

    Map<String, String> chunks = CodegenFragmenter.fragment(src);

    assertTrue(chunks.containsKey("doIt("), "Expected a single key for doIt(");
    String stored = chunks.get("doIt(");

    // Document current limitation: only one of the overload signatures will be present
    boolean containsNoArg = stored.contains("public void doIt()");
    boolean containsIntArg = stored.contains("public void doIt(int x)");
    assertTrue(
        containsNoArg ^ containsIntArg,
        "Key clash: only one overload is stored under 'doIt('. Consider including parameter types in the key.");
  }

  @Test
  void extractsHeaderStoppingBeforeFirstMethodJavadoc() throws Exception {
    String source =
        "package demo;\n"
            + "\n"
            + "public class Sample {\n"
            + "  /**\n"
            + "   * First method javadoc\n"
            + "   */\n"
            + "  public void first() {}\n"
            + "}\n";

    File src = new File(tempDir, "Sample.java");
    Files.write(src.toPath(), source.getBytes());

    // Parse + prepare inputs for headerExtractor
    CompilationUnit cu = StaticJavaParser.parse(src);
    List<MethodDeclaration> methods = cu.findAll(MethodDeclaration.class);
    List<String> lines = Files.readAllLines(src.toPath());

    // Reflectively invoke private static headerExtractor(List<MethodDeclaration>, List<String>)
    Method m = CodegenFragmenter.class.getDeclaredMethod("headerExtractor", List.class, List.class);
    m.setAccessible(true);
    String header = (String) m.invoke(null, methods, lines);

    // Assertions: header starts with marker, includes package + class, and excludes method
    // docs/signature
    assertNotNull(header);
    assertTrue(header.startsWith("Header:\n"), "Header should start with marker 'Header:\\n'");
    assertTrue(header.contains("package demo;"), "Header should include package");
    assertTrue(header.contains("public class Sample"), "Header should include class declaration");
    assertFalse(header.contains("First method javadoc"), "Header must exclude method Javadoc");
    assertFalse(header.contains("public void first()"), "Header must exclude method signature");
  }

  /**
   * Verifies behavior when there is NO Javadoc on the first method: headerExtractor should stop a
   * couple of lines before the first method declaration, still including package and class lines,
   * excluding the method signature.
   */
  @Test
  void extractsHeaderWhenNoMethodJavadocPresent() throws Exception {
    String source =
        "package demo;\n" + "\n" + "public class Sample {\n" + "  public void first() {}\n" + "}\n";

    File src = new File(tempDir, "SampleNoJavadoc.java");
    Files.write(src.toPath(), source.getBytes());

    CompilationUnit cu = StaticJavaParser.parse(src);
    List<MethodDeclaration> methods = cu.findAll(MethodDeclaration.class);
    List<String> lines = Files.readAllLines(src.toPath());

    Method m = CodegenFragmenter.class.getDeclaredMethod("headerExtractor", List.class, List.class);
    m.setAccessible(true);
    String header = (String) m.invoke(null, methods, lines);

    assertNotNull(header);
    assertTrue(header.startsWith("Header:\n"));
    assertTrue(header.contains("package demo;"));
    assertTrue(header.contains("public class Sample"));
    assertFalse(header.contains("public void first()"));
  }

  @Test
  void extractsFirstMethodBlock_includingJavadoc_andExcludesFollowingMethod() throws Exception {
    // Source with a Javadoc on the first method (required because production code calls
    // md.getComment().get())
    String source =
        "package demo;\n"
            + "\n"
            + "public class Sample {\n"
            + "  /**\n"
            + "   * First method javadoc\n"
            + "   */\n"
            + "  public String first(int a) {\n"
            + "    return \"first:\" + a;\n"
            + "  }\n"
            + "\n"
            + "  public void second() {\n"
            + "    System.out.println(\"second\");\n"
            + "  }\n"
            + "}\n";

    File src = new File(tempDir, "Sample.java");
    Files.write(src.toPath(), source.getBytes());

    // Prepare inputs for the private method
    CompilationUnit cu = StaticJavaParser.parse(src);
    List<MethodDeclaration> methods = cu.findAll(MethodDeclaration.class);
    List<String> lines = Files.readAllLines(src.toPath());

    // Sanity: we expect at least 2 methods (first, second)
    assertTrue(methods.size() >= 2, "Expected at least two methods in the sample source");

    // Reflectively invoke: firstDeclarationExtractor(List<MethodDeclaration>, List<String>)
    Method hidden =
        CodegenFragmenter.class.getDeclaredMethod(
            "firstDeclarationExtractor", List.class, List.class);
    hidden.setAccessible(true);

    String chunk = (String) hidden.invoke(null, methods, lines);

    // Assertions: the chunk should include the first method's javadoc and its signature/body
    assertNotNull(chunk);
    assertTrue(chunk.contains("First method javadoc"), "Should include the method Javadoc");
    assertTrue(
        chunk.contains("public String first(int a)"), "Should include the first method signature");
    assertTrue(chunk.contains("return \"first:\" + a;"), "Should include the first method body");

    // And it should NOT include the second method's signature/body
    assertFalse(
        chunk.contains("public void second()"), "Should not include the second method signature");
    assertFalse(
        chunk.contains("System.out.println(\"second\");"),
        "Should not include the second method body");
  }
}

// This takes a codegen output file, and breaks it apart when it encounters the start of a new
// method.
// TODO: More delimiters should be investigated.
//
// Stores everything, before first method declaration, within first chunk designated
// as a header chunk. This is currently considered important information for each prompt, to
// maintain context.
// TODO: Examine whether header contains extraneous information; Should header chunk really be
// included?

// TODO: fix map implementation. Overloaded methods will clash as they will share a key.

package codegenFragmenter;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;
import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class CodegenFragmenter {

  public static Map<String, String> fragment(File file) throws IOException {
    Map<String, String> chunks = new HashMap<>();
    // Read file as list of string for locating lines to act as delimiters
    List<String> lines = Files.readAllLines(file.toPath());
    // Read file again to allow parsing
    CompilationUnit compilationUnit = StaticJavaParser.parse(file);

    List<MethodDeclaration> methods = compilationUnit.findAll(MethodDeclaration.class);
    //        methods.sort(Comparator.comparing(md -> md.getBegin().get().line));

    String key = "Header"; // bracket included for pattern matching in ChunkLinker
    chunks.put(key, headerExtractor(methods, lines));

    key = methods.get(0).getNameAsString() + "(";
    chunks.put(key, firstDeclarationExtractor(methods, lines));

    for (int i = 1; i < methods.size(); i++) {
      MethodDeclaration md = methods.get(i - 1);
      int previousMethod = md.getEnd().get().line; // Line above method declaration
      md = methods.get(i);
      int thisMethod = (md.getEnd().get().line);

      List<String> outputLine =
          lines.subList(
              previousMethod, thisMethod + 1); // All lines between previousMethod and thisMethod
      StringBuilder currentChunk = new StringBuilder();

      Comment comment = md.getComment().get();
      //            System.out.println("Comment: " + comment);

      for (int j = 0; j < outputLine.size(); j++) {
        // Stick lines together into one contiguous chunk
        currentChunk.append(outputLine.get(j) + "\n");
      }

      key = md.getNameAsString() + "("; // bracket included for pattern matching in ChunkLinker
      // TODO:modify the above, or below, to prevent clashes with overloaded methods
      chunks.put(key, currentChunk.toString());
    }
    return chunks;
  }

  private static String headerExtractor(List<MethodDeclaration> methods, List<String> lines) {
    MethodDeclaration md = methods.get(0);
    int thisMethod = 0;
    int nextMethod = md.getBegin().get().line - 2; // Start at first method declaration as failsafe

    Optional<JavadocComment> commentOpt = md.getJavadocComment();
    // Try end the header before the beginning of the comment for the first method declaration
    if (commentOpt.isPresent() && commentOpt.get().getRange().isPresent()) {
      int commentStartLine = commentOpt.get().getRange().get().begin.line - 2;
      //            System.out.println(commentOpt);
      if (commentStartLine < nextMethod) {
        nextMethod = commentStartLine;
      }
    }

    List<String> outputLine =
        lines.subList(thisMethod, nextMethod + 1); // All lines between thisMethod and nextMethod
    StringBuilder currentChunk = new StringBuilder();
    currentChunk.append("Header:\n");

    for (int j = 0; j < outputLine.size(); j++) {
      // Stick lines together into one contiguous chunk
      currentChunk.append(outputLine.get(j) + "\n");
    }

    return currentChunk.toString();
  }

  private static String firstDeclarationExtractor(
      List<MethodDeclaration> methods, List<String> lines) {
    MethodDeclaration md = methods.get(0);
    int thisMethod = md.getBegin().get().line - 1; // Line above method declaration as failsafe
    int nextMethod = md.getEnd().get().line;

    Optional<JavadocComment> commentOpt = md.getJavadocComment();
    // Try end the header before the beginning of the comment for the first method declaration
    if (commentOpt.isPresent() && commentOpt.get().getRange().isPresent()) {
      int commentStartLine = commentOpt.get().getRange().get().begin.line - 2;
      //            System.out.println(commentOpt);
      if (commentStartLine < thisMethod) {
        thisMethod = commentStartLine;
      }
    }

    List<String> outputLine =
        lines.subList(thisMethod, nextMethod + 1); // All lines between thisMethod and nextMethod
    StringBuilder currentChunk = new StringBuilder();

    Comment comment = md.getComment().get();
    //        System.out.println("Comment: " + comment);

    for (int j = 0; j < outputLine.size(); j++) {
      // Stick lines together into one contiguous chunk
      currentChunk.append(outputLine.get(j) + "\n");
    }

    String key = md.getNameAsString() + "("; // bracket included for pattern matching in ChunkLinker
    return currentChunk.toString();
  }

  public CodegenFragmenter() throws IOException {}
}

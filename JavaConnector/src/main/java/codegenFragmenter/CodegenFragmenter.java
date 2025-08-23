//This takes a codegen output file, and breaks it apart when it encounters the start of a new method.
//TODO: More delimiters should be investigated.
//
//Stores everything, before first method declaration, within first chunk designated
//as a header chunk. This is currently considered important information for each prompt, to maintain context.
//TODO: Examine whether header contains extraneous information; Should header chunk really be included?


package codegenFragmenter;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.CompilationUnit;


import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class CodegenFragmenter {

    public static Map<String, String> fragment(File file) throws IOException {
        Map<String, String> chunks = new HashMap<>();
        //Read file as list of string for locating lines to act as delimiters
        List<String> lines = Files.readAllLines(file.toPath());
        //Read file again to allow parsing
        CompilationUnit compilationUnit = StaticJavaParser.parse(file);

        List<MethodDeclaration> methods = compilationUnit.findAll(MethodDeclaration.class);
        methods.sort(Comparator.comparing(md -> md.getBegin().get().line));

        MethodDeclaration md = methods.get(0);
        int thisMethod = 0;
        int nextMethod = md.getBegin().get().line - 2;

        List<String> outputLine = lines.subList(thisMethod, nextMethod + 1); //All lines between thisMethod and nextMethod
        StringBuilder currentChunk = new StringBuilder();
        currentChunk.append("Header:\n");

        for(int j = 0; j < outputLine.size(); j++){
            //Stick lines together into one contiguous chunk
            currentChunk.append(outputLine.get(j) + "\n");
        }

        String key = md.getNameAsString() + "("; //bracket included for pattern matching in ChunkLinker
        chunks.put(key, currentChunk.toString());

        for (int i = 0; i < methods.size(); i++) {
            md = methods.get(i);
            thisMethod = md.getBegin().get().line - 1; //Line above method declaration
            nextMethod = (i + 1 < methods.size()) //Two lines above next method declaration
                    ? methods.get(i+1).getBegin().get().line - 2
                    : lines.size() - 1;

            outputLine = lines.subList(thisMethod, nextMethod + 1); //All lines between thisMethod and nextMethod
            currentChunk = new StringBuilder();

            for(int j = 0; j < outputLine.size(); j++){
                //Stick lines together into one contiguous chunk
                currentChunk.append(outputLine.get(j) + "\n");
            }

            key = md.getNameAsString() + "("; //bracket included for pattern matching in ChunkLinker
            chunks.put(key, currentChunk.toString());
        }
        return chunks;




//        List<String> chunks = new ArrayList<>();
//        BufferedReader reader = new BufferedReader(new FileReader(file));
//
//        String line;
//        StringBuilder currentChunk = new StringBuilder();
//        currentChunk.append("Header:\n");
//
//        while ((line = reader.readLine()) != null) {
//            if (line.contains("@Metadata") || line.contains("@Generated") || line.contains("@ServiceMethod")) {
//                if (currentChunk.length() > 0) { //Maybe redundant;
//                    chunks.add(currentChunk.toString());
//                    // checks if anything exists between tags, or before first tag, before appending chunk to List.
//                    currentChunk.setLength(0);
//                }
//            }
//
//            currentChunk.append(line).append(System.lineSeparator());
//        }
//
//        // add the last chunk if it exists.
//        if (currentChunk.length() > 0) {
//            chunks.add(currentChunk.toString());
//        }
//
//        reader.close();
//        return chunks;
    }

    public CodegenFragmenter() throws IOException {

    }
}

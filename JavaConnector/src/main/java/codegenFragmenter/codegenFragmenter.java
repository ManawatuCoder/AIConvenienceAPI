//This takes a codegen output file, and breaks it apart based upon specific tags present in codegen.
//At the moment this just uses "@Metadata" and "@Generated" as the delimiters for chunks.
//@TODO: More delimiters should be investigated - there very well may be other tags in the codegen.
//I initially tried just @Generated, but noticed this tag is not even present in the file I used,
//while @Metadata was not present in the file I used as reference while exploring ideas.


package codegenFragmenter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class codegenFragmenter {

    public static List<String> fragment(File file) throws IOException {
        List<String> chunks = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;
        StringBuilder currentChunk = new StringBuilder();
        boolean inChunk = false;

        while ((line = reader.readLine()) != null) {
            if (line.contains("@Metadata") || line.contains("@Generated")) {
                if (currentChunk.length() > 0) { //Maybe redundant;
                    chunks.add(currentChunk.toString());
                    // checks if anything exists between tags, or before first tag, before appending chunk to List.
                    currentChunk.setLength(0);
                }
            }

            currentChunk.append(line).append(System.lineSeparator());
        }

        // add the last chunk if it exists.
        if (currentChunk.length() > 0) {
            chunks.add(currentChunk.toString());
        }

        reader.close();
        return chunks;
    }

    public codegenFragmenter() throws IOException {

    }
}

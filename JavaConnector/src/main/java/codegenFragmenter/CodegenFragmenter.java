//This takes a codegen output file, and breaks it apart based upon specific tags present in codegen.
//At the moment this just uses "@Metadata", "@ServiceMethod", and "@Generated" as the delimiters for chunks.
//TODO: More delimiters should be investigated - there very well may be other tags in the codegen. I already found one.
//TODO: Could consider using function declaration as delimiter? - regex exists in DefinitionExtractor.java
//I also know that we will get multiple definitions grouped together, as this delimiter does not exist between them -
//as seen in https://github.com/Azure/azure-sdk-for-java/blob/main/sdk/translation/azure-ai-translation-text/src/main/java/com/azure/ai/translation/text/TextTranslationClient.java#L62
//(ctrl click above link to open in browser)
//I initially tried just @Generated, but noticed this tag is not even present in the file I used,
//while @Metadata was not present in the file I used as reference while exploring ideas (hyperlinked above).
//
//Stores global variables and imports, as well as any initial comments, within first chunk designated
//as a header chunk. This is currently considered important information for each prompt, to maintain context.
//TODO: Examine whether header contains extraneous information; Should header chunk really be included?
//TODO: Header contains global variables, but not variables local to entire class.
//I.E. blobstorage has "private ContainerProperties properties;" which is not included in header.
//Is, instead, contained within its own chunk and is not grouped with fromJson() chunk, despite being used there.


package codegenFragmenter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CodegenFragmenter {

    public static List<String> fragment(File file) throws IOException {
        List<String> chunks = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;
        StringBuilder currentChunk = new StringBuilder();
        boolean inChunk = false;
        currentChunk.append("Header:\n");

        while ((line = reader.readLine()) != null) {
            if (line.contains("@Metadata") || line.contains("@Generated") || line.contains("@ServiceMethod")) {
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

    public CodegenFragmenter() throws IOException {

    }
}

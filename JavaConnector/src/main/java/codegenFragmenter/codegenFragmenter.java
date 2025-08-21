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
                // save previous chunk if it exists
                if (inChunk && currentChunk.length() > 0) {
                    chunks.add(currentChunk.toString());
                    currentChunk.setLength(0);
                }
                inChunk = true; // start new chunk
            }

            if (inChunk) {
                currentChunk.append(line).append(System.lineSeparator());
            }
        }

        // add the last chunk
        if (currentChunk.length() > 0) {
            chunks.add(currentChunk.toString());
        }

        reader.close();
        return chunks;
    }

    public codegenFragmenter() throws IOException {
        File file = new File("../TypeSpec_Conversion/tsp-output/clients/java/src/main/java/azurestoragemanagement/BlobContainer.java");
        List<String> chunks = fragment(file);

        System.out.println(file.getAbsolutePath());
        System.out.println(file.exists());

        System.out.println("GO!");


        for (int i = 0; i < chunks.size(); i++) {
            System.out.println("//// CHUNK " + (i + 1) + " START ////");
            System.out.println(chunks.get(i));
            System.out.println("//// CHUNK " + (i + 1) + " END ////");
            System.out.println();
        }
    }
}

//This is for looking through the list of fragments, and linking dependant fragments together.
//It appends the Header chunk (first chunk in list) to every output chunk.
//
//Currently stores entire chunk containing function definition.
//Perhaps this is undesireable, as I see some chunks may contain multiple definitions.
//If chunks are likely to only contain a small number of these, perhaps this is not a huge issue
//TODO: but should still be optimised out if time allows.

package codegenFragmenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//(public|protected|private|static|\s) +[\w\<\>\[\]]+\s+(\w+) *\([^\)]*\) *(\{?|[^;])
//Regex for function declarations.

public class chunkLinker {
    public static List<Map> linker(List<String> chunkList){

        for (int i = 0; i < chunkList.size(); i++) {
            System.out.println("//// CHUNK " + (i + 1) + " START ////");
            System.out.println(chunkList.get(i));
            System.out.println("//// CHUNK " + (i + 1) + " END ////\n");

        }

        List<Map> jam = new ArrayList<>();

        Pattern methodDefPattern = Pattern.compile(
                "(public|protected|private|static|\\s) +[\\w\\<\\>\\[\\]]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;])"
        );

        for(String chunk : chunkList){
            Matcher matcher = methodDefPattern.matcher(chunk);
            while (matcher.find()) {
                Map<String, String> entry = new HashMap<>();
                entry.put(matcher.group(2), chunk);
                jam.add(entry);
//                String methodDefinition = matcher.group(2);
//                System.out.println("Found method: " + methodDefinition);
            }
        }

        return jam;
    }
}

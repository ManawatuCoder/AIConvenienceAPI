//It appends the Header chunk (first chunk in list) to every output chunk.
//
//Currently stores entire chunk containing function definition.
//Perhaps this is undesirable, as I see some chunks may contain multiple definitions.
//If chunks are likely to only contain a small number of these, perhaps this is not a huge issue
//TODO: but potentially should still be optimised out if time allows.

package codegenFragmenter;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//(public|protected|private|static|\s) +[\w\<\>\[\]]+\s+(\w+) *\([^\)]*\) *(\{?|[^;])
//Regex for function declarations.

public class DefinitionExtractor {

    public static Map<String,String> extract(List<String> chunkList){

        //******************************************
        //Todo: Remove this debug output
        //******************************************
        for (int i = 0; i < chunkList.size(); i++) {
            System.out.println("//// CHUNK " + (i + 1) + " START ////");
            System.out.println(chunkList.get(i));
            System.out.println("//// CHUNK " + (i + 1) + " END ////\n");
        }
        //******************************************

        Map<String, String> definitionList = new HashMap<>();

//        Pattern methodDefPattern = Pattern.compile(
//                "(public|protected|private|static|\\s) +[\\w\\<\\>\\[\\]]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;])"
//        );

        for(String chunk : chunkList){
            System.out.println("Chunk: " + chunk + "\nChunk end:\n");
            String wrapped = "public class Dummy {\n" + chunk + "\n}";
            CompilationUnit compilationUnit = StaticJavaParser.parse(wrapped);

            compilationUnit.findAll(MethodDeclaration.class).forEach(md -> {
                System.out.println(md.getDeclarationAsString(true, true, true));
            });


//            Matcher matcher = methodDefPattern.matcher(chunk);
//            while (matcher.find()) {
//                definitionList.put(matcher.group(2), chunk);
////                Debug output:
////                String methodDefinition = matcher.group(2);
////                System.out.println("Found method: " + methodDefinition);
//            }
        }

        return definitionList;
    }
}

//This is for looking through the list of chunks, and linking dependant fragments together.
//Searches through each chunk, and checks if it contains the name of any defined functions;
//If found, chunk containing function definition is appended to list.
//Returns a list, containing all grouped chunks - each group being another list.

package codegenFragmenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChunkLinker {

    public static List<List<String>> link(Map<String,String> functionChunks){
        List<List<String>> linkedChunks = new ArrayList<>();

        int i = 0;
        for(String chunk : functionChunks.values()){
            List<String> singleChunkList = new ArrayList<>();
            singleChunkList.add(chunk);
            linkedChunks.add(singleChunkList);
            if (!chunk.startsWith("Header:")) { //Header chunk needs no linking. Should be sent with all prompts.
                for(Map.Entry function : functionChunks.entrySet()){
                    if(chunk.contains((CharSequence) function.getKey())){
                        if(!linkedChunks.get(i).contains(function.getValue())){
                            linkedChunks.get(i).add((String) function.getValue());
                        }
                    }
                }
            }
            i++;
        }

        return linkedChunks;
    }
}

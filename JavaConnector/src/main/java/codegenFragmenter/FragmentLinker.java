// This is for looking through the list of fragments, and linking dependant fragments together.
// Searches through each fragment, and checks if it contains the name of any defined functions;
// If found, fragment containing function definition is appended to list.
// Returns a list, containing all grouped fragments - each group being another list.

package codegenFragmenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FragmentLinker {

  public static List<List<String>> link(Map<String, String> functionFragments) {
    List<List<String>> linkedFragments = new ArrayList<>();

    int i = 0;
    for (String fragment : functionFragments.values()) {
      List<String> singleFragmentList = new ArrayList<>();
      // We can add the header fragment here, or we can do it elsewhere depending how prompts will be
      // constructed.
      // TODO: Decide whether to keep following line:
      //            singleFragmentList.add(functionFragments.get("Header"));
      singleFragmentList.add(fragment);
      linkedFragments.add(singleFragmentList);
      if (!fragment.startsWith(
          "Header:")) { // Header fragment needs no linking. Should be sent with all prompts.
        for (Map.Entry function : functionFragments.entrySet()) {
          if (fragment.contains((CharSequence) function.getKey())) {
            if (!linkedFragments.get(i).contains(function.getValue())) {
              linkedFragments.get(i).add((String) function.getValue());
            }
          }
        }
      }
      i++;
    }

    return linkedFragments;
  }
}

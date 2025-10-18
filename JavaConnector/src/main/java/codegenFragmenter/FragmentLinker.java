// This is for looking through the list of fragments, and linking dependant fragments together.
// Searches through each fragment, and checks if it contains the name of any defined functions;
// If found, fragment containing function definition is appended to list.
// Returns a list, containing all grouped fragments - each group being another list.

package codegenFragmenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentLinker {

  public static Map<String, List<String>> link(Map<String, String> functionFragments) {
    // Stores all methods that were referenced in fragment
    Map<String, List<String>> relatedMethods = new HashMap<>();

    for (Map.Entry rawFragment : functionFragments.entrySet()) {
      List<String> singleFragmentList = new ArrayList<>();
      String fragment = rawFragment.getValue().toString();
      // We can add the header fragment here, or we can do it elsewhere depending how
      // prompts will
      // be
      // constructed.
      // TODO: Decide whether to keep following line:
      // singleFragmentList.add(functionFragments.get("Header"));
      singleFragmentList.add(fragment);
      if (!fragment.startsWith(
          "Header:")) { // Header fragment needs no linking. Should be sent with all prompts.
        for (Map.Entry function : functionFragments.entrySet()) {
          if (fragment.contains((CharSequence) function.getKey())) {
            if (!singleFragmentList.contains(function.getValue())) {
              singleFragmentList.add((String) function.getValue());
            }
          }
        }
      }
      relatedMethods.put(rawFragment.getKey().toString(), singleFragmentList);
    }

    return relatedMethods;
  }
}

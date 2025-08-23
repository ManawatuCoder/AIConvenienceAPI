package codegenFragmenter;

import java.util.List;
import java.util.Map;

public class FragmentListPair {
    public List<String> list;
    public Map<String, String> map;
    public FragmentListPair(List<String> list, Map<String, String> map){
        this.list = list;
        this.map = map;
    }
}

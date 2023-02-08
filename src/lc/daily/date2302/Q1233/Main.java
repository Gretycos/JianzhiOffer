package lc.daily.date2302.Q1233;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Trie{
    int idx;
    Map<String, Trie> children;

    public Trie(){
        idx = -1;
        children = new HashMap<>();
    }

}
class Solution {
    private Trie root;
    private List<String> res;
    public List<String> removeSubfolders(String[] folder) {
        root = new Trie();
        for (int i = 0; i <folder.length; i++) {
            add(folder[i], i);
        }
        res = new ArrayList<>();
        Trie p = root;
        dfs(folder, p);
        return res;
    }

    private void add(String f, int idx){
        String[] path = f.split("/");
        Trie p = root;
        for (int i = 1; i < path.length; i++) {
            p.children.putIfAbsent(path[i], new Trie());
            p = p.children.get(path[i]);
            if (i == path.length - 1){
                p.idx = idx;
            }
        }
    }

    private void dfs(String[] folder, Trie node){
        if (node.idx != -1){
            res.add(folder[node.idx]);
            return;
        }
        for (Trie child : node.children.values()) {
            dfs(folder, child);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] folder = {"/a","/a/b","/c/d","/c/d/e","/c/f"};
        System.out.println(solution.removeSubfolders(folder));
    }
}

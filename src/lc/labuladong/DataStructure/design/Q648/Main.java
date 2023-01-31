package lc.labuladong.DataStructure.design.Q648;
import java.util.List;

class TrieMap <V>{
    private static final int R = 26;
    private TrieNode<V> root = null; // 根节点

    private static class TrieNode<V>{
        V val = null;
        TrieNode<V>[] children = new TrieNode[R];
    }

    /*** 增 ***/
    public void put(String key, V val){
        root = put(root, key, val, 0);
    }

    // 定义：向以 node 为根的 Trie 树中插入 key[i..]，返回插入完成后的根节点
    private TrieNode<V> put(TrieNode<V> node, String key, V val, int i){
        if (node == null){
            node = new TrieNode<>();
        }
        if (i == key.length()){
            node.val = val;
            return node;
        }
        char c = key.charAt(i);
        node.children[c - 'a'] = put(node.children[c - 'a'], key, val, i+1);
        return node;
    }

    /*** 查 ***/
    // 在所有键中寻找 query 的最短前缀
    public String shortestPrefixOf(String query){
        TrieNode<V> p = root;
        for (int i = 0; i < query.length(); i++) {
            if (p == null){
                return "";
            }
            if (p.val != null){
                // 找到一个键是query的前缀
                return query.substring(0, i);
            }
            // 向下搜索
            char c = query.charAt(i);
            p = p.children[c - 'a'];
        }

        if (p != null && p.val != null){
            // query本身是个键
            return query;
        }

        return "";
    }

    // 从节点 node 开始搜索 key，如果存在返回对应节点，否则返回 null
    private TrieNode<V> getNode(TrieNode<V> node, String key){
        TrieNode<V> p = node;
        for (int i = 0; i < key.length(); i++) {
            if (p == null){
                return null;
            }
            char c = key.charAt(i);
            p = p.children[c];
        }
        return p;
    }
}
class TrieSet {
    private final TrieMap<Object> map = new TrieMap<>();

    public void add(String key){
        map.put(key, new Object());
    }

    public String shortestPrefixOf(String query){
        return map.shortestPrefixOf(query);
    }
}
class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        TrieSet set = new TrieSet();
        for (String key : dictionary) {
            set.add(key);
        }
        StringBuilder sb = new StringBuilder();
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            String prefix = set.shortestPrefixOf(words[i]);
            if (!prefix.isEmpty()){
                sb.append(prefix);
            } else {
                sb.append(words[i]);
            }
            if (i != words.length - 1){
                sb.append(' ');
            }
        }
        return sb.toString();
    }
}

public class Main {
}

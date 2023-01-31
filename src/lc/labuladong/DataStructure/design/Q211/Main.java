package lc.labuladong.DataStructure.design.Q211;

class TrieMap<V>{
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
    // 判断是和否存在前缀为 pattern 的键
    public boolean hasKeyWithPattern(String pattern){
        return hasKeyWithPattern(root, pattern, 0);
    }

    private boolean hasKeyWithPattern(TrieNode<V> node, String pattern, int i){
        if (node == null){
            return false;
        }

        if (i == pattern.length()){
            return node.val != null;
        }
        char c = pattern.charAt(i);
        // 不是通配符
        if (c != '.'){
            return hasKeyWithPattern(node.children[c - 'a'], pattern, i + 1);
        }
        // 遇到通配符
        for (int j = 0; j < R; j++) {
            if (hasKeyWithPattern(node.children[j],pattern, i+1)){
                return true;
            }
        }
        return false;
    }
}

class TrieSet{
    private final TrieMap<Object> map = new TrieMap<>();

    public void add(String key){
        map.put(key, new Object());
    }

    public boolean hasKeyWithPattern(String pattern){
        return map.hasKeyWithPattern(pattern);
    }
}
class WordDictionary {

    private TrieSet set;
    public WordDictionary() {
        set = new TrieSet();
    }

    public void addWord(String word) {
        set.add(word);
    }

    public boolean search(String word) {
        return set.hasKeyWithPattern(word);
    }
}

public class Main {
}

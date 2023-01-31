package lc.labuladong.DataStructure.design.Q677;


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
        node.children[c-'a'] = put(node.children[c-'a'], key, val, i+1);
        return node;
    }

    // 搜索前缀为 prefix 的所有键
    private int res;
    public int keysWithPrefix(String prefix){
        res = 0;
        TrieNode<V> x = getNode(root, prefix);
        if (x == null){
            return 0;
        }
        traverse(x);
        return res;
    }

    private void traverse(TrieNode<V> node){
        if (node == null){
            return;
        }
        if (node.val != null){
            res += (Integer) node.val;
        }
        for (int c = 0; c < R; c++) {
            traverse(node.children[c]);
        }
    }

    // 从节点 node 开始搜索 key，如果存在返回对应节点，否则返回 null
    private TrieNode<V> getNode(TrieNode<V> node, String key){
        TrieNode<V> p = node;
        for (int i = 0; i < key.length(); i++) {
            if (p == null){
                return null;
            }
            char c = key.charAt(i);
            p = p.children[c-'a'];
        }
        return p;
    }
}

class MapSum {
    private TrieMap<Integer> map;

    public MapSum() {
        map = new TrieMap<>();
    }

    public void insert(String key, int val) {
        map.put(key,val);
    }

    public int sum(String prefix) {
        return map.keysWithPrefix(prefix);
    }
}

public class Main {
}

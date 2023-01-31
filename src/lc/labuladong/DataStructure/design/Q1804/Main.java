package lc.labuladong.DataStructure.design.Q1804;

import java.util.LinkedList;
import java.util.List;

class TrieMap <V>{
    private static final int R = 26; // ASCII
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

    /*** 删 ***/
    public void remove(String key){
        if (!containsKey(key)){
            return;
        }
        root = remove(root, key, 0);
    }

    // 定义：在以 node 为根的 Trie 树中删除 key[i..]，返回删除后的根节点
    private TrieNode<V> remove(TrieNode<V> node, String key, int i){
        if (node == null){
            return null;
        }
        if (i == key.length()){
            // 找到了 key 对应的 TrieNode，删除 val
            node.val = null;
        } else {
            char c = key.charAt(i);
            // 递归去子树进行删除
            node.children[c-'a'] = remove(node.children[c-'a'], key, i + 1);
        }

        // 后序位置，递归路径上的节点可能需要被清理
        if (node.val != null){
            // 如果该 TireNode 存储着 val，不需要被清理
            return node;
        }

        for (int c = 0; c < R; c++) {
            if (node.children[c] != null){
                // 只要存在一个子节点（后缀树枝），就不需要被清理
                return node;
            }
        }
        // 既没有存储 val，也没有后缀树枝，则该节点需要被清理
        return null;
    }

        /*** 查 ***/
    // 搜索 key 对应的值，不存在则返回 null
    public V get(String key){
        TrieNode<V> x = getNode(root, key);
        if (x == null || x.val == null){
            // x 为空或 x 的 val 字段为空都说明 key 没有对应的值
            return null;
        }
        return x.val;
    }

    // 判断 key 是否存在在 Map 中
    public boolean containsKey(String key){
        return get(key) != null;
    }

    public List<String> keysWithPrefix(String prefix){
        List<String> res = new LinkedList<>();
        TrieNode<V> x = getNode(root, prefix);
        if (x == null){
            return res;
        }
        traverse(x, new StringBuilder(prefix), res);
        return res;
    }

    private void traverse(TrieNode<V> node, StringBuilder path, List<String> res){
        if (node == null){
            return;
        }
        if (node.val != null){
            res.add(path.toString());
        }
        for (int c = 0; c < R; c++) {
            path.append(c);
            traverse(node.children[c], path, res);
            path.deleteCharAt(path.length() - 1);
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

class Trie {
    // 封装我们实现的 TrieMap
    TrieMap<Integer> map = new TrieMap<>();

    // 插入 word 并记录插入次数
    public void insert(String word) {
        if (!map.containsKey(word)) {
            map.put(word, 1);
        } else {
            map.put(word, map.get(word) + 1);
        }
    }

    // 查询 word 插入的次数
    public int countWordsEqualTo(String word) {
        if (!map.containsKey(word)) {
            return 0;
        }
        return map.get(word);
    }

    // 累加前缀为 prefix 的键的插入次数总和
    public int countWordsStartingWith(String prefix) {
        int res = 0;
        for (String key : map.keysWithPrefix(prefix)) {
            res += map.get(key);
        }
        return res;
    }

    // word 的插入次数减一
    public void erase(String word) {
        int freq = map.get(word);
        if (freq - 1 == 0) {
            map.remove(word);
        } else {
            map.put(word, freq - 1);
        }
    }


}
public class Main {
}

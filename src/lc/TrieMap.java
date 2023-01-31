package lc;

import java.util.LinkedList;
import java.util.List;

public class TrieMap <V>{
    private static final int R = 256; // ASCII
    private int size = 0; // 键值对个数
    private TrieNode<V> root = null; // 根节点

    private static class TrieNode<V>{
        V val = null;
        TrieNode<V>[] children = new TrieNode[R];
    }

    /*** 增 ***/
    public void put(String key, V val){
        if (!containsKey(key)){
            size++;
        }
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
        node.children[c] = put(node.children[c], key, val, i+1);
        return node;
    }

    /*** 删 ***/
    public void remove(String key){
        if (!containsKey(key)){
            return;
        }
        root = remove(root, key, 0);
        size--;
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
            node.children[c] = remove(node.children[c], key, i + 1);
        }

        // 后序位置，递归路径上的节点可能需要被清理
        if (node.val != null){
            // 如果该 TireNode 存储着 val，不需要被清理
            return node;
        }

        for (char c = 0; c < R; c++) {
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

    /*** 前缀查询 ***/
    // 判断是和否存在前缀为 prefix 的键
    public boolean hasKeyWithPrefix(String prefix){
        return getNode(root, prefix) != null;
    }

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
            p = p.children[c];
        }

        if (p != null && p.val != null){
            // query本身是个键
            return query;
        }

        return "";
    }

    // 在所有键中寻找 query 的最长前缀
    public String longestPrefixOf(String query){
        TrieNode<V> p = root;
        int maxLen = 0;
        for (int i = 0; i < query.length(); i++) {
            if (p == null){
                break;
            }
            if (p.val != null){
                maxLen = i;
            }
            char c = query.charAt(i);
            p = p.children[c];
        }
        if (p != null && p.val != null){
            // query本身是个键
            return query;
        }
        return query.substring(0, maxLen);
    }

    // 搜索前缀为 prefix 的所有键
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
        for (char c = 0; c < R; c++) {
            path.append(c);
            traverse(node.children[c], path, res);
            path.deleteCharAt(path.length() - 1);
        }
    }

    /*** 通配符查询 ***/
    // 通配符 . 匹配任意字符
    public List<String> keysWithPattern(String pattern){
        List<String> res = new LinkedList<>();
        traverse(root, new StringBuilder(), pattern, 0, res);
        return res;
    }

    private void traverse(TrieNode<V> node, StringBuilder path, String pattern, int i, List<String> res){
        if (node == null){
            return;
        }
        if (i == pattern.length()){
            if (node.val != null){
                res.add(path.toString());
            }
            return;
        }
        char c = pattern.charAt(i);
        if (c == '.'){
            for (char j = 0; j < R; j++) {
                path.append(j);
                traverse(node.children[j], path, pattern, i+1, res);
                path.deleteCharAt(path.length() - 1);
            }
        } else {
            path.append(c);
            traverse(node.children[c], path, pattern, i+1, res);
            path.deleteCharAt(path.length() - 1);
        }
    }

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
            return hasKeyWithPattern(node.children[c], pattern, i + 1);
        }
        // 遇到通配符
        for (char j = 0; j < R; j++) {
            if (hasKeyWithPattern(node.children[j],pattern, i+1)){
                return true;
            }
        }
        return false;
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

    public int size(){
        return size;
    }
}

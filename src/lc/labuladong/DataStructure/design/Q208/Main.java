package lc.labuladong.DataStructure.design.Q208;

class Trie {
    private TrieNode root = new TrieNode();
    private static class TrieNode{
        Object val;
        TrieNode[] children = new TrieNode[26];
    }

    public Trie() {

    }

    public void insert(String word) {
        root = put(root, word, 0);
    }

    private TrieNode put(TrieNode node, String key, int i){
        if (node == null){
            node = new TrieNode();
        }
        if (i == key.length()){
            node.val = new Object();
            return node;
        }
        char c = key.charAt(i);
        node.children[c - 'a'] = put(node.children[c - 'a'], key, i+1);
        return node;
    }

    public boolean search(String word) {
        return get(word) != null;
    }

    private Object get(String key){
        TrieNode x = getNode(root, key);
        if (x == null || x.val == null){
            return null;
        }
        return x.val;
    }

    public boolean startsWith(String prefix) {
        return getNode(root, prefix) != null;
    }

    private TrieNode getNode(TrieNode node, String key){
        TrieNode p = node;
        for (int i = 0; i < key.length(); i++) {
            if (p == null) return null;
            char c = key.charAt(i);
            p = p.children[c - 'a'];
        }
        return p;
    }
}

public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
    }
}

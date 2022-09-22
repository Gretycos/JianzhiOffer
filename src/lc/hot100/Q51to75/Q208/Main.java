package lc.hot100.Q51to75.Q208;

/**
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 *
 * 请你实现 Trie 类：
 *
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串word 的前缀之一为 prefix ，返回 true ；
 * 否则，返回 false 。
 *
 *
 * 示例：
 *
 * 输入
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * 输出
 * [null, null, true, false, true, null, true]
 *
 * 解释
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 *
 *
 * 提示：
 *
 * 1 <= word.length, prefix.length <= 2000
 * word 和 prefix 仅由小写英文字母组成
 * insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
 *
 * */

class Trie {
    private class Node{
        char c;
        boolean isFinished = false;
        Node[] next = new Node[26];
        Node(char c){this.c = c;}
    }
    Node root;
    public Trie() {
        root = new Node('#');
    }

    public void insert(String word) {
        Node p = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (p.next[c - 'a'] == null){
                p.next[c - 'a'] = new Node(c);
            }
            p = p.next[c - 'a'];
        }
        p.isFinished = true;
    }

    public boolean search(String word) {
        Node last = getLastNode(word);
        return last != null && last.isFinished;
    }

    public boolean startsWith(String prefix) {
        return getLastNode(prefix) != null;
    }

    private Node getLastNode(String s){
        Node p = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (p.next[c - 'a'] == null){
                return null;
            }
            p = p.next[c - 'a'];
        }
        return p;
    }
}
public class Main {
}

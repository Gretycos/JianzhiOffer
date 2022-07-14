package com.daily.Q745;

import java.util.ArrayList;
import java.util.List;

/**
 * 设计一个包含一些单词的特殊词典，并能够通过前缀和后缀来检索单词。
 *
 * 实现 WordFilter 类：
 *
 * WordFilter(string[] words) 使用词典中的单词 words 初始化对象。
 * f(string pref, string suff) 返回词典中具有前缀prefix和后缀 suff的单词的下标。
 * 如果存在不止一个满足要求的下标，返回其中 最大的下标 。如果不存在这样的单词，返回 -1 。
 *
 *
 * 示例：
 *
 * 输入
 * ["WordFilter", "f"]
 * [[["apple"]], ["a", "e"]]
 * 输出
 * [null, 0]
 * 解释
 * WordFilter wordFilter = new WordFilter(["apple"]);
 * wordFilter.f("a", "e"); // 返回 0 ，因为下标为 0 的单词：前缀 prefix = "a" 且 后缀 suff = "e" 。
 *
 * 提示：
 *
 * 1 <= words.length <= 10^4
 * 1 <= words[i].length <= 7
 * 1 <= pref.length, suff.length <= 7
 * words[i]、pref 和 suff 仅由小写英文字母组成
 * 最多对函数 f 执行 10^4 次调用
 *
 * */
class TrieNode{
    TrieNode[] children;
    List<Integer> indexes; // 对应的词的下标，递增的
    public TrieNode(){
        children = new TrieNode[26];
        indexes = new ArrayList<>();
    }
}


class WordFilter {
    // 前缀字典树，后缀字典树
    private TrieNode prefTrie, suffTrie;

    public WordFilter(String[] words) {
        prefTrie = new TrieNode();
        suffTrie = new TrieNode();
        buildTrie(prefTrie,words,0);
        buildTrie(suffTrie,words,-1);
    }

    public int f(String pref, String suff) {
        List<Integer> prefIndexes = query(prefTrie,pref,0),
                      suffIndexes = query(suffTrie,suff,-1);
        // 两个列表的指针，从后往前遍历，因为两个列表是递增的
        int p1 = prefIndexes.size() - 1, p2 = suffIndexes.size() - 1;
        // 找两个递增列表的交集的最大值
        while(p1 >= 0 && p2 >= 0){
            // 根据指针找元素
            int index1 = prefIndexes.get(p1), index2 = suffIndexes.get(p2);
            if (index1 == index2){
                return index1;
            }
            if (index1 > index2){
                p1 --;
            }else{
                p2 --;
            }
        }
        return -1;
    }

    private void buildTrie(TrieNode root, String[] words, int flag){
        // flag >= 0是前缀，否则是后缀
        for(int i = 0; i < words.length; i++){
            TrieNode p = root;
            int wordLen = words[i].length();
            for (int j = flag >= 0 ? 0 : wordLen - 1; j >= 0 && j < wordLen; j = flag >= 0 ? j + 1 : j - 1){
                int idx = words[i].charAt(j) - 'a';
                if (p.children[idx] == null){
                    p.children[idx] = new TrieNode();
                }
                p = p.children[idx];
                p.indexes.add(i);
            }
        }
    }

    private List<Integer> query(TrieNode root, String s, int flag){
        TrieNode p = root;
        int len = s.length();
        for(int i = flag >= 0 ? 0 : len - 1; i >=0 && i < len; i = flag >= 0? i + 1 : i - 1){
            int idx = s.charAt(i) - 'a';
            if (p.children[idx] == null){
                return new ArrayList<>();
            }
            p = p.children[idx];
        }
        // p最后指向s的最后一个字符对应的节点
        return p.indexes;
    }
}
public class Main {
    public static void main(String[] args) {
        String[] words = {"apple", "app"};
        WordFilter wordFilter = new WordFilter(words);
        System.out.println(wordFilter.f("ap","le"));
    }
}

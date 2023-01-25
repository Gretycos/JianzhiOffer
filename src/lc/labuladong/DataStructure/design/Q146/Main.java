package lc.labuladong.DataStructure.design.Q146;

import java.util.LinkedHashMap;

class LRUCache {
    private int cap;
    private LinkedHashMap<Integer, Integer> cache;
    public LRUCache(int capacity) {
        cap = capacity;
        cache = new LinkedHashMap<>();
    }

    public int get(int key) {
        if (!cache.containsKey(key)){
            return -1;
        }
        makeRecently(key);
        return cache.get(key);
    }

    public void put(int key, int val) {
        if (cache.containsKey(key)){
            cache.put(key, val);
            makeRecently(key);
            return;
        }
        if (cache.size() >= this.cap){
            int oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
        cache.put(key, val);
    }

    private void makeRecently(int key){
        int val = cache.get(key);
        cache.remove(key);
        cache.put(key, val);
    }
}
public class Main {
}

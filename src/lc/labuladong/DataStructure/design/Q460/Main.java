package lc.labuladong.DataStructure.design.Q460;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

class LFUCache {
    private Map<Integer,Integer> keyToVal, keyToFreq;
    private Map<Integer, LinkedHashSet<Integer>> freqToKeys;
    private int minFreq;
    private int cap;

    public LFUCache(int capacity) {
        keyToVal = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKeys = new HashMap<>();
        cap = capacity;
        minFreq = 0;
    }

    public int get(int key) {
        if (!keyToVal.containsKey(key)){
            return -1;
        }
        increaseFreq(key);
        return keyToVal.get(key);
    }

    public void put(int key, int value) {
        if (cap <= 0) return;
        // 没有key
        if (keyToVal.containsKey(key)){
            keyToVal.put(key, value);
            increaseFreq(key);
            return;
        }

        // 有key
        if (cap <= keyToVal.size()){
            removeMinFreqKey();
        }
        // 插入KV
        keyToVal.put(key, value);
        // 插入KF
        keyToFreq.put(key, 1);
        // 如果不存在就new一个，如果存在就add key进去
        freqToKeys.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
        // 更新最小频率
        minFreq = 1;
    }

    private void increaseFreq(int key){
        int freq = keyToFreq.get(key);
        keyToFreq.put(key, freq+1);
        // 移除旧频率的key
        freqToKeys.get(freq).remove(key);
        // 增加新频率的key
        freqToKeys.computeIfAbsent(freq+1, k -> new LinkedHashSet<>()).add(key);
        // 如果旧频率的keys没有了，则移除
        if (freqToKeys.get(freq).isEmpty()){
            freqToKeys.remove(freq);
            // 如果正好是minFreq，更新为新频率
            if (freq == minFreq){
                minFreq = freq + 1;
            }
        }
    }

    private void removeMinFreqKey(){
        LinkedHashSet<Integer> keys = freqToKeys.get(this.minFreq);
        int deletedKey = keys.iterator().next();
        keys.remove(deletedKey);
        if (keys.isEmpty()){
            freqToKeys.remove(this.minFreq);
        }
        keyToVal.remove(deletedKey);
        keyToFreq.remove(deletedKey);
    }
}

public class Main {
}

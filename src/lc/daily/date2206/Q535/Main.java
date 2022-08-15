package lc.daily.date2206.Q535;

import java.util.HashMap;
import java.util.Map;

/**
 * TinyURL 是一种 URL 简化服务， 比如：当你输入一个 URLhttps://leetcode.com/problems/design-tinyurl时，
 * 它将返回一个简化的URLhttp://tinyurl.com/4e9iAk 。请你设计一个类来加密与解密 TinyURL 。
 *
 * 加密和解密算法如何设计和运作是没有限制的，你只需要保证一个 URL 可以被加密成一个 TinyURL ，
 * 并且这个 TinyURL 可以用解密方法恢复成原本的 URL 。
 *
 * 实现 Solution 类：
 *
 * Solution() 初始化 TinyURL 系统对象。
 * String encode(String longUrl) 返回 longUrl 对应的 TinyURL 。
 * String decode(String shortUrl) 返回 shortUrl 原本的 URL 。题目数据保证给定的 shortUrl 是由同一个系统对象加密的。
 *
 *
 * 示例：
 *
 * 输入：url = "https://leetcode.com/problems/design-tinyurl"
 * 输出："https://leetcode.com/problems/design-tinyurl"
 *
 * 解释：
 * Solution obj = new Solution();
 * string tiny = obj.encode(url); // 返回加密后得到的 TinyURL 。
 * string ans = obj.decode(tiny); // 返回解密后得到的原本的 URL 。
 *
 *
 * 提示：
 *
 * 1 <= url.length <= 104
 * 题目数据保证 url 是一个有效的 URL
 *
 * */

class Codec {

    private final String BASE_URL = "http://tinyurl.com/";
    private final int K1 = 1117, K2 = (int) (1e9+7);
    private Map<Integer,String> database;
    private Map<String,Integer> urlToKey;


    public Codec(){
        database = new HashMap<>();
        urlToKey = new HashMap<>();
    }

    // Encodes a URL to a shortened URL.
    // 自定义哈希函数
    public String encode2(String longUrl) {
        // 如果存在了就不需要再生成
        if (urlToKey.containsKey(longUrl)){
            return BASE_URL + urlToKey.get(longUrl);
        }
        // 哈希函数
        // Hash(longUrl) = (sigma(i=0,n-1)(longUrl[i] * K1^i)) % K2
        int key = 0;
        long base = 1;
        for (int i = 0; i < longUrl.length(); i++){
            char c = longUrl.charAt(i);
            key = (int) ((key + (long) c * base) % K2);
            base = (base * K1) % K2;
        }
        while(database.containsKey(key)){
            key ++;
        }
        database.put(key,longUrl);
        urlToKey.put(longUrl,key);
        return BASE_URL + key;
    }

    // Encodes a URL to a shortened URL.
    // 自带哈希函数
    public String encode(String longUrl) {
        // 如果存在了就不需要再生成
        if (urlToKey.containsKey(longUrl)){
            return BASE_URL + urlToKey.get(longUrl);
        }
        // 哈希函数
        int key = longUrl.hashCode();
        while(database.containsKey(key)){
            key ++;
        }
        database.put(key,longUrl);
        urlToKey.put(longUrl,key);
        return BASE_URL + key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        int p = shortUrl.lastIndexOf('/') + 1;
        int key = Integer.parseInt(shortUrl.substring(p));
        return database.get(key);
    }
}
public class Main {
}

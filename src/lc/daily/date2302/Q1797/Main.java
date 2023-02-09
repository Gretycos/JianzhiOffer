package lc.daily.date2302.Q1797;

import java.util.*;

class AuthenticationManager {

    private int ttl;
    private Map<String, Integer> tokens; // token, expireTime
    public AuthenticationManager(int timeToLive) {
        ttl = timeToLive;
        tokens = new HashMap<>();
    }

    public void generate(String tokenId, int currentTime) {
        tokens.put(tokenId, currentTime + ttl);
    }

    public void renew(String tokenId, int currentTime) {
        int expireTime = tokens.getOrDefault(tokenId, 0);
        if (expireTime > currentTime){
            tokens.put(tokenId, currentTime + ttl);
        }
    }

    public int countUnexpiredTokens(int currentTime) {
        int count = 0;
        List<String> remove = new ArrayList<>();
        for (Map.Entry<String, Integer> token : tokens.entrySet()) {
            String key = token.getKey();
            int expireTime = token.getValue();
            // 遍历map的时候不能删除，否则会出现并发错误
            if (currentTime >= expireTime){
                remove.add(key);
            } else {
                count++;
            }
        }
        for (String key : remove) {
            tokens.remove(key);
        }
        return count;
    }
}
public class Main {
}

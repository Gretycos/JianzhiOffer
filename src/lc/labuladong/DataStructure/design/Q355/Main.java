package lc.labuladong.DataStructure.design.Q355;

import java.util.*;

class Twitter {
    // 静态变量才能被静态类访问
    public static int timestamp = 0;

    // 静态内部类不会隐式地产生指向外部类的实例的引用
    private static class Tweet{
        private int id;
        private int time;
        private Tweet next;

        public Tweet(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }

    private static class User{
        private int id;
        // id的用户关注的人
        public Set<Integer> following;
        public Tweet tweetHead;

        public User(int id) {
            this.id = id;
            following = new HashSet<>();
            // 关注自己
            follow(id);
        }

        public void follow(int userId){
            following.add(userId);
        }

        public void unfollow(int userId){
            // 不能取关自己
            if (userId != id){
                following.remove(userId);
            }
        }

        public void post(int tweetId){
            Tweet tweet = new Tweet(tweetId, timestamp++);
            // 新推文插入链表头
            // 越靠前的推文的time越大
            tweet.next = tweetHead;
            tweetHead = tweet;
        }

    }

    private final Map<Integer, User> userMap;
    public Twitter() {
        userMap = new HashMap<>();
    }

    /** 用户userId发送推文tweetId */
    public void postTweet(int userId, int tweetId) {
        // 如果userId不存在则创建并返回
        User user = userMap.computeIfAbsent(userId, uid -> new User(uid));
        user.post(tweetId);
    }

    /** 返回 userId 关注的人（包括自己）的最近10条动态 */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        User user = userMap.getOrDefault(userId, null);
        if (user == null){
            return res;
        }
        // 关注列表
        Set<Integer> following = user.following;
        // 优先队列
        PriorityQueue<Tweet> pq = new PriorityQueue<>(following.size(),
                (t1,t2) -> t2.time - t1.time);

        // 插入所有推文表的头节点
        for (int uid : following) {
            Tweet head = userMap.get(uid).tweetHead;
            if (head == null) continue;
            pq.add(head);
        }

        while (!pq.isEmpty()){
            // 返回最多10条
            if (res.size() == 10) break;
            Tweet tweet = pq.remove();
            res.add(tweet.id);
            if (tweet.next != null){
                pq.add(tweet.next);
            }
        }

        return res;
    }

    /** follower 关注 followee */
    public void follow(int followerId, int followeeId) {
        // 如果follower不存在则创建并返回
        User follower = userMap.computeIfAbsent(followerId, uid -> new User(uid));
        // 如果followee不存在则创建
        userMap.putIfAbsent(followeeId, new User(followeeId));
        follower.follow(followeeId);
    }

    /** follower 取关 followee */
    public void unfollow(int followerId, int followeeId) {
        User follower = userMap.getOrDefault(followerId, null);
        if (follower != null){
            follower.unfollow(followeeId);
        }
    }
}
public class Main {
}

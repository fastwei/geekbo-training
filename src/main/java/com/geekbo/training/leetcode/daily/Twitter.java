package com.geekbo.training.leetcode.daily;

import java.util.*;

/**
 * 这个实现使用了一个HashMap来存储用户信息，其中键是用户ID，值是User对象。
 * 每个User对象包含用户的关注列表和推文链表的头节点。
 * 当用户发布推文时，将推文添加到推文链表的头部。
 * 当用户获取新闻动态时，使用优先队列按照推文的时间戳进行排序，并从中选取最近的10条推文。
 * <p>
 * 算法复杂度分析：
 * - `postTweet`操作的时间复杂度是O(1)，因为只需要在用户的推文链表头部插入一个新的节点。
 * - `getNewsFeed`操作的时间复杂度是O(klogn)，其中k是返回的推文数量（最多10条），n是用户关注的人数。因为需要对每个关注的人的推文链表进行合并，并选择最近的k条推文，所以需要遍历所有关注的人的推文链表，并使用优先队列进行排序。
 * - `follow`和`unfollow`操作的时间复杂度都是O(1)，因为只需要在用户的关注列表中添加或删除一个元素。
 */
class Twitter {
    private int timestamp;
    private Map<Integer, User> userMap;

    private class Tweet {
        int tweetId;
        int time;
        Tweet next;

        public Tweet(int tweetId, int time) {
            this.tweetId = tweetId;
            this.time = time;
            this.next = null;
        }
    }

    private class User {
        int userId;
        Set<Integer> followees;
        Tweet tweetHead;

        public User(int userId) {
            this.userId = userId;
            this.followees = new HashSet<>();
            this.tweetHead = null;
            // 用户默认关注自己
            follow(userId);
        }

        public void follow(int userId) {
            followees.add(userId);
        }

        public void unfollow(int userId) {
            // 用户不能取消关注自己
            if (userId != this.userId) {
                followees.remove(userId);
            }
        }

        public void postTweet(int tweetId) {
            Tweet tweet = new Tweet(tweetId, timestamp);
            timestamp++;
            tweet.next = tweetHead;
            tweetHead = tweet;
        }
    }

    /**
     * Initialize your data structure here.
     */
    public Twitter() {
        this.timestamp = 0;
        this.userMap = new HashMap<>();
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        if (!userMap.containsKey(userId)) {
            User user = new User(userId);
            userMap.put(userId, user);
        }
        User user = userMap.get(userId);
        user.postTweet(tweetId);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> newsFeed = new ArrayList<>();
        if (!userMap.containsKey(userId)) {
            return newsFeed;
        }
        Set<Integer> followees = userMap.get(userId).followees;
        PriorityQueue<Tweet> pq = new PriorityQueue<>((a, b) -> b.time - a.time);
        for (int followeeId : followees) {
            Tweet tweetHead = userMap.get(followeeId).tweetHead;
            if (tweetHead != null) {
                pq.offer(tweetHead);
            }
        }
        int count = 0;
        while (!pq.isEmpty() && count < 10) {
            Tweet tweet = pq.poll();
            newsFeed.add(tweet.tweetId);
            if (tweet.next != null) {
                pq.offer(tweet.next);
            }
            count++;
        }
        return newsFeed;
    }

    public void follow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId)) {
            User user = new User(followerId);
            userMap.put(followerId, user);
        }
        if (!userMap.containsKey(followeeId)) {
            User user = new User(followeeId);
            userMap.put(followeeId, user);
        }
        User follower = userMap.get(followerId);
        follower.follow(followeeId);
    }


    /**
     * Follower unfollows a followee. If the operation is invalid, it should return an error.
     */
    public void unfollow(int followerId, int followeeId) {
        if (userMap.containsKey(followerId)) {
            User follower = userMap.get(followerId);
            follower.unfollow(followeeId);
        }
    }
}




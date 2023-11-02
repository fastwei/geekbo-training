package com.geekbo.training.leetcode.binarysearch;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 981. Time Based Key-Value Store
 * <p>
 * Design a time-based key-value data structure that can store multiple values for the same key
 * at different time stamps and retrieve the key's value at a certain timestamp.
 * <p>
 * Implement the TimeMap class:
 * <p>
 * TimeMap() Initializes the object of the data structure.
 * void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
 * String get(String key, int timestamp) Returns a value such that set was called previously,
 * with timestamp_prev <= timestamp.
 * If there are multiple such values, it returns the value associated with the largest timestamp_prev.
 * If there are no values, it returns "".
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["TimeMap", "set", "get", "get", "set", "get", "get"]
 * [[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
 * Output
 * [null, null, "bar", "bar", null, "bar2", "bar2"]
 * <p>
 * Explanation
 * TimeMap timeMap = new TimeMap();
 * timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
 * timeMap.get("foo", 1);         // return "bar"
 * timeMap.get("foo", 3);         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2,
 * then the only value is at timestamp 1 is "bar".
 * timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
 * timeMap.get("foo", 4);         // return "bar2"
 * timeMap.get("foo", 5);         // return "bar2"
 * <p>
 * 解题思路：
 * <p>
 * 我们可以使用两个哈希表来实现TimeMap类。
 * 第一个哈希表timestampMap用来存储每个key对应的值和时间戳的映射关系。
 * 第二个哈希表valueMap用来存储每个key对应的时间戳和值的映射关系。
 * 对于set方法，我们将值和时间戳添加到timestampMap和valueMap中。
 * 对于get方法，我们首先通过key在timestampMap中查找最接近给定时间戳的时间戳，然后在valueMap中查找对应的值。
 * <p>
 * 算法复杂度分析：
 * <p>
 * 时间复杂度：set方法的时间复杂度为O(1)，get方法的时间复杂度为O(log N)，其中N是set方法被调用的次数。
 * 空间复杂度：算法的空间复杂度为O(N)，其中N是set方法被调用的次数，用来存储timestampMap和valueMap的空间。
 */
class TimeMap {
    private Map<String, TreeMap<Integer, String>> timestampMap;
    private Map<String, String> valueMap;

    public TimeMap() {
        timestampMap = new HashMap<>();
        valueMap = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        timestampMap.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
        valueMap.put(key, value);
    }

    public String get(String key, int timestamp) {
        if (!timestampMap.containsKey(key)) {
            return "";
        }
        TreeMap<Integer, String> treeMap = timestampMap.get(key);
        Integer floorKey = treeMap.floorKey(timestamp);
        return floorKey != null ? treeMap.get(floorKey) : "";
    }

    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();
        timeMap.set("foo", "bar", 1);
        // 预期输出: "bar"
        System.out.println(timeMap.get("foo", 1));
        // 预期输出: "bar"
        System.out.println(timeMap.get("foo", 3));
        timeMap.set("foo", "bar2", 4);
        // 预期输出: "bar2"
        System.out.println(timeMap.get("foo", 4));
        // 预期输出: "bar2"
        System.out.println(timeMap.get("foo", 5));
    }
}
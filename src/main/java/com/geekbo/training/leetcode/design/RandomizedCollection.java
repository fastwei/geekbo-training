package com.geekbo.training.leetcode.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class RandomizedCollection {
    private ArrayList<Integer> nums;
    private HashMap<Integer, HashSet<Integer>> indexMap;
    private Random random;

    /**
     * 初始化RandomizedCollection对象
     */
    public RandomizedCollection() {
        nums = new ArrayList<>();
        indexMap = new HashMap<>();
        random = new Random();
    }

    /**
     * 将一个val项插入到集合中
     *
     * @param val 要插入的值
     * @return 如果该项不存在，则返回true；如果该项已经存在，则返回false
     */
    public boolean insert(int val) {
        nums.add(val);
        HashSet<Integer> indexSet = indexMap.getOrDefault(val, new HashSet<>());
        indexSet.add(nums.size() - 1);
        indexMap.put(val, indexSet);
        return indexSet.size() == 1;
    }

    /**
     * 从集合中移除一个val项
     *
     * @param val 要移除的值
     * @return 如果该项存在，则返回true；如果该项不存在，则返回false
     */
    public boolean remove(int val) {
        if (!indexMap.containsKey(val)) {
            return false;
        }
        int indexToRemove = indexMap.get(val).iterator().next();
        int lastNum = nums.get(nums.size() - 1);
        nums.set(indexToRemove, lastNum);
        indexMap.get(val).remove(indexToRemove);
        indexMap.get(lastNum).remove(nums.size() - 1);
        if (indexToRemove < nums.size() - 1) {
            indexMap.get(lastNum).add(indexToRemove);
        }
        if (indexMap.get(val).size() == 0) {
            indexMap.remove(val);
        }
        nums.remove(nums.size() - 1);
        return true;
    }

    /**
     * 从当前的多个元素集合中返回一个随机元素
     *
     * @return 随机元素
     */
    public int getRandom() {
        return nums.get(random.nextInt(nums.size()));
    }

    public static void main(String[] args) {
        RandomizedCollection randomizedCollection = new RandomizedCollection();
        // 测试插入操作
        System.out.println(randomizedCollection.insert(1)); // true
        System.out.println(randomizedCollection.insert(1)); // false
        System.out.println(randomizedCollection.insert(2)); // true
        // 测试移除操作
        System.out.println(randomizedCollection.remove(1)); // true
        System.out.println(randomizedCollection.remove(1)); // true
        System.out.println(randomizedCollection.remove(2)); // true
        System.out.println(randomizedCollection.remove(2)); // false
        // 测试随机元素
        for (int i = 0; i < 10; i++) {
            System.out.println(randomizedCollection.getRandom()); // 随机输出集合中的元素
        }
    }
}
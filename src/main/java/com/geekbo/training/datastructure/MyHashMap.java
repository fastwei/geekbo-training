package com.geekbo.training.datastructure;

import java.util.Arrays;

/**
 *
 * 解题思路：
 *
 * 我们创建一个大小为1,000,000的数组hashMap来存储键值对，将数组中的所有元素初始化为-1，表示没有映射。
 *
 * 在put方法中，我们简单地使用给定的值更新数组hashMap中指定键的值。
 *
 * 在get方法中，我们返回数组hashMap中指定键的值。
 *
 * 在remove方法中，我们将指定键的值设置为-1，以删除映射。
 *
 * 算法复杂度：
 *
 * put、get 和 remove 的时间复杂度：O(1)
 * 空间复杂度：O(1)
 */
class MyHashMap {
    private final int SIZE = Integer.MAX_VALUE;
    private int[] hashMap;

    /**
     * 初始化HashMap数据结构。
     */
    public MyHashMap() {
        // 创建大小为 1,000,000 的数组来存储键值对，将数组元素初始化为 -1，表示没有映射。
        hashMap = new int[SIZE];
        Arrays.fill(hashMap, -1);
    }
    
    /**
     * 将键值对 (key, value) 插入HashMap。
     * 如果键已经存在于映射中，则更新对应的值。
     * 
     * @param key   插入或更新的键
     * @param value 插入或更新的值，值始终是非负数
     */
    public void put(int key, int value) {
        hashMap[key] = value;
    }
    
    /**
     * 返回指定键对应的值，如果映射中不包含该键则返回 -1。
     * 
     * @param key 指定的键
     * @return 映射中包含的值，或者 -1（如果未找到）
     */
    public int get(int key) {
        return hashMap[key];
    }
    
    /**
     * 移除映射中的指定键值对。
     * 
     * @param key 要移除的键
     */
    public void remove(int key) {
        hashMap[key] = -1;
    }
}

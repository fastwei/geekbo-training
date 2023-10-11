package com.geekbo.training.leetcode.top150;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * 380. Insert Delete GetRandom O(1)
 *
 * RandomizedSet类，包括插入、删除和获取随机元素的功能，满足O(1)时间复杂度的要求。
 * 其中，使用ArrayList来存储元素，使用HashMap来存储元素到索引的映射，以便在O(1)时间内查找元素的索引。
 *
 */
class RandomizedSet {
    private ArrayList<Integer> elements;
    private HashMap<Integer, Integer> elementToIndex;

    public RandomizedSet() {
        elements = new ArrayList<>();
        elementToIndex = new HashMap<>();
    }

    public boolean insert(int val) {
        if (elementToIndex.containsKey(val)) {
            return false;
        } else {
            elements.add(val);
            elementToIndex.put(val, elements.size() - 1);
            return true;
        }
    }

    public boolean remove(int val) {
        if (elementToIndex.containsKey(val)) {
            int index = elementToIndex.get(val);
            int lastElement = elements.get(elements.size() - 1);
            elements.set(index, lastElement);
            elementToIndex.put(lastElement, index);
            elements.remove(elements.size() - 1);
            elementToIndex.remove(val);
            return true;
        } else {
            return false;
        }
    }

    public int getRandom() {
        Random random = new Random();
        int randomIndex = random.nextInt(elements.size());
        return elements.get(randomIndex);
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        System.out.println(randomizedSet.insert(1)); // true
        System.out.println(randomizedSet.remove(2)); // false
        System.out.println(randomizedSet.insert(2)); // true
        System.out.println(randomizedSet.getRandom()); // 1 or 2
        System.out.println(randomizedSet.remove(1)); // true
        System.out.println(randomizedSet.insert(2)); // false
        System.out.println(randomizedSet.getRandom()); // 2
    }
}

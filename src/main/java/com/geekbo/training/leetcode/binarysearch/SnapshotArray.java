package com.geekbo.training.leetcode.binarysearch;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Implement a SnapshotArray that supports the following interface:
 * <p>
 * SnapshotArray(int length) initializes an array-like data structure with the given length. Initially, each element equals 0.
 * void set(index, val) sets the element at the given index to be equal to val.
 * int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
 * int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: ["SnapshotArray","set","snap","set","get"]
 * [[3],[0,5],[],[0,6],[0,0]]
 * Output: [null,null,0,null,5]
 * Explanation:
 * SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
 * snapshotArr.set(0,5);  // Set array[0] = 5
 * snapshotArr.snap();  // Take a snapshot, return snap_id = 0
 * snapshotArr.set(0,6);
 * snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
 * 解题思路：
 * <p>
 * 使用一个二维列表snapshots来保存快照数据，其中snapshots[i]表示索引为i的元素的快照数据。
 * 初始化时，创建长度为length的snapshots列表，每个元素都是一个TreeMap，用于保存快照的键值对（snapId和对应的值）。
 * 在set方法中，将给定索引index的元素的快照数据中插入一个新的键值对，键为当前快照IDsnapId，值为给定的值val。
 * 在snap方法中，返回当前快照IDsnapId并将snapId加1。
 * 在get方法中，根据给定的索引index获取对应的快照数据，然后在该快照数据中查找小于等于给定快照IDsnapId的最大键，返回对应的值。
 * <p>
 * 算法复杂度分析：
 * <p>
 * 初始化时，创建长度为length的snapshots列表，时间复杂度为O(length)。
 * set方法和snap方法的时间复杂度为O(1)，直接对TreeMap进行插入和自增操作。
 * get方法的时间复杂度为O(log n)，其中n为快照数据的数量，通过`floorKey`方法在`TreeMap`中查找小于等于给定快照ID的最大键。
 * - 空间复杂度为O(length * snapId)，其中length为数组的长度，snapId为快照的数量，需要存储每个元素的快照数据。
 */
class SnapshotArray {
    private List<TreeMap<Integer, Integer>> snapshots;
    private int snapId;

    public SnapshotArray(int length) {
        snapshots = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            snapshots.add(new TreeMap<>());
            snapshots.get(i).put(0, 0);
        }
        snapId = 0;
    }

    public void set(int index, int val) {
        snapshots.get(index).put(snapId, val);
    }

    public int snap() {
        return snapId++;
    }

    public int get(int index, int snapId) {
        TreeMap<Integer, Integer> snapshot = snapshots.get(index);
        // Find the latest snapshot with snapId less than or equal to the given snapId
        int key = snapshot.floorKey(snapId);
        return snapshot.get(key);
    }

    public static void main(String[] args) {
        // Test case 1
        SnapshotArray snapshotArr1 = new SnapshotArray(3);
        snapshotArr1.set(0, 5);
        int snapId1 = snapshotArr1.snap();
        snapshotArr1.set(0, 6);
        int result1 = snapshotArr1.get(0, snapId1);
        System.out.println(result1 == 5 ? "Pass" : "Fail");

        // Test case 2
        SnapshotArray snapshotArr2 = new SnapshotArray(5);
        snapshotArr2.set(1, 10);
        snapshotArr2.set(3, 20);
        snapshotArr2.set(4, 30);
        int snapId2 = snapshotArr2.snap();
        snapshotArr2.set(1, 15);
        snapshotArr2.set(3, 25);
        int result2 = snapshotArr2.get(3, snapId2);
        System.out.println(result2 == 20 ? "Pass" : "Fail");
    }
}


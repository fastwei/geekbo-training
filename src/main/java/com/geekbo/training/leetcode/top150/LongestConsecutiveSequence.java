package com.geekbo.training.leetcode.top150;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given an unsorted array of integers nums,
 * return the length of the longest consecutive elements sequence.
 * <p>
 * You must write an algorithm that runs in O(n) time.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * Example 2:
 * <p>
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 */
public class LongestConsecutiveSequence {


    /**
     * todo:需加深union find的理解
     *
     * 使用并查集优化后的算法复杂度：
     * <p>
     * 时间复杂度：遍历整个数组需要O(n)的时间，其中n为数组的长度。
     * 对于每个数字，通过并查集操作可以在O(1)的时间内找到其所属连续序列，并更新最长连续序列的长度。
     * 因此，总体时间复杂度为O(n)。
     * 空间复杂度：使用了两个HashMap来存储数字与其所属连续序列的关系，最坏情况下，HashMap的大小为n。
     * 因此，空间复杂度为O(n)。
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        UnionFind uf = new UnionFind(nums);

        int longestStreak = 0;

        for (int num : nums) {
            // 如果当前数字的下一个数字存在于数组中，则将它们进行合并
            if (uf.contains(num + 1)) {
                uf.union(num, num + 1);
            }

            // 更新最长连续序列的长度
            longestStreak = Math.max(longestStreak, uf.getSize(num));
        }

        return longestStreak;
    }

    /**
     * 解题思路：
     * <p>
     * 遍历整个数组，将所有的数字保存到一个Set中。
     * 再次遍历数组中的每个数字，对于每个数字，如果它是连续序列的最小值（即不存在比它小1的数字），
     * 则向后查找连续的数字，直到找到连续序列的最大值。
     * 更新最长连续序列的长度。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：遍历整个数组需要O(n)的时间，其中n为数组的长度。
     * 在查找连续序列的过程中，每个数字最多被访问两次（一次作为连续序列的最小值，一次作为连续序列的最大值），
     * 所以总体时间复杂度为O(n)。
     * 空间复杂度：使用了一个Set来保存数组中的数字，最坏情况下，Set的大小为n。因此，空间复杂度为O(n)。
     *
     * @param nums
     * @return
     */
    public int longestConsecutive2(int[] nums) {
        // 创建一个Set保存所有的数字
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longestStreak = 0;

        // 遍历数组中的每个数字
        for (int num : nums) {
            // 只对连续序列的最小值进行处理
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                // 继续查找连续的下一个数字
                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }

                // 更新最长连续序列的长度
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence solution = new LongestConsecutiveSequence();

        int[] nums1 = {100, 4, 200, 1, 3, 2};
        int expected1 = 4;
        int result1 = solution.longestConsecutive(nums1);
        System.out.println(result1 == expected1); // true

        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        int expected2 = 9;
        int result2 = solution.longestConsecutive(nums2);
        System.out.println(result2 == expected2); // true
    }
}

class UnionFind {
    private Map<Integer, Integer> parent;
    private Map<Integer, Integer> size;

    public UnionFind(int[] nums) {
        parent = new HashMap<>();
        size = new HashMap<>();

        for (int num : nums) {
            parent.put(num, num);
            size.put(num, 1);
        }
    }

    public int find(int x) {
        if (parent.get(x) != x) {
            parent.put(x, find(parent.get(x)));
        }
        return parent.get(x);
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            parent.put(rootX, rootY);
            size.put(rootY, size.get(rootX) + size.get(rootY));
        }
    }

    public int getSize(int x) {
        return size.get(find(x));
    }

    public boolean contains(int x) {
        return parent.containsKey(x);
    }
}
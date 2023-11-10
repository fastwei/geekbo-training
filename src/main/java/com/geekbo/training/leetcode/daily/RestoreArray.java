package com.geekbo.training.leetcode.daily;

import java.util.*;

/**
 * 1743. Restore the Array From Adjacent Pairs
 * Medium
 * There is an integer array nums that consists of n unique elements, but you have forgotten it.
 * However, you do remember every pair of adjacent elements in nums.
 * <p>
 * You are given a 2D integer array adjacentPairs of size n - 1
 * where each adjacentPairs[i] = [ui, vi] indicates that the elements ui and vi are adjacent in nums.
 * <p>
 * It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1]
 * will exist in adjacentPairs, either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]].
 * The pairs can appear in any order.
 * <p>
 * Return the original array nums. If there are multiple solutions, return any of them.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: adjacentPairs = [[2,1],[3,4],[3,2]]
 * Output: [1,2,3,4]
 * Explanation: This array has all its adjacent pairs in adjacentPairs.
 * Notice that adjacentPairs[i] may not be in left-to-right order.
 * Example 2:
 * <p>
 * Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
 * Output: [-2,4,1,-3]
 * Explanation: There can be negative numbers.
 * Another solution is [-3,1,4,-2], which would also be accepted.
 * Example 3:
 * <p>
 * Input: adjacentPairs = [[100000,-100000]]
 * Output: [100000,-100000]
 */
public class RestoreArray {
    /**
     * 解题思路： 首先，我们需要构建一个邻接表来存储每个元素的相邻元素。
     * 然后，我们找到起始元素，即邻接表中只有一个相邻元素的元素。
     * 接下来，我们使用深度优先搜索（DFS）来构建数组。
     * 从起始元素开始，我们将其加入数组，并标记为已访问。
     * 然后，对于其相邻元素中未访问过的元素，我们递归地进行深度优先搜索。最后，我们将数组转换为整数数组并返回。
     * <p>
     * 算法复杂度分析： 构建邻接表的时间复杂度为O(n)，其中n是adjacentPairs中的元素个数。
     * 深度优先搜索的时间复杂度为O(n)，空间复杂度为O(n)。
     *
     * @param adjacentPairs
     * @return
     */
    public static int[] restoreArray(int[][] adjacentPairs) {
        // Create a map to store the adjacency list
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        // Build the adjacency list
        for (int[] pair : adjacentPairs) {
            int u = pair[0];
            int v = pair[1];
            adjList.putIfAbsent(u, new ArrayList<>());
            adjList.putIfAbsent(v, new ArrayList<>());
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        // Find the start of the array
        int start = 0;
        for (Map.Entry<Integer, List<Integer>> entry : adjList.entrySet()) {
            if (entry.getValue().size() == 1) {
                start = entry.getKey();
                break;
            }
        }

        // Construct the array using depth-first search
        List<Integer> array = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        dfs(adjList, start, array, visited);

        // Convert the list to an array
        int[] nums = new int[array.size()];
        for (int i = 0; i < array.size(); i++) {
            nums[i] = array.get(i);
        }

        return nums;
    }

    private static void dfs(Map<Integer, List<Integer>> adjList, int u, List<Integer> array, Set<Integer> visited) {
        visited.add(u);
        array.add(u);

        for (int v : adjList.get(u)) {
            if (!visited.contains(v)) {
                dfs(adjList, v, array, visited);
            }
        }
    }

    public static void main(String[] args) {
        int[][] adjacentPairs1 = {{2, 1}, {3, 4}, {3, 2}};
        int[][] adjacentPairs2 = {{4, -2}, {1, 4}, {-3, 1}};
        int[][] adjacentPairs3 = {{100000, -100000}};

        int[] nums1 = restoreArray(adjacentPairs1);
        int[] nums2 = restoreArray(adjacentPairs2);
        int[] nums3 = restoreArray(adjacentPairs3);

        System.out.println(Arrays.toString(nums1)); // Output: [1, 2, 3, 4]
        System.out.println(Arrays.toString(nums2)); // Output: [-2, 4, 1, -3]
        System.out.println(Arrays.toString(nums3)); // Output: [100000, -100000]
    }
}



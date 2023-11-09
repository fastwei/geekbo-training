package com.geekbo.training.leetcode.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 218. The Skyline Problem
 * Hard
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Given the locations and heights of all the buildings, return the skyline formed by these buildings collectively.
 * <p>
 * The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:
 * <p>
 * lefti is the x coordinate of the left edge of the ith building.
 * righti is the x coordinate of the right edge of the ith building.
 * heighti is the height of the ith building.
 * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 * <p>
 * The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...]. Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list, which always has a y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends. Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.
 * <p>
 * Note: There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
 * Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
 * Explanation:
 * Figure A shows the buildings of the input.
 * Figure B shows the skyline formed by those buildings. The red points in figure B represent the key points in the output list.
 * Example 2:
 * <p>
 * Input: buildings = [[0,2,3],[2,5,3]]
 * Output: [[0,3],[5,0]]
 */
class TheSkylineProblem {

    /**
     * 使用分治法的思路是将建筑物数组划分为两个子数组，然后递归地计算每个子数组的天际线，最后将两个子数组的天际线合并。
     * 合并的过程中，根据每个关键点的x坐标和高度进行比较，确定天际线的关键点。
     * <p>
     * 算法复杂度分析:
     * - 时间复杂度: O(nlogn)，其中 n 是建筑物的数量。
     * 每次递归将建筑物数组划分为两个子数组，时间复杂度为 O(logn)，合并两个子数组的天际线的时间复杂度为 O(n)。
     * - 空间复杂度: O(n)，每次递归需要额外的空间来存储子数组的天际线，最坏情况下需要 O(n) 的空间。
     */
    public static List<List<Integer>> getSkyline(int[][] buildings) {
        if (buildings.length == 0) {
            return new ArrayList<>();
        }
        return divideAndConquer(buildings, 0, buildings.length - 1);
    }

    private static List<List<Integer>> divideAndConquer(int[][] buildings, int start, int end) {
        if (start == end) {
            List<List<Integer>> skyline = new ArrayList<>();
            skyline.add(Arrays.asList(buildings[start][0], buildings[start][2]));
            skyline.add(Arrays.asList(buildings[start][1], 0));
            return skyline;
        }

        int mid = start + (end - start) / 2;
        List<List<Integer>> leftSkyline = divideAndConquer(buildings, start, mid);
        List<List<Integer>> rightSkyline = divideAndConquer(buildings, mid + 1, end);

        return mergeSkyline(leftSkyline, rightSkyline);
    }

    private static List<List<Integer>> mergeSkyline(List<List<Integer>> leftSkyline, List<List<Integer>> rightSkyline) {
        List<List<Integer>> mergedSkyline = new ArrayList<>();
        int leftHeight = 0, rightHeight = 0;
        int leftIndex = 0, rightIndex = 0;

        while (leftIndex < leftSkyline.size() && rightIndex < rightSkyline.size()) {
            List<Integer> leftPoint = leftSkyline.get(leftIndex);
            List<Integer> rightPoint = rightSkyline.get(rightIndex);
            int x, height;

            if (leftPoint.get(0) < rightPoint.get(0)) {
                x = leftPoint.get(0);
                leftHeight = leftPoint.get(1);
                height = Math.max(leftHeight, rightHeight);
                leftIndex++;
            } else if (leftPoint.get(0) > rightPoint.get(0)) {
                x = rightPoint.get(0);
                rightHeight = rightPoint.get(1);
                height = Math.max(leftHeight, rightHeight);
                rightIndex++;
            } else {
                x = leftPoint.get(0);
                leftHeight = leftPoint.get(1);
                rightHeight = rightPoint.get(1);
                height = Math.max(leftHeight, rightHeight);
                leftIndex++;
                rightIndex++;
            }

            if (mergedSkyline.isEmpty() || height != mergedSkyline.get(mergedSkyline.size() - 1).get(1)) {
                mergedSkyline.add(Arrays.asList(x, height));
            }
        }

        while (leftIndex < leftSkyline.size()) {
            mergedSkyline.add(leftSkyline.get(leftIndex));
            leftIndex++;
        }

        while (rightIndex < rightSkyline.size()) {
            mergedSkyline.add(rightSkyline.get(rightIndex));
            rightIndex++;
        }

        return mergedSkyline;
    }

    /**
     * 计算由建筑物形成的天际线
     * 解题思路: 该问题可以通过扫描线的方法来解决，关键点是确定建筑物的左右边界，并按照x坐标对关键点进行排序。
     * 然后使用最大堆来保存当前的最大高度，并遍历关键点，根据当前的最大高度确定天际线的关键点。
     * <p>
     * 算法复杂度分析:
     * <p>
     * 时间复杂度: O(nlogn)，其中 n 是建筑物的数量。
     * 需要对关键点进行排序，时间复杂度为 O(nlogn)，然后遍历关键点，时间复杂度为 O(n)。
     * 空间复杂度: O(n)，最坏情况下，需要存储所有关键点和最大堆的元素，空间复杂度为 O(n)。
     *
     * @param buildings 建筑物的位置和高度数组
     * @return 天际线的关键点列表
     */
    public static List<List<Integer>> getSkyline2(int[][] buildings) {
        List<List<Integer>> skyline = new ArrayList<>();
        List<int[]> criticalPoints = new ArrayList<>();

        // 将建筑物的左右边界作为关键点，并用负数表示左边界
        for (int[] building : buildings) {
            criticalPoints.add(new int[]{building[0], -building[2]});
            criticalPoints.add(new int[]{building[1], building[2]});
        }

        // 按照x坐标对关键点进行排序
        criticalPoints.sort((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return a[1] - b[1];
            }
        });

        // 使用最大堆来保存当前的最大高度
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        maxHeap.offer(0);
        int prevMaxHeight = 0;

        for (int[] point : criticalPoints) {
            int x = point[0];
            int height = Math.abs(point[1]);

            if (point[1] < 0) { // 左边界
                maxHeap.offer(height);
            } else { // 右边界
                maxHeap.remove(height);
            }

            int currMaxHeight = maxHeap.peek();
            if (currMaxHeight != prevMaxHeight) {
                List<Integer> keyPoint = new ArrayList<>();
                keyPoint.add(x);
                keyPoint.add(currMaxHeight);
                skyline.add(keyPoint);
                prevMaxHeight = currMaxHeight;
            }
        }

        return skyline;
    }

    public static void main(String[] args) {
        // 测试用例1
        int[][] buildings1 = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        List<List<Integer>> skyline1 = getSkyline(buildings1);
        System.out.println("测试用例1: " + skyline1); // 预期输出: [[2, 10], [3, 15], [7, 12], [12, 0], [15, 10], [20, 8], [24, 0]]

        // 测试用例2
        int[][] buildings2 = {{0, 2, 3}, {2, 5, 3}};
        List<List<Integer>> skyline2 = getSkyline(buildings2);
        System.out.println("测试用例2: " + skyline2); // 预期输出: [[0, 3], [5, 0]]
    }
}



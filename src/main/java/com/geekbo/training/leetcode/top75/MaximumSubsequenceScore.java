package com.geekbo.training.leetcode.top75;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaximumSubsequenceScore {


    /**
     * 计算给定长度的子序列的最大得分
     * <p>
     * 解题思路：
     * <p>
     * 创建一个二维数组nums2Indexed，用于存储nums2中的元素和对应的索引。
     * 同时，将nums2Indexed按照nums2中的元素降序排序。
     * 创建一个最小堆pq，用于选择最小的nums1元素。
     * 初始化变量sum为0，用于记录选择的nums1元素的和，初始化变量maxScore为0，用于记录最大得分。
     * 首先，选择最大的k个元素，并计算得分。
     * 遍历nums2Indexed的前k个元素，获取对应的nums1元素的索引和值。将该值加到sum中，并将该值加入pq。
     * 计算初始得分，即sum乘以nums2Indexed[k - 1][0]。
     * 然后，选择剩余的元素，并计算得分。从k开始遍历nums2Indexed的剩余元素，获取对应的nums1元素的索引和值。
     * 如果当前的nums1元素小于等于pq的堆顶元素，则跳过该元素。
     * 否则，更新得分，即将pq的堆顶元素移除，将当前的nums1元素加入pq，更新sum，并将得分与maxScore比较，取较大值。
     * 返回最大得分maxScore。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 创建二维数组nums2Indexed的时间复杂度为O(n)，其中n是nums1和nums2的长度。
     * 对nums2Indexed进行排序的时间复杂度为O(n log n)。
     * 创建最小堆pq的时间复杂度为O(k log k)。
     * 选择最大的k个元素并计算得分的时间复杂度为O(k)。
     * 遍历剩余元素并计算得分的时间复杂度为O((n-k) log k)。
     * 综上，算法的总时间复杂度为O(n log n + (n-k) log k)，其中n是nums1和nums2的长度，k是子序列的长度。
     * 对于空间复杂度，除了输入的数组外，算法使用了一个二维数组nums2Indexed、一个最小堆pq，以及一些额外的变量。所以空间复杂度为O(n)。
     * <p>
     * 因此，该算法的时间复杂度为O(n log n + (n-k) log k)，空间复杂度为O(n)。
     *
     * @param nums1 第一个数组
     * @param nums2 第二个数组
     * @param k     子序列的长度
     * @return 最大得分
     */
    public static long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int[][] nums2Indexed = new int[n][2];

        // 将nums2中的元素和对应的索引存储到nums2Indexed中
        for (int i = 0; i < n; i++) {
            nums2Indexed[i] = new int[]{nums2[i], i};
        }

        // 按照nums2中的元素降序排序nums2Indexed
        Arrays.sort(nums2Indexed, (a, b) -> b[0] - a[0]);

        // 创建最小堆，用于选择最小的nums1元素
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        long sum = 0;
        long maxScore = 0;

        // 选择最大的k个元素，并计算得分
        for (int i = 0; i < k; i++) {
            int index = nums2Indexed[i][1];
            int val = nums1[index];
            sum += val;
            pq.offer(val);
        }

        // 计算初始得分
        maxScore = sum * nums2Indexed[k - 1][0];

        // 选择剩余的元素，并计算得分
        for (int i = k; i < n; i++) {
            int index = nums2Indexed[i][1];
            int val = nums1[index];

            // 如果当前的nums1元素比堆顶元素小，则继续下一个元素
            if (val <= pq.peek()) {
                continue;
            }

            // 更新得分
            sum -= pq.poll();
            pq.offer(val);
            sum += val;
            maxScore = Math.max(maxScore, sum * nums2Indexed[i][0]);
        }

        return maxScore;
    }


    /**
     * 解题思路： 该问题可以通过二分搜索来解决。
     * 首先，确定子序列的长度范围，即左边界为0，右边界为min(k, n)，其中n为数组的长度。
     * 然后，利用二分搜索在该范围内寻找最大得分。
     * 在每次二分搜索的过程中，计算当前长度为mid的子序列的得分，并与历史最大得分进行比较，更新最大得分。最终返回最大得分。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：二分搜索的时间复杂度为O(log k)，其中k为子序列的长度。
     * <p>
     * 计算最大得分的方法
     *
     * @param nums1 第一个数组
     * @param nums2 第二个数组
     * @param k     子序列的长度
     * @return 最大得分
     */
    public static int maxScore2(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int left = 0;
        int right = Math.min(k, n); // 子序列的长度不能超过数组的长度

        int maxScore = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int score = calculateScore(nums1, nums2, mid); // 计算当前长度为mid的子序列的得分

            if (score >= maxScore) {
                maxScore = score;
                left = mid + 1; // 继续向右搜索更大的得分
            } else {
                right = mid - 1; // 向左搜索
            }
        }

        return maxScore;
    }

    /**
     * 计算给定长度的子序列的得分
     *
     * @param nums1 第一个数组
     * @param nums2 第二个数组
     * @param len   子序列的长度
     * @return 子序列的得分
     */
    private static int calculateScore(int[] nums1, int[] nums2, int len) {
        int sum = 0;
        int minNum2 = Integer.MAX_VALUE;

        for (int i = 0; i < len; i++) {
            sum += nums1[i];
            minNum2 = Math.min(minNum2, nums2[i]);
        }

        return sum * minNum2;
    }

    public static void main(String[] args) {
        // 测试用例1
        int[] nums1 = {1, 3, 3, 2};
        int[] nums2 = {2, 1, 3, 4};
        int k = 3;
        // 预期输出: 12
        System.out.println(maxScore(nums1, nums2, k));

        // 测试用例2
        int[] nums3 = {4, 2, 3, 1, 1};
        int[] nums4 = {7, 5, 10, 9, 6};
        int k2 = 1;
        // 预期输出: 30
        System.out.println(maxScore(nums3, nums4, k2));

        // 测试用例3
        int[] nums5 = {1, 2, 3, 4, 5};
        int[] nums6 = {6, 7, 8, 9, 10};
        int k3 = 5;
        // 预期输出: 90
        System.out.println(maxScore(nums5, nums6, k3));
    }
}
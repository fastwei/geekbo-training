package com.geekbo.training.leetcode.daily;

/**
 * 2179. Count Good Triplets in an Array
 * Hard
 * You are given two 0-indexed arrays nums1 and nums2 of length n,
 * both of which are permutations of [0, 1, ..., n - 1].
 * <p>
 * A good triplet is a set of 3 distinct values which are present in increasing
 * order by position both in nums1 and nums2. In other words, if we consider pos1v
 * as the index of the value v in nums1 and pos2v as the index of the value v in nums2,
 * then a good triplet will be a set (x, y, z) where 0 <= x, y, z <= n - 1,
 * such that pos1x < pos1y < pos1z and pos2x < pos2y < pos2z.
 * <p>
 * Return the total number of good triplets.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [2,0,1,3], nums2 = [0,1,2,3]
 * Output: 1
 * Explanation:
 * There are 4 triplets (x,y,z) such that pos1x < pos1y < pos1z. They are (2,0,1), (2,0,3), (2,1,3), and (0,1,3).
 * Out of those triplets, only the triplet (0,1,3) satisfies pos2x < pos2y < pos2z. Hence, there is only 1 good triplet.
 * Example 2:
 * <p>
 * Input: nums1 = [4,0,1,3,2], nums2 = [4,1,0,2,3]
 * Output: 4
 * Explanation: The 4 good triplets are (4,0,3), (4,0,2), (4,1,3), and (4,1,2).
 */
class CountGoodTripletsInArray {
    /**
     * 使用树状数组（Fenwick Tree）来解决问题。
     * 下面是解题思路和算法复杂度的分析：
     * <p>
     * 首先，创建一个长度为n的pos数组，用于记录nums2中每个元素的索引位置。
     * 初始化树状数组ft，并创建两个长度为n的数组left和right，用于记录左侧和右侧的计数。
     * 遍历nums1数组，对于每个元素nums1[i]，通过pos数组获取其在nums2中的索引位置idx。
     * 在树状数组ft中，查询idx之前的元素的和，记录在left[i]中，并更新树状数组ft中idx位置的值为1。
     * 重置树状数组ft，并反向遍历nums1数组，重复步骤4，并记录在right[i]中。
     * 最后，遍历left和right数组，计算left[i] * right[i]的累加和，得到最终的结果ans。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：该算法需要遍历两次nums1数组，每次遍历都需要进行树状数组的查询和更新操作，因此时间复杂度为O(nlogn)。
     * 空间复杂度：除了输入参数和常数大小的额外空间，
     * 算法使用了一个长度为n的pos数组、两个长度为n的辅助数组left和right，以及一个长度为n+1的树状数组ft，因此空间复杂度为O(n)。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] pos = new int[n];

        FenwickTree ft = new FenwickTree(n + 1);

        for (int i = 0; i < n; i++)
            pos[nums2[i]] = i;

        long[] left = new long[n];
        long[] right = new long[n];

        for (int i = 0; i < n; i++) {
            int idx = pos[nums1[i]];
            left[i] = ft.sum(idx - 1);
            ft.update(idx, 1);
        }

        ft = new FenwickTree(n + 1);

        for (int i = n - 1; i >= 0; i--) {
            int idx = pos[nums1[i]];
            right[i] = ft.sum(n + 1) - ft.sum(idx);
            ft.update(idx, 1);
        }

        long ans = 0;

        for (int i = 0; i < n; i++)
            ans += left[i] * right[i];

        return ans;
    }
}

class FenwickTree {
    int[] bit;
    int n;

    FenwickTree(int n) {
        this.n = n;
        this.bit = new int[n + 2];
    }

    public void update(int i, int val) {
        i++;
        while (i < bit.length) {
            bit[i] += val;
            i += (i & (-i));
        }
    }

    public int sum(int i) {
        int sum = 0;
        i++;
        while (i > 0) {
            sum += bit[i];
            i -= (i & (-i));
        }
        return sum;
    }
}
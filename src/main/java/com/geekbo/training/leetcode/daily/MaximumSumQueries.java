package com.geekbo.training.leetcode.daily;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class MaximumSumQueries {

    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        int[][] a = new int[n][2];
        for (int i = 0; i < n; i++) {
            a[i][0] = nums1[i];
            a[i][1] = nums2[i];
        }

        Arrays.sort(nums2);
        TreeMap<Integer, Integer> hm = new TreeMap<>();
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (!hm.containsKey(nums2[i])) {
                hm.put(nums2[i], k++);
            }
        }

        Arrays.sort(a, Comparator.comparingInt(x -> x[0]));

        for (int i = 0; i < queries.length; i++) {
            queries[i] = new int[]{queries[i][0], queries[i][1], i};
        }
        Arrays.sort(queries, Comparator.comparingInt((int[] x) -> -x[0]));

        int[] ans = new int[queries.length];
        int j = 0;
        int i = a.length - 1;

        int[] seg = new int[4 * hm.size() + 4];
        Arrays.fill(seg, -1);
        while (j < queries.length) {
            while (i > -1 && a[i][0] >= queries[j][0]) {
                update(0, hm.size() - 1, hm.get(a[i][1]), a[i][0] + a[i][1], 0, seg);
                i--;
            }

            if (hm.ceilingKey(queries[j][1]) == null) {
                ans[queries[j][2]] = -1;
            } else {
                ans[queries[j][2]] = find(0, hm.size() - 1, hm.ceilingEntry(queries[j][1]).getValue(), hm.size() - 1, 0, seg);
            }
            j++;
        }
        while (j < queries.length) {
            ans[queries[j++][2]] = -1;
        }
        return ans;
    }

    private void update(int low, int high, int ind, int val, int node, int[] seg) {
        if (low == high) {
            seg[node] = Math.max(seg[node], val);
            return;
        }
        int mid = (low + high) >> 1;
        if (ind <= mid)
            update(low, mid, ind, val, 2 * node + 1, seg);
        else
            update(mid + 1, high, ind, val, 2 * node + 2, seg);
        seg[node] = Math.max(seg[2 * node + 1], seg[2 * node + 2]);
    }

    private int find(int low, int high, int l, int r, int node, int[] seg) {
        if (low > r || high < l)
            return -1;
        if (low >= l && high <= r)
            return seg[node];
        int mid = (low + high) >> 1;
        return Math.max(find(low, mid, l, r, 2 * node + 1, seg), find(mid + 1, high, l, r, 2 * node + 2, seg));
    }
}
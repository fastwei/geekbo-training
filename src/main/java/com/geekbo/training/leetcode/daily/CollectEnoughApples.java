package com.geekbo.training.leetcode.daily;

/**
 *
 * 1954. Minimum Garden Perimeter to Collect Enough Apples
 * Solved
 * Medium
 * In a garden represented as an infinite 2D grid, there is an apple tree planted at every integer coordinate. The apple tree planted at an integer coordinate (i, j) has |i| + |j| apples growing on it.
 *
 * You will buy an axis-aligned square plot of land that is centered at (0, 0).
 *
 * Given an integer neededApples, return the minimum perimeter of a plot such that at least neededApples apples are inside or on the perimeter of that plot.
 *
 * The value of |x| is defined as:
 *
 * x if x >= 0
 * -x if x < 0
 *
 */
class CollectEnoughApples {
    /**
    * 题目：1954. Minimum Garden Perimeter to Collect Enough Apples
    * 链接：https://leetcode.com/problems/minimum-garden-perimeter-to-collect-enough-apples/
    * 思路：为了收集到至少neededApples个苹果，我们需要找到一个最小的正方形地块，使得地块内或周围的苹果总数达到或超过neededApples。
    *       首先，我们可以将苹果树的坐标视为一个无限的二维网格。对于每个整数坐标(i, j)，苹果树上的苹果数量为|i| + |j|。
    *       我们需要找到一个正方形地块，其边长为x，使得地块内或周围的苹果总数至少为neededApples。由于地块是以(0, 0)为中心的，所以我们只需计算一个象限内的苹果总数即可。
    *       我们可以通过二分查找的方法找到满足条件的最小边长x。初始边界为lo=1和hi=100_000。在每一次迭代中，我们计算mid=(lo+hi)/2，并判断2 * mid * (mid + 1) * (2 * mid + 1)与neededApples的关系。
    *       如果2 * mid * (mid + 1) * (2 * mid + 1) < neededApples，说明mid较小，需要增加边长，因此更新lo=mid+1。如果2 * mid * (mid + 1) * (2 * mid + 1) >= neededApples，
    *       说明mid较大，需要减小边长，因此更新hi=mid。迭代终止条件为lo=hi，此时找到了满足条件的最小边长x=hi。
    *       最后，正方形地块的周长即为8 * x。
    * 算法复杂度分析：二分查找的过程时间复杂度为O(logN)，其中N为hi的大小。周长计算的时间复杂度为O(1)。
    */

    public long minimumPerimeter(long neededApples) {
        long lo = 1, hi = 100_000;

        while (lo < hi) {
            long mid = lo + hi >> 1;
            if (2 * mid * (mid + 1) * (2 * mid + 1) < neededApples) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return 8 * hi; // 返回最小周长
    }
}
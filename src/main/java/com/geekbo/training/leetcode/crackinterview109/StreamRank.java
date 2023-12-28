package com.geekbo.training.leetcode.crackinterview109;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 面试题 10.10. 数字流的秩
 * 解题思路： 我们可以使用一个有序数组来保存读入的数字，并且在每次读入数字之后对数组进行排序。这样，我们就可以使用二分查找来找到小于或等于 x 的值的个数。
 * <p>
 * 定义一个名为StreamRank的类，该类包含一个成员变量List<Integer> nums用于保存读入的数字。
 * <p>
 * 实现track方法，该方法接收一个整数x作为参数。将x添加到nums中，并对nums进行排序。
 * <p>
 * 实现getRankOfNumber方法，该方法接收一个整数x作为参数。使用二分查找找到x在nums中的插入位置，即小于或等于x的值的个数。
 * <p>
 * 算法复杂度分析：
 * <p>
 * track方法的时间复杂度为O(nlogn)，其中n是nums中的元素个数。因为在每次调用track方法时，我们需要对nums进行排序，而排序算法的时间复杂度为O(nlogn)。
 * getRankOfNumber方法的时间复杂度为O(logn)，其中n是nums中的元素个数。因为我们使用二分查找来找到x在nums中的插入位置。
 */
class StreamRank {
    private List<Integer> nums;

    public StreamRank() {
        nums = new ArrayList<>();
    }

    public void track(int x) {
        nums.add(x);
        Collections.sort(nums);
    }

    public int getRankOfNumber(int x) {
        int left = 0;
        int right = nums.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid) <= x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        StreamRank streamRank = new StreamRank();
        System.out.println(streamRank.getRankOfNumber(1)); // 返回 0
        streamRank.track(0);
        System.out.println(streamRank.getRankOfNumber(0)); // 返回 1
    }
}
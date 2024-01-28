package com.geekbo.training.leetcode.contest;
//380.3
public class ValueCalculator {

    // 优化后的 calculateValueSum 方法
    public static long calculateValueSum(long k, int x) {
        long low = 1, high = (long) 1e18;
        while (low < high) {
            long mid = (low + high) >>> 1;
            if (getValueSum(mid, x) > k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low - 1;
    }

    // 优化后的 getValueSum 方法
    private static long getValueSum(long num, int x) {
        long sum = 0;
        for (long i = 1; i <= 64; i++) {
            if (i % x == 0) {
                sum += bitCount(num, i);
            }
        }
        return sum;
    }

    // 计算 num 内部在给定位上设置位的数量
    private static long bitCount(long num, long i) {
        long mask = (1L << i) - 1;
        long fullBlocks = num >> i;
        long partialBlock = (num & mask) + 1;
        return fullBlocks * i + Math.min(i, partialBlock);
    }

    // 主方法，用于测试
    public static void main(String[] args) {
        System.out.println("Test Case 1: " + calculateValueSum(9, 1)); // 预期输出 6
        System.out.println("Test Case 2: " + calculateValueSum(7, 2)); // 预期输出 9
    }
}

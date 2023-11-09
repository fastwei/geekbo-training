package com.geekbo.training.leetcode.daily;

/**
 * 204. Count Primes
 * Medium
 * <p>
 * Given an integer n, return the number of prime numbers that are strictly less than n.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 * Example 2:
 * <p>
 * Input: n = 0
 * Output: 0
 * Example 3:
 * <p>
 * Input: n = 1
 * Output: 0
 */
class CountPrimes {
    /**
     * 使用埃拉托斯特尼筛法（Sieve of Eratosthenes）来计算小于n的所有质数的数量。
     * 首先创建一个长度为n的布尔数组isPrime，并将所有元素初始化为true。
     * 然后从2开始遍历数组，如果某个数是质数，则将其倍数标记为非质数。
     * 最后，遍历数组并计算isPrime中为true的元素的数量。
     * <p>
     * 该算法的时间复杂度为O(n log log n)。
     *
     * @param n
     * @return
     */
    public static int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i * i < n; i++) {
            if (!isPrime[i]) {
                continue;
            }

            for (int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // 测试用例1
        int n1 = 10;
        System.out.println("测试用例1: " + countPrimes(n1)); // 预期输出: 4

        // 测试用例2
        int n2 = 0;
        System.out.println("测试用例2: " + countPrimes(n2)); // 预期输出: 0

        // 测试用例3
        int n3 = 1;
        System.out.println("测试用例3: " + countPrimes(n3)); // 预期输出: 0
    }
}

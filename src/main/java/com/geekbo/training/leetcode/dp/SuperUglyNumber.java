package com.geekbo.training.leetcode.dp;

import java.util.PriorityQueue;

class SuperUglyNumber {
    /**
     * 解题思路：
     * <p>
     * 这道题可以使用动态规划来解决。
     * 我们维护一个长度为n的数组ugly，用来存储前n个超级丑数。
     * 初始化时，将第一个超级丑数设为1。
     * 我们还需要维护一个长度为primes.length的数组indexes，用来存储每个质数在ugly数组中的当前索引。
     * 我们从第二个超级丑数开始，遍历数组ugly，每次选择质数与当前索引对应的值相乘的最小值作为下一个超级丑数。
     * 当找到下一个超级丑数后，我们更新对应质数的索引，将其加1。
     * 最后返回数组ugly中的第n个元素即可。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度为O(n * k)，其中n是所需的超级丑数的个数，k是质数的个数。
     * 空间复杂度为O(n + k)，需要额外的空间来存储ugly数组和indexes数组。
     * <p>
     * 在使用动态规划解决这个问题时，我们应该使用堆来维护生成的超级丑数序列，以确保我们始终选择最小的超级丑数。
     *
     * @param n
     * @param primes
     * @return
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ugly = new int[n];
        ugly[0] = 1; // The first super ugly number is 1

        PriorityQueue<UglyNumber> pq = new PriorityQueue<>();

        for (int prime : primes) {
            pq.offer(new UglyNumber(prime, prime, 1)); // Initialize the priority queue with the prime numbers
        }

        for (int i = 1; i < n; i++) {
            UglyNumber min = pq.poll(); // Extract the minimum ugly number from the priority queue
            ugly[i] = min.val;

            while (!pq.isEmpty() && pq.peek().val == ugly[i]) { // Remove duplicate ugly numbers from the priority queue
                UglyNumber next = pq.poll();
                next.val = next.prime * ugly[next.index++];
                pq.offer(next);
            }

            min.val = min.prime * ugly[min.index++]; // Generate the next ugly number for the current prime factor
            pq.offer(min); // Add the updated ugly number back to the priority queue
        }

        return ugly[n - 1];
    }

    static class UglyNumber implements Comparable<UglyNumber> {
        int val;
        int prime;
        int index;

        UglyNumber(int val, int prime, int index) {
            this.val = val;
            this.prime = prime;
            this.index = index;
        }

        @Override
        public int compareTo(UglyNumber other) {
            return this.val - other.val;
        }
    }

    public static void main(String[] args) {
        SuperUglyNumber solution = new SuperUglyNumber();

        int n1 = 12;
        int[] primes1 = {2, 7, 13, 19};
        System.out.println(solution.nthSuperUglyNumber(n1, primes1)); // Expected output: 32

        int n2 = 1;
        int[] primes2 = {2, 3, 5};
        System.out.println(solution.nthSuperUglyNumber(n2, primes2)); // Expected output: 1
    }
}
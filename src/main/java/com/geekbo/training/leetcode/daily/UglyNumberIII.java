package com.geekbo.training.leetcode.daily;

public class UglyNumberIII {
    /**
     * We use a binary search approach to find the nth ugly number.
     * We initialize a low variable to 1 and a high variable to Integer.MAX_VALUE.
     * We perform a binary search by continuously updating the low and high variables
     * based on the count of ugly numbers between them until we find the nth ugly number.
     * <p>
     * The countUglyNumbers function calculates the count of ugly numbers
     * up to a given number num using the principle of inclusion-exclusion.
     * It counts the numbers divisible by a, b, or c,
     * subtracts the numbers divisible by any two of them,
     * adds the numbers divisible by all three of them, and returns the count.
     * <p>
     * The lcm function calculates the least common multiple of two numbers
     * using the formula (a * b) / gcd(a, b).
     * <p>
     * The gcd function calculates the greatest common divisor of two numbers using the Euclidean algorithm.
     * <p>
     * <p>
     * <p>
     * This solution has a time complexity of O(log n) and uses O(1) extra space.
     *
     * @param n
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static int nthUglyNumber(int n, int a, int b, int c) {
        long low = 1;
        long high = Integer.MAX_VALUE;
        long result = 0;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            long count = countUglyNumbers(mid, a, b, c);

            if (count >= n) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return (int) result;
    }

    private static long countUglyNumbers(long num, long a, long b, long c) {
        return num / a + num / b + num / c - num / lcm(a, b) - num / lcm(b, c) - num / lcm(a, c) + num / lcm(a, lcm(b, c));
    }

    private static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    private static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(3, 2, 3, 5));    // Output: 4
        System.out.println(nthUglyNumber(4, 2, 3, 4));    // Output: 6
        System.out.println(nthUglyNumber(5, 2, 11, 13));  // Output: 10
    }
}
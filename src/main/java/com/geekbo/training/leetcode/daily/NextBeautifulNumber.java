package com.geekbo.training.leetcode.daily;

public class NextBeautifulNumber {
    private int ans = Integer.MAX_VALUE;

    /**
     * 返回严格大于n的最小美丽数
     * 解题思路：
     * <p>
     * 创建一个整数数组arr，用于统计数字1到6的出现次数。初始时，每个数字出现次数为自身的值。
     * 通过递归函数find，生成严格大于n的数字。在递归过程中，每次尝试添加数字1到6，生成新的数字并更新arr数组。
     * 使用辅助函数check检查arr数组是否符合美丽数的条件，即每个数字出现次数等于其自身的值。
     * 如果找到了严格大于n的最小美丽数，则更新结果ans。
     * 最终返回结果ans。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：该算法的时间复杂度取决于数字n的大小。在最坏情况下，需要
     * <p>
     * 在最坏情况下，需要遍历所有严格大于n的数字，直到找到最小的美丽数。
     * 假设n的位数为d，则最坏情况下需要生成的数字个数为10^d。
     * 对于每个数字，需要进行递归和检查操作，时间复杂度为O(d)。
     * 因此，总体时间复杂度为O(d * 10^d)。
     * <p>
     * 空间复杂度：算法使用了一个长度为10的数组来统计数字出现的次数，以及递归调用栈的空间。
     * 递归调用栈的最大深度为数字n的位数d，因此空间复杂度为O(d)。
     * 请注意，由于数字n的位数通常较小，因此该算法在实际情况中的性能表现较好。
     *
     * @param n 给定的整数n
     * @return 严格大于n的最小美丽数
     */
    public int nextBeautifulNumber(int n) {
        int[] arr = new int[10];
        for (int i = 1; i <= 6; i++) {
            arr[i] = i;
        }
        find(arr, 0L, n);
        return ans;
    }

    /**
     * 递归函数，用于查找严格大于n的最小美丽数
     *
     * @param arr 数字数组，用于统计每个数字出现的次数
     * @param num 当前生成的数字
     * @param n   给定的整数n
     */
    private void find(int[] arr, long num, int n) {
        if (num > n && check(arr)) {
            if (ans > num) {
                ans = (int) num;
            }
            return;
        }
        if (num > 100 * n) {
            return;
        }

        for (int i = 1; i <= 6; i++) {
            if (arr[i] != 0) {
                arr[i]--;
                find(arr, num * 10 + i, n);
                arr[i]++;
            }
        }
    }

    /**
     * 检查数组中的数字是否符合美丽数的条件
     *
     * @param arr 数字数组，用于统计每个数字出现的次数
     * @return 如果数组中的数字符合美丽数的条件，则返回true；否则返回false
     */
    private boolean check(int[] arr) {
        for (int i = 1; i <= 6; i++) {
            if (arr[i] != 0 && arr[i] != i) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        NextBeautifulNumber solution = new NextBeautifulNumber();

        int n1 = 1;
        System.out.println(solution.nextBeautifulNumber(n1)); // 预期输出：22

        int n2 = 1000;
        System.out.println(solution.nextBeautifulNumber(n2)); // 预期输出：1333

        int n3 = 3000;
        System.out.println(solution.nextBeautifulNumber(n3)); // 预期输出：3133
    }
}


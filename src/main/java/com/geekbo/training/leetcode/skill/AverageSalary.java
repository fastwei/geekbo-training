package com.geekbo.training.leetcode.skill;

/**
 * You are given an array of unique integers salary where salary[i] is the salary of the ith employee.
 * <p>
 * Return the average salary of employees excluding the minimum and maximum salary.
 * Answers within 10-5 of the actual answer will be accepted.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: salary = [4000,3000,1000,2000]
 * Output: 2500.00000
 * Explanation: Minimum salary and maximum salary are 1000 and 4000 respectively.
 * Average salary excluding minimum and maximum salary is (2000+3000) / 2 = 2500
 * Example 2:
 * <p>
 * Input: salary = [1000,2000,3000]
 * Output: 2000.00000
 * Explanation: Minimum salary and maximum salary are 1000 and 3000 respectively.
 * Average salary excluding minimum and maximum salary is (2000) / 1 = 2000
 */
public class AverageSalary {

    /**
     * 解题思路：
     * 我们可以遍历数组，找到最小和最大的工资，并计算工资的总和。
     * 然后，通过总和减去最小和最大工资，得到剩余工资的总和。
     * 最后，除以数组长度减2（排除最小和最大工资的个数），得到平均工资。
     * <p>
     * 算法复杂度分析：
     * - 时间复杂度：遍历数组需要O(n)的时间复杂度，其中n是数组的长度。
     * - 空间复杂度：使用了常数个变量，所以空间复杂度为O(1)。
     *
     * @param salary
     * @return
     */
    public double average(int[] salary) {
        int min = Integer.MAX_VALUE; // 最小工资
        int max = Integer.MIN_VALUE; // 最大工资
        int sum = 0; // 工资总和

        // 遍历数组，找到最小和最大的工资，并计算工资总和
        for (int s : salary) {
            min = Math.min(min, s);
            max = Math.max(max, s);
            sum += s;
        }

        // 计算平均工资，排除最小和最大工资
        return (double) (sum - min - max) / (salary.length - 2);
    }

    public static void main(String[] args) {
        AverageSalary avgSalary = new AverageSalary();

        // Test Case 1
        int[] salary = {4000, 3000, 1000, 2000};
        // 预期输出为2500.00000
        System.out.printf("%.5f\n", avgSalary.average(salary));

        // Test Case 2
        salary = new int[]{1000, 2000, 3000};
        // 预期输出为2000.00000
        System.out.printf("%.5f\n", avgSalary.average(salary));
    }
}


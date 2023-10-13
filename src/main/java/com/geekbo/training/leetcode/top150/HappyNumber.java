package com.geekbo.training.leetcode.top150;

import java.util.HashSet;

public class HappyNumber {

    /**
     *
     * 解题思路是按照题目描述的步骤，迭代计算数字的各位数字的平方和，如果最终等于1，则返回true，否则检查是否进入了循环。
     *
     * 算法复杂度为O(logn)。
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        // 用HashSet来检测循环
        HashSet<Integer> seen = new HashSet<>();
        
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            int sum = 0;
            
            // 计算各位数字的平方和
            while (n > 0) {
                int digit = n % 10;
                sum += digit * digit;
                n /= 10;
            }
            
            n = sum;
        }
        
        // 如果最终等于1，则是快乐数
        return n == 1;
    }

    public static void main(String[] args) {
        HappyNumber happyNumber = new HappyNumber();
        System.out.println(happyNumber.isHappy(19)); // 输出 true
        System.out.println(happyNumber.isHappy(2));  // 输出 false
    }
}

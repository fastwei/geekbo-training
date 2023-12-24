package com.geekbo.training.leetcode.premium100;

/**
 *
 *
 * 1056. 易混淆数
 * 已解答
 * 简单
 * 给定一个数字 N，当它满足以下条件的时候返回 true：
 *
 * 原数字旋转 180° 以后可以得到新的数字。
 *
 * 如 0, 1, 6, 8, 9 旋转 180° 以后，得到了新的数字 0, 1, 9, 8, 6 。
 *
 * 2, 3, 4, 5, 7 旋转 180° 后，得到的不是数字。
 *
 * 易混淆数 (confusing number) 在旋转180°以后，可以得到和原来不同的数，且新数字的每一位都是有效的。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：6
 * 输出：true
 * 解释：
 * 把 6 旋转 180° 以后得到 9，9 是有效数字且 9!=6 。
 * 示例 2：
 *
 *
 *
 * 输入：89
 * 输出：true
 * 解释:
 * 把 89 旋转 180° 以后得到 68，68 是有效数字且 89!=68 。
 * 示例 3：
 *
 *
 *
 * 输入：11
 * 输出：false
 * 解释：
 * 把 11 旋转 180° 以后得到 11，11 是有效数字但是值保持不变，所以 11 不是易混淆数字。
 * 示例 4：
 *
 *
 *
 * 输入：25
 * 输出：false
 * 解释：
 * 把 25 旋转 180° 以后得到的不是数字。
 *
 *
 * 提示：
 *
 * 0 <= N <= 10^9
 * 可以忽略掉旋转后得到的前导零，例如，如果我们旋转后得到 0008 那么该数字就是 8 。
 */
class ConfusingNumber {
    /**
     *
     * 解题思路：
     *
     * 创建一个映射表map，用于将原数字的每一位映射到旋转180°后的数字。
     * 使用两个变量original和rotated来记录原数字和旋转后的数字。
     * 从个位开始，将每一位数字通过映射表转换为旋转后的数字，并将其添加到rotated中。
     * 如果旋转后的数字和原数字相等，则返回false；否则返回true。
     *
     * 算法复杂度分析：
     *
     * 时间复杂度：O(logN)，其中N是给定的数字。
     * 空间复杂度：O(1)。
     * @param N
     * @return
     */
    public boolean confusingNumber(int N) {
        int[] map = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
        int original = N;
        int rotated = 0;
        
        while (N > 0) {
            int digit = N % 10;
            if (map[digit] == -1) {
                return false;
            }
            rotated = rotated * 10 + map[digit];
            N /= 10;
        }
        
        return rotated != original;
    }
    
    public static void main(String[] args) {
        ConfusingNumber cn = new ConfusingNumber();
        // Test cases
        System.out.println(cn.confusingNumber(6)); // expected: true
        System.out.println(cn.confusingNumber(89)); // expected: true
        System.out.println(cn.confusingNumber(11)); // expected: false
        System.out.println(cn.confusingNumber(25)); // expected: false
    }
}

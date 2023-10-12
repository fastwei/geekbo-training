package com.geekbo.training.leetcode.top150;

/**
 *
 * todo:还没有正确理解zigzag
 */
class ZigZagConversion {

    /**
     * 解题思路：
     * <p>
     * 创建一个字符串数组，数组的长度为numRows，每个元素代表Z字形中的一行。
     * 遍历输入字符串s中的每个字符，将字符添加到对应行的字符串中。
     * 通过标志变量currentRow和goingDown来控制字符是向上还是向下移动。
     * 最后，将各行的字符串连接起来，即为最终结果。
     * 算法复杂度：
     * <p>
     * 时间复杂度：O(N)，其中N是字符串s的长度。
     * 空间复杂度：O(N)，用于存储每行的字符。
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int currentRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows[currentRow].append(c);
            if (currentRow == 0 || currentRow == numRows - 1) {
                goingDown = !goingDown;
            }
            currentRow += goingDown ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        ZigZagConversion solution = new ZigZagConversion();

        String s1 = "PAYPALISHIRING";
        int numRows1 = 3;
        String result1 = solution.convert(s1, numRows1);
        System.out.println("Example 1: " + result1); // 输出 "PAHNAPLSIIGYIR"

        String s2 = "PAYPALISHIRING";
        int numRows2 = 4;
        String result2 = solution.convert(s2, numRows2);
        System.out.println("Example 2: " + result2); // 输出 "PINALSIGYAHRPI"

        String s3 = "A";
        int numRows3 = 1;
        String result3 = solution.convert(s3, numRows3);
        System.out.println("Example 3: " + result3); // 输出 "A"
    }
}

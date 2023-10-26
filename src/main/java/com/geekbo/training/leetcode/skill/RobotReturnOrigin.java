package com.geekbo.training.leetcode.skill;

/**
 * There is a robot starting at the position (0, 0), the origin, on a 2D plane.
 * Given a sequence of its moves, judge if this robot ends up at (0, 0) after it completes its moves.
 * <p>
 * You are given a string moves that represents the move sequence of the robot where moves[i] represents its ith move.
 * Valid moves are 'R' (right), 'L' (left), 'U' (up), and 'D' (down).
 * <p>
 * Return true if the robot returns to the origin after it finishes all of its moves, or false otherwise.
 * <p>
 * Note: The way that the robot is "facing" is irrelevant.
 * 'R' will always make the robot move to the right once, 'L' will always make it move left, etc.
 * Also, assume that the magnitude of the robot's movement is the same for each move.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: moves = "UD"
 * Output: true
 * Explanation: The robot moves up once, and then down once.
 * All moves have the same magnitude, so it ended up at the origin where it started.
 * Therefore, we return true.
 * Example 2:
 * <p>
 * Input: moves = "LL"
 * Output: false
 * Explanation: The robot moves left twice. It ends up two "moves" to the left of the origin.
 * We return false because it is not at the origin at the end of its moves.
 */
public class RobotReturnOrigin {
    /**
     * 判断机器人是否返回原点
     * 解题思路： 遍历机器人的移动序列，根据移动的方向更新机器人的横坐标和纵坐标。
     * 最后判断机器人的横坐标和纵坐标是否都为0，如果是则表示机器人回到了原点，返回true，否则返回false。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n)，其中n为机器人的移动序列的长度。
     * 空间复杂度：O(1)。
     *
     * @param moves 机器人的移动序列
     * @return 返回true表示机器人回到原点，返回false表示机器人未回到原点
     */
    public boolean judgeCircle(String moves) {
        int x = 0; // 横坐标
        int y = 0; // 纵坐标

        for (char move : moves.toCharArray()) {
            if (move == 'U') {
                y++; // 向上移动，纵坐标加1
            } else if (move == 'D') {
                y--; // 向下移动，纵坐标减1
            } else if (move == 'L') {
                x--; // 向左移动，横坐标减1
            } else if (move == 'R') {
                x++; // 向右移动，横坐标加1
            }
        }

        return x == 0 && y == 0; // 判断机器人是否回到原点
    }

    public static void main(String[] args) {
        // 测试用例1
        String moves1 = "UD";
        boolean expected1 = true;
        RobotReturnOrigin solution = new RobotReturnOrigin();
        boolean result1 = solution.judgeCircle(moves1);
        System.out.println(result1 == expected1); // true

        // 测试用例2
        String moves2 = "LL";
        boolean expected2 = false;
        boolean result2 = solution.judgeCircle(moves2);
        System.out.println(result2 == expected2); // true
    }
}

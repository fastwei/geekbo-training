package com.geekbo.training.leetcode.skill;

/**
 * On an infinite plane, a robot initially stands at (0, 0) and faces north. Note that:
 * <p>
 * The north direction is the positive direction of the y-axis.
 * The south direction is the negative direction of the y-axis.
 * The east direction is the positive direction of the x-axis.
 * The west direction is the negative direction of the x-axis.
 * The robot can receive one of three instructions:
 * <p>
 * "G": go straight 1 unit.
 * "L": turn 90 degrees to the left (i.e., anti-clockwise direction).
 * "R": turn 90 degrees to the right (i.e., clockwise direction).
 * The robot performs the instructions given in order, and repeats them forever.
 * <p>
 * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: instructions = "GGLLGG"
 * Output: true
 * Explanation: The robot is initially at (0, 0) facing the north direction.
 * "G": move one step. Position: (0, 1). Direction: North.
 * "G": move one step. Position: (0, 2). Direction: North.
 * "L": turn 90 degrees anti-clockwise. Position: (0, 2). Direction: West.
 * "L": turn 90 degrees anti-clockwise. Position: (0, 2). Direction: South.
 * "G": move one step. Position: (0, 1). Direction: South.
 * "G": move one step. Position: (0, 0). Direction: South.
 * Repeating the instructions, the robot goes into the cycle: (0, 0) --> (0, 1) --> (0, 2) --> (0, 1) --> (0, 0).
 * Based on that, we return true.
 * Example 2:
 * <p>
 * Input: instructions = "GG"
 * Output: false
 * Explanation: The robot is initially at (0, 0) facing the north direction.
 * "G": move one step. Position: (0, 1). Direction: North.
 * "G": move one step. Position: (0, 2). Direction: North.
 * Repeating the instructions, keeps advancing in the north direction and does not go into cycles.
 * Based on that, we return false.
 * Example 3:
 * <p>
 * Input: instructions = "GL"
 * Output: true
 * Explanation: The robot is initially at (0, 0) facing the north direction.
 * "G": move one step. Position: (0, 1). Direction: North.
 * "L": turn 90 degrees anti-clockwise. Position: (0, 1). Direction: West.
 * "G": move one step. Position: (-1, 1). Direction: West.
 * "L": turn 90 degrees anti-clockwise. Position: (-1, 1). Direction: South.
 * "G": move one step. Position: (-1, 0). Direction: South.
 * "L": turn 90 degrees anti-clockwise. Position: (-1, 0). Direction: East.
 * "G": move one step. Position: (0, 0). Direction: East.
 * "L": turn 90 degrees anti-clockwise. Position: (0, 0). Direction: North.
 * Repeating the instructions, the robot goes into the cycle: (0, 0) --> (0, 1) --> (-1, 1) --> (-1, 0) --> (0, 0).
 * Based on that, we return true.
 */
public class RobotCircle {

    /**
     * 解题思路：
     * 我们可以模拟机器人的移动过程。定义机器人的初始位置和方向，使用一个二维数组dirs表示四个方向的移动情况。
     * 遍历每一个指令，根据指令更新机器人的位置和方向。
     * <p>
     * 如果机器人回到了原点或者机器人不再面向北方，则可以构成一个循环。
     * <p>
     * 算法复杂度分析：
     * - 时间复杂度：遍历每一个指令需要O(n)的时间复杂度，其中n是指令的长度。
     * - 空间复杂度：使用了常数个变量，所以空间复杂度为O(1)。
     *
     * @param instructions
     * @return
     */
    public boolean isRobotBounded(String instructions) {
        // 定义机器人的初始位置和方向
        int x = 0;
        int y = 0;
        int direction = 0; // 0表示北，1表示东，2表示南，3表示西

        // 定义方向的增量数组，分别表示北、东、南、西四个方向的移动情况
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // 遍历每一个指令
        for (char c : instructions.toCharArray()) {
            if (c == 'G') {
                // 前进一步
                x += dirs[direction][0];
                y += dirs[direction][1];
            } else if (c == 'L') {
                // 左转
                direction = (direction + 3) % 4;
            } else if (c == 'R') {
                // 右转
                direction = (direction + 1) % 4;
            }
        }

        // 如果机器人回到了原点或者机器人不再面向北方，则可以构成一个循环
        return (x == 0 && y == 0) || direction != 0;
    }

    public static void main(String[] args) {
        RobotCircle robot = new RobotCircle();

        // Test Case 1
        String instructions = "GGLLGG";
        // 预期输出为true
        System.out.println(robot.isRobotBounded(instructions));

        // Test Case 2
        instructions = "GG";
        // 预期输出为false
        System.out.println(robot.isRobotBounded(instructions));

        // Test Case 3
        instructions = "GL";
        // 预期输出为true
        System.out.println(robot.isRobotBounded(instructions));
    }
}


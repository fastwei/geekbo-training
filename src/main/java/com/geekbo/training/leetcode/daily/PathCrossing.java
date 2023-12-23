package com.geekbo.training.leetcode.daily;

import java.util.HashSet;
import java.util.Set;

/**
 * 1496. Path Crossing
 * Easy
 * Given a string path, where path[i] = 'N', 'S', 'E' or 'W', each representing moving one unit north, south, east, or west, respectively. You start at the origin (0, 0) on a 2D plane and walk on the path specified by path.
 * <p>
 * Return true if the path crosses itself at any point, that is, if at any time you are on a location you have previously visited. Return false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: path = "NES"
 * Output: false
 * Explanation: Notice that the path doesn't cross any point more than once.
 * Example 2:
 * <p>
 * <p>
 * Input: path = "NESWW"
 * Output: true
 * Explanation: Notice that the path visits the origin twice.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= path.length <= 104
 * path[i] is either 'N', 'S', 'E', or 'W'.
 */
public class PathCrossing {
    /**
     * 解题思路：
     * <p>
     * 使用两个变量x和y来记录当前的位置，初始化为(0, 0)
     * 使用一个哈希集合visited来记录已经访问过的位置，初始时将(0, 0)加入到visited中
     * 遍历给定的路径字符串path，根据每个方向字符更新当前位置x和y的值
     * 每次更新位置后，将新的位置加入到visited中，如果新的位置已经存在于visited中，则路径交叉，返回true
     * 如果遍历完整个路径后都没有发现路径交叉，则返回false
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历路径字符串需要O(n)的时间，其中n是路径字符串的长度
     * 空间复杂度：使用了一个哈希集合来记录已经访问过的位置，最坏情况下需要O(n)的额外空间，其中n是路径字符串的长度
     *
     * @param path
     * @return
     */
    public static boolean isPathCrossing(String path) {
        int x = 0, y = 0;
        Set<String> visited = new HashSet<>();
        visited.add("0,0");

        for (char direction : path.toCharArray()) {
            if (direction == 'N') {
                y++;
            } else if (direction == 'S') {
                y--;
            } else if (direction == 'E') {
                x++;
            } else if (direction == 'W') {
                x--;
            }

            String position = x + "," + y;
            if (visited.contains(position)) {
                return true;
            }

            visited.add(position);
        }

        return false;
    }

    public static void main(String[] args) {
        // Test cases
        String path1 = "NES";
        // The path "NES" does not cross any point more than once
        // Expected output: false
        System.out.println(isPathCrossing(path1));

        String path2 = "NESWW";
        // The path "NESWW" visits the origin twice
        // Expected output: true
        System.out.println(isPathCrossing(path2));
    }
}

package com.geekbo.training.leetcode.dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 403. Frog Jump
 * Hard
 * A frog is crossing a river. The river is divided into some number of units, and at each unit,
 * there may or may not exist a stone.
 * The frog can jump on a stone, but it must not jump into the water.
 * <p>
 * Given a list of stones positions (in units) in sorted ascending order,
 * determine if the frog can cross the river by landing on the last stone. Initially,
 * the frog is on the first stone and assumes the first jump must be 1 unit.
 * <p>
 * If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units.
 * The frog can only jump in the forward direction.
 * Example 1:
 * <p>
 * Input: stones = [0,1,3,5,6,8,12,17]
 * Output: true
 * Explanation: The frog can jump to the last stone by jumping 1 unit to the 2nd stone,
 * then 2 units to the 3rd stone, then 2 units to the 4th stone, then 3 units to the 6th stone, 4 units to the 7th stone,
 * and 5 units to the 8th stone.
 * Example 2:
 * <p>
 * Input: stones = [0,1,2,3,4,8,9,11]
 * Output: false
 * Explanation: There is no way to jump to the last stone as the gap between the 5th and 6th stone is too large.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= stones.length <= 2000
 * 0 <= stones[i] <= 231 - 1
 * stones[0] == 0
 * stones is sorted in a strictly increasing order.
 */
public class FrogJump {
    /**
     * 解题思路：
     * <p>
     * 这个问题可以使用动态规划来解决。
     * 我们使用一个哈希表来存储每个石头的位置和可以跳跃的步长集合。
     * 初始化时，将第一个石头的位置设置为0，步长为1。
     * 遍历每个石头的位置，对于每个石头，遍历其对应的步长集合。
     * 如果能够跳到最后一个石头，则返回true；否则，更新下一个石头的步长集合。
     * 如果无法跳到最后一个石头，则返回false。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：遍历每个石头的位置和步长集合，总共需要遍历stones数组，所以时间复杂度为O(n^2)，其中n为石头的数量。
     * <p>
     * 空间复杂度：使用了一个哈希表来存储石头的位置和步长集合，哈希表的大小取决于石头的数量，所以空间复杂度为O(n)，其中n为石头的数量。
     * 总结：
     * <p>
     * 使用动态规划的思路，通过哈希表来存储每个石头的位置和可以跳跃的步长集合。
     * 时间复杂度为O(n^2)，空间复杂度为O(n)。
     *
     * @param stones
     * @return
     */
    public boolean canCross(int[] stones) {
        // 创建一个哈希表，用于存储每个石头的位置和可以跳跃的步长集合
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int n = stones.length;

        // 初始化哈希表，将每个石头的位置对应的步长集合设置为空
        for (int i = 0; i < n; i++) {
            map.put(stones[i], new HashSet<>());
        }

        // 初始条件，第一个石头的位置为0，步长为1
        map.get(0).add(1);

        // 遍历每个石头的位置
        for (int i = 0; i < n; i++) {
            int stone = stones[i];

            // 遍历当前石头位置的步长集合
            for (int step : map.get(stone)) {
                int reach = stone + step;

                // 如果能够跳到最后一个石头，则返回true
                if (reach == stones[n - 1]) {
                    return true;
                }

                // 如果下一个石头存在，则更新下一个石头的步长集合
                if (map.containsKey(reach)) {
                    Set<Integer> set = map.get(reach);
                    if (step - 1 > 0) {
                        set.add(step - 1);
                    }
                    set.add(step);
                    set.add(step + 1);
                }
            }
        }

        // 如果无法跳到最后一个石头，则返回false
        return false;
    }

    public static void main(String[] args) {
        FrogJump solution = new FrogJump();

        // 测试用例1
        int[] stones1 = {0, 1, 3, 5, 6, 8, 12, 17};
        boolean result1 = solution.canCross(stones1);
        System.out.println("测试用例1:");
        System.out.println(result1); // 预期输出: true

        // 测试用例2
        int[] stones2 = {0, 1, 2, 3, 4, 8, 9, 11};
        boolean result2 = solution.canCross(stones2);
        System.out.println("测试用例2:");
        System.out.println(result2); // 预期输出: false
    }
}

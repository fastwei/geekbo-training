package com.geekbo.training.leetcode.daily;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1436. Destination City
 * Easy
 * You are given the array paths, where paths[i] = [cityAi, cityBi] means there exists a direct path going from cityAi to cityBi. Return the destination city, that is, the city without any path outgoing to another city.
 * <p>
 * It is guaranteed that the graph of paths forms a line without any loop, therefore, there will be exactly one destination city.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
 * Output: "Sao Paulo"
 * Explanation: Starting at "London" city you will reach "Sao Paulo" city which is the destination city. Your trip consist of: "London" -> "New York" -> "Lima" -> "Sao Paulo".
 * Example 2:
 * <p>
 * Input: paths = [["B","C"],["D","B"],["C","A"]]
 * Output: "A"
 * Explanation: All possible trips are:
 * "D" -> "B" -> "C" -> "A".
 * "B" -> "C" -> "A".
 * "C" -> "A".
 * "A".
 * Clearly the destination city is "A".
 * Example 3:
 * <p>
 * Input: paths = [["A","Z"]]
 * Output: "Z"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= paths.length <= 100
 * paths[i].length == 2
 * 1 <= cityAi.length, cityBi.length <= 10
 * cityAi != cityBi
 * All strings consist of lowercase and uppercase English letters and the space character.
 */
public class DestinationCity {
    /**
     * 找出目的地城市
     * 解题思路：
     * <p>
     * 遍历给定的路径列表，将每个城市的出度和入度记录在一个哈希表中。
     * 对于每个城市，如果它的出度为0，则它是目的地城市。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n)，其中 n 是路径列表的长度。需要遍历所有的路径。
     * 空间复杂度：O(n)，需要使用哈希表来记录城市的出度和入度。
     *
     * @param paths 路径列表
     * @return 目的地城市
     */
    public static String findDestinationCity(List<List<String>> paths) {
        // 记录城市的出度和入度
        Map<String, Integer> outDegree = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();

        // 遍历路径列表，统计城市的出度和入度
        for (List<String> path : paths) {
            String from = path.get(0);
            String to = path.get(1);

            outDegree.put(from, outDegree.getOrDefault(from, 0) + 1);
            inDegree.put(to, inDegree.getOrDefault(to, 0) + 1);
        }

        // 找出目的地城市
        for (String city : inDegree.keySet()) {
            if (!outDegree.containsKey(city)) {
                return city;
            }
        }

        return "";
    }

    public static void main(String[] args) {
        // 测试用例
        List<List<String>> paths1 = Arrays.asList(Arrays.asList("London", "New York"), Arrays.asList("New York", "Lima"), Arrays.asList("Lima", "Sao Paulo"));
        System.out.println(findDestinationCity(paths1)); // Output: "Sao Paulo"

        List<List<String>> paths2 = Arrays.asList(Arrays.asList("B", "C"), Arrays.asList("D", "B"), Arrays.asList("C", "A"));
        System.out.println(findDestinationCity(paths2)); // Output: "A"

        List<List<String>> paths3 = Arrays.asList(Arrays.asList("A", "Z"));
        System.out.println(findDestinationCity(paths3)); // Output: "Z"
    }
}
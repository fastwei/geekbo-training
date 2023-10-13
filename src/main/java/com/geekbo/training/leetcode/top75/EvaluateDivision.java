package com.geekbo.training.leetcode.top75;

import java.util.*;

public class EvaluateDivision {

    /**
     * 解题思路：
     *
     * 首先构建一个图结构，用于存储变量之间的关系。对于每个方程Ai / Bi = values[i]，在图中创建两个映射，一个是从Ai到Bi，值为values[i]，另一个是从Bi到Ai，值为1 / values[i]。
     * 针对每个查询，使用深度优先搜索（DFS）来查找变量之间的关系，计算结果。
     * 如果无法找到关系或者变量未定义，返回-1.0。
     * 算法复杂度：
     *
     * 时间复杂度：O(N + Q)，其中N是方程的数量，Q是查询的数量。对于每个查询，需要进行深度优先搜索，最坏情况下需要遍历整个图。
     * 空间复杂度：O(N)，用于存储图结构。
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 创建图结构，用于存储变量之间的关系
        Map<String, Map<String, Double>> graph = new HashMap<>();

        // 填充图结构
        for (int i = 0; i < equations.size(); i++) {
            String source = equations.get(i).get(0);
            String target = equations.get(i).get(1);
            double value = values[i];
            
            graph.computeIfAbsent(source, k -> new HashMap<>()).put(target, value);
            graph.computeIfAbsent(target, k -> new HashMap<>()).put(source, 1 / value);
        }

        // 处理查询并计算结果
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            results[i] = dfs(graph, queries.get(i).get(0), queries.get(i).get(1), new HashSet<>(), 1.0);
        }
        return results;
    }

    private double dfs(Map<String, Map<String, Double>> graph, String source, String target, Set<String> visited, double currentResult) {
        if (!graph.containsKey(source) || visited.contains(source)) {
            return -1.0;
        }
        if (source.equals(target)) {
            return currentResult;
        }

        visited.add(source);
        Map<String, Double> neighbors = graph.get(source);
        for (String neighbor : neighbors.keySet()) {
            double result = dfs(graph, neighbor, target, visited, currentResult * neighbors.get(neighbor));
            if (result != -1.0) {
                return result;
            }
        }

        visited.remove(source);
        return -1.0;
    }

    /**
     *
     * 解题思路：建立一个图，根据给定的变量对和值，构建有向加权图。然后对于每个查询，使用BFS查找两个变量之间的路径，并计算权重。
     *
     * 算法复杂度：假设有n个变量和m个查询，建立图的时间复杂度为O(n+m)，BFS的时间复杂度为O(n)，因此总体时间复杂度为O(n+m)。
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquationBfs(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 创建一个图，用于存储变量对之间的权重
        Map<String, Map<String, Double>> graph = new HashMap<>();

        // 添加边和权重
        for (int i = 0; i < equations.size(); i++) {
            String from = equations.get(i).get(0);
            String to = equations.get(i).get(1);
            double value = values[i];

            graph.computeIfAbsent(from, k -> new HashMap<>()).put(to, value);
            graph.computeIfAbsent(to, k -> new HashMap<>()).put(from, 1.0 / value);
        }

        // 处理查询
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String from = queries.get(i).get(0);
            String to = queries.get(i).get(1);

            if (!graph.containsKey(from) || !graph.containsKey(to)) {
                results[i] = -1.0;
            } else if (from.equals(to)) {
                results[i] = 1.0;
            } else {
                results[i] = bfs(graph, from, to);
            }
        }
        return results;
    }

    // 使用BFS查找两个变量之间的权重
    private double bfs(Map<String, Map<String, Double>> graph, String from, String to) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(from);

        Map<String, Double> values = new HashMap<>();
        values.put(from, 1.0);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(to)) {
                return values.get(current);
            }

            for (Map.Entry<String, Double> neighbor : graph.get(current).entrySet()) {
                if (!values.containsKey(neighbor.getKey())) {
                    values.put(neighbor.getKey(), values.get(current) * neighbor.getValue());
                    queue.offer(neighbor.getKey());
                }
            }
        }

        return -1.0;
    }

    public static void main(String[] args) {
        EvaluateDivision solution = new EvaluateDivision();

        List<List<String>> equations1 = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"));
        double[] values1 = {2.0, 3.0};
        List<List<String>> queries1 = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("b", "a"), Arrays.asList("a", "e"), Arrays.asList("a", "a"), Arrays.asList("x", "x"));
        double[] results1 = solution.calcEquation(equations1, values1, queries1);
        System.out.println(Arrays.toString(results1)); // [6.0, 0.5, -1.0, 1.0, -1.0]


        List<List<String>> equations2 = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"), Arrays.asList("bc", "cd"));
        double[] values2 = {1.5, 2.5, 5.0};
        List<List<String>> queries2 = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("c", "b"), Arrays.asList("bc", "cd"), Arrays.asList("cd", "bc"));
        double[] results2 = solution.calcEquation(equations2, values2, queries2);
        System.out.println(Arrays.toString(results2)); // [3.75, 0.4, 5.0, 0.2]

        List<List<String>> equations3 = Arrays.asList(Arrays.asList("a", "b"));
        double[] values3 = {0.5};
        List<List<String>> queries3 = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "a"), Arrays.asList("a", "c"), Arrays.asList("x", "y"));
        double[] results3 = solution.calcEquation(equations3, values3, queries3);
        System.out.println(Arrays.toString(results3)); // [0.5, 2.0, -1.0, -1.0]
    }

}

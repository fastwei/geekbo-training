package com.geekbo.training.sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 解题思路：
 * <p>
 * 构建有向图的邻接表表示，并记录每个节点的入度。
 * 使用BFS进行拓扑排序，从入度为0的节点开始，依次将节点加入拓扑排序结果，并将邻接节点的入度减一。
 * 如果拓扑排序结果的节点个数与课程总数相等，则表示拓扑排序成功，否则表示存在环，拓扑排序不可能完成。
 * 算法复杂度：
 * <p>
 * 构建邻接表和入度数组的时间复杂度为O(E)，其中E为先修关系的数量。
 * BFS的时间复杂度为O(V+E)，其中V为课程总数，E为先修关系的数量。
 * <p>
 * 如果拓扑排序成功，遍历结果列表的时间复杂度为O(V)，其中V为课程总数。
 * 因此，总的时间复杂度为O(V+E)。
 * <p>
 * 算法的空间复杂度为O(V)，其中V为课程总数。主要用于存储邻接表、入度数组、结果列表和队列。
 * <p>
 * 我们使用了一个示例测试用例：
 * <p>
 * int numCourses = 4;
 * int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
 * List<Integer> result = topologicalSort.topologicalSort(numCourses, prerequisites);
 * System.out.println(result); // 预期输出：[0, 1, 2, 3]
 * 这个测试用例中有4门课程，先修关系为1依赖于0，2也依赖于0，3依赖于1和2。
 * 预期的拓扑排序结果是[0, 1, 2, 3]，即先修关系满足的顺序。
 */
public class TopologicalSort {
    /**
     * 拓扑排序
     *
     * @param numCourses    课程总数
     * @param prerequisites 课程先修关系的二维数组
     * @return 拓扑排序结果，如果不存在拓扑排序则返回空列表
     */
    public List<Integer> topologicalSort(int numCourses, int[][] prerequisites) {
        // 构建邻接表表示的有向图
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        int[] inDegree = new int[numCourses]; // 记录每个节点的入度
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];
            graph.get(prerequisiteCourse).add(course); // 添加依赖关系到邻接表
            inDegree[course]++; // 入度加一
        }

        // 使用BFS进行拓扑排序
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i); // 将入度为0的节点入队
            }
        }
        while (!queue.isEmpty()) {
            int course = queue.poll();
            result.add(course); // 将节点加入拓扑排序结果
            for (int neighbor : graph.get(course)) {
                inDegree[neighbor]--; // 将邻接节点的入度减一
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor); // 若邻接节点的入度为0，则入队
                }
            }
        }

        if (result.size() != numCourses) {
            result.clear(); // 若存在环，则拓扑排序不可能完成，返回空列表
        }
        return result;
    }

    public static void main(String[] args) {
        TopologicalSort topologicalSort = new TopologicalSort();
        int numCourses = 4;
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        List<Integer> result = topologicalSort.topologicalSort(numCourses, prerequisites);
        System.out.println(result); // 预期输出：[0, 1, 2, 3]
    }
}


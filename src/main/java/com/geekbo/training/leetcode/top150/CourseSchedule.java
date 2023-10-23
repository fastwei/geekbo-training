package com.geekbo.training.leetcode.top150;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi]
 * indicates that you must take course bi first if you want to take course ai.
 * <p>
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0,
 * and to take course 0 you should also have finished course 1.
 * So it is impossible.
 */
public class CourseSchedule {

    /**
     * 解题思路：
     * <p>
     * 首先，根据给定的课程依赖关系构建邻接表，表示课程之间的依赖关系。
     * 通过 DFS 判断是否存在环，如果存在环则无法完成所有课程，返回 false。
     * 如果不存在环，则可以完成所有课程，返回 true。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：假设有 n 门课程和 m 个课程依赖关系。
     * 构建邻接表的时间复杂度为 O(m)。
     * 使用 DFS 判断是否存在环的时间复杂度为 O(n+m)。 综上，算法的总时间复杂度为 O(n+m)。
     * 空间复杂度：算法使用了邻接表和 visited 数组来存储数据，空间复杂度为 O(n+m)。
     * <p>
     * <p>
     * 优化思路：
     * <p>
     * 使用 memo 数组进行记忆化搜索，避免重复搜索已经搜索过的课程。
     * 在每次搜索开始时，先判断当前课程是否已经搜索过，如果是，则直接返回结果。
     * 算法复杂度分析不变。
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // 创建邻接表，用于表示课程之间的依赖关系
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // 将依赖关系转化为邻接表
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];
            adjacencyList.get(course).add(prerequisiteCourse);
        }

        // 创建 visited 数组，用于判断课程是否已经访问过
        boolean[] visited = new boolean[numCourses];
        // 创建 memo 数组，用于记忆化搜索
        boolean[] memo = new boolean[numCourses];

        // 使用 DFS 判断是否存在环
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, adjacencyList, visited, memo)) {
                return false;
            }
        }

        return true;
    }

    private static boolean dfs(int course, List<List<Integer>> adjacencyList, boolean[] visited, boolean[] memo) {
        // 如果当前课程已经访问过，说明存在环，直接返回 false
        if (visited[course]) {
            return false;
        }

        // 如果当前课程已经搜索过，直接返回结果
        if (memo[course]) {
            return true;
        }

        // 将当前课程标记为已访问
        visited[course] = true;

        // 遍历当前课程的所有依赖课程
        for (int prerequisiteCourse : adjacencyList.get(course)) {
            // 递归判断依赖课程是否存在环
            if (!dfs(prerequisiteCourse, adjacencyList, visited, memo)) {
                return false;
            }
        }

        // 将当前课程标记为未访问，进入下一个课程的判断
        visited[course] = false;
        // 标记当前课程的搜索结果
        memo[course] = true;

        return true;
    }

    public static boolean canFinishBfs(int numCourses, int[][] prerequisites) {
        // 创建邻接表，用于表示课程之间的依赖关系
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // 创建入度数组，用于记录每门课程的入度
        int[] inDegree = new int[numCourses];

        // 将依赖关系转化为邻接表和入度数组
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];
            adjacencyList.get(prerequisiteCourse).add(course);
            inDegree[course]++;
        }

        // 创建队列，用于进行拓扑排序
        Queue<Integer> queue = new LinkedList<>();

        // 将入度为 0 的课程加入队列
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 进行拓扑排序
        while (!queue.isEmpty()) {
            int course = queue.poll();
            numCourses--;

            // 遍历当前课程的所有后续课程
            for (int nextCourse : adjacencyList.get(course)) {
                // 将后续课程的入度减 1
                inDegree[nextCourse]--;

                // 如果入度为 0，则加入队列
                if (inDegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }

        // 如果所有课程都完成了，则返回 true
        return numCourses == 0;
    }


    public static void main(String[] args) {
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        // 预期输出: true
        System.out.println(canFinish(numCourses1, prerequisites1));

        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        // 预期输出: false
        System.out.println(canFinish(numCourses2, prerequisites2));
    }
}

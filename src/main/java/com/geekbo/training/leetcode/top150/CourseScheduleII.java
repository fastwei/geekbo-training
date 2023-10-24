package com.geekbo.training.leetcode.top150;

import java.util.*;


/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates
 * that you must take course bi first if you want to take course ai.
 * <p>
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses.
 * If there are many valid answers, return any of them.
 * If it is impossible to finish all courses, return an empty array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So the correct course order is [0,1].
 * Example 2:
 * <p>
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take.
 * To take course 3 you should have finished both courses 1 and 2.
 * Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 * Example 3:
 * <p>
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 */
public class CourseScheduleII {
    /**
     * 解题思路：
     * <p>
     * 首先，根据给定的课程依赖关系构建邻接表，表示课程之间的依赖关系。
     * 通过拓扑排序判断是否存在环，如果存在环则无法完成所有课程，返回一个空数组。
     * 如果不存在环，则可以完成所有课程，返回一个合法的课程顺序数组。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：假设有 n 门课程和 m 个课程依赖关系。
     * 构建邻接表的时间复杂度为 O(m)。
     * 使用拓扑排序判断是否存在环的时间复杂度为 O(n+m)。
     * 综上，算法的总时间复杂度为 O(n+m)。
     * <p>
     * 空间复杂度：算法使用了邻接表和入度数组来存储数据，空间复杂度为 O(n+m)。
     *
     * @param numCourses    课程总数
     * @param prerequisites 课程依赖关系数组
     * @return 课程顺序数组
     */
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
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

        // 创建结果数组
        int[] result = new int[numCourses];
        int index = 0;

        // 进行拓扑排序
        while (!queue.isEmpty()) {
            int course = queue.poll();
            result[index++] = course;
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

        // 如果所有课程都完成了，则返回结果数组
        return numCourses == 0 ? result : new int[0];
    }


    public static void main(String[] args) {
        // 测试用例1
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        int[] result1 = findOrder(numCourses1, prerequisites1);
        System.out.println(Arrays.toString(result1)); // [0, 1]

        // 测试用例2
        int numCourses2 = 4;
        int[][] prerequisites2 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int[] result2 = findOrder(numCourses2, prerequisites2);
        System.out.println(Arrays.toString(result2)); // [0, 2, 1, 3]

        // 测试用例3
        int numCourses3 = 1;
        int[][] prerequisites3 = {};
        int[] result3 = findOrder(numCourses3, prerequisites3);
        System.out.println(Arrays.toString(result3)); // [0]
    }
}
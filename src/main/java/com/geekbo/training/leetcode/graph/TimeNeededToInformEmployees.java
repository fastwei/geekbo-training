package com.geekbo.training.leetcode.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1376. Time Needed to Inform All Employees
 * A company has n employees with a unique ID for each employee from 0 to n - 1.
 * The head of the company is the one with headID.
 * <p>
 * Each employee has one direct manager given in the manager array where manager[i]
 * is the direct manager of the i-th employee, manager[headID] = -1.
 * Also, it is guaranteed that the subordination relationships have a tree structure.
 * <p>
 * The head of the company wants to inform all the company employees of an urgent piece of news.
 * He will inform his direct subordinates, and they will inform their subordinates, and so on until all employees know about the urgent news.
 * <p>
 * The i-th employee needs informTime[i] minutes to inform all of his direct subordinates
 * (i.e., After informTime[i] minutes, all his direct subordinates can start spreading the news).
 * <p>
 * Return the number of minutes needed to inform all the employees about the urgent news.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 1, headID = 0, manager = [-1], informTime = [0]
 * Output: 0
 * Explanation: The head of the company is the only employee in the company.
 * Example 2:
 * <p>
 * <p>
 * Input: n = 6, headID = 2, manager = [2,2,-1,2,2,2], informTime = [0,0,1,0,0,0]
 * Output: 1
 * Explanation: The head of the company with id = 2 is the direct manager of
 * all the employees in the company and needs 1 minute to inform them all.
 * The tree structure of the employees in the company is shown.
 */
public class TimeNeededToInformEmployees {
    /**
     * Return the number of minutes needed to inform all employees about the urgent news.
     *
     * @param n          The number of employees.
     * @param headID     The ID of the head of the company.
     * @param manager    The array of direct managers for each employee.
     * @param informTime The array of inform time for each employee.
     * @return The number of minutes needed to inform all employees.
     */
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        // Create a list to store the subordinates for each employee
        List<List<Integer>> subordinates = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            subordinates.add(new ArrayList<>());
        }

        // Populate the list of subordinates for each employee
        for (int i = 0; i < n; i++) {
            if (manager[i] != -1) {
                subordinates.get(manager[i]).add(i);
            }
        }

        // Perform DFS starting from the head of the company
        return dfs(subordinates, informTime, headID);
    }

    /**
     * Recursive method to perform DFS starting from a specific employee and calculate the time needed to inform all employees.
     *
     * @param subordinates The list of subordinates for each employee.
     * @param informTime   The array of inform time for each employee.
     * @param employee     The ID of the current employee.
     * @return The time needed to inform all employees starting from the current employee.
     */
    private int dfs(List<List<Integer>> subordinates, int[] informTime, int employee) {
        int maxTime = 0;

        // Calculate the time needed for each subordinate and find the maximum time
        for (int subordinate : subordinates.get(employee)) {
            maxTime = Math.max(maxTime, dfs(subordinates, informTime, subordinate));
        }

        // Return the time needed for the current employee plus the maximum time of the subordinates
        return informTime[employee] + maxTime;
    }

    /**
     * Method to perform BFS starting from a specific employee and calculate the time needed to inform all employees.
     *
     * @param subordinates The list of subordinates for each employee.
     * @param informTime   The array of inform time for each employee.
     * @param employee     The ID of the current employee.
     * @return The time needed to inform all employees starting from the current employee.
     */
    private int bfs(List<List<Integer>> subordinates, int[] informTime, int employee) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(employee);

        int maxTime = 0;

        while (!queue.isEmpty()) {
            int currentEmployee = queue.poll();

            // Calculate the time needed for each subordinate and update the maximum time
            for (int subordinate : subordinates.get(currentEmployee)) {
                queue.offer(subordinate);
                maxTime = Math.max(maxTime, informTime[currentEmployee] + informTime[subordinate]);
            }
        }

        // Return the maximum time needed to inform all employees
        return maxTime;
    }

    public static void main(String[] args) {
        TimeNeededToInformEmployees solution = new TimeNeededToInformEmployees();

        // Test case 1
        int n1 = 1, headID1 = 0;
        int[] manager1 = {-1}, informTime1 = {0};
        int result1 = solution.numOfMinutes(n1, headID1, manager1, informTime1);
        System.out.println("Test Case 1:");
        System.out.println(result1); // Expected: 0

        // Test case 2
        int n2 = 6, headID2 = 2;
        int[] manager2 = {2, 2, -1, 2, 2, 2}, informTime2 = {0, 0, 1, 0, 0, 0};
        int result2 = solution.numOfMinutes(n2, headID2, manager2, informTime2);
        System.out.println("Test Case 2:");
        System.out.println(result2); // Expected: 1
    }
}

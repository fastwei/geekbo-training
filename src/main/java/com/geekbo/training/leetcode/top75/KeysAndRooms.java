package com.geekbo.training.leetcode.top75;

import java.util.*;

public class KeysAndRooms {

    /**
     * 解题思路：
     * <p>
     * 使用深度优先搜索（DFS）来模拟访问房间的过程。
     * 从房间0开始，使用栈来管理已访问和待访问的房间。
     * 对于每个房间，将其中的钥匙对应的房间加入栈中，并标记为已访问。
     * 继续遍历栈中的房间，直到栈为空。
     * 最后检查是否所有房间都被访问，如果是则返回true，否则返回false。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(N + E)，其中N为房间数，E为钥匙的总数。
     * 空间复杂度：O(N)，用于存储已访问房间的布尔数组。
     *
     * @param rooms
     * @return
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];

        // 使用栈来进行深度优先搜索
        Stack<Integer> stack = new Stack<>();

        // 初始时，我们位于房间0
        stack.push(0);
        visited[0] = true;

        // 开始深度优先搜索
        while (!stack.isEmpty()) {
            int currentRoom = stack.pop();
            for (int key : rooms.get(currentRoom)) {
                // 对于每个房间中的钥匙，如果该房间还未访问过，将其加入栈中
                if (!visited[key]) {
                    stack.push(key);
                    visited[key] = true;
                }
            }
        }

        // 检查是否所有房间都被访问
        for (boolean roomVisited : visited) {
            if (!roomVisited) {
                return false;
            }
        }

        return true;
    }

    /**
     * 解题思路：
     * <p>
     * 使用HashSet跟踪已访问的房间，使用Queue（队列）来广度优先搜索。
     * 从房间0开始，将其标记为已访问并添加到队列中。
     * 遍历队列，对于每个房间，获取其中的钥匙并将未访问的房间添加到队列中。
     * 最后，检查已访问的房间数量是否等于总房间数量，如果是，则返回true，否则返回false。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(N + E)，其中N是房间数量，E是所有钥匙的数量。
     * 空间复杂度：O(N)，用于存储已访问的房间。
     *
     * @param rooms
     * @return
     */
    public boolean canVisitAllRoomsBFS(List<List<Integer>> rooms) {
        int numRooms = rooms.size();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        // Start with room 0
        visited.add(0);
        queue.offer(0);

        while (!queue.isEmpty()) {
            int currentRoom = queue.poll();
            for (int key : rooms.get(currentRoom)) {
                if (!visited.contains(key)) {
                    visited.add(key);
                    queue.offer(key);
                }
            }
        }

        // If we visited all rooms, return true
        return visited.size() == numRooms;
    }

    public static void main(String[] args) {
        KeysAndRooms solution = new KeysAndRooms();

        List<List<Integer>> rooms1 = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3),
                new ArrayList<>()
        );
        System.out.println(solution.canVisitAllRooms(rooms1)); // 输出：true
        System.out.println(solution.canVisitAllRoomsBFS(rooms1)); // 输出：true

        List<List<Integer>> rooms2 = Arrays.asList(
                Arrays.asList(1, 3),
                Arrays.asList(3, 0, 1),
                Arrays.asList(2),
                Arrays.asList(0)
        );
        System.out.println(solution.canVisitAllRooms(rooms2)); // 输出：false
        System.out.println(solution.canVisitAllRoomsBFS(rooms2)); // 输出：false
    }
}

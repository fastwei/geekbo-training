package com.geekbo.training.leetcode.template;

import java.util.LinkedList;
import java.util.Queue;

/**
 * bfs函数实现了广度优先搜索的逻辑。
 * 它接受两个参数：start是起始节点，graph是图的邻接矩阵表示。
 * 在函数中，我们首先创建一个队列和一个数组，分别用于存储待访问的节点和记录节点是否已经访问过。
 * 然后将起始节点加入队列，并标记为已访问。接下来，我们遍历队列，每次从队列中取出一个节点，并处理该节点。
 * 然后遍历当前节点的邻接节点，如果邻接节点未访问过，则将其加入队列，并标记为已访问。最后输出结果。
 * <p>
 * 在main函数中，我们创建了一个BFSTemplate对象，并定义了一个图的邻接矩阵表示。
 * 然后调用bfs函数进行广度优先搜索。最后输出结果。
 */
public class BFSTemplate {

    public void bfs(int start, int[][] graph) {
        int n = graph.length; // 图的节点个数

        // 创建一个队列用于存储待访问的节点
        Queue<Integer> queue = new LinkedList<>();

        // 创建一个数组用于记录节点是否已经访问过
        boolean[] visited = new boolean[n];

        // 将起始节点加入队列，并标记为已访问
        queue.offer(start);
        visited[start] = true;

        // 遍历队列，直到队列为空
        while (!queue.isEmpty()) {
            int node = queue.poll();

            // 处理当前节点
            System.out.println("Visiting node: " + node);

            // 遍历当前节点的邻接节点
            for (int neighbor : graph[node]) {
                // 如果邻接节点未访问过，则将其加入队列，并标记为已访问
                if (!visited[neighbor]) {
                    queue.offer(neighbor);
                    visited[neighbor] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        BFSTemplate template = new BFSTemplate();

        // 定义一个图的邻接矩阵表示
        int[][] graph = {
                {1, 2},     // 节点0的邻接节点为1和2
                {0, 3, 4},  // 节点1的邻接节点为0、3和4
                {0, 5},     // 节点2的邻接节点为0和5
                {1},        // 节点3的邻接节点为1
                {1},        // 节点4的邻接节点为1
                {2}         // 节点5的邻接节点为2
        };

        int startNode = 0;
        template.bfs(startNode, graph);
    }
}
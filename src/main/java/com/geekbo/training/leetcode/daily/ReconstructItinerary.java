package com.geekbo.training.leetcode.daily;

import java.util.*;

/**
 * 332. Reconstruct Itinerary
 * Hard
 * You are given a list of airline tickets where tickets[i] = [fromi, toi]
 * represent the departure and the arrival airports of one flight.
 * Reconstruct the itinerary in order and return it.
 * <p>
 * All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK".
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
 * <p>
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * You may assume all tickets form at least one valid itinerary.
 * You must use all the tickets once and only once.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * Output: ["JFK","MUC","LHR","SFO","SJC"]
 * Example 2:
 * <p>
 * <p>
 * Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.
 */
public class ReconstructItinerary {
    /**
     * 解题思路：
     * <p>
     * 首先，我们需要构建一个图，将每个起点和终点之间的航班连接起来。
     * 然后，我们对每个起点的航班列表进行排序，以确保在有多个航班可以选择时，我们选择字典序最小的航班。
     * 最后，我们从起点"JFK"开始进行深度优先搜索，逐步选择下一个目的地，直到没有可选的航班为止。
     * 在搜索过程中，我们将已经选择的目的地添加到行程中。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 构建图的时间复杂度为O(n)，其中n是航班的数量。
     * 对每个起点的航班列表进行排序的时间复杂度为O(nlogn)。
     * 进行深度优先搜索的时间复杂度为O(n)。
     * 因此，总的时间复杂度为O(nlogn)。
     * 空间复杂度为O(n)，用于存储图的数据结构和行程。
     *
     * @param tickets
     * @return
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        // Create a map to store the destinations and their corresponding list of flights
        Map<String, List<String>> graph = new HashMap<>();

        // Build the graph
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            if (!graph.containsKey(from)) {
                graph.put(from, new ArrayList<>());
            }
            graph.get(from).add(to);
        }

        // Sort the destinations in lexical order
        for (List<String> flights : graph.values()) {
            Collections.sort(flights);
        }

        // Create a list to store the itinerary
        List<String> itinerary = new ArrayList<>();

        // Perform DFS starting from "JFK"
        dfs("JFK", graph, itinerary);

        // Reverse the itinerary to get the correct order
        Collections.reverse(itinerary);

        return itinerary;
    }

    private void dfs(String from, Map<String, List<String>> graph, List<String> itinerary) {
        List<String> flights = graph.get(from);

        while (flights != null && !flights.isEmpty()) {
            String to = flights.remove(0);
            dfs(to, graph, itinerary);
        }

        itinerary.add(from);
    }

    public static void main(String[] args) {
        // Test case 1
        List<List<String>> tickets1 = new ArrayList<>();
        tickets1.add(Arrays.asList("MUC", "LHR"));
        tickets1.add(Arrays.asList("JFK", "MUC"));
        tickets1.add(Arrays.asList("SFO", "SJC"));
        tickets1.add(Arrays.asList("LHR", "SFO"));
        ReconstructItinerary solution = new ReconstructItinerary();
        List<String> itinerary1 = solution.findItinerary(tickets1);
        System.out.println(itinerary1); // Output: ["JFK","MUC","LHR","SFO","SJC"]

        // Test case 2
        List<List<String>> tickets2 = new ArrayList<>();
        tickets2.add(Arrays.asList("JFK", "SFO"));
        tickets2.add(Arrays.asList("JFK", "ATL"));
        tickets2.add(Arrays.asList("SFO", "ATL"));
        tickets2.add(Arrays.asList("ATL", "JFK"));
        tickets2.add(Arrays.asList("ATL", "SFO"));
        List<String> itinerary2 = solution.findItinerary(tickets2);
        System.out.println(itinerary2); // Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
    }
}


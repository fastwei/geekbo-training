package com.geekbo.training.leetcode.top150;

import java.util.*;

/**
 * A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
 * <p>
 * Suppose we need to investigate a mutation from a gene string startGene to a gene string endGene
 * where one mutation is defined as one single character changed in the gene string.
 * <p>
 * For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
 * There is also a gene bank bank that records all the valid gene mutations.
 * A gene must be in bank to make it a valid gene string.
 * <p>
 * Given the two gene strings startGene and endGene and the gene bank bank,
 * return the minimum number of mutations needed to mutate from startGene to endGene. If there is no such a mutation, return -1.
 * <p>
 * Note that the starting point is assumed to be valid, so it might not be included in the bank.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: startGene = "AACCGGTT", endGene = "AACCGGTA", bank = ["AACCGGTA"]
 * Output: 1
 * Example 2:
 * <p>
 * Input: startGene = "AACCGGTT", endGene = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
 * Output: 2
 */
public class MinimumGeneticMutation {
    /**
     * 解题思路：
     * <p>
     * 可以将问题转化为求解图中最短路径的问题，其中图的节点表示基因字符串，边表示基因之间的变异关系。
     * 使用广度优先搜索算法来求解最短路径。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：假设基因字符串的长度为L，基因库的长度为N。
     * 构建变异关系图的时间复杂度为O(N*L)。
     * 使用广度优先搜索算法的时间复杂度为O(N*L)。
     * 综上，算法的总时间复杂度为O(N*L)。
     * <p>
     * 空间复杂度：算法使用了队列和集合来存储数据，空间复杂度为O(N)。
     *
     * @param startGene 起始基因字符串
     * @param endGene   目标基因字符串
     * @param bank      基因库
     * @return 最小变异次数，如果无法变异则返回-1
     */
    public static int minMutation(String startGene, String endGene, String[] bank) {
        // 将基因库转化为集合，方便查找
        Set<String> geneBank = new HashSet<>(Arrays.asList(bank));

        // 如果目标基因字符串不在基因库中，则无法变异，返回-1
        if (!geneBank.contains(endGene)) {
            return -1;
        }

        // 定义基因变异的4个方向
        char[] directions = {'A', 'C', 'G', 'T'};

        // 创建队列，用于进行广度优先搜索
        Queue<String> queue = new LinkedList<>();
        queue.offer(startGene);

        // 创建集合，用于记录已经访问过的基因字符串
        Set<String> visited = new HashSet<>();
        visited.add(startGene);

        // 创建变异次数变量
        int mutationCount = 0;

        // 进行广度优先搜索
        while (!queue.isEmpty()) {
            // 遍历当前层的所有基因字符串
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currentGene = queue.poll();

                // 如果当前基因字符串等于目标基因字符串，返回变异次数
                if (currentGene.equals(endGene)) {
                    return mutationCount;
                }

                // 遍历当前基因字符串的每个位置
                char[] geneArray = currentGene.toCharArray();
                for (int j = 0; j < geneArray.length; j++) {
                    char originalChar = geneArray[j];

                    // 尝试将当前位置的字符变异为其他字符
                    for (char direction : directions) {
                        geneArray[j] = direction;
                        String newGene = new String(geneArray);

                        // 如果变异后的基因字符串存在于基因库中，并且没有被访问过，则加入队列进行下一轮搜索
                        if (geneBank.contains(newGene) && !visited.contains(newGene)) {
                            queue.offer(newGene);
                            visited.add(newGene);
                        }
                    }

                    // 恢复当前位置的字符
                    geneArray[j] = originalChar;
                }
            }

            // 增加变异次数
            mutationCount++;
        }

        // 如果无法变异到目标基因字符串，返回-1
        return -1;
    }

    public static void main(String[] args) {
        // 测试用例1
        String startGene1 = "AACCGGTT";
        String endGene1 = "AACCGGTA";
        String[] bank1 = {"AACCGGTA"};
        int expected1 = 1;
        int result1 = minMutation(startGene1, endGene1, bank1);
        System.out.println(result1 == expected1 ? "Test case 1 passed" : "Test case 1 failed");

        // 测试用例2
        String startGene2 = "AACCGGTT";
        String endGene2 = "AAACGGTA";
        String[] bank2 = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
        int expected2 = 2;
        int result2 = minMutation(startGene2, endGene2, bank2);
        System.out.println(result2 == expected2 ? "Test case 2 passed" : "Test case 2 failed");
    }
}
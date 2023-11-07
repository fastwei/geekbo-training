package com.geekbo.training.leetcode.graph;

/**
 * 997. Find the Town Judge
 * <p>
 * In a town, there are n people labeled from 1 to n.
 * There is a rumor that one of these people is secretly the town judge.
 * <p>
 * If the town judge exists, then:
 * <p>
 * The town judge trusts nobody.
 * Everybody (except for the town judge) trusts the town judge.
 * There is exactly one person that satisfies properties 1 and 2.
 * You are given an array trust where trust[i] = [ai, bi] representing that the person labeled ai trusts the person labeled bi.
 * If a trust relationship does not exist in trust array, then such a trust relationship does not exist.
 * <p>
 * Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2, trust = [[1,2]]
 * Output: 2
 * Example 2:
 * <p>
 * Input: n = 3, trust = [[1,3],[2,3]]
 * Output: 3
 * Example 3:
 * <p>
 * Input: n = 3, trust = [[1,3],[2,3],[3,1]]
 * Output: -1
 */
public class FindTheTownJudge {
    /**
     * 寻找小镇法官的标签。
     * 解题思路：
     * <p>
     * 使用一个数组trustCounts来记录每个人被信任的次数。
     * 遍历信任关系数组trust，对于每个关系[ai, bi]，将ai的信任次数减一，将bi的信任次数加一。
     * 遍历trustCounts数组，找到被信任次数为n-1的人，即为小镇法官。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历信任关系数组和trustCounts数组都需要O(E)，其中E是信任关系的数量。
     *
     * @param n     小镇上的人数。
     * @param trust 信任关系数组。
     * @return 小镇法官的标签，如果不存在则返回-1。
     */
    public int findJudge(int n, int[][] trust) {
        // 创建一个数组用于记录每个人被信任的次数
        int[] trustCounts = new int[n + 1];

        // 遍历信任关系数组，统计每个人被信任的次数
        for (int[] relation : trust) {
            int personA = relation[0];
            int personB = relation[1];
            trustCounts[personA]--; // 信任别人的次数减一
            trustCounts[personB]++; // 被信任的次数加一
        }

        // 遍历每个人被信任的次数数组，找到被信任次数为n-1的人
        for (int i = 1; i <= n; i++) {
            if (trustCounts[i] == n - 1) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        FindTheTownJudge solution = new FindTheTownJudge();

        // 测试用例1
        int n1 = 2;
        int[][] trust1 = {{1, 2}};
        int expected1 = 2;
        int result1 = solution.findJudge(n1, trust1);
        System.out.println("测试用例1:");
        System.out.println("预期输出: " + expected1);
        System.out.println("实际输出: " + result1);

        // 测试用例2
        int n2 = 3;
        int[][] trust2 = {{1, 3}, {2, 3}};
        int expected2 = 3;
        int result2 = solution.findJudge(n2, trust2);
        System.out.println("测试用例2:");
        System.out.println("预期输出: " + expected2);
        System.out.println("实际输出: " + result2);

        // 测试用例3
        int n3 = 3;
        int[][] trust3 = {{1, 3}, {2, 3}, {3, 1}};
        int expected3 = -1;
        int result3 = solution.findJudge(n3, trust3);
        System.out.println("测试用例3:");
        System.out.println("预期输出: " + expected3);
        System.out.println("实际输出: " + result3);
    }
}

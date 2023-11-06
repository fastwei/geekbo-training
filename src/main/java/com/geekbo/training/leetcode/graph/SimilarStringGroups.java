package com.geekbo.training.leetcode.graph;

/**
 * 839. Similar String Groups
 * Hard
 * Two strings, X and Y, are considered similar if either they are identical
 * or we can make them equivalent by swapping at most two letters (in distinct positions) within the string X.
 * <p>
 * For example, "tars" and "rats" are similar (swapping at positions 0 and 2),
 * and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".
 * <p>
 * Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.
 * Notice that "tars" and "arts" are in the same group even though they are not similar.
 * Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.
 * <p>
 * We are given a list strs of strings where every string in strs is an anagram of every other string in strs.
 * How many groups are there?
 * <p>
 * Example 1:
 * <p>
 * Input: strs = ["tars","rats","arts","star"]
 * Output: 2
 * Example 2:
 * <p>
 * Input: strs = ["omv","ovm"]
 * Output: 1
 */
public class SimilarStringGroups {
    /**
     * 计算给定字符串列表中的组数
     * 解题思路：
     * <p>
     * 我们可以使用并查集来解决这个问题。首先，我们遍历所有字符串对，如果它们是相似的，则将它们合并到同一个组中。
     * 为了判断两个字符串是否相似，我们可以比较它们的字符差异次数。如果字符差异次数不超过 2，则认为两个字符串相似。
     * 最后，我们统计并查集中不同的根节点数，即为组数。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 遍历所有字符串对的时间复杂度为O(N^2*M)，
     * 其中 N 是字符串列表的长度，M 是每个字符串的平均长度。
     * 在遍历所有字符串对时，我们需要比较每对字符串的字符差异次数，这需要 O(M)的时间复杂度。
     * 因此，总的时间复杂度为 O(N^2*M)。
     * <p>
     * 并查集的空间复杂度为 O(N)，其中 N 是字符串列表的长度。
     *
     * @param strs 字符串列表
     * @return 组数
     */
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        UnionFind uf = new UnionFind(n);

        // 遍历所有字符串对，如果它们是相似的，则将它们合并到同一个组中
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isSimilar(strs[i], strs[j])) {
                    uf.union(i, j);
                }
            }
        }

        // 统计不同的组数
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (uf.find(i) == i) {
                count++;
            }
        }

        return count;
    }

    /**
     * 判断两个字符串是否相似
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 是否相似
     */
    private boolean isSimilar(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
                if (count > 2) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 并查集类
     */
    private static class UnionFind {
        private int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
            }
        }
    }

    public static void main(String[] args) {
        // 创建解法对象
        SimilarStringGroups similarStringGroups = new SimilarStringGroups();

        // 测试用例1
        // 预期输出: 2
        String[] strs1 = {"tars", "rats", "arts", "star"};
        int result1 = similarStringGroups.numSimilarGroups(strs1);
        System.out.println(result1);

        // 测试用例2
        // 预期输出: 1
        String[] strs2 = {"omv", "ovm"};
        int result2 = similarStringGroups.numSimilarGroups(strs2);
        System.out.println(result2);
    }
}

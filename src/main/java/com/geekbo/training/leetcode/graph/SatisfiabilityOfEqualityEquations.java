package com.geekbo.training.leetcode.graph;

import java.util.HashMap;
import java.util.Map;

public class SatisfiabilityOfEqualityEquations {
    /**
     * 判断是否满足等式方程
     * 解题思路：
     * <p>
     * 首先，使用并查集来判断变量之间的关系。
     * 先处理所有的等式，将相等的变量合并到一个集合中。
     * 再处理所有的不等式，查看两个变量是否在同一个集合中，如果在同一个集合中，则说明存在矛盾，返回false；
     * 如果不在同一个集合中，则继续判断下一个不等式。
     * 如果所有的不等式都判断完成后，返回true。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 遍历所有等式，时间复杂度为O(n)，其中n为等式的数量。
     * 并查集的操作的时间复杂度为O(α(n))，其中α(n)为阿克曼函数的反函数，在实际情况下可以认为是一个较小的常数。
     * 因此，总的时间复杂度为O(n * α(n))。
     * 空间复杂度分析：
     * <p>
     * 使用了一个哈希表来存储并查集的信息，空间复杂度为O(n)，其中n为变量的数量。
     * <p>
     * <p>
     * 请注意，由于题目要求的是判断是否满足等式方程，因此我没有对变量进行实际的赋值操作，只是使用了并查集来判断变量之间的关系。
     * 如果需要具体的变量赋值，可以在并查集的实现中进行相应的修改。
     *
     * @param equations 等式方程数组
     * @return 是否满足
     */
    public static boolean equationsPossible(String[] equations) {
        // 使用并查集来判断变量之间的关系
        UnionFind uf = new UnionFind();
        // 先处理所有的等式，将相等的变量合并到一个集合中
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                char x = equation.charAt(0);
                char y = equation.charAt(3);
                uf.union(x, y);
            }
        }
        // 再处理所有的不等式，查看两个变量是否在同一个集合中
        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                char x = equation.charAt(0);
                char y = equation.charAt(3);
                if (uf.isConnected(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 并查集类
     */
    static class UnionFind {
        private Map<Character, Character> parent;

        public UnionFind() {
            parent = new HashMap<>();
        }

        /**
         * 查找变量的根节点
         *
         * @param x 变量
         * @return 根节点
         */
        public char find(char x) {
            if (!parent.containsKey(x)) {
                parent.put(x, x);
            }
            if (x != parent.get(x)) {
                parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);
        }

        /**
         * 合并两个变量所在的集合
         *
         * @param x 变量1
         * @param y 变量2
         */
        public void union(char x, char y) {
            char rootX = find(x);
            char rootY = find(y);
            if (rootX != rootY) {
                parent.put(rootX, rootY);
            }
        }

        /**
         * 判断两个变量是否在同一个集合中
         *
         * @param x 变量1
         * @param y 变量2
         * @return 是否在同一个集合中
         */
        public boolean isConnected(char x, char y) {
            return find(x) == find(y);
        }
    }

    public static void main(String[] args) {
        // 测试用例1
        String[] equations1 = {"a==b", "b!=a"};
        // 预期输出：false
        System.out.println(equationsPossible(equations1));

        // 测试用例2
        String[] equations2 = {"b==a", "a==b"};
        // 预期输出：true
        System.out.println(equationsPossible(equations2));
    }
}

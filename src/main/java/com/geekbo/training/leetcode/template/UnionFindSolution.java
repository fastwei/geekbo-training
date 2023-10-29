package com.geekbo.training.leetcode.template;

public class UnionFindSolution {
    // 定义并查集数据结构
    private int[] parent;
    
    public UnionFindSolution(int n) {
        // 初始化并查集，每个元素独立成集合
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }
    
    public int find(int x) {
        // 查找元素所属的集合（根节点）
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    public void union(int x, int y) {
        // 合并两个元素所属的集合
        int rootX = find(x);
        int rootY = find(y);
        parent[rootX] = rootY;
    }
    
    public boolean isConnected(int x, int y) {
        // 判断两个元素是否属于同一个集合
        return find(x) == find(y);
    }
}
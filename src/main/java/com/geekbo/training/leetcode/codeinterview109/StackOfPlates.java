package com.geekbo.training.leetcode.codeinterview109;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class StackOfPlates {
    int capacity;// 每堆盘子的容量
    List<Deque<Integer>> allPlates; // 盘子堆

    // 初始化容量及盘子堆
    public StackOfPlates(int cap) {
        this.capacity = cap;
        allPlates = new LinkedList<>();
    }
    
    public void push(int val) {
        if(capacity <= 0){// 容量为0，不需要继续放盘子
            return;
        }
        // 没有盘子堆 或者 最后一堆的数量等于容量时，需要新开一堆盘子
        if(allPlates.isEmpty() || allPlates.get(allPlates.size() - 1).size() == capacity){
            allPlates.add(new ArrayDeque<>());
        }
        allPlates.get(allPlates.size()-1).offerFirst(val);// 放最后
    }
    
    public int pop() {
        // 没有盘子了 返回-1
        if(allPlates.size() == 0){
            return -1;
        }
        // 从最后一堆盘子顶上拿一个盘子
        int result = allPlates.get(allPlates.size() - 1).pollFirst();
        // 拿完之后，如果空了，就把这堆删掉
        if(allPlates.get(allPlates.size() - 1).size() == 0){
            allPlates.remove(allPlates.size() - 1);
        }
        return result; 
    }
    
    public int popAt(int index) {
        // 如果索引不合法，并且没有盘子了，就返回-1
        if(index < 0 || index >= allPlates.size() || allPlates.isEmpty()){
            return -1;
        }
        int result = allPlates.get(index).pollFirst();
        if(allPlates.get(index).size() == 0){
            allPlates.remove(index);
        }
        return result;
    }
}

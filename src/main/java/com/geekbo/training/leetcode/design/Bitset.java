package com.geekbo.training.leetcode.design;

import java.util.HashSet;
import java.util.Set;

class Bitset {
    private int[] bits;

    // 初始化Bitset，将所有位设为0
    public Bitset(int size) {
        bits = new int[size];
    }

    // 将指定索引位置的位设为1，如果已经是1则不做改变
    public void fix(int idx) {
        bits[idx] = 1;
    }

    // 将指定索引位置的位设为0，如果已经是0则不做改变
    public void unfix(int idx) {
        bits[idx] = 0;
    }

    // 翻转所有位的值，0变为1，1变为0
    public void flip() {
        for (int i = 0; i < bits.length; i++) {
            bits[i] = 1 - bits[i];
        }
    }

    // 检查所有位是否都为1，如果是返回true，否则返回false
    public boolean all() {
        for (int bit : bits) {
            if (bit != 1) {
                return false;
            }
        }
        return true;
    }

    // 检查是否至少有一个位的值为1，如果有返回true，否则返回false
    public boolean one() {
        for (int bit : bits) {
            if (bit == 1) {
                return true;
            }
        }
        return false;
    }

    // 返回值为1的位的总数
    public int count() {
        int count = 0;
        for (int bit : bits) {
            if (bit == 1) {
                count++;
            }
        }
        return count;
    }

    // 返回Bitset的当前组成，字符串中的每个字符与Bitset的每个位的值对应
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int bit : bits) {
            sb.append(bit);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // 创建Bitset实例
        Bitset bitset = new Bitset(5);

        // 测试fix和unfix方法
        bitset.fix(3); // bitset = "00010"
        bitset.fix(1); // bitset = "01010"
        bitset.unfix(0); // bitset = "00101"

        // 测试flip方法
        bitset.flip(); // bitset = "11010"

        // 测试all方法
        boolean isAllOne = bitset.all(); // false

        // 测试one方法
        boolean isAtLeastOneOne = bitset.one(); // true

        // 测试count方法
        int count = bitset.count(); // 2

        // 测试toString方法
        String bitsetString = bitset.toString(); // "01010"

        // 打印测试结果
        System.out.println(isAllOne);
        System.out.println(isAtLeastOneOne);
        System.out.println(count);
        System.out.println(bitsetString);
    }
}

class Bitset2 {
    int size;
    Set<Integer> one = new HashSet<>();
    Set<Integer> zero = new HashSet<>();
    public Bitset2(int size) {
        this.size = size;
        for(int i=0;i<size;i++) zero.add(i);
    }

    public void fix(int idx) {
        one.add(idx);
        zero.remove(idx);
    }

    public void unfix(int idx) {
        one.remove(idx);
        zero.add(idx);
    }

    //swapping object's referrence is O(1)
    public void flip() {
        Set<Integer> s = one;
        one = zero;
        zero = s;
    }

    public boolean all() {
        return one.size() == size;
    }

    public boolean one() {
        return one.size()>=1;
    }

    public int count() {
        return one.size();
    }

    public String toString() {
        StringBuilder sb=  new StringBuilder();
        for(int i=0;i<size;i++) {
            if(one.contains(i)) sb.append("1");
            else if(zero.contains(i)) sb.append("0");
        }
        return sb.toString();
    }
}
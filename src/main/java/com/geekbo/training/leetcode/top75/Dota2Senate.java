package com.geekbo.training.leetcode.top75;

import java.util.LinkedList;
import java.util.Queue;


/**
 *
 *
 * 解题思路：
 *
 * 使用两个队列分别存储Radiant和Dire的索引。
 * 遍历senate字符串，将Radiant和Dire的索引分别放入相应队列中。
 * 进行循环，比较队列头部的索引值，较小的索引值代表该议员应该禁掉另一方的议员。
 * 如果Radiant议员的索引小于Dire议员的索引，Radiant禁掉Dire议员，并重新加入队列，否则Dire禁掉Radiant议员，并重新加入队列。
 * 循环结束后，判断哪一方的队列为空，即可确定胜利方。
 * 算法复杂度分析：
 *
 * 时间复杂度：O(n)，其中n是senate字符串的长度。
 * 空间复杂度：O(n)，使用了两个队列来存储议员的索引。
 *
 */
public class Dota2Senate {

    public String predictPartyVictory(String senate) {
        Queue<Integer> radiant = new LinkedList<>(); // 用队列分别存储Radiant和Dire的索引
        Queue<Integer> dire = new LinkedList<>();

        int n = senate.length();
        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R') {
                radiant.offer(i); // Radiant放入Radiant队列
            } else {
                dire.offer(i); // Dire放入Dire队列
            }
        }

        while (!radiant.isEmpty() && !dire.isEmpty()) {
            int radiantIndex = radiant.poll();
            int direIndex = dire.poll();
            if (radiantIndex < direIndex) {
                radiant.offer(radiantIndex + n); // Radiant禁掉Dire，并重新加入队列
            } else {
                dire.offer(direIndex + n); // Dire禁掉Radiant，并重新加入队列
            }
        }

        return radiant.isEmpty() ? "Dire" : "Radiant"; // 判断胜利方
    }

    public static void main(String[] args) {
        Dota2Senate dota2 = new Dota2Senate();
        System.out.println(dota2.predictPartyVictory("RD")); // 输出 "Radiant"
        System.out.println(dota2.predictPartyVictory("RDD")); // 输出 "Dire"
    }
}

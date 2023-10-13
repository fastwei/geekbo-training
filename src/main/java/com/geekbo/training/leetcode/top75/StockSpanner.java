package com.geekbo.training.leetcode.top75;

import java.util.Stack;


/**
 * Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's price for the current day.
 * <p>
 * The span of the stock's price in one day is the maximum number of consecutive days
 * (starting from that day and going backward) for which the stock price was less than or equal to the price of that day.
 * <p>
 * For example, if the prices of the stock in the last four days is [7,2,1,2] and the price of the stock today is 2,
 * then the span of today is 4 because starting from today, the price of the stock was less than or equal 2 for 4 consecutive days.
 * Also, if the prices of the stock in the last four days is [7,34,1,2] and the price of the stock today is 8,
 * then the span of today is 3 because starting from today, the price of the stock was less than or equal 8 for 3 consecutive days.
 * Implement the StockSpanner class:
 * <p>
 * StockSpanner() Initializes the object of the class.
 * int next(int price) Returns the span of the stock's price given that today's price is price.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
 * [[], [100], [80], [60], [70], [60], [75], [85]]
 * Output
 * [null, 1, 1, 1, 2, 1, 4, 6]
 * <p>
 * Explanation
 * StockSpanner stockSpanner = new StockSpanner();
 * stockSpanner.next(100); // return 1
 * stockSpanner.next(80);  // return 1
 * stockSpanner.next(60);  // return 1
 * stockSpanner.next(70);  // return 2
 * stockSpanner.next(60);  // return 1
 * stockSpanner.next(75);  // return 4,
 * because the last 4 prices (including today's price of 75) were less than or equal to today's price.
 * stockSpanner.next(85);  // return 6
 * <p>
 * <p>
 * 解题思路：
 * <p>
 * 使用两个栈，一个用于存储价格，一个用于存储对应的跨度。
 * 每次调用next方法时，比较当前价格与栈顶价格的大小关系：
 * 如果当前价格大于栈顶价格，说明跨度为1，并且需要将栈顶的价格和跨度出栈。
 * 如果当前价格小于等于栈顶价格，说明跨度需要累加，并将当前价格和跨度入栈。
 * 返回最终的跨度。
 * <p>
 * 算法复杂度：
 * <p>
 * 时间复杂度：O(1)。每个价格只入栈和出栈一次，所以时间复杂度是常数级别的。
 * 空间复杂度：O(n)，其中n是调用next方法的次数。需要使用两个栈来存储价格和跨度。
 */
public class StockSpanner {
    private Stack<Integer> prices;
    private Stack<Integer> spans;

    public StockSpanner() {
        prices = new Stack<>();
        spans = new Stack<>();
    }

    public int next(int price) {
        int span = 1;

        while (!prices.isEmpty() && prices.peek() <= price) {
            prices.pop();
            span += spans.pop();
        }

        prices.push(price);
        spans.push(span);

        return span;
    }

    public static void main(String[] args) {
        // 测试用例
        StockSpanner stockSpanner = new StockSpanner();
        System.out.println(stockSpanner.next(100)); // 1
        System.out.println(stockSpanner.next(80));  // 1
        System.out.println(stockSpanner.next(60));  // 1
        System.out.println(stockSpanner.next(70));  // 2
        System.out.println(stockSpanner.next(60));  // 1
        System.out.println(stockSpanner.next(75));  // 4
        System.out.println(stockSpanner.next(85));  // 6
    }
}
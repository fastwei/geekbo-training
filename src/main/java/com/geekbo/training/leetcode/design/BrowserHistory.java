package com.geekbo.training.leetcode.design;

import java.util.ArrayList;
import java.util.List;

/**
 * 1472. Design Browser History
 * Medium
 * Topics
 * Companies
 * Hint
 * You have a browser of one tab where you start on the homepage and you can visit another url,
 * get back in the history number of steps or move forward in the history number of steps.
 * <p>
 * Implement the BrowserHistory class:
 * <p>
 * BrowserHistory(string homepage) Initializes the object with the homepage of the browser.
 * void visit(string url) Visits url from the current page. It clears up all the forward history.
 * string back(int steps) Move steps back in history. If you can only return x steps in the history and steps > x,
 * you will return only x steps. Return the current url after moving back in history at most steps.
 * string forward(int steps) Move steps forward in history.
 * If you can only forward x steps in the history and steps > x,
 * you will forward only x steps.
 * Return the current url after forwarding in history at most steps.
 * <p>
 * <p>
 * Example:
 * <p>
 * Input:
 * ["BrowserHistory","visit","visit","visit","back","back","forward","visit","forward","back","back"]
 * [["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[1],[1],[1],["linkedin.com"],[2],[2],[7]]
 * Output:
 * [null,null,null,null,"facebook.com","google.com","facebook.com",null,"linkedin.com","google.com","leetcode.com"]
 * <p>
 * Explanation:
 * BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
 * browserHistory.visit("google.com");       // You are in "leetcode.com". Visit "google.com"
 * browserHistory.visit("facebook.com");     // You are in "google.com". Visit "facebook.com"
 * browserHistory.visit("youtube.com");      // You are in "facebook.com". Visit "youtube.com"
 * browserHistory.back(1);                   // You are in "youtube.com", move back to "facebook.com" return "facebook.com"
 * browserHistory.back(1);                   // You are in "facebook.com", move back to "google.com" return "google.com"
 * browserHistory.forward(1);                // You are in "google.com", move forward to "facebook.com" return "facebook.com"
 * browserHistory.visit("linkedin.com");     // You are in "facebook.com". Visit "linkedin.com"
 * browserHistory.forward(2);                // You are in "linkedin.com", you cannot move forward any steps.
 * browserHistory.back(2);                   // You are in "linkedin.com", move back two steps to "facebook.com" then to "google.com". return "google.com"
 * browserHistory.back(7);                   // You are in "google.com", you can move back only one step to "leetcode.com". return "leetcode.com"
 * <p>
 * 解题思路：
 * <p>
 * 使用一个列表记录浏览历史记录，使用一个变量记录当前所在的索引位置。
 * 在 visit 操作中，首先清除当前位置之后的历史记录，然后将新的 url 添加到历史记录中，并更新当前索引位置。
 * 在 back 操作中，将当前索引位置减去 steps，并返回当前位置对应的 url。注意要处理索引位置小于 0 的情况。
 * 在 forward 操作中，将当前索引位置加上 steps，并返回当前位置对应的 url。
 * 注意要处理索引位置大于等于历史记录长度的情况。
 * 算法复杂度：
 * <p>
 * visit 操作的时间复杂度为 O(1)。
 * back 操作的时间复杂度为 O(1)。
 * forward 操作的时间复杂度为 O(1)。
 */
class BrowserHistory {
    private List<String> history;
    private int currentIndex;

    public BrowserHistory(String homepage) {
        history = new ArrayList<>();
        history.add(homepage);
        currentIndex = 0;
    }

    public void visit(String url) {
        // Clear forward history
        while (history.size() > currentIndex + 1) {
            history.remove(history.size() - 1);
        }
        // Add new url to history
        history.add(url);
        currentIndex++;
    }

    public String back(int steps) {
        currentIndex = Math.max(currentIndex - steps, 0);
        return history.get(currentIndex);
    }

    public String forward(int steps) {
        currentIndex = Math.min(currentIndex + steps, history.size() - 1);
        return history.get(currentIndex);
    }

    public static void main(String[] args) {
        BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
        browserHistory.visit("google.com");
        browserHistory.visit("facebook.com");
        browserHistory.visit("youtube.com");
        System.out.println(browserHistory.back(1)); // Output: facebook.com
        System.out.println(browserHistory.back(1)); // Output: google.com
        System.out.println(browserHistory.forward(1)); // Output: facebook.com
        browserHistory.visit("linkedin.com");
        System.out.println(browserHistory.forward(2)); // Output: linkedin.com
        System.out.println(browserHistory.back(2)); // Output: google.com
        System.out.println(browserHistory.back(7)); // Output: leetcode.com
    }
}
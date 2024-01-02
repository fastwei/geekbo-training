package com.geekbo.training.leetcode.classic119;

import java.util.TreeMap;

/**
 *
 * 729. My Calendar I
 * Solved
 * Medium
 * You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a double booking.
 *
 * A double booking happens when two events have some non-empty intersection (i.e., some moment is common to both events.).
 *
 * The event can be represented as a pair of integers start and end that represents a booking on the half-open interval [start, end), the range of real numbers x such that start <= x < end.
 *
 * Implement the MyCalendar class:
 *
 * MyCalendar() Initializes the calendar object.
 * boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.
 *
 *
 * Example 1:
 *
 * Input
 * ["MyCalendar", "book", "book", "book"]
 * [[], [10, 20], [15, 25], [20, 30]]
 * Output
 * [null, true, false, true]
 *
 * Explanation
 * MyCalendar myCalendar = new MyCalendar();
 * myCalendar.book(10, 20); // return True
 * myCalendar.book(15, 25); // return False, It can not be booked because time 15 is already booked by another event.
 * myCalendar.book(20, 30); // return True, The event can be booked, as the first event takes every time less than 20, but not including 20.
 *
 *
 * Constraints:
 *
 * 0 <= start < end <= 109
 * At most 1000 calls will be made to book.
 *
 * 解题思路：
 * <p>
 * 使用 TreeMap 数据结构来存储已经预定的事件，其中键表示事件的起始时间，值表示事件的结束时间。
 * 对于每个新的事件，我们需要检查是否存在与其相交的事件，即有些时间段同时被两个事件占用。
 * 为了实现这个功能，我们使用 TreeMap 的 floorKey() 和 ceilingKey() 方法来获取早于新事件的最近事件和晚于新事件的最早事件。
 * 如果早于新事件的最近事件的结束时间早于或等于新事件的起始时间，并且晚于新事件的最早事件的起始时间晚于或等于新事件的结束时间，则可以将新事件添加到日历中。
 * 如果存在相交的事件，则无法将新事件添加到日历中。
 * <p>
 * 算法复杂度分析：
 * <p>
 * 时间复杂度：每次调用 book() 方法时，需要在 TreeMap 中执行两次查找操作，其时间复杂度为 O(log n)，其中 n 是已经预定的事件的数量。
 * 因此，总体的时间复杂度为 O(log n)。
 * 空间复杂度：使用的额外空间主要是 TreeMap，其大小取决于已经预定的事件的数量，因此空间复杂度为 O(n)。
 */
class MyCalendar {
    private TreeMap<Integer, Integer> calendar;

    public MyCalendar() {
        calendar = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        // Check if there is any event that intersects with the new event
        Integer prev = calendar.floorKey(start);
        Integer next = calendar.ceilingKey(start);
        if ((prev == null || calendar.get(prev) <= start) && (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        System.out.println(myCalendar.book(10, 20)); // true
        System.out.println(myCalendar.book(15, 25)); // false
        System.out.println(myCalendar.book(20, 30)); // true
    }
}

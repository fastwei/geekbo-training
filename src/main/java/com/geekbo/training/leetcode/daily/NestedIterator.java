package com.geekbo.training.leetcode.daily;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * You are given a nested list of integers nestedList.
 * Each element is either an integer or a list whose elements may also be integers or other lists.
 * Implement an iterator to flatten it.
 * <p>
 * Implement the NestedIterator class:
 * <p>
 * NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with the nested list nestedList.
 * int next() Returns the next integer in the nested list.
 * boolean hasNext() Returns true if there are still some integers in the nested list and false otherwise.
 * Your code will be tested with the following pseudocode:
 * <p>
 * initialize iterator with nestedList
 * res = []
 * while iterator.hasNext()
 * append iterator.next() to the end of res
 * return res
 * If res matches the expected flattened list, then your code will be judged as correct.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nestedList = [[1,1],2,[1,1]]
 * Output: [1,1,2,1,1]
 * Explanation: By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,1,2,1,1].
 * Example 2:
 * <p>
 * Input: nestedList = [1,[4,[6]]]
 * Output: [1,4,6]
 * Explanation: By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,4,6].
 */
public class NestedIterator implements Iterator<Integer> {
    private List<Integer> flattenedList;
    private Iterator<Integer> iterator;

    /**
     * 解题思路： 这道题可以使用递归和迭代器来解决。
     * 我们首先定义一个NestedIterator类，它实现了Iterator接口。
     * 在构造函数中，我们使用递归将嵌套列表展平成一个一维的列表，并将展平后的列表保存在flattenedList中。
     * 然后，我们使用flattenedList的迭代器来实现next()和hasNext()方法。
     * <p>
     * 在递归函数flattenNestedList中，我们遍历nestedList中的每个元素。
     * 如果当前元素是一个整数，我们将其加入flattenedList中。
     * 如果当前元素是一个嵌套列表，我们递归调用flattenNestedList函数来处理嵌套列表。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：构造函数中的递归函数flattenNestedList的时间复杂度为O(n)，其中n是嵌套列表中的元素个数。
     * next()和hasNext()方法的时间复杂度都是O(1)。
     * <p>
     * 空间复杂度：构造函数中的递归函数flattenNestedList使用了额外的空间来保存展平后的列表，
     * 空间复杂度为O(n)，其中n是嵌套列表中的整数的个数。
     *
     * @param nestedList
     */
    public NestedIterator(List<NestedInteger> nestedList) {
        flattenedList = new ArrayList<>();
        flattenNestedList(nestedList);
        iterator = flattenedList.iterator();
    }

    @Override
    public Integer next() {
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    private void flattenNestedList(List<NestedInteger> nestedList) {
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                flattenedList.add(nestedInteger.getInteger());
            } else {
                flattenNestedList(nestedInteger.getList());
            }
        }
    }

    public static void main(String[] args) {
        // Test Case 1
        List<NestedInteger> nestedList1 = new ArrayList<>();
        nestedList1.add(new NestedInteger(Arrays.asList(new NestedInteger(1), new NestedInteger(1))));
        nestedList1.add(new NestedInteger(2));
        nestedList1.add(new NestedInteger(Arrays.asList(new NestedInteger(1), new NestedInteger(1))));
        NestedIterator iterator1 = new NestedIterator(nestedList1);
        List<Integer> result1 = new ArrayList<>();
        while (iterator1.hasNext()) {
            result1.add(iterator1.next());
        }
        System.out.println(result1);  // Expected output: [1, 1, 2, 1, 1]

        // Test Case 2
        List<NestedInteger> nestedList2 = new ArrayList<>();
        nestedList2.add(new NestedInteger(1));
        nestedList2.add(new NestedInteger(Arrays.asList(new NestedInteger(4), new NestedInteger(Arrays.asList(new NestedInteger(6))))));
        NestedIterator iterator2 = new NestedIterator(nestedList2);
        List<Integer> result2 = new ArrayList<>();
        while (iterator2.hasNext()) {
            result2.add(iterator2.next());
        }
        System.out.println(result2);  // Expected output: [1, 4, 6]
    }
}

@Getter
class NestedInteger {
    private List<NestedInteger> list;
    private Integer integer;

    public NestedInteger(List<NestedInteger> list) {
        this.list = list;
    }

    public NestedInteger(Integer integer) {
        this.integer = integer;
    }

    public boolean isInteger() {
        return integer != null;
    }

}


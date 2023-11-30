package com.geekbo.training.leetcode.design;

import java.util.ArrayList;
import java.util.List;

/**
 * 1656. Design an Ordered Stream
 * Easy
 * There is a stream of n (idKey, value) pairs arriving in an arbitrary order, where idKey is an integer between 1 and n and value is a string. No two pairs have the same id.
 * <p>
 * Design a stream that returns the values in increasing order of their IDs by returning a chunk (list) of values after each insertion. The concatenation of all the chunks should result in a list of the sorted values.
 * <p>
 * Implement the OrderedStream class:
 * <p>
 * OrderedStream(int n) Constructs the stream to take n values.
 * String[] insert(int idKey, String value) Inserts the pair (idKey, value) into the stream, then returns the largest possible chunk of currently inserted values that appear next in the order.
 * <p>
 * <p>
 * Example:
 * <p>
 * <p>
 * <p>
 * Input
 * ["OrderedStream", "insert", "insert", "insert", "insert", "insert"]
 * [[5], [3, "ccccc"], [1, "aaaaa"], [2, "bbbbb"], [5, "eeeee"], [4, "ddddd"]]
 * Output
 * [null, [], ["aaaaa"], ["bbbbb", "ccccc"], [], ["ddddd", "eeeee"]]
 * <p>
 * Explanation
 * // Note that the values ordered by ID is ["aaaaa", "bbbbb", "ccccc", "ddddd", "eeeee"].
 * OrderedStream os = new OrderedStream(5);
 * os.insert(3, "ccccc"); // Inserts (3, "ccccc"), returns [].
 * os.insert(1, "aaaaa"); // Inserts (1, "aaaaa"), returns ["aaaaa"].
 * os.insert(2, "bbbbb"); // Inserts (2, "bbbbb"), returns ["bbbbb", "ccccc"].
 * os.insert(5, "eeeee"); // Inserts (5, "eeeee"), returns [].
 * os.insert(4, "ddddd"); // Inserts (4, "ddddd"), returns ["ddddd", "eeeee"].
 * // Concatentating all the chunks returned:
 * // [] + ["aaaaa"] + ["bbbbb", "ccccc"] + [] + ["ddddd", "eeeee"] = ["aaaaa", "bbbbb", "ccccc", "ddddd", "eeeee"]
 * // The resulting order is the same as the order above.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 1000
 * 1 <= id <= n
 * value.length == 5
 * value consists only of lowercase letters.
 * Each call to insert will have a unique id.
 * Exactly n calls will be made to insert.
 */
class OrderedStream {
    private String[] stream;
    private int ptr;

    /**
     * 解题思路：
     * <p>
     * 使用一个数组 stream 来存储插入的值，数组的索引表示idKey，数组的值表示value。
     * 维护一个指针 ptr，指向下一个应该返回的chunk的起始位置。
     * 在每次插入时，将对应的idKey和value插入到数组 stream 中，并返回从当前位置到 ptr 的chunk。
     * 同时更新 ptr 的位置，直到遇到一个空位置或者到达数组的末尾。
     * 算法复杂度分析：
     * <p>
     * 构造函数 OrderedStream 的时间复杂度为O(1)，空间复杂度为O(n)。
     * 插入函数 insert 的时间复杂度为O(1)（平均情况），空间复杂度为O(1)。
     * 注意：
     * <p>
     * 数组 stream 的大小为 n+1，因为idKey是从1到n的，而数组的索引是从0到n的。所以数组的大小需要为 n+1。
     * 插入函数中使用了一个 while 循环来寻找下一个chunk的起始位置。
     * 这个循环的时间复杂度是O(1)（平均情况），因为 ptr 最多会遍历整个数组一次。
     *
     * @param n
     */
    public OrderedStream(int n) {
        stream = new String[n + 1];
        ptr = 1;
    }

    public List<String> insert(int idKey, String value) {
        stream[idKey] = value;
        List<String> chunk = new ArrayList<>();

        while (ptr < stream.length && stream[ptr] != null) {
            chunk.add(stream[ptr]);
            ptr++;
        }

        return chunk;
    }

    public static void main(String[] args) {
        OrderedStream os = new OrderedStream(5);
        System.out.println(os.insert(3, "ccccc")); // []
        System.out.println(os.insert(1, "aaaaa")); // ["aaaaa"]
        System.out.println(os.insert(2, "bbbbb")); // ["bbbbb", "ccccc"]
        System.out.println(os.insert(5, "eeeee")); // []
        System.out.println(os.insert(4, "ddddd")); // ["ddddd", "eeeee"]
    }
}

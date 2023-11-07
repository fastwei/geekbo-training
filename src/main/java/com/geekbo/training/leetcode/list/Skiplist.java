package com.geekbo.training.leetcode.list;

import java.util.Random;
import java.util.Stack;

/**
 * 1206. Design Skiplist
 * Hard
 * Design a Skiplist without using any built-in libraries.
 * <p>
 * A skiplist is a data structure that takes O(log(n)) time to add, erase and search. Comparing with treap and red-black tree which has the same function and performance, the code length of Skiplist can be comparatively short and the idea behind Skiplists is just simple linked lists.
 * <p>
 * For example, we have a Skiplist containing [30,40,50,60,70,90] and we want to add 80 and 45 into it. The Skiplist works this way:
 * <p>
 * <p>
 * Artyom Kalinin [CC BY-SA 3.0], via Wikimedia Commons
 * <p>
 * You can see there are many layers in the Skiplist. Each layer is a sorted linked list. With the help of the top layers, add, erase and search can be faster than O(n). It can be proven that the average time complexity for each operation is O(log(n)) and space complexity is O(n).
 * <p>
 * See more about Skiplist: https://en.wikipedia.org/wiki/Skip_list
 * <p>
 * Implement the Skiplist class:
 * <p>
 * Skiplist() Initializes the object of the skiplist.
 * bool search(int target) Returns true if the integer target exists in the Skiplist or false otherwise.
 * void add(int num) Inserts the value num into the SkipList.
 * bool erase(int num) Removes the value num from the Skiplist and returns true. If num does not exist in the Skiplist, do nothing and return false. If there exist multiple num values, removing any one of them is fine.
 * Note that duplicates may exist in the Skiplist, your code needs to handle this situation.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["Skiplist", "add", "add", "add", "search", "add", "search", "erase", "erase", "search"]
 * [[], [1], [2], [3], [0], [4], [1], [0], [1], [1]]
 * Output
 * [null, null, null, null, false, null, true, false, true, false]
 * <p>
 * Explanation
 * Skiplist skiplist = new Skiplist();
 * skiplist.add(1);
 * skiplist.add(2);
 * skiplist.add(3);
 * skiplist.search(0); // return False
 * skiplist.add(4);
 * skiplist.search(1); // return True
 * skiplist.erase(0);  // return False, 0 is not in skiplist.
 * skiplist.erase(1);  // return True
 * skiplist.search(1); // return False, 1 has already been erased.
 * <p>
 * 解题思路：
 * -
 * 使用跳表实现 Skiplist
 * 数据结构，
 * <p>
 * 跳表能够在 O(log(n))的时间复杂度内进行插入、删除和搜索操作。
 * -跳表的每一层都是一个有序的链表，通过顶层的链表可以加速插入、删除和搜索操作，
 * <p>
 * 使时间复杂度降低到 O(log(n))。
 * -
 * 在 Skiplist
 * 类中，我们使用一个头节点来表示跳表的顶层，每个节点包含一个值和两个指针：
 * right 指针指向下一个节点，
 * down 指针指向下一层的对应节点。
 * -在搜索操作中，从顶层开始向下遍历跳表，每次往右走时找到小于等于目标值的最右节点。如果最右节点的值等于目标值，则找到了目标值；否则，继续向下一层搜索。
 * -在插入操作中，从顶层开始向下遍历跳表，找到小于等于待插入值的最右节点。然后，根据随机数决定是否要在上一层插入该节点。如果需要增加新的层，
 * 则复制节点并连接到上一层。最后，将新节点插入到当前层中。
 * -在删除操作中，从顶层开始向下遍历跳表，找到小于等于目标值的最右节点。如果最右节点的值等于目标值，则将其右指针指向下一个节点，从而删除目标值。
 * <p>
 * 算法复杂度分析：
 * -
 * <p>
 * 搜索操作的时间复杂度为 O(log(n))，其中 n 是跳表中的元素个数。
 * -
 * <p>
 * 插入操作的平均时间复杂度为 O(log(n))，因为每个元素有一半的概率在上一层插入。
 * -
 * <p>
 * 删除操作的平均时间复杂度为 O(log(n))。
 * -
 * <p>
 * 空间复杂度为 O(n)，
 * 其中 n 是跳表中的元素个数。
 * <p>
 * todo:待完成
 */
class Skiplist {
    static final double SKIP_PROBABILITY = 0.5;

    class Node {
        int val;
        Node next, down;

        public Node(int val, Node next, Node down) {
            this.val = val;
            this.next = next;
            this.down = down;
        }
    }

    Node head = new Node(-1, null, null);
    Random rand = new Random();
    int maxLevel = 0;

    public Skiplist() {

    }

    public boolean search(int target) {
        Node cur = head;
        for (int i = maxLevel; i >= 0; i--) {
            while (cur.next != null && cur.next.val < target) {
                cur = cur.next;
            }
            if (cur.next != null && cur.next.val == target) {
                return true;
            }
            cur = cur.down;
        }
        return false;
    }

    public void add(int num) {
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        for (int i = maxLevel; i >= 0; i--) {
            while (cur.next != null && cur.next.val < num) {
                cur = cur.next;
            }
            stack.push(cur);
            cur = cur.down;
        }
        boolean insert = true;
        Node down = null;
        while (insert && !stack.isEmpty()) {
            cur = stack.pop();
            cur.next = new Node(num, cur.next, down);
            down = cur.next;
            insert = rand.nextDouble() < SKIP_PROBABILITY;
        }
        if (insert) {
            head = new Node(-1, null, head);
            maxLevel++;
        }
    }

    public boolean erase(int num) {
        Node cur = head;
        boolean found = false;
        for (int i = maxLevel; i >= 0; i--) {
            while (cur.next != null && cur.next.val < num) {
                cur = cur.next;
            }
            if (cur.next != null && cur.next.val == num) {
                found = true;
                cur.next = cur.next.next;
            }
            cur = cur.down;
        }
        return found;
    }

    public static void main(String[] args) {
        Skiplist skiplist = new Skiplist();
        skiplist.add(1);
        skiplist.add(2);
        skiplist.add(3);
        System.out.println(skiplist.search(0)); // Output: false
        skiplist.add(4);
        System.out.println(skiplist.search(1)); // Output: true
        System.out.println(skiplist.erase(0));  // Output: false
        System.out.println(skiplist.erase(1));  // Output: true
        System.out.println(skiplist.search(1)); // Output: false
    }
}


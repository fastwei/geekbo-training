package com.geekbo.training.leetcode.skill;

/**
 * At a lemonade stand, each lemonade costs $5.
 * Customers are standing in a queue to buy from you and order one at a time (in the order specified by bills).
 * Each customer will only buy one lemonade and pay with either a $5, $10, or $20 bill.
 * You must provide the correct change to each customer so that the net transaction is that the customer pays $5.
 * <p>
 * Note that you do not have any change in hand at first.
 * <p>
 * Given an integer array bills where bills[i] is the bill the ith customer pays,
 * return true if you can provide every customer with the correct change, or false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: bills = [5,5,5,10,20]
 * Output: true
 * Explanation:
 * From the first 3 customers, we collect three $5 bills in order.
 * From the fourth customer, we collect a $10 bill and give back a $5.
 * From the fifth customer, we give a $10 bill and a $5 bill.
 * Since all customers got correct change, we output true.
 * Example 2:
 * <p>
 * Input: bills = [5,5,10,10,20]
 * Output: false
 * Explanation:
 * From the first two customers in order, we collect two $5 bills.
 * For the next two customers in order, we collect a $10 bill and give back a $5 bill.
 * For the last customer, we can not give the change of $15 back because we only have two $10 bills.
 * Since not every customer received the correct change, the answer is false.
 */
public class LemonadeChange {

    /**
     * 解题思路：
     * 我们可以使用贪心算法来解决这个问题。从第一个顾客开始，根据收到的钞票进行找零操作。
     * - 如果收到$5，直接加到手中的$5的数量。
     * - 如果收到$10，判断手中是否有足够的$5进行找零。如果没有，则返回false。如果有，则找零$5，并将手中的$10的数量加1。
     * - 如果收到$20，优先使用一张$10和一张$5进行找零。如果没有足够的$10和$5，则尝试找零3张$5。
     * 如果仍然无法找零，则返回false。
     * <p>
     * 算法复杂度分析：
     * - 时间复杂度：遍历bills数组需要O(n)的时间复杂度，其中n是bills数组的长度。
     * - 空间复杂度：使用了常数个变量，所以空间复杂度为O(1)。
     *
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        int five = 0; // 记录手中的$5的数量
        int ten = 0; // 记录手中的$10的数量

        for (int bill : bills) {
            if (bill == 5) {
                five++; // 如果收到$5，直接加到手中的$5的数量
            } else if (bill == 10) {
                if (five == 0) {
                    return false; // 如果手中没有$5，无法找零
                }
                five--; // 找零$5
                ten++; // 手中的$10的数量加1
            } else {
                if (five > 0 && ten > 0) {
                    five--; // 找零$5
                    ten--; // 找零$10
                } else if (five >= 3) {
                    five -= 3; // 找零3张$5
                } else {
                    return false; // 手中没有足够的$5和$10，无法找零
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        LemonadeChange lemonade = new LemonadeChange();

        // Test Case 1
        int[] bills = {5, 5, 5, 10, 20};
        // 预期输出为true
        System.out.println(lemonade.lemonadeChange(bills));

        // Test Case 2
        bills = new int[]{5, 5, 10, 10, 20};
        // 预期输出为false
        System.out.println(lemonade.lemonadeChange(bills));
    }
}


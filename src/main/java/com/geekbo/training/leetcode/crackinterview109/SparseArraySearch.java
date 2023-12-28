

package com.geekbo.training.leetcode.crackinterview109;

/**
 *
 * 面试题 10.05. 稀疏数组搜索
 * 简单
 * 稀疏数组搜索。有个排好序的字符串数组，其中散布着一些空字符串，编写一种方法，找出给定字符串的位置。
 *
 * 示例1:
 *
 *  输入: words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ta"
 *  输出：-1
 *  说明: 不存在返回-1。
 * 示例2:
 *
 *  输入：words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ball"
 *  输出：4
 * 提示:
 *
 * words的长度在[1, 1000000]之间
 */
public class SparseArraySearch {

    public static int findString(String[] words, String s) {
        int left = 0;
        int right = words.length - 1;
        int middle = 0;
        int temp = middle;
        while (left <= right) {
            // 1. 定位到不为空的左右边界
            while (left <= right && words[left].length() == 0) left++;
            while (left <= right && words[right].length() == 0) right--;
            // 边界找到之后，验证数据集是否依然有效：
            //     - 如果此时 left 已经比 right 大，则跳过下面的处理逻辑，直接返回 -1；
            //     - 如果此时 right 已经比 left 小，则跳过下面的处理逻辑，直接返回 -1；
            if (left <= right) {
                // 2. 使用二分查找，查找目标字符串
                // 防止数据溢出
                middle = left + ((right - left) >> 1);
                temp = middle;
                // 如果 middle 的值为空，则向右线性探查，直到不为空
                while (words[middle].length() == 0) middle++;
                if (words[middle].compareTo(s) > 0) right = temp - 1;
                else if (words[middle].compareTo(s) < 0) left = middle + 1;
                else return middle;
            }
        }
        return -1;
    }
    /**
     * 稀疏数组搜索
     * 由于字符串数组是排好序的，我们可以使用二分查找的方法来找到给定字符串的位置。
     * <p>
     * 首先，我们定义一个左指针left和一个右指针right，分别指向数组的起始位置和末尾位置。然后，我们不断地对数组进行二分查找，直到找到给定字符串或者左指针超过右指针。
     * <p>
     * 在每一次二分查找中，我们先找到中间位置mid，然后判断该位置的字符串是否为空。如果为空，我们需要向左或者向右继续查找，直到找到一个非空的字符串为止。
     * <p>
     * 如果中间位置的字符串与给定字符串相等，那么我们就找到了目标字符串的位置，返回mid。
     * <p>
     * 如果中间位置的字符串大于给定字符串，那么目标字符串可能在左半部分，我们更新右指针为mid-1。
     * <p>
     * 如果中间位置的字符串小于给定字符串，那么目标字符串可能在右半部分，我们更新左指针为mid+1。
     * <p>
     * 如果左指针超过了右指针，说明目标字符串不存在于数组中，返回-1。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(log n)，其中n是字符串数组的长度。每一次二分查找都将搜索范围缩小一半。
     * 空间复杂度：O(1)，只使用了常数级别的额外空间。
     * todo:还有一些测试用例没有通过
     * @param words 字符串数组，长度为n
     * @param s     给定字符串
     * @return 目标字符串的位置，不存在返回-1
     * 时间复杂度：O(log n)
     * 空间复杂度：O(1)
     */
    public static int findString2(String[] words, String s) {
        int left = 0;
        int right = words.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // 找到第一个非空字符串
            while (mid < right && words[mid].isEmpty()) {
                mid++;
            }

            if (words[mid].equals(s)) {
                return mid;
            } else if (words[mid].compareTo(s) > 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        String[] words = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        String s = "ta";
        int expected = -1;
        int result = findString(words, s);
        System.out.println(result == expected); // true

        s = "ball";
        expected = 4;
        result = findString(words, s);
        System.out.println(result == expected); // true
    }
}
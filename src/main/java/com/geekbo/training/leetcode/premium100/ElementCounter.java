package com.geekbo.training.leetcode.premium100;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * 1426. 数元素
 * 已解答
 * 简单
 * 给你一个整数数组 arr， 对于元素 x ，只有当 x + 1 也在数组 arr 里时，才能记为 1 个数。
 *
 * 如果数组 arr 里有重复的数，每个重复的数单独计算。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [1,2,3]
 * 输出：2
 * 解释：1 和 2 被计算次数因为 2 和 3 在数组 arr 里。
 * 示例 2：
 *
 * 输入：arr = [1,1,3,3,5,5,7,7]
 * 输出：0
 * 解释：所有的数都不算, 因为数组里没有 2、4、6、8。
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 1000
 * 0 <= arr[i] <= 1000
 */
class ElementCounter {

    /**
     *
     * 解题思路：
     *
     * 创建一个集合 set 用于存储数组中的元素。
     * 遍历数组，将数组中的元素添加到集合中。
     * 再次遍历数组，对于每个元素 num，检查 num+1 是否也在集合中，如果是，则计数器 count 加一。
     * 返回计数器 count 的值。
     *
     * 算法复杂度：
     *
     * 时间复杂度：O(n)，其中 n 是数组的长度。遍历数组需要 O(n) 的时间，集合中的查找操作的平均时间复杂度为 O(1)。
     * 空间复杂度：O(n)，需要使用一个集合来存储数组中的元素。
     *
     * @param arr
     * @return
     */
    public int countElements(int[] arr) {
        Set<Integer> set = new HashSet<>();
        int count = 0;

        // 将数组中的元素添加到集合中
        for (int num : arr) {
            set.add(num);
        }

        // 遍历数组中的元素，检查 x+1 是否也在集合中
        for (int num : arr) {
            if (set.contains(num + 1)) {
                count++;
            }
        }

        return count;
    }
}
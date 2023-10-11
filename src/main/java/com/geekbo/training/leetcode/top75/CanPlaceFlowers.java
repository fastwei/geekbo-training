package com.geekbo.training.leetcode.top75;

/**
 *You have a long flowerbed in which some of the plots are planted, and some are not.
 * However, flowers cannot be planted in adjacent plots.
 *
 * Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n,
 * return true if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule and false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: flowerbed = [1,0,0,0,1], n = 1
 * Output: true
 * Example 2:
 *
 * Input: flowerbed = [1,0,0,0,1], n = 2
 * Output: false
 *
 *
 * 实现思路：
 *
 * 遍历数组flowerbed中的每个元素，如果当前元素为0（表示空闲的花坛）且前后两个元素都为0（即满足不相邻的条件），
 * 则可以在当前位置种花，并且计数器count加1。
 * 最后，比较计数器count和目标种花数量n，如果count大于等于n，则返回true；否则，返回false。
 * 算法复杂度分析：
 *
 * 时间复杂度：遍历整个数组花费O(N)的时间，其中N是数组的长度。
 * 空间复杂度：仅使用了常数级别的额外空间，因此空间复杂度为O(1)。
 * 该实现通过一次遍历数组，判断并种植花朵，以及计数已种植的花朵数量，最后比较数量是否满足要求。
 * 它是一种简单且高效的解决方案，能够在较短的时间内判断是否能种植足够的花朵，而不违反相邻花朵的规则。
 *
 *实现思路：
 *
 * 初始化一个计数器 count 为 0，用于统计可以种植的花的数量。
 *
 * 遍历花坛数组 flowerbed。
 *
 * 对于每个位置 i，检查是否满足可以种花的条件：
 *
 * 当前位置为空地 (flowerbed[i] == 0)。
 * 前一个位置为空地或者当前位置是第一个位置 (i == 0 || flowerbed[i - 1] == 0)。
 * 后一个位置为空地或者当前位置是最后一个位置 (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)。
 * 如果满足以上条件，将当前位置种花，并将计数器 count 增加 1。
 *
 * 继续遍历下一个位置 i。
 *
 * 最后，检查计数器 count 是否大于等于 n，如果是，则返回 true，否则返回 false。
 *
 * 这样，代码实现了在不违反相邻花朵规则的情况下，判断是否可以种植指定数量的花。
 *
 * Array / String
 */
public class CanPlaceFlowers {

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0; // 用于计数可以种植的花的数量
        int i = 0;

        while (i < flowerbed.length) {
            // 如果当前位置为空地且前后都为空地（或者是边界情况），则可以种花
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1; // 在当前位置种花
                count++; // 增加计数
            }
            i++; // 移动到下一个位置
        }

        return count >= n; // 如果可以种的花的数量大于等于n，则返回true，否则返回false
    }

    public static void main(String[] args) {
        int[] flowerbed1 = {1, 0, 0, 0, 1};
        int n1 = 1;
        System.out.println(canPlaceFlowers(flowerbed1, n1)); // Output: true

        int[] flowerbed2 = {1, 0, 0, 0, 1};
        int n2 = 2;
        System.out.println(canPlaceFlowers(flowerbed2, n2)); // Output: false
    }
}
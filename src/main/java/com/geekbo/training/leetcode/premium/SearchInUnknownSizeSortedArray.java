package com.geekbo.training.leetcode.premium;

// ArrayReader接口
interface ArrayReaderSimple {
    int get(int index);
}

/**
 *
 *
 * 702. 搜索长度未知的有序数组
 * 中等
 * 您有一个升序整数数组，其长度未知。您没有访问数组的权限，但是可以使用 ArrayReader 接口访问它。你可以调用 ArrayReader.get(i):
 *
 * 返回数组第ith个索引(0-indexed)处的值(即secret[i])，或者
 *
 * 如果 i  超出了数组的边界，则返回 231 - 1
 *
 * 你也会得到一个整数 target。
 *
 * 如果存在secret[k] == target，请返回索引 k 的值；否则返回 -1
 *
 * 你必须写一个时间复杂度为 O(log n) 的算法。
 *
 *
 *
 * 示例 1：
 *
 * 输入: secret = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 存在在 nums 中，下标为 4
 * 示例 2：
 *
 * 输入: secret = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不在数组中所以返回 -1
 *
 *
 * 提示：
 *
 * 1 <= secret.length <= 104
 * -104 <= secret[i], target <= 104
 * secret 严格递增
 */
public class SearchInUnknownSizeSortedArray {
    /**
     * 该问题可以通过二分查找来解决。
     * 由于数组长度未知，我们需要先找到一个足够大的边界。
     * 我们可以从数组的第一个元素开始，以指数级别递增来搜索边界。
     * 然后使用二分查找在确定的边界内搜索目标值。
     * 
     * 算法复杂度分析：
     * 时间复杂度：由于我们使用指数级递增来搜索边界，然后使用二分查找来搜索目标值，所以总体时间复杂度为 O(log n)。
     * 空间复杂度：除了输入数组外，算法只使用了常数级的额外空间，所以空间复杂度为 O(1)。
     */
    public static int search(ArrayReaderSimple reader, int target) {
        int left = 0;
        int right = 1;
        
        // 找到一个足够大的边界
        while (reader.get(right) < target) {
            left = right;
            right <<= 1;
        }
        
        // 二分查找目标值
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int num = reader.get(mid);
            
            if (num == target) {
                return mid;
            } else if (num < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }

    public static void main(String[] args) {
        // 测试用例1
        int[] secret1 = {-1, 0, 3, 5, 9, 12};
        int target1 = 9;
        ArrayReaderSimple reader1 = new ArrayReaderSimple() {
            public int get(int index) {
                if (index >= secret1.length) {
                    return Integer.MAX_VALUE;
                }
                return secret1[index];
            }
        };
        int expected1 = 4;
        int result1 = search(reader1, target1);
        System.out.println(result1 == expected1); // 输出: true

        // 测试用例2
        int[] secret2 = {-1, 0, 3, 5, 9, 12};
        int target2 = 2;
        ArrayReaderSimple reader2 = new ArrayReaderSimple() {
            public int get(int index) {
                if (index >= secret2.length) {
                    return Integer.MAX_VALUE;
                }
                return secret2[index];
            }
        };
        int expected2 = -1;
        int result2 = search(reader2, target2);
        System.out.println(result2 == expected2); // 输出: true
    }
}
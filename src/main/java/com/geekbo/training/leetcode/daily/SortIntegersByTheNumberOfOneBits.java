package com.geekbo.training.leetcode.daily;

import java.util.Arrays;
import java.util.Comparator;

/**
 * You are given an integer array arr.
 * Sort the integers in the array in ascending order by the number of 1's
 * in their binary representation and in case of two
 * or more integers have the same number of 1's you have to sort them in ascending order.
 * <p>
 * Return the array after sorting it.
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [0,1,2,3,4,5,6,7,8]
 * Output: [0,1,2,4,8,3,5,6,7]
 * Explantion: [0] is the only integer with 0 bits.
 * [1,2,4,8] all have 1 bit.
 * [3,5,6] have 2 bits.
 * [7] has 3 bits.
 * The sorted array by bits is [0,1,2,4,8,3,5,6,7]
 * Example 2:
 * <p>
 * Input: arr = [1024,512,256,128,64,32,16,8,4,2,1]
 * Output: [1,2,4,8,16,32,64,128,256,512,1024]
 * Explantion: All integers have 1 bit in the binary representation,
 * you should just sort them in ascending order.
 */
public class SortIntegersByTheNumberOfOneBits {

    /**
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：排序算法的时间复杂度为 O(nlogn)，其中 n 是数组的长度。
     * 空间复杂度：使用了额外的空间来存储比较器，空间复杂度为 O(1)。
     *
     * @param arr
     * @return
     */
    public static int[] sortByBits(int[] arr) {
        // 将原始的int数组转换为Integer对象数组
        Integer[] integerArr = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            integerArr[i] = arr[i];
        }

        // 对integerArr数组进行排序，使用Arrays.sort()方法，传入自定义的Comparator比较器
        // 比较器的规则是首先按照数字二进制表示中1的个数进行排序，如果1的个数相同则按照数字大小进行排序
        // 使用Integer.bitCount()方法计算二进制表示中1的个数
        // 如果1的个数相同，则使用Integer.compare()方法比较数字的大小
        Arrays.sort(integerArr, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                int count1 = Integer.bitCount(a);
                int count2 = Integer.bitCount(b);
                if (count1 == count2) {
                    return Integer.compare(a, b);
                } else {
                    return Integer.compare(count1, count2);
                }
            }
        });

        // 将排序后的Integer对象数组转换回原始的int数组
        for (int i = 0; i < arr.length; i++) {
            arr[i] = integerArr[i];
        }

        return arr;
    }

    public static void main(String[] args) {
        // 测试用例
        int[] arr1 = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        int[] arr2 = {1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};

        // 预期输出
        int[] expected1 = {0, 1, 2, 4, 8, 3, 5, 6, 7};
        int[] expected2 = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024};

        // 调用sortByBits方法对arr1和arr2进行排序
        int[] result1 = sortByBits(arr1);
        int[] result2 = sortByBits(arr2);

        // 比较预期输出和实际输出是否相同
        System.out.println(Arrays.equals(result1, expected1)); // true
        System.out.println(Arrays.equals(result2, expected2)); // true
    }
}

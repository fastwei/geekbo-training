package com.geekbo.training.leetcode.premium;

/**
 * 数组读取器类
 */
class ArrayReader {
    private int[] arr;

    public ArrayReader(int[] arr) {
        this.arr = arr;
    }

    /**
     * 比较子数组的和
     *
     * @param l 第一个子数组的起始索引
     * @param r 第一个子数组的结束索引
     * @param x 第二个子数组的起始索引
     * @param y 第二个子数组的结束索引
     * @return 1 若 arr[l]+arr[l+1]+...+arr[r] > arr[x]+arr[x+1]+...+arr[y]
     * 0 若 arr[l]+arr[l+1]+...+arr[r] == arr[x]+arr[x+1]+...+arr[y]
     * -1 若 arr[l]+arr[l+1]+...+arr[r] < arr[x]+arr[x+1]+...+arr[y]
     */
    public int compareSub(int l, int r, int x, int y) {
        int sum1 = 0;
        for (int i = l; i <= r; i++) {
            sum1 += arr[i];
        }

        int sum2 = 0;
        for (int i = x; i <= y; i++) {
            sum2 += arr[i];
        }

        if (sum1 > sum2) {
            return 1;
        } else if (sum1 == sum2) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 返回数组的长度
     *
     * @return 数组的长度
     */
    public int length() {
        return arr.length;
    }
}

/**
 * 1533. 找到最大整数的索引
 * 中等
 * 我们有这样一个整数数组 arr ，除了一个最大的整数外，其他所有整数都相等。你不能直接访问该数组，你需要通过 API ArrayReader 来间接访问，这个 API 有以下成员函数：
 * <p>
 * int compareSub(int l, int r, int x, int y)：其中 0 <= l, r, x, y < ArrayReader.length()， l <= r 且 x <= y。这个函数比较子数组 arr[l..r] 与子数组 arr[x..y] 的和。该函数返回：
 * 1 若 arr[l]+arr[l+1]+...+arr[r] > arr[x]+arr[x+1]+...+arr[y] 。
 * 0 若 arr[l]+arr[l+1]+...+arr[r] == arr[x]+arr[x+1]+...+arr[y] 。
 * -1 若 arr[l]+arr[l+1]+...+arr[r] < arr[x]+arr[x+1]+...+arr[y] 。
 * int length()：返回数组的长度。
 * 你最多可以调用函数 compareSub() 20 次。你可以认为这两个函数的时间复杂度都为 O(1) 。
 * <p>
 * 返回 arr 中最大整数的索引。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: arr = [7,7,7,7,10,7,7,7]
 * 输出: 4
 * 解释: API 的调用如下：
 * reader.compareSub(0, 0, 1, 1) // 返回 0。比较子数组 (0, 0) 与子数组 (1, 1) （即比较 arr[0] 和 arr[1]）。
 * 因此我们知道 arr[0] 和 arr[1] 不包含最大元素。
 * reader.compareSub(2, 2, 3, 3) // 返回 0。我们可以排除 arr[2] 和 arr[3]。
 * reader.compareSub(4, 4, 5, 5) // 返回 1。因此，可以确定 arr[4] 是数组中最大的元素。
 * 注意，我们只调用了 3 次 compareSub，所以这个答案是有效的。
 * 示例 2:
 * <p>
 * 输入: nums = [6,6,12]
 * 输出: 2
 * <p>
 * <p>
 * 提示:
 * <p>
 * 2 <= arr.length <= 5 * 10^5
 * 1 <= arr[i] <= 100
 * arr 中除一个最大元素外，其余所有元素都相等。
 * <p>
 * <p>
 * 进阶
 * <p>
 * 如果 arr 中有两个整数比其他数大呢？
 * 如果有一个数比其他数大，另一个数比其他数小呢？
 */
public class FindMaxIndex {
    /**
     * 算法的复杂度：
     * <p>
     * 时间复杂度：该算法使用二分查找，每次将数组的一半进行比较，直到找到最大整数的索引。
     * 时间复杂度为O(log n)，其中n是数组的长度。
     * 空间复杂度：算法只使用了常数级别的额外空间，因此空间复杂度为O(1)。
     *
     * @param reader
     * @return
     */
    public static int getIndex(ArrayReader reader) {
        int length = reader.length();
        int left = 0, right = length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            boolean odd = (right - left + 1) % 2 == 1;
            int compareSub = odd ? reader.compareSub(left, mid, mid, right) : reader.compareSub(left, mid, mid + 1, right);
            if (compareSub < 0) {
                left = odd ? mid : mid + 1;
            } else if (compareSub > 0) {
                right = mid;
            } else {
                return mid;
            }
        }

        return 0;
    }


    public static void main(String[] args) {
        int[] arr1 = {7, 7, 7, 7, 10, 7, 7, 7};
        ArrayReader reader1 = new ArrayReader(arr1);
        System.out.println(getIndex(reader1)); // Output: 4

        int[] arr2 = {6, 6, 12};
        ArrayReader reader2 = new ArrayReader(arr2);
        System.out.println(getIndex(reader2)); // Output: 2
    }
}
package com.geekbo.training.sort;

/**
 * 1649. Create Sorted Array through Instructions
 * Hard
 * <p>
 * Given an integer array instructions,
 * you are asked to create a sorted array from the elements in instructions.
 * You start with an empty container nums. For each element from left to right in instructions,
 * insert it into nums. The cost of each insertion is the minimum of the following:
 * <p>
 * The number of elements currently in nums that are strictly less than instructions[i].
 * The number of elements currently in nums that are strictly greater than instructions[i].
 * For example, if inserting element 3 into nums = [1,2,3,5],
 * the cost of insertion is min(2, 1) (elements 1 and 2 are less than 3,
 * element 5 is greater than 3) and nums will become [1,2,3,3,5].
 * <p>
 * Return the total cost to insert all elements from instructions into nums.
 * Since the answer may be large, return it modulo 109 + 7
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: instructions = [1,5,6,2]
 * Output: 1
 * Explanation: Begin with nums = [].
 * Insert 1 with cost min(0, 0) = 0, now nums = [1].
 * Insert 5 with cost min(1, 0) = 0, now nums = [1,5].
 * Insert 6 with cost min(2, 0) = 0, now nums = [1,5,6].
 * Insert 2 with cost min(1, 2) = 1, now nums = [1,2,5,6].
 * The total cost is 0 + 0 + 0 + 1 = 1.
 * Example 2:
 * <p>
 * Input: instructions = [1,2,3,6,5,4]
 * Output: 3
 * Explanation: Begin with nums = [].
 * Insert 1 with cost min(0, 0) = 0, now nums = [1].
 * Insert 2 with cost min(1, 0) = 0, now nums = [1,2].
 * Insert 3 with cost min(2, 0) = 0, now nums = [1,2,3].
 * Insert 6 with cost min(3, 0) = 0, now nums = [1,2,3,6].
 * Insert 5 with cost min(3, 1) = 1, now nums = [1,2,3,5,6].
 * Insert 4 with cost min(3, 2) = 2, now nums = [1,2,3,4,5,6].
 * The total cost is 0 + 0 + 0 + 0 + 1 + 2 = 3.
 * Example 3:
 * <p>
 * Input: instructions = [1,3,3,3,2,4,2,1,2]
 * Output: 4
 * Explanation: Begin with nums = [].
 * Insert 1 with cost min(0, 0) = 0, now nums = [1].
 * Insert 3 with cost min(1, 0) = 0, now nums = [1,3].
 * Insert 3 with cost min(1, 0) = 0, now nums = [1,3,3].
 * Insert 3 with cost min(1, 0) = 0, now nums = [1,3,3,3].
 * Insert 2 with cost min(1, 3) = 1, now nums = [1,2,3,3,3].
 * Insert 4 with cost min(5, 0) = 0, now nums = [1,2,3,3,3,4].
 * ​​​​​​​Insert 2 with cost min(1, 4) = 1, now nums = [1,2,2,3,3,3,4].
 * ​​​​​​​Insert 1 with cost min(0, 6) = 0, now nums = [1,1,2,2,3,3,3,4].
 * ​​​​​​​Insert 2 with cost min(2, 4) = 2, now nums = [1,1,2,2,2,3,3,3,4].
 * The total cost is 0 + 0 + 0 + 0 + 1 + 0 + 1 + 0 + 2 = 4.
 */
public class CreateSortedArray {
    /**
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(N * logN)，其中N是instructions数组的长度。归并排序的时间复杂度为O(N * logN)。
     * 空间复杂度：O(N)，需要使用与instructions数组长度相同的辅助数组。
     * 这段代码实现了使用归并排序算法对instructions数组进行排序，并计算逆序对的数量。
     * 最终返回逆序对数量的模1,000,000,007的结果。
     *
     * @param instructions
     * @return
     */
    public int createSortedArray(int[] instructions) {
        int[] less = new int[instructions.length];
        int[] larger = new int[instructions.length];

        int[] index = new int[instructions.length];
        for (int i = 0; i < instructions.length; i++) {
            index[i] = i;
        }

        mergeSort(instructions, 0, instructions.length - 1, index, less, larger);

        long count = 0;
        for (int i = 0; i < less.length; i++) {
            count += Math.min(less[i], larger[i]);
        }

        return (int) (count % 1_000_000_007);
    }

    private void mergeSort(int[] nums, int left, int right, int[] index, int[] less, int[] larger) {
        if (right == left) {
            return;
        }

        int mid = (left + right) / 2;
        mergeSort(nums, left, mid, index, less, larger);
        mergeSort(nums, mid + 1, right, index, less, larger);

        for (int i = mid, j = right; i >= left && j >= mid + 1; ) {
            if (nums[index[i]] < nums[index[j]]) {
                less[index[j]] += i - left + 1;
                j--;
            } else {
                i--;
            }
        }

        for (int i = left, j = mid + 1; i <= mid && j <= right; ) {
            if (nums[index[i]] > nums[index[j]]) {
                larger[index[j]] += mid - i + 1;
                j++;
            } else {
                i++;
            }
        }

        merge(nums, left, mid, mid + 1, right, index);
    }

    private void merge(int[] nums, int left1, int right1, int left2, int right2, int[] index) {
        if (left1 == left2) {
            return;
        }
        int begin = left1;
        int[] data = new int[right1 - left1 + 1 + right2 - left2 + 1];
        int idx = 0;

        while (left1 <= right1 && left2 <= right2) {
            data[idx++] = nums[index[left1]] <= nums[index[left2]] ? index[left1++] : index[left2++];
        }

        while (left1 <= right1) {
            data[idx++] = index[left1++];
        }
        while (left2 <= right2) {
            data[idx++] = index[left2++];
        }

        for (int i = 0; i < data.length; i++) {
            index[begin + i] = data[i];
        }
    }

    public static void main(String[] args) {
        CreateSortedArray solution = new CreateSortedArray();
        int[] instructions1 = {1, 5, 6, 2};
        System.out.println("Test Case 1: " + solution.createSortedArray(instructions1)); // Output: 1

        int[] instructions2 = {1, 2, 3, 6, 5, 4};
        System.out.println("Test Case 2: " + solution.createSortedArray(instructions2)); // Output: 3

        int[] instructions3 = {1, 3, 3, 3, 2, 4, 2, 1, 2};
        System.out.println("Test Case 3: " + solution.createSortedArray(instructions3)); // Output: 4
    }
}

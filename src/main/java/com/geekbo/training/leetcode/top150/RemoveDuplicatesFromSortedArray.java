package com.geekbo.training.leetcode.top150;

/**
 * Array / String
 *
 *给定一个按非递减顺序排列的整数数组 nums，要求原地删除重复的元素，使得每个唯一的元素只出现一次。要保持元素的相对顺序不变。然后返回数组中唯一元素的数量。
 *
 * 考虑数组 nums 中唯一元素的数量为 k，为了被接受，你需要完成以下步骤：
 *
 * 修改数组 nums，使得数组的前 k 个元素包含原始顺序中出现的唯一元素。数组中剩余的元素和数组的大小不再重要。
 *
 * 返回 k。
 *
 *解决思路：
 *
 * 如果输入数组为空，直接返回0。
 *
 * 初始化唯一元素的数量为1，因为第一个元素一定是唯一的。
 *
 * 遍历数组，从第二个元素开始（索引为1），比较当前元素和前一个元素是否相等。
 *
 * 如果当前元素不等于前一个元素，则将当前元素放在数组的第k个位置，同时增加唯一元素的数量k。
 *
 * 最后返回k作为唯一元素的数量。
 *
 * 时间复杂度分析：
 *
 * 这个算法只需要一次遍历输入数组，时间复杂度为O(n)，其中n是输入数组的长度。因此，算法的时间复杂度是线性的。
 *
 *Array / String
 *
 */
public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        
        int k = 1; // 初始化唯一元素的数量为1，因为第一个元素一定是唯一的
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[k] = nums[i]; // 将不重复的元素放在数组的前面
                k++; // 唯一元素数量加1
            }
        }
        
        return k;
    }

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArray solution = new RemoveDuplicatesFromSortedArray();
        int[] nums ={0,0,1,1,1,2,2,3,3,4}; // 输入数组
        int[] expectedNums ={0,1,2,3,4}; // 预期正确长度的答案

        int k = solution.removeDuplicates(nums); // 调用你的实现

        assert k == expectedNums.length;
        for (int i = 0; i < k; i++) {
            assert nums[i] == expectedNums[i];
        }

    }

}

package com.geekbo.training.leetcode.top150;

/**
 * Given an array of integers nums containing n + 1 integers
 * where each integer is in the range [1, n] inclusive.
 * <p>
 * There is only one repeated number in nums, return this repeated number.
 * <p>
 * You must solve the problem without modifying the array nums and uses only constant extra space.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 * Example 2:
 * <p>
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 105
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * All the integers in nums appear only once except for precisely one integer
 * which appears two or more times.
 * <p>
 * <p>
 * Follow up:
 * <p>
 * How can we prove that at least one duplicate number must exist in nums?
 * Can you solve the problem in linear runtime complexity?
 */
public class FindDuplicateNumber {
    /**
     * 解题思路： 这道题可以使用快慢指针来解决。
     * 我们可以将数组中的每个元素看作是一个链表节点，节点的值表示下一个节点的索引值。
     * 由于数组中存在重复的元素，所以一定存在一个环。
     * <p>
     * 我们可以使用两个指针slow和fast，初始时都指向数组的第一个元素。
     * 通过不断地迭代，slow每次移动一步，fast每次移动两步，直到两个指针相遇。
     * <p>
     * 当两个指针相遇时，我们可以确定数组中存在一个环。
     * 此时，我们将slow重新指向数组的第一个元素，并继续移动slow和fast，每次移动一步，直到两个指针再次相遇。
     * 当两个指针再次相遇时，即为环的入口点。
     * <p>
     * 我们可以通过数学推导证明上述算法的正确性。
     * 假设环的入口点的索引值为entry，环的长度为cycle，快慢指针相遇的位置距离环的入口点的距离为distance。
     * <p>
     * 当快慢指针相遇时，假设快指针走了k步，则慢指针走了k/2步。
     * 由于快指针的速度是慢指针的两倍，所以快指针走了2k步。
     * 在这2k步中，快指针走了k步进入了环，然后又在环中绕了n圈（n>=1），最后再绕了distance步到达相遇的位置。
     * 所以，k+n*cycle+distance=2k，即k=n*cycle-distance。
     * <p>
     * 由于慢指针走了k/2步，所以慢指针走了n*cycle-distance的步数，即走了n圈再回到相遇的位置。
     * 所以，我们将慢指针重新指向数组的第一个元素，并继续移动slow和fast，每次移动一步，直到两个指针再次相遇。
     * 当两个指针再次相遇时，即为环的入口点。
     * <p>
     * 算法复杂度分析：
     * -时间复杂度：O(n)，其中n是数组的长度。
     * 使用快慢指针的方法，我们最多需要遍历数组两次，所以时间复杂度是O(n)。
     * -空间复杂度：O(1)，使用了常数个额外空间。
     * <p>
     * Follow up:
     * 我们可以通过鸽笼原理来证明至少存在一个重复的数字。假设数组的长度为n+1，且数组中的每个数字都在1到n的范围内。
     * 如果数组中不存在重复的数字，那么数组中最多只能有n个不同的数字。
     * 但是数组的长度为n+1，所以至少存在一个数字出现了两次。
     * <p>
     * 我们也可以使用二分查找的方法来找到重复的数字。
     * 我们可以将数组的数字从1到n的范围进行二分查找，对于每个中间的数字mid，统计数组中小于等于mid的数字的个数count。
     * 如果count大于mid，说明重复的数字在1到mid之间，否则重复的数字在mid+1到n之间。
     * 通过不断缩小查找范围，最终可以找到重复的数字。这种方法的时间复杂度是O(nlogn)。
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];

        // Find the intersection point of the two pointers
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // Find the entrance of the cycle
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    public static void main(String[] args) {
        FindDuplicateNumber findDuplicateNumber = new FindDuplicateNumber();

        // Test Case 1
        int[] nums1 = {1, 3, 4, 2, 2};
        int result1 = findDuplicateNumber.findDuplicate(nums1);
        System.out.println(result1);  // Expected output: 2

        // Test Case 2
        int[] nums2 = {3, 1, 3, 4, 2};
        int result2 = findDuplicateNumber.findDuplicate(nums2);
        System.out.println(result2);  // Expected output: 3
    }
}

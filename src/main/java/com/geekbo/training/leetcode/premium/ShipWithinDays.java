package com.geekbo.training.leetcode.premium;

/**
 *
 * 1011. 在 D 天内送达包裹的能力
 * 中等
 * 传送带上的包裹必须在 days 天内从一个港口运送到另一个港口。
 *
 * 传送带上的第 i 个包裹的重量为 weights[i]。
 * 每一天，我们都会按给出重量（weights）的顺序往传送带上装载包裹。
 * 我们装载的重量不会超过船的最大运载重量。
 *
 * 返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。
 *
 *
 *
 * 示例 1：
 *
 * 输入：weights = [1,2,3,4,5,6,7,8,9,10], days = 5
 * 输出：15
 * 解释：
 * 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
 * 第 1 天：1, 2, 3, 4, 5
 * 第 2 天：6, 7
 * 第 3 天：8
 * 第 4 天：9
 * 第 5 天：10
 *
 * 请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
 * 示例 2：
 *
 * 输入：weights = [3,2,2,4,1,4], days = 3
 * 输出：6
 * 解释：
 * 船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
 * 第 1 天：3, 2
 * 第 2 天：2, 4
 * 第 3 天：1, 4
 * 示例 3：
 *
 * 输入：weights = [1,2,3,1,1], days = 4
 * 输出：3
 * 解释：
 * 第 1 天：1
 * 第 2 天：2
 * 第 3 天：3
 * 第 4 天：1, 1
 *
 *
 * 提示：
 *
 * 1 <= days <= weights.length <= 5 * 104
 * 1 <= weights[i] <= 500
 */
class ShipWithinDays {

    /**
     * 解题思路： 我们可以使用二分查找来找到最低运载能力。
     * 首先确定运载能力的上下界，最低为最大包裹的重量，最高为所有包裹重量的和。
     * 然后进行二分查找，每次取中间的运载能力，然后模拟运输过程，检查是否能够在days天内将所有包裹送达。
     * 根据运输结果，逐步缩小运载能力的范围，直到找到最低运载能力。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：O(nlog(sum(weights)))，其中n是包裹的数量，sum(weights)是所有包裹重量的和。
     * 二分查找的时间复杂度为log(sum(weights))，每次查找需要进行一次模拟运输，时间复杂度为O(n)。
     * 空间复杂度：O(1)。
     *
     * @param weights
     * @param days
     * @return
     */
    public int shipWithinDays(int[] weights, int days) {
        // 确定运载能力的上下界
        int left = 0, right = 0;
        for (int weight : weights) {
            left = Math.max(left, weight);
            right += weight;
        }

        // 二分查找最低运载能力
        while (left < right) {
            int mid = left + (right - left) / 2;
            int curr = 0;
            int needDays = 1;
            for (int weight : weights) {
                if (curr + weight > mid) {
                    curr = 0;
                    needDays++;
                }
                curr += weight;
            }

            if (needDays <= days) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        ShipWithinDays solution = new ShipWithinDays();
        int[] weights1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int days1 = 5;
        int result1 = solution.shipWithinDays(weights1, days1);
        System.out.println(result1);  // 输出：15

        int[] weights2 = {3, 2, 2, 4, 1, 4};
        int days2 = 3;
        int result2 = solution.shipWithinDays(weights2, days2);
        System.out.println(result2);  // 输出：6

        int[] weights3 = {1, 2, 3, 1, 1};
        int days3 = 4;
        int result3 = solution.shipWithinDays(weights3, days3);
        System.out.println(result3);  // 输出：3
    }
}
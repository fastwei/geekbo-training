package com.geekbo.training.leetcode.daily;

/**
 * There are buckets buckets of liquid, where exactly one of the buckets is poisonous.
 * To figure out which one is poisonous,
 * you feed some number of (poor) pigs the liquid to see whether they will die or not.
 * Unfortunately, you only have minutesToTest minutes to determine which bucket is poisonous.
 * <p>
 * You can feed the pigs according to these steps:
 * <p>
 * Choose some live pigs to feed.
 * For each pig, choose which buckets to feed it.
 * The pig will consume all the chosen buckets simultaneously and will take no time.
 * Each pig can feed from any number of buckets, and each bucket can be fed from by any number of pigs.
 * Wait for minutesToDie minutes. You may not feed any other pigs during this time.
 * After minutesToDie minutes have passed, any pigs that have been fed the poisonous bucket will die,
 * and all others will survive.
 * Repeat this process until you run out of time.
 * Given buckets, minutesToDie, and minutesToTest,
 * return the minimum number of pigs needed to figure out which bucket is poisonous within the allotted time.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: buckets = 4, minutesToDie = 15, minutesToTest = 15
 * Output: 2
 * Explanation: We can determine the poisonous bucket as follows:
 * At time 0, feed the first pig buckets 1 and 2, and feed the second pig buckets 2 and 3.
 * At time 15, there are 4 possible outcomes:
 * - If only the first pig dies, then bucket 1 must be poisonous.
 * - If only the second pig dies, then bucket 3 must be poisonous.
 * - If both pigs die, then bucket 2 must be poisonous.
 * - If neither pig dies, then bucket 4 must be poisonous.
 * Example 2:
 * <p>
 * Input: buckets = 4, minutesToDie = 15, minutesToTest = 30
 * Output: 2
 * Explanation: We can determine the poisonous bucket as follows:
 * At time 0, feed the first pig bucket 1, and feed the second pig bucket 2.
 * At time 15, there are 2 possible outcomes:
 * - If either pig dies, then the poisonous bucket is the one it was fed.
 * - If neither pig dies, then feed the first pig bucket 3, and feed the second pig bucket 4.
 * At time 30, one of the two pigs must die, and the poisonous bucket is the one it was fed.
 */
public class PoorPigs {
    public static void main(String[] args) {
        int buckets1 = 4, minutesToDie1 = 15, minutesToTest1 = 15;
        System.out.println(poorPigs(buckets1, minutesToDie1, minutesToTest1)); // Output: 2

        int buckets2 = 4, minutesToDie2 = 15, minutesToTest2 = 30;
        System.out.println(poorPigs(buckets2, minutesToDie2, minutesToTest2)); // Output: 2
    }

    /**
     * 解题思路：
     * 假设我们有x只猪，共有t次测试时间。
     * 每只猪在一个单位时间内最多可以测试的桶数为t + 1（包括初始时间为0）。
     * 如果只有一只猪，那么我们可以在t + 1个桶上进行测试，即t + 1个桶中的任意一个桶。
     * 如果有两只猪，我们可以将桶排列成一个二维矩阵，行数为t + 1，列数为t + 1。
     * 我们可以使用两只猪分别测试行和列，一只猪测试行，另一只猪测试列。
     * 这样，我们可以通过猪的生死状态在t + 1个测试时间内找到有毒的桶。
     * 所以，我们可以通过猪的数量来确定我们可以测试的桶的数量。
     * 具体来说，我们可以将测试时间t + 1的数量表示为x的幂，即(x^(t+1))。
     * 我们需要找到最小的x，使得 (x^(t+1)) >= buckets。
     * 取对数后得到 log(buckets) / log(x) >= t + 1。
     * 因此，我们可以通过对数公式计算最小的x，并向上取整。
     * 算法的时间复杂度为O(1)。
     *
     * @param buckets       桶的数量
     * @param minutesToDie  毒发时间
     * @param minutesToTest 测试时间
     * @return 最少需要的猪的数量
     */
    public static int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int pigs = 0;
        int tests = minutesToTest / minutesToDie + 1;
        while (Math.pow(tests, pigs) < buckets) {
            pigs++;
        }
        return pigs;
    }
}
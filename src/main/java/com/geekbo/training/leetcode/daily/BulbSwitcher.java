package com.geekbo.training.leetcode.daily;

/**
 * 319. Bulb Switcher
 * Medium
 * There are n bulbs that are initially off.
 * You first turn on all the bulbs, then you turn off every second bulb.
 * <p>
 * On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on).
 * For the ith round, you toggle every i bulb. For the nth round, you only toggle the last bulb.
 * <p>
 * Return the number of bulbs that are on after n rounds.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 3
 * Output: 1
 * Explanation: At first, the three bulbs are [off, off, off].
 * After the first round, the three bulbs are [on, on, on].
 * After the second round, the three bulbs are [on, off, on].
 * After the third round, the three bulbs are [on, off, off].
 * So you should return 1 because there is only one bulb is on.
 * Example 2:
 * <p>
 * Input: n = 0
 * Output: 0
 * Example 3:
 * <p>
 * Input: n = 1
 * Output: 1
 */
class BulbSwitcher {
    /**
     * 可以通过观察规律得出答案。
     * <p>
     * 我们可以发现，对于第i个灯泡来说，它会在第j轮被开关的条件是j能够整除i，也就是i % j == 0。
     * 每一轮开关之后，灯泡的状态会发生变化，如果灯泡的状态从关闭变为打开或者从打开变为关闭，
     * 那么在第n轮之后，灯泡的状态就会是关闭的。
     * <p>
     * 换句话说，对于每个灯泡来说，它的状态会在第i个开关操作之后发生改变，而第i个开关操作会在能够整除i的轮数中进行。
     * 因此，我们只需要找到每个灯泡的开关操作次数，并统计开关操作次数为奇数的灯泡的个数。
     * <p>
     * 在这个问题中，每个灯泡的开关操作次数等于它的约数个数。
     * 因为一个数的约数总是成对出现的，比如12的约数有1、2、3、4、6、12，可以发现除了完全平方数之外，
     * 所有的约数都是成对出现的。所以对于完全平方数，它的约数个数是奇数个，其他数的约数个数是偶数个。
     * <p>
     * 因此，我们只需要找到n以内的完全平方数的个数，即为开关操作次数为奇数的灯泡的个数，
     * 也就是开关之后仍然打开的灯泡的个数。
     */
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}
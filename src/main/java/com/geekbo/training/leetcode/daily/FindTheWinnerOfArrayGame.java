package com.geekbo.training.leetcode.daily;

/**
 * Given an integer array arr of distinct integers and an integer k.
 * <p>
 * A game will be played between the first two elements of the array (i.e. arr[0] and arr[1]).
 * In each round of the game, we compare arr[0] with arr[1],
 * the larger integer wins and remains at position 0, and the smaller integer moves to the end of the array.
 * The game ends when an integer wins k consecutive rounds.
 * <p>
 * Return the integer which will win the game.
 * <p>
 * It is guaranteed that there will be a winner of the game.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [2,1,3,5,4,6,7], k = 2
 * Output: 5
 * Explanation: Let's see the rounds of the game:
 * Round |       arr       | winner | win_count
 * 1   | [2,1,3,5,4,6,7] | 2      | 1
 * 2   | [2,3,5,4,6,7,1] | 3      | 1
 * 3   | [3,5,4,6,7,1,2] | 5      | 1
 * 4   | [5,4,6,7,1,2,3] | 5      | 2
 * So we can see that 4 rounds will be played and 5 is the winner because it wins 2 consecutive games.
 * Example 2:
 * <p>
 * Input: arr = [3,2,1], k = 10
 * Output: 3
 * Explanation: 3 will win the first 10 rounds consecutively.
 */
class FindTheWinnerOfArrayGame {
    /**
     * 解题思路： 我们使用一个变量winner来记录当前的胜利者，初始值为数组的第一个元素arr[0]。
     * 同时，使用一个变量winCount来记录当前胜利者连续胜出的次数，初始值为0。
     * <p>
     * 从数组的第二个元素开始，比较当前元素和winner。
     * 如果当前元素大于winner，则将winner更新为当前元素，并将winCount重置为1；否则，将winCount加1。
     * <p>
     * 在每一轮比较后，我们检查winCount是否等于k。
     * 如果等于k，表示winner连续胜出了k次，那么winner即为最终的胜利者，可以直接返回。
     * <p>
     * 如果到达数组的末尾仍然没有返回结果，说明最终的胜利者就是当前的winner。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历一次数组，时间复杂度为O(n)，其中n为数组的长度。
     * 空间复杂度：只使用了常数级别的额外空间，空间复杂度为O(1)。
     *
     * @param arr
     * @param k
     * @return
     */
    public int getWinner(int[] arr, int k) {
        int winner = arr[0];
        int winCount = 0;

        // 从第一个元素开始比较，如果当前元素大于winner，
        // 则将winner更新为当前元素，并将winCount重置为1；
        // 否则，将winCount加1。
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > winner) {
                winner = arr[i];
                winCount = 1;
            } else {
                winCount++;
            }

            // 如果winCount等于k，表示winner连续胜出了k次，
            // 则winner即为最终的胜利者，可以直接返回。
            if (winCount == k) {
                return winner;
            }
        }

        return winner;
    }

    public static void main(String[] args) {
        FindTheWinnerOfArrayGame findTheWinnerOfArrayGame = new FindTheWinnerOfArrayGame();

        // 测试用例
        int[] arr1 = {2, 1, 3, 5, 4, 6, 7};
        int k1 = 2;
        // 预期输出: 5
        System.out.println(findTheWinnerOfArrayGame.getWinner(arr1, k1));

        int[] arr2 = {3, 2, 1};
        int k2 = 10;
        // 预期输出: 3
        System.out.println(findTheWinnerOfArrayGame.getWinner(arr2, k2));
    }
}


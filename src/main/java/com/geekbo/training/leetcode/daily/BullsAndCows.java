package com.geekbo.training.leetcode.daily;

/**
 * 299. Bulls and Cows
 * Medium
 * You are playing the Bulls and Cows game with your friend.
 * <p>
 * You write down a secret number and ask your friend to guess what the number is.
 * When your friend makes a guess, you provide a hint with the following info:
 * <p>
 * The number of "bulls", which are digits in the guess that are in the correct position.
 * The number of "cows", which are digits in the guess that are in your secret number
 * but are located in the wrong position. Specifically, the non-bull digits in the guess that could be rearranged such that they become bulls.
 * Given the secret number secret and your friend's guess guess, return the hint for your friend's guess.
 * <p>
 * The hint should be formatted as "xAyB", where x is the number of bulls and y is the number of cows.
 * Note that both secret and guess may contain duplicate digits.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: secret = "1807", guess = "7810"
 * Output: "1A3B"
 * Explanation: Bulls are connected with a '|' and cows are underlined:
 * "1807"
 * |
 * "7810"
 * Example 2:
 * <p>
 * Input: secret = "1123", guess = "0111"
 * Output: "1A1B"
 * Explanation: Bulls are connected with a '|' and cows are underlined:
 * "1123"        "1123"
 * |      or     |
 * "0111"        "0111"
 * Note that only one of the two unmatched 1s is counted as a cow since the non-bull digits can only be rearranged to allow one 1 to be a bull.
 */
public class BullsAndCows {

    /**
     * 你正在和朋友玩“公牛和奶牛”游戏。你先写下一个秘密数字，然后让你的朋友猜这个数字。
     * 当你的朋友猜测时，你会给出以下提示：
     * <p>
     * “公牛”：猜测中数字位置正确的位数。
     * “奶牛”：猜测中数字在你的秘密数字中出现但位置不正确的位数。
     * 具体来说，奶牛是指非公牛的数字，如果它们被重新排列，可以变成公牛。
     * 给定秘密数字secret和朋友的猜测guess，返回朋友猜测的提示。
     * 提示应该以“xAyB”的格式表示，其中x是公牛的数量，y是奶牛的数量。请注意，秘密数字和猜测都可能包含重复的数字。
     * <p>
     * 解题思路： 我们可以使用两个哈希表来解决这个问题。
     * 首先，我们遍历秘密数字的每个数字，并将其出现的次数记录在一个哈希表中。
     * 然后，我们遍历朋友的猜测数字，如果该数字在秘密数字中出现，则增加公牛的数量，并将秘密数字中该数字的出现次数减一。
     * 最后，我们再次遍历朋友的猜测数字，如果该数字在秘密数字中出现过（但位置不正确），则增加奶牛的数量，并将秘密数字中该数字的出现次数减一。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度为O(n)，其中n是秘密数字和朋友猜测数字的长度。
     * 空间复杂度为O(1)，因为秘密数字和朋友猜测数字的可能取值范围是有限的。
     *
     * @param secret
     * @param guess
     * @return
     */
    public static String getHint(String secret, String guess) {
        int bulls = 0, cows = 0;
        int[] secretCount = new int[10];
        int[] guessCount = new int[10];

        for (int i = 0; i < secret.length(); i++) {
            char s = secret.charAt(i);
            char g = guess.charAt(i);

            if (s == g) {
                bulls++;
            } else {
                secretCount[s - '0']++;
                guessCount[g - '0']++;
            }
        }

        for (int i = 0; i < 10; i++) {
            cows += Math.min(secretCount[i], guessCount[i]);
        }

        return bulls + "A" + cows + "B";
    }

    public static void main(String[] args) {
        // Test case 1
        String secret1 = "1807";
        String guess1 = "7810";
        // 公牛为1，位置为0的数字1；奶牛为3，分别是数字8、0和7
        String expected1 = "1A3B";
        String result1 = getHint(secret1, guess1);
        System.out.println(result1.equals(expected1)); // Output: true

        // Test case 2
        String secret2 = "1123";
        String guess2 = "0111";
        // 公牛为1，位置为3的数字1；奶牛为1，位置为0的数字1
        String expected2 = "1A1B";
        String result2 = getHint(secret2, guess2);
        System.out.println(result2.equals(expected2)); // Output: true
    }
}
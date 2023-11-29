package com.geekbo.training.leetcode.daily;

/**
 * 420. Strong Password Checker
 * Hard
 * A password is considered strong if the below conditions are all met:
 * <p>
 * It has at least 6 characters and at most 20 characters.
 * It contains at least one lowercase letter, at least one uppercase letter, and at least one digit.
 * It does not contain three repeating characters in a row (i.e., "Baaabb0" is weak, but "Baaba0" is strong).
 * Given a string password, return the minimum number of steps required to make password strong. if password is already strong, return 0.
 * <p>
 * In one step, you can:
 * <p>
 * Insert one character to password,
 * Delete one character from password, or
 * Replace one character of password with another character.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: password = "a"
 * Output: 5
 * Example 2:
 * <p>
 * Input: password = "aA1"
 * Output: 3
 * Example 3:
 * <p>
 * Input: password = "1337C0d3"
 * Output: 0
 */
public class StrongPasswordChecker {
    public static int strongPasswordChecker(String password) {
        int n = password.length();
        int hasLower = 0, hasUpper = 0, hasDigit = 0;
        for (int i = 0; i < n; ++i) {
            char ch = password.charAt(i);
            if (Character.isLowerCase(ch)) {
                hasLower = 1;
            } else if (Character.isUpperCase(ch)) {
                hasUpper = 1;
            } else if (Character.isDigit(ch)) {
                hasDigit = 1;
            }
        }
        int categories = hasLower + hasUpper + hasDigit;

        if (n < 6) {
            return Math.max(6 - n, 3 - categories);
        } else if (n <= 20) {
            int replace = 0;
            int cnt = 0;
            char cur = '#';

            for (int i = 0; i < n; ++i) {
                char ch = password.charAt(i);
                if (ch == cur) {
                    ++cnt;
                } else {
                    replace += cnt / 3;
                    cnt = 1;
                    cur = ch;
                }
            }
            replace += cnt / 3;
            return Math.max(replace, 3 - categories);
        } else {
            // 替换次数和删除次数
            int replace = 0, remove = n - 20;
            // k mod 3 = 1 的组数，即删除 2 个字符可以减少 1 次替换操作
            int rm2 = 0;
            int cnt = 0;
            char cur = '#';

            for (int i = 0; i < n; ++i) {
                char ch = password.charAt(i);
                if (ch == cur) {
                    ++cnt;
                } else {
                    if (remove > 0 && cnt >= 3) {
                        if (cnt % 3 == 0) {
                            // 如果是 k % 3 = 0 的组，那么优先删除 1 个字符，减少 1 次替换操作
                            --remove;
                            --replace;
                        } else if (cnt % 3 == 1) {
                            // 如果是 k % 3 = 1 的组，那么存下来备用
                            ++rm2;
                        }
                        // k % 3 = 2 的组无需显式考虑
                    }
                    replace += cnt / 3;
                    cnt = 1;
                    cur = ch;
                }
            }
            if (remove > 0 && cnt >= 3) {
                if (cnt % 3 == 0) {
                    --remove;
                    --replace;
                } else if (cnt % 3 == 1) {
                    ++rm2;
                }
            }
            replace += cnt / 3;

            // 使用 k % 3 = 1 的组的数量，由剩余的替换次数、组数和剩余的删除次数共同决定
            int use2 = Math.min(Math.min(replace, rm2), remove / 2);
            replace -= use2;
            remove -= use2 * 2;
            // 由于每有一次替换次数就一定有 3 个连续相同的字符（k / 3 决定），因此这里可以直接计算出使用 k % 3 = 2 的组的数量
            int use3 = Math.min(replace, remove / 3);
            replace -= use3;
            remove -= use3 * 3;
            return (n - 20) + Math.max(replace, 3 - categories);
        }
    }

    public static void main(String[] args) {
        String password1 = "a";
        System.out.println(strongPasswordChecker(password1)); // Output: 5

        String password2 = "aA1";
        System.out.println(strongPasswordChecker(password2)); // Output: 3

        String password3 = "1337C0d3";
        System.out.println(strongPasswordChecker(password3)); // Output: 0

        String password4 = "Baaabb0";
        System.out.println(strongPasswordChecker(password4)); // Output: 2

        String password5 = "Baaba0";
        System.out.println(strongPasswordChecker(password5)); // Output: 0
    }
}
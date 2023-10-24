package com.geekbo.training.leetcode.top150;

/**
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring. T
 * he same letter cell may not be used more than once.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * Output: true
 * Example 3:
 * <p>
 * <p>
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * Output: false
 * <p>
 * <p>
 * Follow up: Could you use search pruning to make your solution faster with a larger board?
 */
public class WordSearch {
    /**
     * 判断给定的字符串是否存在于字符网格中
     * <p>
     * 解题思路：
     * <p>
     * 遍历字符网格，找到与字符串的第一个字符匹配的位置。
     * 对于每个匹配的位置，使用回溯算法进行搜索：
     * 判断当前位置是否超出字符网格的范围，或者当前位置的字符与要匹配的字符不相等，如果是，则返回 false。
     * 如果索引等于字符串的长度，说明已经匹配完了所有的字符，返回 true。
     * 将当前位置的字符标记为已访问，防止重复使用。
     * 在当前位置的上、下、左、右四个方向进行递归搜索。
     * 还原当前位置的字符。
     * 如果找到任意一个位置能够匹配完整个字符串，返回 true。否则，返回 false。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 假设字符网格的行数为 m，列数为 n，字符串的长度为 k。
     * 在最坏情况下，需要遍历整个字符网格，所以时间复杂度为 O(m * n)。
     * 对于每个匹配的位置，需要进行递归搜索，每次递归最多有四个方向可选，所以递归的深度最多为字符串的长度 k。
     * 每次递归的时间复杂度为 O(1)。
     * 因此，总的时间复杂度为 O(m * n * k)。
     * 对于空间复杂度，递归的最大深度为字符串的长度 k，所以空间复杂度为 O(k)。
     *
     * @param board 字符网格
     * @param word  给定的字符串
     * @return 是否存在于字符网格中
     */
    public static boolean exist(char[][] board, String word) {
        // 获取字符网格的行数和列数
        int rows = board.length;
        int cols = board[0].length;

        // 遍历字符网格，找到与字符串的第一个字符匹配的位置
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == word.charAt(0)) {
                    // 使用回溯算法进行搜索
                    if (backtrack(board, word, 0, i, j)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * 回溯算法进行搜索
     *
     * @param board 字符网格
     * @param word  给定的字符串
     * @param index 当前要匹配的字符在字符串中的索引
     * @param row   当前位置的行索引
     * @param col   当前位置的列索引
     * @return 是否找到匹配的字符串
     */
    private static boolean backtrack(char[][] board, String word, int index, int row, int col) {
        // 如果索引等于字符串的长度，说明已经匹配完了所有的字符，返回 true
        if (index == word.length()) {
            return true;
        }

        // 如果当前位置超出了字符网格的范围，或者当前位置的字符与要匹配的字符不相等，返回 false
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != word.charAt(index)) {
            return false;
        }

        // 将当前位置的字符标记为已访问，防止重复使用
        char temp = board[row][col];
        board[row][col] = '*';

        // 在当前位置的上、下、左、右四个方向进行递归搜索
        boolean result = backtrack(board, word, index + 1, row - 1, col) ||
                backtrack(board, word, index + 1, row + 1, col) ||
                backtrack(board, word, index + 1, row, col - 1) ||
                backtrack(board, word, index + 1, row, col + 1);

        // 还原当前位置的字符
        board[row][col] = temp;

        return result;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word1 = "ABCCED";
        // 预期输出：true
        System.out.println(exist(board, word1));

        String word2 = "SEE";
        // 预期输出：true
        System.out.println(exist(board, word2));

        String word3 = "ABCB";
        // 预期输出：false
        System.out.println(exist(board, word3));
    }
}

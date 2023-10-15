package com.geekbo.training.leetcode.top150;

import java.util.Stack;

/**
 *
 * Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, convert it to the simplified canonical path.
 *
 * In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'. For this problem, any other format of periods such as '...' are treated as file/directory names.
 *
 * The canonical path should have the following format:
 *
 * The path starts with a single slash '/'.
 * Any two directories are separated by a single slash '/'.
 * The path does not end with a trailing '/'.
 * The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
 * Return the simplified canonical path.
 *
 *
 *
 * Example 1:
 *
 * Input: path = "/home/"
 * Output: "/home"
 * Explanation: Note that there is no trailing slash after the last directory name.
 * Example 2:
 *
 * Input: path = "/../"
 * Output: "/"
 * Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
 * Example 3:
 *
 * Input: path = "/home//foo/"
 * Output: "/home/foo"
 * Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
 *
 */
public class SimplifyPath {
    /**
     * 要将Unix风格文件系统中的绝对路径转换为简化的规范路径，可以按照以下步骤进行操作：
     * <p>
     * 使用斜杠'/'作为分隔符将路径拆分为单独的目录或文件名。
     * 创建一个空栈来存储规范路径。
     * 遍历从拆分路径中获得的每个目录或文件名：
     * 如果名称是'..'，则从栈中弹出顶部元素（如果栈不为空），以返回上一级目录。
     * 如果名称是'.'，则忽略它，因为它表示当前目录。
     * 如果名称不为空，则将其推入栈中。
     * 使用斜杠'/'作为分隔符，将栈的元素连接起来形成简化的规范路径。
     * 在结果路径之前添加斜杠'/'，以确保它以单个斜杠开头。
     * 返回简化的规范路径。
     * 以下是使用Java实现上述算法的代码，其中包含了注释：
     * <p>
     * 该算法的时间复杂度为O(n)，其中n是输入路径的长度。
     * 这是因为我们将路径拆分为单个名称，在每个名称上进行迭代，并在栈中连接这些名称。
     * 空间复杂度也为O(n)，因为我们将规范路径存储在栈中。
     */
    public String simplifyPath(String path) {
        // 使用斜杠'/'作为分隔符拆分路径
        String[] dirs = path.split("/");

        // 创建一个栈来存储规范路径
        Stack<String> stack = new Stack<>();

        // 遍历每个目录或文件名
        for (String dir : dirs) {
            if (dir.equals("..")) {
                // 如果名称是'..'，返回上一级目录
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!dir.isEmpty() && !dir.equals(".")) {
                // 如果名称不为空且不是'.'，将其推入栈中
                stack.push(dir);
            }
        }

        // 使用斜杠'/'作为分隔符连接栈的元素
        StringBuilder sb = new StringBuilder();
        for (String dir : stack) {
            sb.append("/").append(dir);
        }

        // 在结果路径之前添加斜杠'/'
        String canonicalPath = sb.toString();
        if (canonicalPath.isEmpty()) {
            canonicalPath = "/";
        }

        return canonicalPath;
    }

    public static void main(String[] args) {
        SimplifyPath simplifyPath = new SimplifyPath();

        // 调用simplifyPath方法，并验证输出结果
        System.out.println(simplifyPath.simplifyPath("/home/"));  // 输出: "/home"
        System.out.println(simplifyPath.simplifyPath("/../"));  // 输出: "/"
        System.out.println(simplifyPath.simplifyPath("/home//foo/"));  // 输出: "/home/foo"
    }
}

package com.geekbo.training.leetcode.graph;

import java.util.*;

/**
 * 721. Accounts Merge
 * Medium
 * Given a list of accounts where each element accounts[i] is a list of strings,
 * where the first element accounts[i][0] is a name,
 * and the rest of the elements are emails representing emails of the account.
 * <p>
 * Now, we would like to merge these accounts.
 * Two accounts definitely belong to the same person if there is some common email to both accounts.
 * Note that even if two accounts have the same name,
 * they may belong to different people as people could have the same name.
 * A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 * <p>
 * After merging the accounts, return the accounts in the following format:
 * the first element of each account is the name, and the rest of the elements are emails in sorted order.
 * The accounts themselves can be returned in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 * Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 * Explanation:
 * The first and second John's are the same person as they have the common email "johnsmith@mail.com".
 * The third John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 * Example 2:
 * <p>
 * Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
 * Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
 */
public class AccountsMerge {
    /**
     * 合并账户。
     * 解题思路：
     * 这道题可以使用并查集来解决。
     * 首先，遍历账户列表，将每个邮箱与账户的关系进行记录，并将相同账户的邮箱进行合并。
     * 然后，使用深度优先搜索，将相同账户的所有邮箱添加到一个集合中。
     * 最后，将集合中的邮箱按照字典序排序，并将姓名和排序后的邮箱添加到结果列表中。
     * <p>
     * 算法复杂度分析：
     * 假设账户列表的长度为n，平均每个账户的邮箱数量为m。
     * -遍历账户列表的时间复杂度为O(nm)。
     * -深度优先搜索的时间复杂度为O(m)。
     * -对邮箱进行排序的时间复杂度为O(mlogm)。
     * 综上所述，算法的总时间复杂度为O(nm+mlogm)。
     *
     * @param accounts 账户列表，每个账户为一个字符串列表，第一个元素为姓名，后面的元素为邮箱。
     * @return 合并后的账户列表，每个账户的第一个元素为姓名，后面的元素为排序后的邮箱。
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // 创建一个映射，将邮箱与账户的关系进行记录
        Map<String, String> emailToName = new HashMap<>();
        // 创建一个映射，将邮箱与相同账户的其他邮箱进行记录
        Map<String, Set<String>> emailToEmails = new HashMap<>();

        // 遍历账户列表
        for (List<String> account : accounts) {
            String name = account.get(0);
            // 遍历邮箱列表
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                // 将邮箱与账户的关系进行记录
                emailToName.put(email, name);
                // 将邮箱与相同账户的其他邮箱进行记录
                emailToEmails.putIfAbsent(email, new HashSet<>());
                if (i > 1) {
                    String prevEmail = account.get(i - 1);
                    // 将当前邮箱与前一个邮箱进行合并
                    emailToEmails.get(email).add(prevEmail);
                    emailToEmails.get(prevEmail).add(email);
                }
            }
        }

        // 创建一个结果列表，用于存储合并后的账户
        List<List<String>> mergedAccounts = new ArrayList<>();
        // 创建一个集合，用于记录已经访问过的邮箱
        Set<String> visitedEmails = new HashSet<>();

        // 遍历邮箱列表
        for (String email : emailToName.keySet()) {
            // 如果当前邮箱已经访问过，则跳过
            if (visitedEmails.contains(email)) {
                continue;
            }
            // 创建一个集合，用于存储当前账户的所有邮箱
            Set<String> currentAccountEmails = new TreeSet<>();
            // 深度优先搜索，将相同账户的所有邮箱添加到集合中
            dfs(email, emailToEmails, currentAccountEmails, visitedEmails);
            // 创建一个账户列表，将姓名和排序后的邮箱添加到列表中
            List<String> mergedAccount = new ArrayList<>();
            mergedAccount.add(emailToName.get(email));
            mergedAccount.addAll(currentAccountEmails);
            // 将账户添加到结果列表中
            mergedAccounts.add(mergedAccount);
        }

        return mergedAccounts;
    }

    /**
     * 深度优先搜索，将相同账户的所有邮箱添加到集合中。
     *
     * @param email                当前邮箱
     * @param emailToEmails        邮箱与相同账户的其他邮箱的映射
     * @param currentAccountEmails 当前账户的所有邮箱
     * @param visitedEmails        已经访问过的邮箱集合
     */
    private void dfs(String email, Map<String, Set<String>> emailToEmails, Set<String> currentAccountEmails, Set<String> visitedEmails) {
        // 如果当前邮箱已经访问过，则返回当邮箱已经访问过，则返回。
        // 否则，将当前邮箱添加到已访问邮箱集合中，并将其添加到当前账户的所有邮箱集合中。
        // 然后，遍历与当前邮箱相同账户的其他邮箱，对每个邮箱进行深度优先搜索。

        visitedEmails.add(email);
        currentAccountEmails.add(email);

        // 遍历与当前邮箱相同账户的其他邮箱
        for (String nextEmail : emailToEmails.getOrDefault(email, new HashSet<>())) {
            // 如果下一个邮箱已经访问过，则跳过
            if (visitedEmails.contains(nextEmail)) {
                continue;
            }
            // 对下一个邮箱进行深度优先搜索
            dfs(nextEmail, emailToEmails, currentAccountEmails, visitedEmails);
        }
    }

    public static void main(String[] args) {
        AccountsMerge solution = new AccountsMerge();

        // 测试用例1
        List<List<String>> accounts1 = new ArrayList<>();
        accounts1.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts1.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts1.add(Arrays.asList("Mary", "mary@mail.com"));
        accounts1.add(Arrays.asList("John", "johnnybravo@mail.com"));
        List<List<String>> expected1 = new ArrayList<>();
        expected1.add(Arrays.asList("John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"));
        expected1.add(Arrays.asList("Mary", "mary@mail.com"));
        expected1.add(Arrays.asList("John", "johnnybravo@mail.com"));
        List<List<String>> result1 = solution.accountsMerge(accounts1);
        System.out.println("测试用例1:");
        System.out.println("预期输出: " + expected1);
        System.out.println("实际输出: " + result1);

        // 测试用例2
        List<List<String>> accounts2 = new ArrayList<>();
        accounts2.add(Arrays.asList("Gabe", "Gabe0@m.co", "Gabe3@m.co", "Gabe1@m.co"));
        accounts2.add(Arrays.asList("Kevin", "Kevin3@m.co", "Kevin5@m.co", "Kevin0@m.co"));
        accounts2.add(Arrays.asList("Ethan", "Ethan5@m.co", "Ethan4@m.co", "Ethan0@m.co"));
        accounts2.add(Arrays.asList("Hanzo", "Hanzo3@m.co", "Hanzo1@m.co", "Hanzo0@m.co"));
        accounts2.add(Arrays.asList("Fern", "Fern5@m.co", "Fern1@m.co", "Fern0@m.co"));
        List<List<String>> expected2 = new ArrayList<>();
        expected2.add(Arrays.asList("Ethan", "Ethan0@m.co", "Ethan4@m.co", "Ethan5@m.co"));
        expected2.add(Arrays.asList("Gabe", "Gabe0@m.co", "Gabe1@m.co", "Gabe3@m.co"));
        expected2.add(Arrays.asList("Hanzo", "Hanzo0@m.co", "Hanzo1@m.co", "Hanzo3@m.co"));
        expected2.add(Arrays.asList("Kevin", "Kevin0@m.co", "Kevin3@m.co", "Kevin5@m.co"));
        expected2.add(Arrays.asList("Fern", "Fern0@m.co", "Fern1@m.co", "Fern5@m.co"));
        List<List<String>> result2 = solution.accountsMerge(accounts2);
        System.out.println("测试用例2:");
        System.out.println("预期输出: " + expected2);
        System.out.println("实际输出: " + result2);
    }
}


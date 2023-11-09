package com.geekbo.training.leetcode.design;

/**
 * 2241. Design an ATM Machine
 * Medium
 * There is an ATM machine that stores banknotes of 5 denominations: 20, 50, 100, 200, and 500 dollars. Initially the ATM is empty. The user can use the machine to deposit or withdraw any amount of money.
 * <p>
 * When withdrawing, the machine prioritizes using banknotes of larger values.
 * <p>
 * For example, if you want to withdraw $300 and there are 2 $50 banknotes, 1 $100 banknote, and 1 $200 banknote, then the machine will use the $100 and $200 banknotes.
 * However, if you try to withdraw $600 and there are 3 $200 banknotes and 1 $500 banknote, then the withdraw request will be rejected because the machine will first try to use the $500 banknote and then be unable to use banknotes to complete the remaining $100. Note that the machine is not allowed to use the $200 banknotes instead of the $500 banknote.
 * Implement the ATM class:
 * <p>
 * ATM() Initializes the ATM object.
 * void deposit(int[] banknotesCount) Deposits new banknotes in the order $20, $50, $100, $200, and $500.
 * int[] withdraw(int amount) Returns an array of length 5 of the number of banknotes that will be handed to the user in the order $20, $50, $100, $200, and $500, and update the number of banknotes in the ATM after withdrawing. Returns [-1] if it is not possible (do not withdraw any banknotes in this case).
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["ATM", "deposit", "withdraw", "deposit", "withdraw", "withdraw"]
 * [[], [[0,0,1,2,1]], [600], [[0,1,0,1,1]], [600], [550]]
 * Output
 * [null, null, [0,0,1,0,1], null, [-1], [0,1,0,0,1]]
 * <p>
 * Explanation
 * ATM atm = new ATM();
 * atm.deposit([0,0,1,2,1]); // Deposits 1 $100 banknote, 2 $200 banknotes,
 * // and 1 $500 banknote.
 * atm.withdraw(600);        // Returns [0,0,1,0,1]. The machine uses 1 $100 banknote
 * // and 1 $500 banknote. The banknotes left over in the
 * // machine are [0,0,0,2,0].
 * atm.deposit([0,1,0,1,1]); // Deposits 1 $50, $200, and $500 banknote.
 * // The banknotes in the machine are now [0,1,0,3,1].
 * atm.withdraw(600);        // Returns [-1]. The machine will try to use a $500 banknote
 * // and then be unable to complete the remaining $100,
 * // so the withdraw request will be rejected.
 * // Since the request is rejected, the number of banknotes
 * // in the machine is not modified.
 * atm.withdraw(550);        // Returns [0,1,0,0,1]. The machine uses 1 $50 banknote
 * // and 1 $500 banknote.
 */
class ATM {
    long[] notes = new long[5];                                                // Note: use long[] instead of int[] to avoid getting error in large testcases
    int[] denoms;

    public ATM() {
        denoms = new int[]{20, 50, 100, 200, 500};                               // create an array to represent money value.
    }

    public void deposit(int[] banknotesCount) {
        for (int i = 0; i < banknotesCount.length; i++) {
            notes[i] += banknotesCount[i];                                       // add new deposit money to existing
        }
    }

    public int[] withdraw(int amount) {
        int[] result = new int[5];                                              // create result array to store quantity of each notes we will be using to withdraw "amount"
        for (int i = 4; i >= 0; i--) {
            if (amount >= denoms[i]) {
                int quantity = (int) Math.min(notes[i], amount / denoms[i]);     // pick the minimum quanity. because if say, amount/denoms[i] gives 3 but you only have 1 note. so you have to use 1 only instead of 3
                amount -= denoms[i] * quantity;                                 // amount left = 100
                result[i] = quantity;
            }
        }
        if (amount != 0) {
            return new int[]{-1};
        }
        for (int i = 0; i < 5; i++) {
            notes[i] -= result[i];
        }                   // deduct the quantity we have used.
        return result;
    }
}
package com.geekbo.training.leetcode.list;

/**
 * 2296. Design a Text Editor
 * Hard
 * Topics
 * Companies
 * Hint
 * Design a text editor with a cursor that can do the following:
 * <p>
 * Add text to where the cursor is.
 * Delete text from where the cursor is (simulating the backspace key).
 * Move the cursor either left or right.
 * When deleting text, only characters to the left of the cursor will be deleted. The cursor will also remain within the actual text and cannot be moved beyond it. More formally, we have that 0 <= cursor.position <= currentText.length always holds.
 * <p>
 * Implement the TextEditor class:
 * <p>
 * TextEditor() Initializes the object with empty text.
 * void addText(string text) Appends text to where the cursor is. The cursor ends to the right of text.
 * int deleteText(int k) Deletes k characters to the left of the cursor. Returns the number of characters actually deleted.
 * string cursorLeft(int k) Moves the cursor to the left k times. Returns the last min(10, len) characters to the left of the cursor, where len is the number of characters to the left of the cursor.
 * string cursorRight(int k) Moves the cursor to the right k times. Returns the last min(10, len) characters to the left of the cursor, where len is the number of characters to the left of the cursor.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["TextEditor", "addText", "deleteText", "addText", "cursorRight", "cursorLeft", "deleteText", "cursorLeft", "cursorRight"]
 * [[], ["leetcode"], [4], ["practice"], [3], [8], [10], [2], [6]]
 * Output
 * [null, null, 4, null, "etpractice", "leet", 4, "", "practi"]
 * <p>
 * Explanation
 * TextEditor textEditor = new TextEditor(); // The current text is "|". (The '|' character represents the cursor)
 * textEditor.addText("leetcode"); // The current text is "leetcode|".
 * textEditor.deleteText(4); // return 4
 * // The current text is "leet|".
 * // 4 characters were deleted.
 * textEditor.addText("practice"); // The current text is "leetpractice|".
 * textEditor.cursorRight(3); // return "etpractice"
 * // The current text is "leetpractice|".
 * // The cursor cannot be moved beyond the actual text and thus did not move.
 * // "etpractice" is the last 10 characters to the left of the cursor.
 * textEditor.cursorLeft(8); // return "leet"
 * // The current text is "leet|practice".
 * // "leet" is the last min(10, 4) = 4 characters to the left of the cursor.
 * textEditor.deleteText(10); // return 4
 * // The current text is "|practice".
 * // Only 4 characters were deleted.
 * textEditor.cursorLeft(2); // return ""
 * // The current text is "|practice".
 * // The cursor cannot be moved beyond the actual text and thus did not move.
 * // "" is the last min(10, 0) = 0 characters to the left of the cursor.
 * textEditor.cursorRight(6); // return "practi"
 * // The current text is "practi|ce".
 * // "practi" is the last min(10, 6) = 6 characters to the left of the cursor.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= text.length, k <= 40
 * text consists of lowercase English letters.
 * At most 2 * 104 calls in total will be made to addText, deleteText, cursorLeft and cursorRight.
 * <p>
 * <p>
 * Follow-up: Could you find a solution with time complexity of O(k) per call?
 */

/**
 * 使用一个双向链表来表示文本内容，并使用一个指针 cursor 来表示光标的位置。
 * 每个节点 Node 包含一个字符 chr，以及指向前一个节点和后一个节点的引用 prev 和 next。
 * <p>
 * 在 TextEditor 的构造函数中，创建了一个虚拟的头节点 fakeHead 和尾节点 fakeTail。
 * 初始时，它们互相连接，并且 cursor 指向 fakeHead。
 * <p>
 * 添加文本时，遍历待添加的字符数组，创建新的节点，并将其插入到 cursor 之后的位置。
 * 然后更新指针和连接关系，最后将 cursor 指向新插入的节点。
 * 删除文本时，从 cursor 开始向前删除指定数量的字符。
 * 通过修改节点的连接关系，将待删除的节点从链表中移除。同时更新指针和连接关系，最后返回实际删除的字符数。
 * 向左移动光标时，将 cursor 向前移动指定次数，然后调用 printLeftString 方法返回光标位置左边的字符。
 * 向右移动光标时，将 cursor 向后移动指定次数，然后调用 printLeftString 方法返回光标位置左边的字符。
 * 如果 cursor 移动到了 fakeTail，则将其移动到前一个节点。
 * 算法复杂度分析：
 * <p>
 * 添加文本的时间复杂度为 O(k)，其中 k 是待添加的字符数。
 * 删除文本的时间复杂度为 O(k)，其中 k 是待删除的字符数。
 * 移动光标的时间复杂度为 O(k)，其中 k 是移动的次数。
 * printLeftString 方法中的循环最多执行 10 次，因此时间复杂度为 O(1)。
 */
class TextEditor {
    class Node {
        char chr;
        Node next, prev;

        Node(char _chr, Node _next, Node _prev) {
            this.chr = _chr;
            this.next = _next;
            this.prev = _prev;
        }
    }

    Node fakeHead, fakeTail, cursor;

    public TextEditor() {
        this.fakeHead = new Node('1', null, null);
        this.fakeTail = new Node('9', null, null);

        this.fakeHead.next = this.fakeTail;
        this.fakeTail.prev = this.fakeHead;

        this.cursor = this.fakeHead;
    }

    public void addText(String text) {
        for (char c : text.toCharArray()) {
            Node node = new Node(c, cursor.next, cursor);
            cursor.next.prev = node;
            cursor.next = node;
            cursor = node;
        }
    }

    public int deleteText(int k) {
        int x = k;
        while (x > 0 && cursor != fakeHead) {
            cursor.prev.next = cursor.next;
            cursor.next.prev = cursor.prev;
            cursor = cursor.prev;
            x--;
        }
        return k - x;
    }

    public String cursorLeft(int k) {
        while (k > 0 && cursor != fakeHead) {
            cursor = cursor.prev;
            k--;
        }
        return printLeftString(cursor);
    }

    public String cursorRight(int k) {
        while (k > 0 && cursor != fakeTail) {
            cursor = cursor.next;
            k--;
        }
        if (cursor == fakeTail) {
            cursor = fakeTail.prev;
        }
        return printLeftString(cursor);
    }

    private String printLeftString(Node temp) {
        int i = 0;
        StringBuffer sb = new StringBuffer();

        while (i < 10 && temp != fakeHead) {
            sb.append(Character.toString(temp.chr));
            temp = temp.prev;
            i++;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
        // Test case 1
        textEditor.addText("leetcode");
        textEditor.deleteText(4);
        textEditor.addText("practice");
        String result1 = textEditor.cursorRight(3);
        String result2 = textEditor.cursorLeft(8);
        int deletedChars = textEditor.deleteText(10);
        String result3 = textEditor.cursorLeft(2);
        String result4 = textEditor.cursorRight(6);
        System.out.println(result1); // "etpractice"
        System.out.println(result2); // "leet"
        System.out.println(deletedChars); // 4
        System.out.println(result3); // ""
        System.out.println(result4); // "practi"
    }
}

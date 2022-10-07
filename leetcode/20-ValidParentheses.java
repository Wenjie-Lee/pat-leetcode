/*20. Valid Parentheses
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 *
 * Example 1:
 * Input: s = "()"
 * Output: true
 *
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 *
 * Example 3:
 * Input: s = "(]"
 * Output: false
 *
 * Constraints:
 * 1 <= s.length <= 104
 * s consists of parentheses only '()[]{}'.
 * */

/* 解法：
 * 使用栈数据结构
 * 全部左括号入栈，遇到右括号时与栈顶对比，若是同一套括号，则栈顶出栈继续，否则为非法括号序列
 * */

import java.util.Stack;

class Solution {
    public boolean isValid(String s) {

        // a String start with right brackets always return false
        if (s.charAt(0) == ')' || s.charAt(0) == ']' || s.charAt(0) == '}')
            return false;

        // use Stack, less memory usage, little more time cost
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{')
                stack.push(c);
            else {
                if (stack.isEmpty()) return false;
                if (c == ')') {
                    if ('(' != stack.pop())
                        return false;
                } else if (c == ']') {
                    if ('[' != stack.pop())
                        return false;
                } else if (c == '}') {
                    if ('{' != stack.pop())
                        return false;
                }
            }
        }
        return stack.isEmpty();
    }
    public boolean isValid1(String s) {
        // least time cost, most memory usage
        char[] list = new char[s.length()];
        int idx = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(', '[', '{' -> list[idx++] = c;
                case ')' -> {
                    if (idx - 1 < 0 || list[--idx] != '(')
                        return false;
                }
                case ']' -> {
                    if (idx - 1 < 0 || list[--idx] != '[')
                        return false;
                }
                case '}' -> {
                    if (idx - 1 < 0 || list[--idx] != '{')
                        return false;
                }
            }
        }
        return idx == 0;
    }
}

class Test {
    public static void main(String[] args) {
        Solution s = new Solution();

        // test 0 null | "" guarantee that these will not occur
        // test 1
        System.out.println(s.isValid("()[]{}"));

        // test 2
        System.out.println(s.isValid("([{}])"));

        // test 3
        System.out.println(s.isValid("([{}()])[]"));

        // test 3 invalid situation
        System.out.println(s.isValid("([)]"));
        System.out.println(s.isValid("(["));
        System.out.println(s.isValid("})(]"));

    }
}
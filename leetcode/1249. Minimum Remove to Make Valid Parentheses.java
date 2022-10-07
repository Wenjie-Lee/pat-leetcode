/* 1249. Minimum Remove to Make Valid Parentheses
 *
 * */

import java.util.Stack;

class Solution {
    public String minRemoveToMakeValid(String s) {
        if (s == null) return null;

        // use a array instead of a Stack to make it faster
        char[] chars = s.toCharArray();
        Stack<Integer> st = new Stack<>();
        // remove paired parentheses, and mark the unpaired as '@'
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') st.push(i);
            else if (chars[i] == ')') {
                if (!st.isEmpty()) st.pop();
                else chars[i] = '@';
            }
        }
        while (!st.isEmpty())
            chars[st.pop()] = '@';

        StringBuilder stringBuilder = new StringBuilder();
        for (char aChar : chars) {
            if (aChar != '@') stringBuilder.append(aChar);
        }
        return stringBuilder.toString();
    }
    public String minRemoveToMakeValid2(String s) {
        if (s == null) return null;

        Stack<Character> st = new Stack<>();
        Stack<Character> parentheses = new Stack<>();
        // remove paired parentheses, and add the unpaired ')' into parentheses
        for (char c : s.toCharArray()) {
            if (c == '(') {             // may have unpaired '(' here
                st.push(c);
                parentheses.push(c);
            }
            else if (c == ')') {
                if (!parentheses.isEmpty() && parentheses.peek() == '(') {
                    parentheses.pop();
                    st.push(c);
                }
            }
            else st.push(c);
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!st.isEmpty()) {
            if (!parentheses.isEmpty()) {   // if parentheses not empty
                if (st.peek() == '(') {     // if remains is '('
                    st.pop();
                    parentheses.pop();
                }
                else stringBuilder.append(st.pop());
            } else stringBuilder.append(st.pop());
        }

        return stringBuilder.reverse().toString();
    }
}

class Test {
    public static void main(String[] args) {
        Solution s = new Solution();

        // test 0 s = "" | "()", Output: "" | "()"
        System.out.printf("'%s'\n", s.minRemoveToMakeValid(""));
        System.out.printf("'%s'\n", s.minRemoveToMakeValid("()"));
        System.out.printf("'%s'\n", s.minRemoveToMakeValid("abcd"));
        System.out.printf("'%s'\n", s.minRemoveToMakeValid("abcd()"));

        // test 1 s = "))((", Output: ""
        System.out.printf("'%s'\n", s.minRemoveToMakeValid("))(("));

        // test 2 s = "lee(t(c)o)de)", Output: "lee(t(c)o)de"
        System.out.printf("'%s'\n", s.minRemoveToMakeValid("lee(t(c)o)de)"));

        // test 3 s = "a)b(c)d", Output: "ab(c)d"
        System.out.printf("'%s'\n", s.minRemoveToMakeValid("a)b(c)d"));

        // test 5 s = "a))((abc)))d)", Output: "a((abc))d"
        System.out.printf("'%s'\n", s.minRemoveToMakeValid("a((abc))d"));

        // test 6 s = ")a(b(c))d))(e(f))))(d))(()(", Output: "a(b(c)d)(e(f))(d)()"
        System.out.printf("'%s'\n", s.minRemoveToMakeValid(")a(b(c))d))(e(f))))(d))(()("));

        // test 7 s = "())((((a)", Output: "()(a)"
        System.out.printf("'%s'\n", s.minRemoveToMakeValid("())((((a)"));
    }
}
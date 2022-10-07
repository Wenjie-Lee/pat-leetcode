/*1209. Remove All Adjacent Duplicates in String II
 * 给定一个字符串，其中包含重复字符；给定一个数字k，将`相邻`重复次数超过k次的字符删除至k以下
 * 输出剩余字符串
 * */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Solution {
    // stack version, time cost O(n)
    public String removeDuplicates(String s, int k) {
        if (s.length() < k) return s;
        Stack<Character> st = new Stack<>();
        Stack<Integer> lens = new Stack<>();

        for (char c: s.toCharArray()) {
            if (st.isEmpty()) {
                lens.push(1);
                st.push(c);
            } else {
                if (st.peek() == c) lens.push(lens.peek() + 1);
                else lens.push(1);
                st.push(c);             // 压入栈
            }

            if (lens.peek() == k) {     // 若栈中元素个数=k，
                for (int i = 0; i < k; i++) {
                    st.pop();
                    lens.pop();
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!st.isEmpty()) stringBuilder.append(st.pop());
        return stringBuilder.reverse().toString();
    }
    // queue version, Time Limit Exceeded
    public String removeDuplicates1(String s, int k) {
        Queue<Character> q = new LinkedList<>();
        for (Character c: s.toCharArray()) {
            q.add(c);
        }

        char cur;
        int len = 1, qSize = 0;
        boolean noDelete = false;
        while (true) {
            if (qSize == 0) {
                qSize = q.size();
                if (qSize < k) break;
                if (noDelete) break;
                else noDelete = true;
            }
            cur = q.poll();                 // 子字符串第一个字符
            len = 1;
            qSize--;
            while (qSize > 0 && len < k && cur == q.peek()) {// 记录重复子字符串长度, 每次只删除k个
                len++;
                qSize--;
                q.poll();
            }
            if (len == k) {                  // 长度==k，则都抛弃
                noDelete = false;
            } else {
                while (len > 0) {
                    q.add(cur);
                    len--;
                }
            }
        }
        char[] chars = new char[q.size()];
        int idx = 0;
        while (!q.isEmpty()) chars[idx++] = q.poll();
        return new String(chars);
    }
}

class Test {
    public static void main(String[] args) {
        Solution s = new Solution();

        // test 1 1 <= s.length <= 10^5, 2 <= k <= 10^4
        String s1 = "abcd";
        System.out.println(s.removeDuplicates(s1, 2));

        // test 2 s = "deeedbbcccbdaa", k = 3, Output: "aa"
        String s2 = "deeedbbcccbdaa";
        System.out.println(s.removeDuplicates(s2, 3));

        // test 3 s = "pbbcggttciiippooaais", k = 2, Output: "ps"
        String s3 = "pbbcggttciiippooaais";
        System.out.println(s.removeDuplicates(s3, 2));

        // test 4 s = "aabbba", k = 2, Output: ""
        String s4 = "aabbba";
        System.out.println(s.removeDuplicates(s4, 2));

    }
}
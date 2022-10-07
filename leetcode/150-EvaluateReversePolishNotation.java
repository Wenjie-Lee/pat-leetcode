import java.util.*;

/*150. Evaluate Reverse Polish Notation
 *
 * */
class Solution {
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length < 1) {
            System.out.println("String[] tokens is null or empty");
            return 0;
        }
        int[] integers = new int[tokens.length / 2 + 1];
        int idx = 0;
        for (String token : tokens) {
            switch (token) {
                case "+" -> {
                    integers[idx - 2] = integers[idx - 2] + integers[idx - 1];
                    idx--;
                }
                case "-" -> {
                    integers[idx - 2] = integers[idx - 2] - integers[idx - 1];
                    idx--;
                }
                case "*" -> {
                    integers[idx - 2] = integers[idx - 2] * integers[idx - 1];
                    idx--;
                }
                case "/" -> {
                    integers[idx - 2] = integers[idx - 2] / integers[idx - 1];
                    idx--;
                }
                default -> integers[idx++] = Integer.parseInt(token);
            }
        }
        return integers[0];
    }

    public int evalRPN1(String[] tokens) {
        if (tokens == null || tokens.length < 1) {
            System.out.println("String[] tokens is null or empty");
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            switch (token) {
                case "+" -> stack.push(stack.pop() + stack.pop());
                case "-" -> {
                    int a = stack.pop(), b = stack.pop();
                    stack.push(b - a);
                }
                case "*" -> stack.push(stack.pop() * stack.pop());
                case "/" -> {
                    int a = stack.pop(), b = stack.pop();
                    stack.push(b / a);
                }
                default -> stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}

class Test {
    public static void main(String[] args) {
        Solution s = new Solution();

        // test1
        System.out.println(s.evalRPN(null));
        System.out.println(s.evalRPN(new String[]{"1"}));
        System.out.println(s.evalRPN(new String[]{"1", "2", "-"}));

        // test2
        // Input: tokens = ["2","1","+","3","*"]
        // Output: 9
        // Explanation: ((2 + 1) * 3) = 9
        System.out.println(s.evalRPN(new String[] {"2", "1", "+", "3", "*"}));

        // Input: tokens = ["4","13","5","/","+"]
        // Output: 6
        // Explanation: (4 + (13 / 5)) = 6
        System.out.println(s.evalRPN(new String[] {"4","13","5","/","+"}));

        // Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
        // Output: 22
        // Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
        System.out.println(s.evalRPN(new String[] {"10","6","9","3","+","-11","*","/","*","17","+","5","+"}));
    }
}
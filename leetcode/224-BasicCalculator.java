import java.util.Stack;

/* 224. Basic Calculator
 *
 *
 * */
class Solution {
    // recursive way
    class Index {
        public int idx;
        Index() {
            idx = 0;
        }
    }
    public int calculate(String s) {
        Index idx = new Index();
        return cal(s, idx);
    }
    private int cal(String s, Index idx) {
        int num = 0, localRes = 0, sign = 1;
        while (idx.idx < s.length()) {
            char c = s.charAt(idx.idx++);
            switch (c) {
                case ' ' -> {}
                case '(' -> num = cal(s, idx);
                case ')' -> {
                    return localRes + sign * num;
                }
                case '+' -> {
                    localRes += sign * num;
                    num = 0;
                    sign = 1;
                }
                case '-' -> {
                    localRes += sign * num;
                    num = 0;
                    sign = -1;
                }
                default -> num = c - '0' + num * 10;
            }
        }
        return localRes + sign * num;
    }

    public int calculate1(String s) {
        // guarantee has a valid s
        Stack<Character> operators = new Stack<>();
        Stack<Integer> numbers = new Stack<>();
        Stack<Character> tempOp = new Stack<>();
        Stack<Integer> tempInt = new Stack<>();
        int currentInputNumber = 0;
        boolean hasChanged = false;
        char lastChar = '(';
        for (int i = -1; i <= s.length(); i++) {
            char c;
            if (i < 0) c = '(';
            else if (i == s.length()) c = ')';
            else c = s.charAt(i);
            switch (c) {
                case ' ' -> {}
                case '(' -> {
                    operators.push('(');
                    lastChar = c;
                }
                case '+' -> {
                    if (hasChanged) {
                        numbers.push(currentInputNumber);
                        currentInputNumber = 0;
                        hasChanged = false;
                    }
                    operators.push('+');
                    lastChar = c;
                }
                case '-' -> {
                    if (hasChanged) {
                        numbers.push(currentInputNumber);
                        currentInputNumber = 0;
                        hasChanged = false;
                    }
                    // for (-(1-2)) and 1-(-2) situation
                    if (lastChar == '(')
                        numbers.push(0);
                    operators.push('-');
                    lastChar = c;
                }
                case ')' -> {
                    if (hasChanged) {
                        numbers.push(currentInputNumber);
                        currentInputNumber = 0;
                        hasChanged = false;
                    }
                    while (!operators.peek().equals('(')) {
                        tempInt.push(numbers.pop());
                        tempOp.push(operators.pop());
                    }
                    tempInt.push(numbers.pop());
                    operators.pop();    // pop '('
                    while (!tempOp.isEmpty()) {
                        int a = tempInt.pop(), b = tempInt.pop();
                        char operator = tempOp.pop();
                        int after = operator == '+' ? a + b : a - b;
                        tempInt.push(after);
                    }
                    numbers.push(tempInt.pop());
                    lastChar = c;
                }
                default -> {
                    currentInputNumber = c - '0' + currentInputNumber * 10;
                    hasChanged = true;
                    lastChar = c;
                }
            }
        }
        return numbers.pop();
    }
}

class Test {
    public static void main(String[] args) {
        Solution s = new Solution();

        // test1
        System.out.println(s.calculate("2147483647"));
        System.out.println(s.calculate("1-(     -2)"));
        System.out.println(s.calculate("1 + 0"));
        System.out.println(s.calculate("0 + (0 - 0) + 0"));
        System.out.println(s.calculate(" 2-1 + 2 "));
        System.out.println(s.calculate("1+(0+2)-3"));
        System.out.println(s.calculate("(1+(4+5+2)-3)+(6+8)"));
        System.out.println(s.calculate("(1 + 2)"));
    }
}
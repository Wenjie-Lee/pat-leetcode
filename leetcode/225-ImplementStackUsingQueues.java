/*225. Implement Stack using Queues
 *
 * 使用两个queue实现stack
 * 解法：
 *
 * */

import java.util.LinkedList;
import java.util.Queue;

class MyStack {
    private Queue<Integer>  q1, q2;
    private boolean useQ1;
    private int top;

    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
        useQ1 = true;
        top = 0;
    }

    public void push(int x) {
        if (useQ1) {
            if (q1.add(x))
                top = x;
        }
        else {
            if (q2.add(x))
                top = x;
        }
    }

    public int pop() {
        // guarantee pop & top are all valid
        if (this.empty()) {
            System.out.println("stack is empty");
            return 0;
        } else {
            if (useQ1) {
                useQ1 = false;
                while (q1.size() > 1) {
                    top = q1.peek();
                    q2.add(q1.poll());
                }
                return q1.poll();
            } else {
                useQ1 = true;
                while (q2.size() > 1) {
                    top = q2.peek();
                    q1.add(q2.poll());
                }
                return q2.poll();
            }
        }
    }

    public int top() {
        // guarantee pop & top are all valid
        if (this.empty()) {
            System.out.println("stack is empty");
            return 0;
        }
        return top;
    }

    public boolean empty() {
        return q1.isEmpty() && q2.isEmpty();
    }
}

public class Solution {


}

class Test {
    public static void main(String[] args) {

        // test1
        System.out.println("test 1: -----------------------");
        MyStack myStack = new MyStack();
        myStack.push(1);
        System.out.println(myStack.top());
        System.out.println(myStack.empty());
        System.out.println(myStack.pop());
        System.out.println(myStack.empty());
        System.out.println("------------------------------");

        //test 2
        System.out.println("test 2: -----------------------");
        MyStack myStack1 = new MyStack();
        myStack1.push(1);
        myStack1.push(2);
        myStack1.push(3);
        myStack1.push(4);
        System.out.println(myStack1.empty());
        System.out.println(myStack1.top());
        System.out.println(myStack1.pop());
        System.out.println(myStack1.empty());
        System.out.println(myStack1.top());
        System.out.println(myStack1.pop());
        System.out.println(myStack1.empty());
        System.out.println(myStack1.top());
        System.out.println(myStack1.pop());
        System.out.println(myStack1.empty());
        System.out.println(myStack1.top());
        System.out.println(myStack1.pop());
        System.out.println(myStack1.empty());
        System.out.println(myStack1.top());
        System.out.println(myStack1.pop());
        System.out.println("-------------------------------");

        //test 3
        System.out.println("test 3: -----------------------");
        MyStack myStack2 = new MyStack();
        System.out.println(myStack1.empty());
        System.out.println(myStack1.top());
        System.out.println(myStack1.pop());
        myStack1.push(1);
        System.out.println(myStack1.empty());
        System.out.println(myStack1.top());
        System.out.println(myStack1.pop());
        myStack1.push(2);
        myStack1.push(3);
        System.out.println(myStack1.empty());
        System.out.println(myStack1.top());
        System.out.println(myStack1.pop());
        myStack1.push(4);
        System.out.println(myStack1.empty());
        System.out.println(myStack1.top());
        System.out.println(myStack1.pop());
        System.out.println(myStack1.empty());
        System.out.println(myStack1.top());
        System.out.println(myStack1.pop());
        System.out.println("-------------------------------");
    }
}
/*206
*
* */

import java.util.Stack;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class ReverseLinkedList {

    /**
     * @description: 反转 ListNode 列表
     * @param head ListNode 头
     * @return: ListNode 反转后的 ListNode 列表
     * @author: youzh
     * @date: 2022/9/29 0:36
     */
    public static ListNode reverseList(ListNode head) {
        // 递归方式
        // 5 -> 4 -> 3 -> 2 -> 1 ----- 1 -> 2 -> 3 -> 4 -> 5
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseList(head.next);  // 5 -> 4   1 -> 2 -> 3 -> 4
        head.next.next = head;                      // 4 -> 5 -> 4
        head.next = null;                           // 4 -> 5
        return newHead;

        // 非递归方式
//        if (head == null || head.next == null) return head;
//        Stack<ListNode> stack = new Stack<>();
//        while (head != null) {
//            stack.push(head);
//            head = head.next;
//        }
//        head = stack.pop();
//        ListNode node = head;
//        while (!stack.isEmpty()) {
//            node.next = stack.pop();
//            node.next.next = null;
//            node = node.next;
//        }
//        return head;
    }

    /**
     * @description: 打印 ListNode
     * @param head ListNode 头
     * @param prefix 为打印信息添加前缀
     * @Return: void
     * @Author: youzh
     * @Date: 2022/9/29 0:27
     */
    private static void printListNode(ListNode head, String prefix) {
        StringBuilder buffer = new StringBuilder(prefix);
        ListNode it = head;
        buffer.append("[");
        while (it != null) {
            buffer.append(it.val);
            if (it.next != null) {
                buffer.append(", ");
            }
            it = it.next;
        }
        System.out.println(buffer.append("]").toString());
    }

    /**
     * @Description: 根据一个int[]创建ListNode
     * @param array int[] array
     * @Return: ListNode ListNode 首节点地址
     * @Author: youzh
     * @Date: 2022/9/28 23:44
     */
    public static ListNode createListNodeByArray(int[] array) {
        if (array == null || array.length == 0) return null;

        ListNode cur = new ListNode(array[0]), head = cur;
        for (int i = 1; i < array.length; i++) {
            cur.next = new ListNode(array[i]);
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {

        // test1 null
        printListNode(null, "test1: ");
        ListNode testList1 = reverseList(null);
        testList1 = Solution.reverseList(testList1);
        printListNode(testList1, "test1: ");

        // test2 [1]
        int[] a1 = new int[] {1};
        ListNode testList2 = createListNodeByArray(a1);
        printListNode(testList2, "test2: ");
        testList2 = Solution.reverseList(testList2);
        printListNode(testList2, "test2: ");

        // test 3 [1,2,3]
        int[] a3 = new int[] {1,2,3};
        ListNode testList3 = createListNodeByArray(a3);
        printListNode(testList3, "test3: ");
        testList3 = Solution.reverseList(testList3);
        printListNode(testList3, "test3: ");

        // test4 [3,2,1]
        int[] a4 = new int[] {3,2,1};
        ListNode testList4 = createListNodeByArray(a4);
        printListNode(testList4, "test4: ");
        testList4 = Solution.reverseList(testList4);
        printListNode(testList4, "test4: ");

        // test5 [5,5,5,5,4,1,1,2,3]
        int[] a5 = new int[] {5,5,5,5,4,1,1,2,3};
        ListNode testList5 = createListNodeByArray(a5);
        printListNode(testList5, "test5: ");
        testList5 = Solution.reverseList(testList5);
        printListNode(testList5, "test5: ");

    }
}

/* 92. Reverse Linked List II
*
* */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null) return head;

        // guarantee 1 <= left <= right <= n <= 500
        int pi = 1;
        ListNode fakeHead = new ListNode();
        fakeHead.next = head;
        ListNode pPre = fakeHead;

        while (pi < left) {
            pPre = pPre.next;
            pi++;
        }
//        int reverseLenght = right - left + 1;
        ListNode q = pPre.next, qAfter = q.next;

        // avoid stack
        for (int i = 0; i < right - left; i++) {
            q.next = qAfter.next;
            qAfter.next = pPre.next;
            pPre.next = qAfter;
            qAfter = q.next;    // iter
        }

        // stack-using way is slow
//        Stack<ListNode> ll = new Stack<>();
//        while (pi <= right) {
//            ll.push(q);
//            q = q.next;
//            pi++;
//        }
//        while (!ll.isEmpty()) {
//            pPre.next = ll.pop();
//            pPre.next.next = null;
//            pPre = pPre.next;
//        }
//        pPre.next = q;
        return fakeHead.next;
    }

//    public static ListNode reverseList(ListNode head, int right, int now) {
//        // 递归方式
//        // 5 -> 4 -> 3 -> 2 -> 1 ----- 1 -> 2 -> 3 -> 4 -> 5
//        if (head == null || head.next == null) return head;
//        ListNode newHead = reverseList(head.next);  // 5 -> 4   1 -> 2 -> 3 -> 4
//        head.next.next = head;                      // 4 -> 5 -> 4
//        head.next = null;                           // 4 -> 5
//        return newHead;
//    }

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
        Solution s = new Solution();
        // test1 null
        printListNode(null, "test1: ");
        ListNode testList1 = s.reverseBetween(null, 0, 0);
        printListNode(testList1, "test1 solution: ");

        // test2 [5]
        int[] a1 = new int[] {5};
        ListNode testList2 = Solution.createListNodeByArray(a1);
        printListNode(testList2, "test2: ");
        testList2 = s.reverseBetween(testList2, 1, 1);
        printListNode(testList2, "test2 solution: ");

        // test 3 [1,2,3,4,5]
        int[] a3 = new int[] {1,2,3,4,5};
        ListNode testList3 = Solution.createListNodeByArray(a3);
        printListNode(testList3, "test3: ");
        testList3 = s.reverseBetween(testList3, 2, 4);
        printListNode(testList3, "test3 solution: ");

        // test4 [1,2,3,4,5]
        int[] a4 = new int[] {1,2,3,4,5};
        ListNode testList4 = Solution.createListNodeByArray(a4);
        printListNode(testList4, "test4: ");
        testList4 = s.reverseBetween(testList4, 2, 5);
        printListNode(testList4, "test4 solution: ");

        // test5 [1,2,3,4,5]
        int[] a5 = new int[] {1,2,3,4,5};
        ListNode testList5 = Solution.createListNodeByArray(a5);
        printListNode(testList5, "test5: ");
        testList5 = s.reverseBetween(testList5, 1, 3);
        printListNode(testList5, "test5 solution: ");

        // test6 [1,2,3,4,5]
        int[] a6 = new int[] {1,2,3,4,5};
        ListNode testList6 = Solution.createListNodeByArray(a6);
        printListNode(testList6, "test6: ");
        testList6 = s.reverseBetween(testList6, 1, 5);
        printListNode(testList6, "test6 solution: ");

        // test6 [1,2,3,4,5]
        int[] a7 = new int[] {1,2,3,4,5};
        ListNode testList7 = Solution.createListNodeByArray(a7);
        printListNode(testList7, "test7: ");
        testList7 = s.reverseBetween(testList7, 3, 3);
        printListNode(testList7, "test7 solution: ");

    }
}
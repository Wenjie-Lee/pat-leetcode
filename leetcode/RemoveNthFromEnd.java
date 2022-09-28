
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class RemoveNthFromEnd {

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        // cal the length of ListNode
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len += 1;
            cur = cur.next;
        }
        if (len < n) {
            System.out.printf("Not enough nodes to %d\n", n);
            return null;
        }

        // guarantee len >= k
        cur = head;
        while (n-- > 0) {
            cur = cur.next;
        }
        ListNode fakeHead = new ListNode();
        fakeHead.next = head;
        ListNode cur2 = head, pre2 = fakeHead;
        while (cur != null) {
            pre2 = pre2.next;
            cur2 = cur2.next;
            cur = cur.next;
        }

        pre2.next = cur2.next;

        return fakeHead.next;
    }

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

    public static void main(String[] args) {

        int n = 4;
        // test1 null ListNode head
        printListNode(null, "test1: ");
        ListNode testList1 = Solution.removeNthFromEnd(null, n);
        printListNode(testList1, "test1: ");
        System.out.println("should be null []");

        // test2 nodes length less than 4
        ListNode testList2 = new ListNode(1);
        printListNode(testList2, "test2: ");
        testList2 = Solution.removeNthFromEnd(testList2, n);
        printListNode(testList2, "test2: ");
        System.out.println("print not enougth nodes to 4");

        // test3 nodes length equal to 4
        ListNode testList3 = new ListNode(5);
        ListNode temp3 = testList3;
        for (int i = 0; i < 4; i++) {
            temp3.next = new ListNode(i);
            temp3 = temp3.next;
        }
        testList3 = testList3.next;
        printListNode(testList3, "test3: ");
        testList3 = Solution.removeNthFromEnd(testList3, n);
        printListNode(testList3, "test3: ");

        // test4 nodes length bigger than 4
        ListNode testList4 = new ListNode(5);
        ListNode temp4 = testList4;
        for (int i = 0; i < 7; i++) {
            temp4.next = new ListNode(i);
            temp4 = temp4.next;
        }
        testList4 = testList4.next;
        printListNode(testList4, "test4: ");
        testList4 = Solution.removeNthFromEnd(testList4, n);
        printListNode(testList4, "test4: ");

        // test5 nodes length bigger than 4, and length is even
        ListNode testList5 = new ListNode(5);
        ListNode temp5 = testList5;
        for (int i = 0; i < 10; i++) {
            temp5.next = new ListNode(i);
            temp5 = temp5.next;
        }
        testList5 = testList5.next;
        printListNode(testList5, "test5: ");
        testList5 = Solution.removeNthFromEnd(testList5, n);
        printListNode(testList5, "test5: ");

}
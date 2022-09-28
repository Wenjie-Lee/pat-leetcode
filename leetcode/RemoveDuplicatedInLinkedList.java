class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {

    // 删除重复节点，重复节点只留一个
    public static ListNode deleteDuplicates(ListNode head) {
        // guaranteed to be sorted in ascending order, means same value gathering together
        if (head == null) return null; //null 无删除操作

        ListNode p = head;
        while (p.next != null) {
            if (p.val == p.next.val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return head;
    }

    // 删除重复节点，且重复节点一个不留
    public static ListNode deleteDuplicates2(ListNode head) {
        // guaranteed to be sorted in ascending order, means same value gathering together
//        if (head == null) return null; //null 无删除操作
//        int[] map = new int[201];
//
//        ListNode p = head, newHead = new ListNode(), q = newHead;
//        while (p != null) {
//            map[p.val + 100] += 1;
//            p = p.next;
//        }
//        p = head;
//        while (p != null) {
//            if (map[p.val + 100] == 1) {
//                q.next = p;
//                q = p;
//                p = p.next;
//                q.next = null;
//            }
//            else {
//                p = p.next;
//            }
//        }
//        return newHead.next;
        if (head == null || head.next == null) return head; //null 无删除操作

        ListNode headnote = new ListNode(101);
        headnote.next = head;
        ListNode cur = head, pre = headnote;

        while (cur != null) {
            while (cur.next != null && cur.next.val == cur.val) {
                cur = cur.next;
            }
            if (pre.next == cur) {
                pre = pre.next;
            }
            else {
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return headnote.next;
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

        // test1 null Listnode head
        ListNode testList1 = null;
        printListNode(testList1, "test1: ");
        testList1 = Solution.deleteDuplicates(testList1);
        printListNode(testList1, "test1: ");
        // test2 head has 1 ListNode
        ListNode testList2 = new ListNode(1);
        printListNode(testList2, "test2: ");
        testList2 = Solution.deleteDuplicates(testList2);
        printListNode(testList2, "test2: ");
        // test3 no duplicated ListNodes
        ListNode testList3 = new ListNode(1);
        testList3.next = new ListNode(2);
        printListNode(testList3, "test3: ");
        testList3 = Solution.deleteDuplicates(testList3);
        printListNode(testList3, "test3: ");
        // test4 some duplicateds
        ListNode testList4 = new ListNode(1);
        ListNode temp = testList4;
        temp.next = new ListNode(1);
        temp = temp.next;
        temp.next = new ListNode(2);
        temp = temp.next;
        temp.next = new ListNode(3);
        temp = temp.next;
        temp.next = new ListNode(3);
        printListNode(testList4, "test4: ");
        testList4 = Solution.deleteDuplicates(testList4);
        printListNode(testList4, "test4: ");
        // test5 all are duplicateds
        ListNode testList5 = new ListNode(5);
        ListNode temp1 = testList5;
        for (int i = 0; i < 5; i++) {
            temp1.next = new ListNode(5);
            temp1 = temp1.next;
        }
        printListNode(testList5, "test5: ");
        testList5 = Solution.deleteDuplicates(testList5);
        printListNode(testList5, "test5: ");

    }
}
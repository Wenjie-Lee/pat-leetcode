
/* 876. Middle of the Linked List
 * Given the head of a singly linked list, return the middle node of the linked list.
 * If there are two middle nodes, return the second middle node.
 * 给定单链表，返回链表中间节点，如果中间节点有两个，返回第二个
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [3,4,5]
 * Explanation: The middle node of the list is node 3.
 *
 * Example 2:
 * Input: head = [1,2,3,4,5,6]
 * Output: [4,5,6]
 * Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.
 *
 * Constraints:
 * The number of nodes in the list is in the range [1, 100].
 * 1 <= Node.val <= 100
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
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode p = head, q = p;
        while (q != null && q.next != null) {
            p = p.next;
            q = q.next.next;
        }
        return p;
    }
    /**
     * @description: 打印 ListNode
     * @param head ListNode 头
     * @param prefix 为打印信息添加前缀
     * @Return: void
     * @Author: youzh
     * @Date: 2022/9/29 0:27
     */
    public static void printListNode(ListNode head, String prefix) {
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
}

class Test {
    public static void main(String[] args) {
        Solution s = new Solution();

        // test 1 null | one node
        Solution.printListNode(null, "test1: ");
        ListNode testList1 = s.middleNode(null);
        Solution.printListNode(testList1, "test1 middle: ");

        // test 2
        ListNode testList2 = Solution.createListNodeByArray(new int[] {1,2});
        Solution.printListNode(testList2, "test2: ");
        testList2 = s.middleNode(testList2);
        Solution.printListNode(testList2, "test2 middle: ");

        // test 3
        ListNode testList3 = Solution.createListNodeByArray(new int[] {1,2,3,4,5});
        Solution.printListNode(testList3, "test3: ");
        testList3 = s.middleNode(testList3);
        Solution.printListNode(testList3, "test3 middle: ");

        // test 4
        ListNode testList4 = Solution.createListNodeByArray(new int[] {1,2,3,4,5,6});
        Solution.printListNode(testList4, "test4: ");
        testList4 = s.middleNode(testList4);
        Solution.printListNode(testList4, "test4 middle: ");

    }
}
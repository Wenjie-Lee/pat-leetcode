/* 328. Odd Even Linked List
 * 给定一个单链表，将奇数节点链接，然后将偶数节点链接
 *
 * The first node is considered odd, and the second node is even, and so on.
 * Note that the relative order inside both the even and odd groups should remain as it was in the input.
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 *
 * Constraints:
 * The number of nodes in the linked list is in the range [0, 104].
 * -106 <= Node.val <= 106
 *
 * 解题思路：
 * 快慢指针分别链接到不同的头节点上，然后链接两个头节点
 * */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode oddHead = new ListNode(), evenHead = new ListNode();
        ListNode oh = oddHead, eh = evenHead;

        ListNode o = head;
        while (o != null && o.next != null) {
            oh.next = o;
            eh.next = o.next;
            o = o.next.next;
            oh = oh.next;
            eh = eh.next;
        }
        oh.next = null;
        eh.next = null;
        // if last if odd(last even has been dealt with by the while loop)
        if (o != null) {
            o.next = null;
            oh.next = o;
            oh = oh.next;
        }

        oh.next = evenHead.next;
        return oddHead.next;
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

        // test 1 []
        Solution.printListNode(null, "test1: ");
        ListNode t1 = s.oddEvenList(null);
        Solution.printListNode(t1, "test1 s: ");

        // test 2 head = [1,2], Output: [1,2]
        ListNode i2 = Solution.createListNodeByArray(new int[] {1,2});
        Solution.printListNode(i2, "test2: ");
        ListNode t2 = s.oddEvenList(i2);
        Solution.printListNode(t2, "test2 s: ");

        // test 3 head = [1,2,3,4,5], Output: [1,3,5,2,4]
        ListNode i3 = Solution.createListNodeByArray(new int[] {1,2,3,4,5});
        Solution.printListNode(i3, "test3: ");
        ListNode t3 = s.oddEvenList(i3);
        Solution.printListNode(t3, "test3 s: ");
    }
}
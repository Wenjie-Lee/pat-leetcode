/* 160. Intersection of Two Linked Lists
 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect.
 * If the two linked lists have no intersection at all, return null.
 * For example, the following two linked lists begin to intersect at node c1:
 *
 * The test cases are generated such that there are no cycles anywhere in the entire linked structure.
 * Note that the linked lists must retain their original structure after the function returns.
 *
 * Custom Judge:
 * The inputs to the judge are given as follows (your program is not given these inputs):
 * intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
 * listA - The first linked list.
 * listB - The second linked list.
 * skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
 * skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
 *
 * The judge will then create the linked structure based on these inputs and pass the two heads,
 * headA and headB to your program. If you correctly return the intersected node, then your solution will be accepted.
 *
 * Constraints:
 * The number of nodes of listA is in the m.
 * The number of nodes of listB is in the n.
 * 1 <= m, n <= 3 * 10^4
 * 1 <= Node.val <= 10^5
 * 0 <= skipA < m
 * 0 <= skipB < n
 * intersectVal is 0 if listA and listB do not intersect.
 * intersectVal == listA[skipA] == listB[skipB] if listA and listB intersect.
 *
 * Follow up: Could you write a solution that runs in O(m + n) time and use only O(1) memory?
 * */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        int lenA = 0, lenB = 0;

        // cal the len of two linked list
        ListNode head = headA;
        while (head != null) {
            lenA += 1;
            head = head.next;
        }
        head = headB;
        while (head != null) {
            lenB += 1;
            head = head.next;
        }

        // make linked list have same length(count from backward)
        while (lenA > lenB) {
            headA = headA.next;
            lenA--;
        }
        while (lenB > lenA) {
            headB = headB.next;
            lenB--;
        }

        while (headA != null && headB != null) {
            if (headA == headB)
                return headA;
            else {
                headA = headA.next;
                headB = headB.next;
            }
        }
        return null;
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
        ListNode testList1 = s.getIntersectionNode(null, null);
        Solution.printListNode(testList1, "test1 middle: ");

        // test 2
        ListNode testList2 = Solution.createListNodeByArray(new int[] {4,1,8,4,5});
        ListNode testList2_1 = Solution.createListNodeByArray(new int[] {5,6,1,8,4,5});
        Solution.printListNode(testList2, "test2: ");
        Solution.printListNode(testList2_1, "test2: ");
        testList2 = s.getIntersectionNode(testList2, testList2_1);
        Solution.printListNode(testList2, "test2 middle: ");
    }
}
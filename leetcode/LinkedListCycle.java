/* 141. Linked List Cycle
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * There is a cycle in a linked list if there is some node in the list that can be reached again
 * by continuously following the next pointer. Internally, pos is used to denote the index of the
 * node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 *
 * 给定一个单链表，判断是否有循环节
 *
 * Follow up: Can you solve it using O(1) (i.e. constant) memory?
 * */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        // all 1 node return branch
        if (head.next == head) return true;
        if (head.next == null) return false;

        // 1 more node branch
        // 利用两个快慢指针互相追赶，若有环则二者一定会相遇，否则快指针一定会先指向null
        ListNode p1 = head, p2 = head;
        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
            if (p2 == null) break;
            else p2 = p2.next;
            if (p1 == p2) break;
        }

        return p2 != null;
    }
}

class Test {
    public static void main(String[] args) {
        Solution s = new Solution();



    }
}
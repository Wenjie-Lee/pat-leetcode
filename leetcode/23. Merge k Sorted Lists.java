/*23. Merge k Sorted Lists
 *
 * */

import java.util.Comparator;
import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }
}

// the minHeap-using method and mergeSort method are both O(nlogn) in average
// but add()&remove() for minHeap are time-coster comparing to just the connections
class Solution {
    // use MergeSort, naturally divide, so conquer
    // time cost O(nlogn), memory usage O(logn) for recursive calls
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];

        return mergeSort(lists, 0, lists.length - 1);
    }
    // mergeSort for LinkedList
    private ListNode merge(ListNode left, ListNode right) {
        if (left == null) return right;
        if (right == null) return left;

        ListNode head = null, tail = null;
        // merge left & right list
        while (left != null && right != null) {
            ListNode curr;
            if (left.val <= right.val) {
                curr = left;
                left = left.next;
            } else {
                curr = right;
                right = right.next;
            }
            // insert
            if (head == null) {
                head = tail = curr;
            } else {
                tail.next = curr;
                tail = curr;
                tail.next = null;
            }
        }
        // check if len(left) != len(right)
        if (left != null) tail.next = left; // while() for array
        if (right != null) tail.next = right;
        return head;
    }
    private ListNode mergeSort(ListNode[] lists, int low, int high) {
        if (low > high) return null;
        else if (low == high) return lists[low];

        int mid = low + (high - low) / 2;
        ListNode left = mergeSort(lists, low, mid);
        ListNode right = mergeSort(lists, mid + 1, high);
        return merge(left, right);
    }
    // O(nlogn) in average
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists.length == 0) return null;
        ListNode fakeHead = new ListNode(), head = fakeHead;
        PriorityQueue<ListNode> pq =
                new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        while (true) {
            boolean allNull = true;
            for (int i = 0; i < lists.length; i++) {    // O(n)
                ListNode temp = lists[i];
                while (temp != null) {      // O(k)
                    ListNode now = temp;
                    temp = temp.next;
                    now.next = null;
                    pq.add(now);            // O(logX) X∈[1, n]
                    allNull = false;
                }
                lists[i] = null;
            }
            if (allNull) break;
        }
        while (!pq.isEmpty()) {
            head.next = pq.remove();        // O(logX) X∈[1, n]
            head = head.next;
        }
        return fakeHead.next;
    }
    // O(nlogk) in average
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];

        ListNode dummy = new ListNode(), head = dummy;
        PriorityQueue<ListNode> minHeap =
                new PriorityQueue<>(lists.length, Comparator.comparingInt(o -> o.val));
        for (ListNode node : lists) {
            if (node != null) minHeap.add(node); // O(logk), k = list.length
        }
        while (!minHeap.isEmpty()) { // O(n * logk)
            ListNode curList = minHeap.remove(); // always out the smallest ListNode
            if (curList.next != null)
                minHeap.add(curList.next);
            head.next = curList;
            head = head.next;
        }
        return dummy.next;
    }

    public static ListNode[] createkList(int[] nums, int[] len) {
        ListNode[] ans = new ListNode[len.length];
        int idx = 0;
        for (int i = 0; i < len.length; i++) {
            ListNode fakeHead = new ListNode();
            ListNode head = fakeHead;
            while (idx < len[i]) {
                ListNode now = new ListNode(nums[idx++]);
                head.next = now;
                head = now;
            }
            ans[i] = fakeHead.next;
        }
        return ans;
    }

    public static String ListNodeToString(ListNode head) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        while (head != null) {
            stringBuilder.append(head.val);
            if (head.next != null)
                stringBuilder.append(",");
            head = head.next;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}

class Test {
    public static void main(String[] args) {
        Solution s = new Solution();
        // test0: lists = [] -> []
        System.out.println(
                Solution.ListNodeToString(s.mergeKLists(new ListNode[]{})));
        System.out.println(
                Solution.ListNodeToString(s.mergeKLists(new ListNode[]{null})));
        // test1: lists = [[1,4,5],[1,3,4],[2,6]] -> [1,1,2,3,4,4,5,6]
        System.out.println(
                Solution.ListNodeToString(
                        s.mergeKLists(Solution.createkList(new int[]{1,4,5,1,3,4,2,6}, new int[]{3,6,8}))
                )
        );

    }
}
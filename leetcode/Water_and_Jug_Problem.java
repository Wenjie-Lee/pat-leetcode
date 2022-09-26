/*
365. Water and Jug Problem

You are given two jugs with capacities jug1Capacity and jug2Capacity liters. There is an infinite amount of water supply
available. Determine whether it is possible to measure exactly targetCapacity liters using these two jugs.
给定两个水池和水池容量，给定一个目标容量；若有限次迭代下一个或两个水池中能得到目标容量，返回成功
If targetCapacity liters of water are measurable, you must have targetCapacity liters of water contained within one or
both buckets by the end.

Operations allowed:
Fill any of the jugs with water.
Empty any of the jugs.
Pour water from one jug into another till the other jug is completely full, or the first jug itself is empty.
允许的操作：1. 充满泳池；2. 清空泳池；3。转移A水池到B水池，知道A空了或者B满了
Example 1:
Input: jug1Capacity = 3, jug2Capacity = 5, targetCapacity = 4
Output: true
Explanation: The famous Die Hard example

* Example 2:
Input: jug1Capacity = 2, jug2Capacity = 6, targetCapacity = 5
Output: false

* Example 3:
Input: jug1Capacity = 1, jug2Capacity = 2, targetCapacity = 3
Output: true

Constraints:
1 <= jug1Capacity, jug2Capacity, targetCapacity <= 10^6
* */

/*
求两个数的加减集合，即集合内任意两个数的加减求得的数依然在集合内
3, 5->4 True 即 [3,5,8] 5-3=2,5+3=8, [1,2,3,5,8] 3-2=1, [1,2,3,5,8] 1+3=4,
2, 6->3 False 即 [2,6,8] 6-2=4,6+2=8, [2,4,6,8], 4-2=2



不能使用动态规划，因为不满足子问题无后效性，
例如：
    11，3的全集 -> 8,3的全集 -> 5,3的全集 -> 3，2的全集 -> 2，1的全集
                                                    2，1的全集子问题会影响3，2；5，3子问题
* */


import java.util.*;

//class Solution {
//    public int GCD(int x, int y) {
//        while (y != 0) {
//            int t = y;
//            y = x % y;
//            x = t;
//        }
//        return x;
//    }
//    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
//        // 边界处理
//        if (jug1Capacity < 0 || jug2Capacity < 0
//                || targetCapacity < 0) return false;
//        if (targetCapacity > jug1Capacity + jug2Capacity) return false;
//        if (jug1Capacity == targetCapacity || jug2Capacity == targetCapacity
//                || jug1Capacity + jug2Capacity == targetCapacity) return true;
//
////        int[] visited = new int[1000000];
////        Queue<Integer> q = new LinkedList<>();
//
//        return targetCapacity % GCD(jug1Capacity, jug2Capacity) == 0;
//    }
//}

class Solution {
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        // 边界处理
        if (jug1Capacity < 0 || jug2Capacity < 0
                || targetCapacity < 0) return false;
        if (targetCapacity > jug1Capacity + jug2Capacity) return false;
        if (jug1Capacity == targetCapacity || jug2Capacity == targetCapacity
                || jug1Capacity + jug2Capacity == targetCapacity) return true;

//        int[] visited = new int[1000000];
        HashSet<Integer> set = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur + jug1Capacity < jug1Capacity + jug2Capacity &&
                    set.add(cur + jug1Capacity)) {
                q.add(cur + jug1Capacity);
//                visited[cur + jug1Capacity] = 1;
            }
            if (cur + jug2Capacity < jug1Capacity + jug2Capacity &&
                    set.add(cur + jug2Capacity)) {
                q.add(cur + jug2Capacity);
//                visited[cur + jug2Capacity] = 1;
            }
            if (cur - jug1Capacity >= 0 && set.add(cur - jug1Capacity)) {
                q.add(cur - jug1Capacity);
//                visited[cur - jug1Capacity] = 1;
            }
            if (cur - jug2Capacity >= 0 && set.add(cur - jug2Capacity)) {
                q.add(cur - jug2Capacity);
//                visited[cur - jug2Capacity] = 1;
            }
//            if (visited[targetCapacity] == 1) return true;
            if (set.contains(targetCapacity)) return true;
        }
        return false;
    }
}
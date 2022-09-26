/*
1492. The kth Factor of n
数n的第K个因数
You are given two positive integers n and k. A factor of an integer n is defined as an integer i where n % i == 0.
返回第K个因数，若n没有k个因数，则返回-1
Consider a list of all factors of n sorted in ascending order, return the kth factor in this list or return -1 if
n has less than k factors.

Example 1:
Input: n = 12, k = 3
Output: 3
Explanation: Factors list is [1, 2, 3, 4, 6, 12], the 3rd factor is 3.

Example 2:
Input: n = 7, k = 2
Output: 7
Explanation: Factors list is [1, 7], the 2nd factor is 7.

Example 3:
Input: n = 4, k = 4
Output: -1
Explanation: Factors list is [1, 2, 4], there is only 3 factors. We should return -1.

Constraints:
1 <= k <= n <= 1000
Could you solve this problem in less than O(n) complexity?
* */

/*
先获取所有数n的因数列表
* */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int kthFactor(int n, int k) {

        int cur = 0, factor = 1;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                cur++;
                if (cur == k) {
                    factor = i;
                }
            }
        }
        if (cur < k) {
            return -1;
        }
        else {
            return factor;
        }
    }
}
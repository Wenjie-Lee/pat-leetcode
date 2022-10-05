/*
* 1716. Calculate Money in Leetcode Bank
*
Hercy wants to save money for his first car. He puts money in the Leetcode bank every day.
周一放1块，之后每天都比前一天多放一块；且每个周一都比上一个周一多放一块
He starts by putting in $1 on Monday, the first day. Every day from Tuesday to Sunday, he will put in $1 more than the
day before. On every subsequent Monday, he will put in $1 more than the previous Monday.
Given n, return the total amount of money he will have in the Leetcode bank at the end of the n-th day.

* Example 1:
Input: n = 4
Output: 10
Explanation: After the 4th day, the total is 1 + 2 + 3 + 4 = 10.

* Example 2:
Input: n = 10
Output: 37
Explanation: After the 10th day, the total is (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4) = 37.
Notice that on the 2nd Monday, Hercy only puts in $2.

* Example 3:
Input: n = 20
Output: 96
Explanation: After the 20th day, the total is (1 + 2 + 3 + 4 + 5 + 6 + 7) +
(2 + 3 + 4 + 5 + 6 + 7 + 8) + (3 + 4 + 5 + 6 + 7 + 8) = 96.

Constraints:
1 <= n <= 1000
 */

class Solution {
    public static int getWeekMoney(int day, int whichWeek) {
        int res = 0;
        int curDay = 1;
        while (curDay <= day) {
            res += curDay++ + whichWeek;
        }
        return res;
    }
    public int totalMoney(int n) {
        if (n == 0) return 0;
        int day = n % 7, week = n / 7;
        int res = 0;
        for (int i = 0; i <= week; i++) {
            if (i < week) { res += getWeekMoney(7, i); }
            else { { res += getWeekMoney(day, i); } }
        }

        return res;
    }
}
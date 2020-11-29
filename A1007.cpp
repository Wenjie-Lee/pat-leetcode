//#include <iostream>
#include <cstdio>
#include <cstring>
//#include <string>
//#include <queue>
//#include <vector>
#include <algorithm>

using namespace std;

// A 1007
// 解题思路1：给定一个序列, 求一个子序列，这个子序列相比其他，它的和是最大的。 
// ***注意 答案有可能不止有一个子序列 ， 但只输出子序列头尾索引最小的（即最早出现的子序列） 
// ***注意 序列有可能全为负数，此时 sum = 0， 头尾即是序列的头尾（题目要求） 
 
// *1
// 6
// -2 11 -4 13 -5 -2
// :20 1 3
// *2
// 10
// -10 1 2 3 4 -5 -23 3 7 -21
// :20 1 4
// *3
// 2
// 0 0
// *4
// 3
// -1 -2 -4
// *5
// 3
// 1 -1 -2

// const int maxn = 10010;
// int k, sum[maxn]{0};
// int l = 1, h = k, maxsum = -1, templ = 1;
// bool nflag = true; 

// int main() {
//  scanf("%d", &k);
//  // sum[0] = 0 作为哨兵, 减少检测 
//  for(int i = 1; i <= k; i++) {
//      int t = 0;
//      scanf("%d", &t);
//      if(t >= 0) nflag = false;
//      sum[i] = sum[i-1] + t;
        
//      // templ记录每次计算时子序列的开头 
//      int tempsum = sum[i] - sum[templ-1];
//      if(tempsum < 0) {
//          // 若新加入的数使得sum<0，则 
//          templ = i + 1;
//      }
//      else if(tempsum > maxsum) {
//          // 当 
//          maxsum = tempsum;
//          l = templ;
//          h = i;
//      }
//  }
//  // sum[]， 求i，j，使得sum[j]-sum[i-1]值最大
//  if(nflag) printf("0 %d %d", sum[1], sum[k] - sum[k-1]);
//  else printf("%d %d %d", maxsum, sum[l]-sum[l-1], sum[h] - sum[h-1]);
//  return 0;
// }

/*
给定一个序列A1~n，求出i，j，使sum(Ai,Ai+1,...,Aj)最大
解题思路2：
    定义数组b，b[i] = sum[a0~ai]，
    b[j]-b[i-1]即是ai~aj子序列的和；
    求解最大子序列和，就是从b[j]-b[i-1]中找出最大

*1
6
-2 11 -4 13 -5 -2
:20 1 3
*1
10
-10 1 2 3 4 -5 -23 3 7 -21
:20 1 4
*/
// const int maxn = 10010;
// int n, a[maxn], b[maxn], max_sum = -100000000, x, y;
// int main() {
//  bool flag = true;
//  scanf("%d", &n);
//  for (int i = 1; i <= n; i++) {
//      scanf("%d", &a[i]);
//      // 用于判断是否全负
//      if (a[i] >= 0)
//          flag = false;
//      b[i] = a[i];
//      b[i] += b[i - 1];
//  }
//  if (flag) {
//      printf("0 %d %d", a[1], a[n]);
//      return 0;
//  }
//  int t;
//  for (int i = 1; i <= n; i++)
//  {
//      for (int j = i; j <= n; j++)
//      {
//          t = b[j] - b[i - 1];
//          if (t > max_sum) {
//              max_sum = t;
//              x = i;
//              y = j;
//          }
//      }
//  }
//  printf("%d %d %d", max_sum, a[x], a[y]);
//  return 0;
// }

/*
解题思路3：
    动态规划法解题。
*/
const int maxn = 10010;
int n, a[maxn], dp[maxn], s[maxn];  // s[] 记录子序列开头的索引
int main() {
    bool flag = true;
    scanf("%d", &n);
    for (int i = 1; i <= n; i++) {
        scanf("%d", &a[i]);
        // 用于判断是否全负
        if (a[i] >= 0)
            flag = false;
    }
    if (flag) {
        printf("0 %d %d", a[1], a[n]);
        return 0;
    }
    dp[1] = a[1];
    s[1] = 1;
    int t;
    for (int i = 2; i <= n; i++)
    {
        t = dp[i - 1] + a[i];
        if (t > a[i]) {
            dp[i] = t;
            s[i] = s[i - 1];
        }
        else {
            dp[i] = a[i];
            s[i] = i;
        }
    }
    t = 1;
    for (int i = 2; i <= n; i++)
        if (dp[i] > dp[t])
            t = i;
    printf("%d %d %d", dp[t], a[s[t]], a[t]);
    return 0;
}

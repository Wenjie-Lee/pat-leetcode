#include <cstdio>
#include <cstring>
//#include <iostream>
//#include <string>
//#include <vector>
//#include <map>
//#include <queue>
#include <algorithm>
//#include <fstream>
//#include <functional>
//#include <time.h>

using namespace std;

// A 1040 Longest Symmetric String (25分)
/*
解题思路2：最长回文子序列
dp[i][j] = dp[i+1][j-1], str[i] == str[j];
dp[i][j] = 0, str[i] != str[j]
边界条件：
    (1) dp[i][i] = 1;
    (2) dp[i][i+1] = (str[i] == str[i+1]) ? 1:0;

*1
Is PAT&TAP symmetric?
:11(s PAT&TAP s)
*2
12
:1
*3
121
:3
*/
const int maxn = 1010;
int dp[maxn][maxn];
char str[maxn];
int main() {
    //scanf("%s", str);
    fgets(str, maxn, stdin);
    int len = strlen(str);
    str[--len] = '\0';

    // 初始化边界条件，即1位时和2位时
    for (int i = 0; i < len; i++) {
        dp[i][i] = 1;
        if (i < len - 1)
            dp[i][i + 1] = (str[i] == str[i + 1]) ? 1 : 0;
    }
    // 从3位开始判断回文字符串
    int l = 3, ans = 1;
    for (l; l <= len; l++)
    {
        // 0~i+l-1 -> len-l+1~len
        for (int i = 0; i + l - 1 < len; i++)
        {
            int j = i + l - 1;
            if (str[i] == str[j] && dp[i + 1][j - 1] == 1) {
                dp[i][j] = 1;
                ans = l;
            }
        }
    }
    printf("%d", ans);
    return 0;
}
// A 1045 Favorite Color Stripe (30分)
/*
给定一个颜色序列A，求一个长度不超过200的子序列，子序列中的数字必须
按照一个给定的序列O来确定，且要按照O的顺序
**有没有可能A中没有O的某一个数字

*1
6
5 2 3 1 5 6
12 2 2 4 1 5 5 6 3 1 1 5 6
:7

解题思路1：最长不下降子序列LIS
*/
const int maxn = 210, maxl = 10010;     // stripe, dp的最大长度是maxl
int n, m, l, stripe[maxl], dp[maxl], order[maxn];
int main() {
    scanf("%d\n%d", &n, &m);
    int tmp;
    // 没有出现的数字就标记为-1，出现的数字就按出现排序标记
    memset(order, -1, sizeof(order));
    for (int i = 0; i < m; i++) {
        scanf("%d", &tmp);
        order[tmp] = i;
    }
    scanf("%d", &l);
    int t = 0;
    for (int i = 0; i < l; i++) {
        scanf("%d", &tmp);
        if (order[tmp] >= 0)
            stripe[t++] = order[tmp];   // 直接将数字转化为排序索引
    }
    // l 以及没用了
    int ans = -1;
    for (int i = 0; i < t; i++)
    {
        dp[i] = 1;
        for (int j = 0; j < i; j++)
        {
            // stripe[i]在o中的排序要等于或高于stripe[j]在o中的排序位置
            if (stripe[i]  >= stripe[j] && (dp[j] + 1 > dp[i])) {
                dp[i] = dp[j] + 1;
            }
        }
        ans = max(ans, dp[i]);
    }
    printf("%d", ans);
    return 0;
}

/*
解题思路2：最长公共子序列LCS
        常规LCS是基于两个序列的一一不重复对应，A[i]==B[j],则dp[i][j]==dp[i-1][j-1]+1；
        但此题可以重复对应，dp[2][2]可以被dp[2][1]和dp[1][2]同时影响，而不再仅仅是dp[1][1]；
        即A[i]==B[j],则dp[i][j]==max(dp[i][j-1], dp[i-1][j])+1。
*/
const int maxn = 210, maxl = 10010;
int n, m, l, dp[maxl][maxl], stripe[maxl], order[maxn];

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 1; i <= m; i++)
        scanf("%d", &order[i]);
    scanf("%d", &l);
    for (int i = 1; i <= l; i++)
        scanf("%d", &stripe[i]);

    // 初始化边界 dp[i][0]=dp[0][j] = 0, i<=m,j<=t 
    for (int i = 0; i <= m; i++)
        dp[i][0] = 0;
    for (int i = 0; i <= l; i++)
        dp[0][i] = 0;
    int tmp;
    for (int i = 1; i <= m; i++)
    {
        for (int j = 1; j <= l; j++)
        {
            tmp = max(dp[i][j - 1], dp[i - 1][j]);
            if (order[i] == stripe[j])
                dp[i][j] = tmp + 1;
            else
                dp[i][j] = tmp;
        }
    }
    printf("%d", dp[m][l]);
    return 0;
}

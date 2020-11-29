// A 1045 Favorite Color Stripe (30分)
/*
给定一个颜色序列A，求一个长度不超过200的子序列，子序列中的数字必须
按照一个给定的序列O来确定，且要按照O的顺序
**有没有可能A中没有O的某一个数字

解题思路1：最长不下降子序列LIS

*1
6
5 2 3 1 5 6
12 2 2 4 1 5 5 6 3 1 1 5 6
:7
*2

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

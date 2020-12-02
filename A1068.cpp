// A1068 Find More Coins (30分)

/*
从含有n个数的一个序列中选出k个数（k不定），使这k个数的和刚好等于m;
答案可能不唯一，输出其中的最小序列；也可能没有答案，输出“No Solution”;
翻译为：有一个容量为m的口袋，和一堆重量和价值相等的物品，
从物品序列里尝试放入东西，使容量尽量满的情况下价值最大。
当容量m满时，总价值不是m就是无解的情况

*1
8 9
5 9 8 7 2 3 4 1
:1 3 5
*2
4 8
7 2 4 3
:No Solution
*/

const int maxn = 10010;
int n, m, val[maxn], dp[maxn];
bool choice[maxn][maxn], trace[maxn];

bool cmp(int a, int b) {
    return a > b;
}

int main() {
    scanf("%d%d", &n, &m);
    for (int i = 1; i <= n; i++)
        scanf("%d", &val[i]);
    // 对val从大到小排序，可满足获得的第一个答案就是最小序
    sort(val + 1, val + n + 1, cmp);
    //sort(val + 1, val + n + 1);
    // 边界 dp[0][m] == 0
    for (int i = 1; i <= n; i++)
    {
        for (int v = m; v >= val[i]; v--)
        {
            // <= 的意思是由于有多种答案的可能，要把所有等的结果都保留
            if (dp[v] <= dp[v - val[i]] + val[i]) {
                dp[v] = dp[v - val[i]] + val[i];
                choice[i][v] = true;
            }
            else 
                choice[i][v] = false;
        }
    }
    if (dp[m] != m) printf("No Solution");
    else {
        int k = n, num = 0, v = m;
        // 从后向前，倒推路径点；先记录值小的点
        while (k >= 0)
        {
            if (choice[k][v]) {
                trace[k] = true;
                v -= val[k];
                num++;
            }
            else
                trace[k] = false;
            k--;
        }
        for (int i = n; i >= 0; i--)
        {
            if (trace[i]) {
                printf("%d", val[i]);
                num--;
                if (num > 0)
                    printf(" ");
            }
        }
    }

    return 0;
}
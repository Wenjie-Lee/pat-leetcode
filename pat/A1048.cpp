// PAT  A 1048 Find Coins (25分)
const int maxn = 100010;
int n, m, coins[maxn];

int main() {
    scanf("%d%d", &n, &m);
    // 输入所有硬币
    for (int i = 0; i < n; i++)
        scanf("%d", &coins[i]);
    sort(coins, coins + n);
    bool trigger = false;
    // 题目要求要先输出索引小的，所以做一个循环
    for (int i = 0; i < n; i++)
    {
        // 寻找第一个值，使得coins[i] + coins[result] = m， -coins 是为了得到目标索引值
        int res = lower_bound(coins, coins + n, m - coins[i]) - coins;
        // 得到的索引值不一定满足coins[i] + coins[result] = m
        // res 不能等于 i，即同一个币不能用两次
        if (coins[i] + coins[res] == m && res != i)
        {
            trigger = true;
            printf("%d %d", coins[i], coins[res]);
            break;
        }
    }
    if (!trigger)
        printf("No Solution");
    return 0;
}
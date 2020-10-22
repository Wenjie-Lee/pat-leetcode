// PAT A 1044 Shopping in Mars (25分)
const int maxn = 100010;
int n, m, sum[maxn];
vector<pair<int, int> > vec;
int main() {
    scanf("%d %d", &n, &m);
    sum[0] = 0;
    for (int i = 1; i <= n; i++)
    {
        scanf("%d", &sum[i]);
        // sum数组每位存的是1~i个diamond的价值和
        sum[i] += sum[i-1];
    }
    for (int i = 1; i <= n; i++)
    {
        // 给出第一个满足 sum[j] - sum[i-1] >= m 时的索引，注意是>=，存在只有>的情况 
        int a = lower_bound(sum + i, sum + n + 1, m + sum[i-1]) - sum;
        // sum[a] - sum[i-1], 输出的数是i和a
        if (sum[a] - sum[i - 1] >= m)
            vec.push_back(make_pair(i, a));
    }
    // res 用来记录==m的输出个数
    int res = 0;
    for (auto p : vec) {
        if (sum[p.second] - sum[p.first - 1] == m) {
            res++;
            printf("%d-%d\n", p.first, p.second);
        }
    }
    // 当res==0， 即没有正好的情况是，输出>m的情况中最接近m的
    if (!res)
    {
        
        int closest = INT32_MAX - 1;
        for (auto p : vec) {
            if (sum[p.second] - sum[p.first - 1] > m) {
                closest = min(closest, sum[p.second] - sum[p.first - 1]);
            }
        }
        for (auto p : vec) {
            if (sum[p.second] - sum[p.first - 1] == closest) {
                printf("%d-%d\n", p.first, p.second);
            }
        }
    }

    return 0;
}
// PAT A 1103 Integer Factorization (30分)
/*
N 数， P 平方数， K 多项式项数
使：
N = n[1]^P + ... n[K]^P
若有多个答案，要求输出系数和最大的那个，若仍有多个，输出系数序列最大的那个；
若不可能，则输出
Impossible
*/
int n, k, p, max_expo = -1;
vector<int> list, ans;

int power(int x) {
	int ans = 1;
	for (int i = 0; i < p; i++)
		ans *= x;
	return ans;
}

void dfs(int remain, int now_k, int now_n, int now_expo) {
	// 当系数和超过时、或者系数个数超过时跳出
	if (remain < 0 || now_k > k)
		return;
	// 当已完成匹配时跳出
	if (remain == 0 && now_k == k) {
		// 当前系数和是最大
		if (now_expo > max_expo) {
			max_expo = now_expo;
			ans = list;
		}
		return;
	}
	// 未完成时，在给定范围继续选
	if (now_n > 0) {
		list.push_back(now_n);
		// 选择这个数 now_n，当然有可能选多个，所以下一个递归依然时 now_n
		dfs(remain - power(now_n), now_k + 1, now_n, now_expo + now_n);
		list.pop_back();
		// 不选这个数
		dfs(remain, now_k, now_n - 1, now_expo);
	}
}

int main() {
	scanf("%d%d%d", &n, &k, &p);
	// p > 1，不会有 sqrt(n)~p 的数被宣选到
	int sqrt_n = sqrt(n);
	dfs(n, 0, sqrt_n, 0);
	// 若max_expo没变，即没找到可能的序列
	if (max_expo < 0) {
		printf("Impossible");
	}
	else {
		printf("%d = ", n);
		printf("%d^%d", ans[0], p);
		for (int i = 1; i < ans.size(); i++)
			printf(" + %d^%d", ans[i], p);
	}
	return 0;
}
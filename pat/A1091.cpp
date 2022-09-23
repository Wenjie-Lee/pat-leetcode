// PAT A 1091 Acute Stroke (30分)
/*
给定一个正方体数据块，全为0、1。
0代表正常，1代表异常；
寻找连通的数据为1的区域，且仅输出数据和大于t的连通域
*** 三维空间的BFS问题
*/
int m, n, l, t, volume_strokecore = 0;
int cube[60][1286][128];
bool visit[60][1286][128];
// 六个面方向
int X[6] = { 0, 0, 0, 0, 1, -1 };
int Y[6] = { 0, 0, 1, -1, 0, 0 };
int Z[6] = { 1, -1, 0, 0, 0, 0 };

struct node {
	int x, y, z;
	node(int a, int b, int c) :
		x(a), y(b), z(c) {};
};

// 坐标是否合法
bool judge(int x, int y, int z) {
	if (x >= l || x < 0 || y >= m || y < 0 || z >= n || z < 0)
		return false;
	if (cube[x][y][z] == 0 || visit[x][y][z] == true)
		return false;
	return true;
}
// 输出volume. 若此连通域大小小于t，就输出0
int bfs(int x, int y, int z) {
	int volume = 0;
	queue<node> q;
	q.push(node(x,y,z));
	while (!q.empty())
	{
		node pixel = q.front();
		q.pop();
		// 若此点不合法，就跳过
		if (!judge(pixel.x, pixel.y, pixel.z)) continue;
		// volume 记录+1
		volume++;
		visit[pixel.x][pixel.y][pixel.z] = true;
		// 遍历六个方向，都加入队列
		for (int i = 0; i < 6; i++)
		{
			int newx = pixel.x + X[i];
			int newy = pixel.y + Y[i];
			int newz = pixel.z + Z[i];
			q.push(node(newx, newy, newz));
		}
	}
	if (volume >= t)
		return volume;
	else
		return 0;
}

int main() {
	scanf("%d%d%d%d", &m, &n, &l, &t);
	for (int i = 0; i < l; i++)
		for (int a = 0; a < m; a++)
			for (int b = 0; b < n; b++)
				scanf("%d", &cube[i][a][b]);
	for (int i = 0; i < l; i++)
		for (int a = 0; a < m; a++)
			for (int b = 0; b < n; b++) {
				if (!visit[i][a][b] && cube[i][a][b] == 1)
					volume_strokecore += bfs(i, a, b);
			}
	printf("%d", volume_strokecore);
	return 0;
}
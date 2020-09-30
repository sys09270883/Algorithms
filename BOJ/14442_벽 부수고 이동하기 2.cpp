#include <bits/stdc++.h>
using namespace std;
using ll = long long;
using ull = unsigned long long;
using pii = pair<int, int>;
using pll = pair<ll, ll>;
using pli = pair<ll, int>;
#pragma GCC optimize("Ofast")
#pragma GCC optimize("unroll-loops")
#define FASTIO                        \
	ios_base::sync_with_stdio(false); \
	cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define rall(x) (x).rbegin(), (x).rend()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

struct Node
{
	int x, y, cnt, brk;
};

const int MAX = 1001;
int N, M, K;
int arr[MAX][MAX];
bool vis[MAX][MAX][11];
int dx[] = {-1, 0, 1, 0};
int dy[] = {0, -1, 0, 1};

int BFS()
{
	queue<Node> q;
	q.push({0, 0, 1, 0});
	vis[0][0][0] = true;
	while (q.size())
	{
		auto tmp = q.front();
		q.pop();
		int x = tmp.x;
		int y = tmp.y;
		int cnt = tmp.cnt;
		int brk = tmp.brk;
		if (x == N - 1 && y == M - 1)
			return cnt;
		for (int i = 0; i < 4; i++)
		{
			int nx = x + dx[i];
			int ny = y + dy[i];
			int ncnt = cnt + 1;
			if (nx < 0 || ny < 0 || nx >= N || ny >= M)
				continue;
			int nbrk = arr[nx][ny] ? brk + 1 : brk;
			if (brk > K || vis[nx][ny][nbrk])
				continue;
			vis[nx][ny][nbrk] = true;
			q.push({nx, ny, ncnt, nbrk});
		}
	}
	return -1;
}

int main()
{
	FASTIO
	cin >> N >> M >> K;
	for (int i = 0; i < N; i++)
	{
		string s;
		cin >> s;
		for (int j = 0; j < M; j++)
		{
			arr[i][j] = s[j] - '0';
		}
	}
	cout << BFS();
}

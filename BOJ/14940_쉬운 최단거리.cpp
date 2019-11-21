/*
https://www.acmicpc.net/problem/14940
[문제]
지도가 주어지면 모든 지점에 대해서 목표지점까지의 거리를 구하여라.

문제를 쉽게 만들기 위해 오직 가로와 세로로만 움직일 수 있다고 하자.

[입력]
지도의 크기 n과 m이 주어진다. n은 세로의 크기, m은 가로의 크기다.(2≤n≤1000, 2≤m≤1000)

다음 n개의 줄에 m개의 숫자가 주어진다. 0은 갈 수 없는 땅이고 1은 갈 수 있는 땅, 2는 목표지점이다. 
입력에서 2는 단 한개이다.

[출력]
각 지점에서 목표지점까지의 거리를 출력한다. 
원래 벽인 위치는 0을 출력하고, 원래 땅인 부분 중에서 도달할 수 없는 위치는 -1을 출력한다.

[풀이]
dist_map을 -1로 초기화 해놓고 정석적인 BFS를 진행한다.

*/

#include <iostream>
#include <queue>
using namespace std;

struct Node {
	int x, y, cnt;

	Node(int x, int y, int cnt) : x(x), y(y), cnt(cnt) {}
};

int n, m, sx, sy;
int map[1001][1001];
int dist_map[1001][1001];
bool visited[1001][1001];
int dx[]{ -1 ,0, 1, 0 };
int dy[]{ 0, -1, 0, 1 };

void BFS() {
	queue<Node> q;
	q.push({ sx, sy, 1 });
	dist_map[sx][sy] = 0;
	visited[sx][sy] = true;

	while (!q.empty()) {
		Node tmp = q.front();
		int x = tmp.x;
		int y = tmp.y;
		int cnt = tmp.cnt;
		q.pop();

		for (int i = 0; i < 4; i++)
		{
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= n || ny >= m)
				continue;

			if (visited[nx][ny] || map[nx][ny] == 0)
				continue;

			dist_map[nx][ny] = cnt;
			visited[nx][ny] = true;
			q.push({ nx, ny, cnt + 1 });
		}
	}
}

int main() {
	cin >> n >> m;

	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < m; j++)
		{
			cin >> map[i][j];

			dist_map[i][j] = -1;

			if (map[i][j] == 2) {
				sx = i;
				sy = j;
			}

			else if (map[i][j] == 0) {
				dist_map[i][j] = 0;
			}
		}
	}

	BFS();

	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < m; j++)
		{
			cout << dist_map[i][j] << ' ';
		}
		cout << '\n';
	}

	return 0;
}

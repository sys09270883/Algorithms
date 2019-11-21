/*
https://www.acmicpc.net/problem/14940
[����]
������ �־����� ��� ������ ���ؼ� ��ǥ���������� �Ÿ��� ���Ͽ���.

������ ���� ����� ���� ���� ���ο� ���ηθ� ������ �� �ִٰ� ����.

[�Է�]
������ ũ�� n�� m�� �־�����. n�� ������ ũ��, m�� ������ ũ���.(2��n��1000, 2��m��1000)

���� n���� �ٿ� m���� ���ڰ� �־�����. 0�� �� �� ���� ���̰� 1�� �� �� �ִ� ��, 2�� ��ǥ�����̴�. 
�Է¿��� 2�� �� �Ѱ��̴�.

[���]
�� �������� ��ǥ���������� �Ÿ��� ����Ѵ�. 
���� ���� ��ġ�� 0�� ����ϰ�, ���� ���� �κ� �߿��� ������ �� ���� ��ġ�� -1�� ����Ѵ�.

[Ǯ��]
dist_map�� -1�� �ʱ�ȭ �س��� �������� BFS�� �����Ѵ�.

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

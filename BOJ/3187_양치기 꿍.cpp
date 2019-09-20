/*
https://www.acmicpc.net/problem/3187
[����]
��ġ�� ���� �ǳ� ���밡 ��Ÿ���ٰ� ���� ������� �ӿ����� ���� ���̻� ���� ������� ���� �ʴ´�. 
ȭ�� �� ���� �����ɿ� ��Ÿ �ƿ� ������� ����� �ִ� ��Ÿ���ȿ� ���� ����־� ����� ��Ƹ԰� �ߴ�.

������ ����� ���� ����� �ƴϴ�. ���� ��Ÿ�� ���� ���� ����� ���ڰ� ������ ���ں��� �� ���� ��� ���밡 ���� ��Ƹ�����. 
���� �� ���� ���� ���� ���� ��Ƹ��������� ���̴�.

���� ���� �ȶ��߱� ������ �̵��� ����� �̹� �˰��ִ�. 
���� �� ������ '.'(��)���� ��Ÿ���� ��Ÿ���� '#', ���븦 'v', ���� 'k'��� ��Ÿ���ٸ� 
�������� �� ������ ��� ���밡 ��Ƴ����� ����� �� �ְڴ°�?

��, ��Ÿ���� ������ ���� �������� ��� ���밡 ������ ��� ����� �밢������ �̵��� �� ����.

[�Է�]
�Է��� ù ��° �ٿ��� ���� ������ ���ο� ������ ���̸� ��Ÿ���� �� ���� ���� R, C (3 �� R, C �� 250)�� �־�����.

���� �� R�ٿ��� C���� ���ڰ� �־����� �̵��� ������ ������ ��ȣ���̴�.

[���]
��Ƴ��� �Ǵ� ��� ������ ���� ���� ������� ����Ѵ�.

[Ǯ��]
�������� DFS, BFS��������.
BFS�� �ϸ鼭 �ش� ������ �ִ� ����� ���� ���� ����, ���� ���� �� ũ�ٸ� �� ���� ���� �׸�ŭ ������Ų��. (�ݴ뵵 ��������)

*/
#include <iostream>
#include <vector>
#include <queue>

using namespace std;

struct Node {
	int x, y;

	Node(int x, int y) : x(x), y(y) {}
};

const int MAX_SIZE = 251;
int R, C, wolf_total_cnt, sheep_total_cnt, wolf_cnt, sheep_cnt;
vector<vector<char>> map;
bool visited[MAX_SIZE][MAX_SIZE];
int dx[] = { -1, 0, 1, 0 };
int dy[] = { 0, -1, 0, 1 };

void BFS(int r, int c) {
	queue<Node> queue;
	queue.push(Node(r, c));
	visited[r][c] = true;

	switch (map[r][c]) {
	case 'v':
		wolf_cnt++;
		break;
	case 'k':
		sheep_cnt++;
		break;
	}

	while (!queue.empty()) {
		Node tmp = queue.front();
		int x = tmp.x;
		int y = tmp.y;
		queue.pop();

		for (size_t i = 0; i < 4; i++)
		{
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= R || ny >= C)
				continue;

			if (visited[nx][ny] || map[nx][ny] == '#')
				continue;

			switch (map[nx][ny]) {
			case 'v':
				wolf_cnt++;
				break;
			case 'k':
				sheep_cnt++;
				break;
			}

			visited[nx][ny] = true;
			queue.push(Node(nx, ny));
		}
	}

	sheep_cnt > wolf_cnt ? sheep_total_cnt += sheep_cnt : wolf_total_cnt += wolf_cnt;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> R >> C;

	for (size_t i = 0; i < R; i++)
	{
		vector<char> tmp;
		tmp.resize(C);
		map.push_back(tmp);
	}

	for (size_t i = 0; i < R; i++)
	{
		for (size_t j = 0; j < C; j++)
		{
			cin >> map[i][j];
		}
	}

	for (size_t i = 0; i < R; i++)
	{
		for (size_t j = 0; j < C; j++)
		{
			if (map[i][j] != '#' && !visited[i][j]) {
				wolf_cnt = 0, sheep_cnt = 0;
				BFS(i, j);
			}
		}
	}

	cout << sheep_total_cnt << " " << wolf_total_cnt;

	return 0;
}
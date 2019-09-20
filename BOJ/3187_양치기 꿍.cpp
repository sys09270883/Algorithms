/*
https://www.acmicpc.net/problem/3187
[문제]
양치기 꿍은 맨날 늑대가 나타났다고 마을 사람들을 속였지만 이젠 더이상 마을 사람들이 속지 않는다. 
화가 난 꿍은 복수심에 불타 아예 늑대들을 양들이 있는 울타리안에 마구 집어넣어 양들을 잡아먹게 했다.

하지만 양들은 보통 양들이 아니다. 같은 울타리 영역 안의 양들의 숫자가 늑대의 숫자보다 더 많을 경우 늑대가 전부 잡아먹힌다. 
물론 그 외의 경우는 양이 전부 잡아먹히겠지만 말이다.

꿍은 워낙 똑똑했기 때문에 이들의 결과는 이미 알고있다. 
만약 빈 공간을 '.'(점)으로 나타내고 울타리를 '#', 늑대를 'v', 양을 'k'라고 나타낸다면 
여러분은 몇 마리의 양과 늑대가 살아남을지 계산할 수 있겠는가?

단, 울타리로 막히지 않은 영역에는 양과 늑대가 없으며 양과 늑대는 대각선으로 이동할 수 없다.

[입력]
입력의 첫 번째 줄에는 각각 영역의 세로와 가로의 길이를 나타내는 두 개의 정수 R, C (3 ≤ R, C ≤ 250)가 주어진다.

다음 각 R줄에는 C개의 문자가 주어지며 이들은 위에서 설명한 기호들이다.

[출력]
살아남게 되는 양과 늑대의 수를 각각 순서대로 출력한다.

[풀이]
전형적인 DFS, BFS문제였다.
BFS를 하면서 해당 영역에 있는 늑대와 양의 수를 세고, 양의 수가 더 크다면 총 양의 수를 그만큼 증가시킨다. (반대도 마찬가지)

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
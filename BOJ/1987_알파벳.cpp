/*
https://www.acmicpc.net/problem/1987
[문제]
세로 R칸, 가로 C칸으로 된 표 모양의 보드가 있다. 보드의 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 좌측 상단 칸 (1행 1열) 에는 말이 놓여 있다.

말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데, 새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에 적혀 있는 알파벳과는 달라야 한다. 즉, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.

좌측 상단에서 시작해서, 말이 최대한 몇 칸을 지날 수 있는지를 구하는 프로그램을 작성하시오. 말이 지나는 칸은 좌측 상단의 칸도 포함된다.

[입력]
첫째 줄에 R과 C가 빈칸을 사이에 두고 주어진다. (1<=R,C<=20) 둘째 줄부터 R개의 줄에 걸쳐서 보드에 적혀 있는 C개의 대문자 알파벳들이 빈칸 없이 주어진다.

[출력]
첫째 줄에 말이 지날 수 있는 최대의 칸 수를 출력한다.

[풀이]
DFS를 하면서 이미 지나온 알파벳에 대해서 백트래킹을 한다.
4방향 모두 갈 곳이 없으면 최댓값을 갱신하고 종료한다.

*/
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int R, C, ans = 0;
vector<vector<char>> v;
bool alpha[26];
int dx[] = { -1, 0, 1, 0 };
int dy[] = { 0, -1, 0, 1 };

void DFS(int x, int y, int cnt) {
	alpha[v[x][y] - 'A'] = true;
	bool finished = true;

	for (size_t i = 0; i < 4; i++)
	{
		int nx = x + dx[i];
		int ny = y + dy[i];

		if (nx < 0 || ny < 0 || nx >= R || ny >= C)
			continue;

		if (alpha[v[nx][ny] - 'A'])
			continue;

		finished = false;
		DFS(nx, ny, cnt + 1);

		// backtracking
		alpha[v[nx][ny] - 'A'] = false;
	}

	if (finished)
		ans = max(ans, cnt);
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> R >> C;

	for (size_t i = 0; i < R; i++)
	{
		vector<char> row;
		row.resize(C);
		v.push_back(row);
	}

	for (size_t i = 0; i < R; i++)
	{
		for (size_t j = 0; j < C; j++)
		{
			cin >> v[i][j];
		}

	}

	DFS(0, 0, 1);
	cout << ans;

	return 0;
}
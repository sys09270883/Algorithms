/*
https://www.acmicpc.net/problem/1987
[����]
���� Rĭ, ���� Cĭ���� �� ǥ ����� ���尡 �ִ�. ������ �� ĭ���� �빮�� ���ĺ��� �ϳ��� ���� �ְ�, ���� ��� ĭ (1�� 1��) ���� ���� ���� �ִ�.

���� �����¿�� ������ �� ĭ ���� �� ĭ���� �̵��� �� �ִµ�, ���� �̵��� ĭ�� ���� �ִ� ���ĺ��� ���ݱ��� ������ ��� ĭ�� ���� �ִ� ���ĺ����� �޶�� �Ѵ�. ��, ���� ���ĺ��� ���� ĭ�� �� �� ���� �� ����.

���� ��ܿ��� �����ؼ�, ���� �ִ��� �� ĭ�� ���� �� �ִ����� ���ϴ� ���α׷��� �ۼ��Ͻÿ�. ���� ������ ĭ�� ���� ����� ĭ�� ���Եȴ�.

[�Է�]
ù° �ٿ� R�� C�� ��ĭ�� ���̿� �ΰ� �־�����. (1<=R,C<=20) ��° �ٺ��� R���� �ٿ� ���ļ� ���忡 ���� �ִ� C���� �빮�� ���ĺ����� ��ĭ ���� �־�����.

[���]
ù° �ٿ� ���� ���� �� �ִ� �ִ��� ĭ ���� ����Ѵ�.

[Ǯ��]
DFS�� �ϸ鼭 �̹� ������ ���ĺ��� ���ؼ� ��Ʈ��ŷ�� �Ѵ�.
4���� ��� �� ���� ������ �ִ��� �����ϰ� �����Ѵ�.

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
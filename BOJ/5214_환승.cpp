/*
https://www.acmicpc.net/problem/5214
[����]
���� �� �̷��� ������� ���� ���� ����ϴ� ���߱����� ������Ʃ���̴�. ������Ʃ�� �ϳ��� �� K���� ���� �����Ѵ�. 
1�������� N�������� ���µ� �湮�ϴ� �ּ� ���� ���� �� ���ϱ�?

[�Է�]
ù° �ٿ� ���� �� N�� �� ������Ʃ�갡 ���� �����ϴ� ���� ���� K, ������Ʃ���� ���� M�� �־�����. 
(1 �� N �� 100,000, 1 �� K, M �� 1000)

���� M�� �ٿ��� ������Ʃ���� ������ �� �ٿ� �ϳ��� �־�����. �� K�� ���ڰ� �־�����, �� ���ڴ� �� ������Ʃ�갡 ���� �����ϴ� ���� ��ȣ�̴�.

[���]
ù° �ٿ� 1�������� N�������� ���µ� �湮�ϴ� ���� ������ �ּڰ��� ����Ѵ�. ����, �� �� ���ٸ� -1�� ����Ѵ�.

[Ǯ��]
������Ʃ��� ���� �� ���̸� �����ϹǷ� ���� ���հ� ������Ʃ���� �������� �̺б׷����� ������ ����.
BFS�� �ϸ鼭 ī������ �ϸ�, �� �� �ִ� ��쿡�� �� �̵��Ÿ����� ������Ʃ���� ������ ���ش�. ((cnt / 2) + 1)

*/
#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N, K, M, ans;
vector<vector<int>> v;
bool visited[101001];

int BFS() {
	queue<pair<int, int>> queue;
	visited[1] = true;
	queue.push(pair<int, int>(1, 0));

	while (!queue.empty()) {
		int cur = queue.front().first;
		int cnt = queue.front().second;
		queue.pop();

		if (cur == N)
			return cnt;

		for (int i : v[cur]) {
			if (!visited[i]) {
				visited[i] = true;
				queue.push(pair<int, int>(i, cnt + 1));
			}
		}
	}

	return -1;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> K >> M;
	v.resize(N + M + 1);

	for (int i = N + 1; i < N + M + 1; i++)
	{
		for (int j = 0; j < K; j++)
		{
			int k;
			cin >> k;
			v[i].push_back(k);
			v[k].push_back(i);
		}
	}

	if ((ans = BFS()) > -1)
		cout << ans / 2 + 1;

	else
		cout << ans;

	return 0;
}
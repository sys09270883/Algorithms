/*
https://www.acmicpc.net/problem/2252
[����]
N���� �л����� Ű ������� ���� ������� �Ѵ�. 
�� �л��� Ű�� ���� �缭 �����ϸ� �����ϰ�����, ������ ����� ��� �� �л��� Ű�� ���ϴ� ����� ����ϱ�� �Ͽ���. 
�׳����� ��� �л����� �� ���� �� ���� �ƴϰ�, �Ϻ� �л����� Ű���� ���� ���Ҵ�.

�Ϻ� �л����� Ű�� ���� ����� �־����� ��, ���� ����� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� N(1��N��32,000), M(1��M��100,000)�� �־�����. M�� Ű�� ���� ȸ���̴�. 
���� M���� �ٿ��� Ű�� ���� �� �л��� ��ȣ A, B�� �־�����. �̴� �л� A�� �л� B�� �տ� ���� �Ѵٴ� �ǹ��̴�.

�л����� ��ȣ�� 1������ N���̴�.

[���]
ù° �ٺ��� �տ������� ���� ���� ����� ����Ѵ�. ���� ���� ������ ��쿡�� �ƹ��ų� ����Ѵ�.

[Ǯ��]
Ű ������ ��������Ʈ�� �����ϰ� ����� ������ �� �� �̾��� �ִ��� ������ ������ �����Ѵ�.
����� ������ ������ 0�� ������ ť�� �����鼭 DFS�� �����ϰ�, ť���� �����鼭 ����Ѵ�.

*/

#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int N, M, A, B;
vector<int> adj[32001];
bool visited[32001];
int edge[32001];

void topological_sort() {
	queue<int> queue;

	for (int i = 1; i <= N; i++) {
		if (edge[i] == 0)
			queue.push(i);
	}

	while (!queue.empty()) {
		int cur = queue.front();
		queue.pop();

		cout << cur << ' ';

		for (int next : adj[cur]) {
			edge[next]--;

			if (edge[next] == 0)
				queue.push(next);
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M;

	for (int i = 0; i < M; i++)
	{
		cin >> A >> B;
		adj[A].push_back(B);
		edge[B]++;
	}

	topological_sort();

	return 0;
}
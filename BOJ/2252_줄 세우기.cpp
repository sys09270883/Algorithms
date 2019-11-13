/*
https://www.acmicpc.net/problem/2252
[문제]
N명의 학생들을 키 순서대로 줄을 세우려고 한다. 
각 학생의 키를 직접 재서 정렬하면 간단하겠지만, 마땅한 방법이 없어서 두 학생의 키를 비교하는 방법을 사용하기로 하였다. 
그나마도 모든 학생들을 다 비교해 본 것이 아니고, 일부 학생들의 키만을 비교해 보았다.

일부 학생들의 키를 비교한 결과가 주어졌을 때, 줄을 세우는 프로그램을 작성하시오.

[입력]
첫째 줄에 N(1≤N≤32,000), M(1≤M≤100,000)이 주어진다. M은 키를 비교한 회수이다. 
다음 M개의 줄에는 키를 비교한 두 학생의 번호 A, B가 주어진다. 이는 학생 A가 학생 B의 앞에 서야 한다는 의미이다.

학생들의 번호는 1번부터 N번이다.

[출력]
첫째 줄부터 앞에서부터 줄을 세운 결과를 출력한다. 답이 여러 가지인 경우에는 아무거나 출력한다.

[풀이]
키 순서를 인접리스트에 저장하고 연결된 정점이 몇 번 이어져 있는지 간선의 개수를 저장한다.
연결된 간선의 개수가 0인 지점을 큐에 넣으면서 DFS를 진행하고, 큐에서 뽑으면서 출력한다.

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
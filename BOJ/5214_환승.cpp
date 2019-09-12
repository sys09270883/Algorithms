/*
https://www.acmicpc.net/problem/5214
[문제]
아주 먼 미래에 사람들이 가장 많이 사용하는 대중교통은 하이퍼튜브이다. 하이퍼튜브 하나는 역 K개를 서로 연결한다. 
1번역에서 N번역으로 가는데 방문하는 최소 역의 수는 몇 개일까?

[입력]
첫째 줄에 역의 수 N과 한 하이퍼튜브가 서로 연결하는 역의 개수 K, 하이퍼튜브의 개수 M이 주어진다. 
(1 ≤ N ≤ 100,000, 1 ≤ K, M ≤ 1000)

다음 M개 줄에는 하이퍼튜브의 정보가 한 줄에 하나씩 주어진다. 총 K개 숫자가 주어지며, 이 숫자는 그 하이퍼튜브가 서로 연결하는 역의 번호이다.

[출력]
첫째 줄에 1번역에서 N번역으로 가는데 방문하는 역의 개수의 최솟값을 출력한다. 만약, 갈 수 없다면 -1을 출력한다.

[풀이]
하이퍼튜브는 역과 역 사이를 연결하므로 역의 집합과 하이퍼튜브의 집합으로 이분그래프로 나누어 진다.
BFS를 하면서 카운팅을 하며, 갈 수 있는 경우에는 총 이동거리에서 하이퍼튜브의 개수를 빼준다. ((cnt / 2) + 1)

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
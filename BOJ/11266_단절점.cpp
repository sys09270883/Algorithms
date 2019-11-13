/*
https://www.acmicpc.net/problem/11266
[문제]
그래프가 주어졌을 때, 단절점을 모두 구해 출력하는 프로그램을 작성하시오.

단절점이란 그 정점을 제거했을 때, 그래프가 두 개 또는 그 이상으로 나누어지는 정점을 말한다. 
즉, 제거했을 때 그래프의 connected component의 개수가 증가하는 정점을 말한다.

[입력]
첫째 줄에 두 정수 V(1≤V≤10,000), E(1≤E≤100,000)가 주어진다. 
이는 그래프가 V개의 정점과 E개의 간선으로 이루어져 있다는 의미이다. 
다음 E개의 줄에는 간선에 대한 정보를 나타내는 두 정수 A, B가 주어진다. 
이는 A번 정점과 B번 정점이 연결되어 있다는 의미이며, 방향은 양방향이다.

입력으로 주어지는 그래프는 연결 그래프가 아닐 수도 있다.

[출력]
첫째 줄에 단절점의 개수를 출력한다.

둘째 줄에는 단절점의 번호를 공백으로 구분해 오름차순으로 출력한다.

[풀이]
각 정점을 삭제하고 DFS를 하면서 카운트하는 방법은 DFS를 V번 해야하므로 불가능하다.
한 번의 DFS 스패닝 트리로 절단점에 필요한 정보를 얻을 수 있다.

DFS 스패닝 트리는 교차하는 간선이 없다.
정점 u가 있을 때, u와 연결된 정점은 u의 조상이거나 u의 자손이다.
u의 자손들을 루트로 하는 서브트리들은 서로 연결되어 있지 않다. (DFS 스패닝 트리에는 교차 간선이 없기 때문)

따라서 u가 없을 때 그래프가 쪼개지지 않는 경우는 u의 선조와 u의 자손들이 모두 역방향으로 연결되어 있을 때 밖에 없다.

스패닝 트리의 루트가 자손이 없거나 하나밖에 없는 경우는 해당 정점이 없어져도 그래프가 쪼개지지 않는다.
따라서 u가 루트일 경우 둘 이상의 자손을 가질 때만 절단점이 된다.

구현을 할 때에는 u의 조상은 항상 u보다 먼저 접근했으므로, DFS를 하면서 cnt를 체크하고, cnt를 비교함으로써
자손의 서브트리와 조상이 연결되어 있는지를 알 수 있다.

 + 종만북 참고...
*/
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std; 

vector<vector<int>> adj;
vector<int> discovered;
vector<bool> cut_vertex;
int cnt = 0, V, E, A, B, ans;

int find_cut_vertex(int cur, bool root) {
	discovered[cur] = cnt++;
	int ret = discovered[cur];
	int child = 0;

	for (int next : adj[cur]) {
		if (discovered[next] == -1) {
			child++;
			int subtree = find_cut_vertex(next, false);

			if (!root && subtree >= discovered[cur])
				cut_vertex[cur] = true;

			ret = min(ret, subtree);
		}

		else
			ret = min(ret, discovered[next]);
	}

	if (root && child > 1)
		cut_vertex[cur] = true;

	return ret;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> V >> E;
	adj.resize(V + 1);
	discovered.resize(V + 1, -1);
	cut_vertex.resize(V + 1, false);

	for (int i = 0; i < E; i++)
	{
		cin >> A >> B;
		adj[A].push_back(B);
		adj[B].push_back(A);
	}

	for (int i = 1; i <= V; i++)
	{
		if (discovered[i] == -1)
			find_cut_vertex(i, true);
	}

	vector<int> ls;

	for (int i = 1; i <= V; i++)
	{
		if (cut_vertex[i]) {
			ls.push_back(i);
			ans++;
		}
	}

	cout << ans << '\n';
	int size = ls.size();

	for (int i = 0; i < size; i++)
	{
		cout << ls[i] << ' ';
	}

	return 0;
}
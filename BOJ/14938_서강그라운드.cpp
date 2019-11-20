/*
https://www.acmicpc.net/problem/14938
[����]
�����̴� ���� ���� �αⰡ �ִ� ���� �����׶��带 ���� �ִ�. 
�����׶���� ���� ������ �ϳ��� ������ ���ϻ��� Ÿ�� �����Ͽ�, �� ������ ������ �ִ� �����۵��� �̿��� �����̹��� �ϴ� �����̴�. 
�����׶��忡�� 1���� �ϸ� �������� ġŲ�� �ִµ�, �����̴� �� �ѹ��� ġŲ�� ���� ���� ������. 
�ڽ��� ġŲ�� �� �Դ� ������ �Ƿ� ������ �ƴ϶� ������ ���� ������ ������ �����̴� 
���ϻ꿡�� ������ �� �� ������ ������ ���� �� �� �ִ��� �˷��ִ� ���α׷��� ������ �Ͽ����� ���� �����ؾ� 
�ڽ��� ���� ���� ������ ���� ���� �������� ���� �� �ִ��� �� �� ������.

�� ������ ������ ���� l (1 �� l �� 15)�� ��� �ٸ� ������ ����Ǿ� �ְ� �� ���� ����� ������ �����ϴ�. 
�����̴� ������ ������ �߽����� �Ÿ��� ���� ���� m (1 �� m �� 15) �̳��� ��� ������ �������� ���� �����ϴٰ� �� ��, 
�����̰� ���� �� �ִ� �������� �ִ� ������ �˷�����.



�־��� �ʵ尡 ���� �׸��� ����, �������� ���������� 4��� ����. 
( �� ���� ���ڴ� ���� ��ȣ, ���� ���ڴ� ������ ��, �� ���� ���ڴ� �Ÿ��� �ǹ��Ѵ�) 
�����̰� 2�� ������ �������� �Ǹ� 1��,2��(�ڱ� ����), 3��, 5�� ������ ������ �� �ִ�. 
(4�� ������ ��� ���� �Ÿ��� 3 + 5 = 8 > 4(��������) �̹Ƿ� 4�� ������ �������� ���� �� ����.) 
�̷��� �Ǹ� �����̴� 23���� �������� ���� �� �ְ�, �̴� ���� �ʵ忡�� �����̰� ���� �� �ִ� �������� �ִ� �����̴�.

[�Է�]
ù° �ٿ��� ������ ���� n (1 �� n �� 100)�� �������� �������� m (1 �� m �� 15), ���� ���� r (1 �� r �� 100)�� �־�����.

��° �ٿ��� n���� ���ڰ� ���ʴ��  �� ������ �ִ� �������� �� t (1 �� t �� 30)�� �˷��ش�.

�� ��° �ٺ��� r+2��° �� ���� �� �� ���� �����ϴ� ������ ��ȣ a, b, �׸��� ���� ���� l (1 �� l �� 15)�� �־�����.

[���]
�����̰� ���� �� �ִ� �ִ� ������ ������ ����Ѵ�.

[Ǯ��]
��� ���� ���ؼ� ���ͽ�Ʈ�� �ϸ鼭 �ִ� ������ ������ �����Ѵ�.
 + N�� ũ�Ⱑ �۱� ������ �÷��̵� �ͼ��� �����ϴ�.

*/
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

struct Node {
	int idx, dist;

	Node() {}
	Node(int idx, int dist) : idx(idx), dist(dist) {}
};

bool operator<(const Node & n1, const Node & n2) {
	return n1.dist > n2.dist;
}

const int INF = 987654321;
int n, m, r, a, b, l, total_item;
vector<vector<Node>> adj;
vector<int> area;
vector<int> dists;

void dijkstra(int idx) {
	fill(dists.begin(), dists.end(), INF);
	priority_queue<Node> pq;
	pq.push({ idx, 0 });
	dists[idx] = 0;

	while (!pq.empty()) {
		Node tmp = pq.top();
		pq.pop();
		int cur = tmp.idx;
		int dist = tmp.dist;

		if (dist > dists[cur])
			continue;

		for (Node n : adj[cur]) {
			int next = n.idx;
			int next_dist = n.dist;

			if (dists[cur] + next_dist > m)
				continue;

			if (dists[next] > dists[cur] + next_dist) {
				dists[next] = dists[cur] + next_dist;
				pq.push({ next, dists[next] });
			}
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> n >> m >> r;
	adj.resize(n + 1);
	for (int i = 0; i < n + 1; i++)
	{
		vector<Node> row(n + 1);
		adj.push_back(row);
	}
	area.resize(n + 1);
	dists.resize(n + 1);

	for (int i = 1; i < n + 1; i++)
	{
		cin >> area[i];
	}

	for (int i = 0; i < r; i++)
	{
		cin >> a >> b >> l;
		adj[a].push_back({ b, l });
		adj[b].push_back({ a, l });
	}

	for (int i = 1; i < n + 1; i++)
	{
		int item = 0;
		dijkstra(i);

		for (int j = 1; j < n + 1; j++)
		{
			if (dists[j] != INF)
				item += area[j];
		}

		total_item = max(total_item, item);
	}

	return !(cout << total_item);
}
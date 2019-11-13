/*
https://www.acmicpc.net/problem/11266
[����]
�׷����� �־����� ��, �������� ��� ���� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.

�������̶� �� ������ �������� ��, �׷����� �� �� �Ǵ� �� �̻����� ���������� ������ ���Ѵ�. 
��, �������� �� �׷����� connected component�� ������ �����ϴ� ������ ���Ѵ�.

[�Է�]
ù° �ٿ� �� ���� V(1��V��10,000), E(1��E��100,000)�� �־�����. 
�̴� �׷����� V���� ������ E���� �������� �̷���� �ִٴ� �ǹ��̴�. 
���� E���� �ٿ��� ������ ���� ������ ��Ÿ���� �� ���� A, B�� �־�����. 
�̴� A�� ������ B�� ������ ����Ǿ� �ִٴ� �ǹ��̸�, ������ ������̴�.

�Է����� �־����� �׷����� ���� �׷����� �ƴ� ���� �ִ�.

[���]
ù° �ٿ� �������� ������ ����Ѵ�.

��° �ٿ��� �������� ��ȣ�� �������� ������ ������������ ����Ѵ�.

[Ǯ��]
�� ������ �����ϰ� DFS�� �ϸ鼭 ī��Ʈ�ϴ� ����� DFS�� V�� �ؾ��ϹǷ� �Ұ����ϴ�.
�� ���� DFS ���д� Ʈ���� �������� �ʿ��� ������ ���� �� �ִ�.

DFS ���д� Ʈ���� �����ϴ� ������ ����.
���� u�� ���� ��, u�� ����� ������ u�� �����̰ų� u�� �ڼ��̴�.
u�� �ڼյ��� ��Ʈ�� �ϴ� ����Ʈ������ ���� ����Ǿ� ���� �ʴ�. (DFS ���д� Ʈ������ ���� ������ ���� ����)

���� u�� ���� �� �׷����� �ɰ����� �ʴ� ���� u�� ������ u�� �ڼյ��� ��� ���������� ����Ǿ� ���� �� �ۿ� ����.

���д� Ʈ���� ��Ʈ�� �ڼ��� ���ų� �ϳ��ۿ� ���� ���� �ش� ������ �������� �׷����� �ɰ����� �ʴ´�.
���� u�� ��Ʈ�� ��� �� �̻��� �ڼ��� ���� ���� �������� �ȴ�.

������ �� ������ u�� ������ �׻� u���� ���� ���������Ƿ�, DFS�� �ϸ鼭 cnt�� üũ�ϰ�, cnt�� �������ν�
�ڼ��� ����Ʈ���� ������ ����Ǿ� �ִ����� �� �� �ִ�.

 + ������ ����...
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
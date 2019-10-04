/*
https://www.acmicpc.net/problem/10451
[����]


1���� N���� ���� N���� �̷���� ������ ��Ÿ���� ����� ���� ������ �ִ�. 
���� ���, 8���� ���� �̷���� ���� (3, 2, 7, 8, 1, 4, 5, 6)�� �迭�� �̿��� ǥ���ϸ�  �� ����. 
�Ǵ�, Figure 1�� ���� ���� �׷����� ��Ÿ�� ���� �ִ�.

������ �迭�� �̿���  �� ��Ÿ�´ٸ�, i���� ��i�� ������ �̾� �׷����� ���� �� �ִ�.

Figure 1�� �����ִ� �� ó��, ���� �׷��� (3, 2, 7, 8, 1, 4, 5, 6) ���� �� 3���� ����Ŭ�� �ִ�. 
�̷��� ����Ŭ�� "���� ����Ŭ" �̶�� �Ѵ�.

N���� ������ �̷���� ������ �־����� ��, ���� ����Ŭ�� ������ ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� �׽�Ʈ ���̽��� ���� T�� �־�����. 
�� �׽�Ʈ ���̽��� ù° �ٿ��� ������ ũ�� N (2 �� N �� 1,000)�� �־�����. 
��° �ٿ��� ������ �־�����, �� ������ �������� ���еǾ� �ִ�.

[���]
�� �׽�Ʈ ���̽�����, �Է����� �־��� ������ �����ϴ� ���� ����Ŭ�� ������ ����Ѵ�.

[Ǯ��]
������ ���ؼ� DFS�� �� ������ ī��Ʈ���ش�.

*/
#include <iostream>
#include <vector>

using namespace std;

int T, N, start, ans;
vector<int> v;
vector<bool> visited;

void DFS(int idx) {
	if (!visited[idx])
		visited[idx] = true;

	if (!visited[v[idx]]) {
		visited[v[idx]] = true;
		DFS(v[idx]);
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> T;

	while (T-- > 0) {
		cin >> N;

		ans = 0;
		v.resize(N + 1);
		visited.resize(N + 1);
		fill(v.begin(), v.end(), 0);
		fill(visited.begin(), visited.end(), false);

		for (size_t i = 1; i <= N; i++)
		{
			cin >> v[i];
		}

		for (size_t i = 1; i <= N; i++)
		{
			if (!visited[i]) {
				ans++;
				DFS(i);
			}
		}

		cout << ans << "\n";
	}

	return 0;
}
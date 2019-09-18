/*
https://www.acmicpc.net/problem/1182
[����]
N���� ������ �̷���� ������ ���� ��, ũ�Ⱑ ����� �κм��� �߿��� 
�� ������ ���Ҹ� �� ���� ���� S�� �Ǵ� ����� ���� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� ������ ������ ��Ÿ���� N�� ���� S�� �־�����. 
(1 �� N �� 20, |S| �� 1,000,000) ��° �ٿ� N���� ������ �� ĭ�� ���̿� �ΰ� �־�����. �־����� ������ ������ 100,000�� ���� �ʴ´�.

[���]
ù° �ٿ� ���� S�� �Ǵ� �κм����� ������ ����Ѵ�.

[Ǯ��]
��� ��쿡 ���ؼ� �κ� ������ ���� N�� �Ǵ� ��츦 ���غ���. (N�� ����� �۱� ����)
DFS ��� ȣ��� N�� ������ ������ ���� ������Ű��, N�� �������� �� �κ����� ���ٸ� ans�� ������Ų��.

�� N�� 0�� ���, �ƹ��͵� �������� �ʴ� ��쿡�� �ϳ��� ī���õǹǷ� 1�� ���ش�. (sum�� ��� 0�� ���)
*/
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
int N, S, ans;
vector<int> v;

void DFS(int i, int sum) {
	if (i == N) {
		sum == S ? ans++ : ans;
		return;
	}

	DFS(i + 1, sum);
	DFS(i + 1, sum + v[i]);
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> S;
	v.resize(N);
	S == 0 ? ans-- : ans;

	for (size_t i = 0; i < N; i++)
	{
		cin >> v[i];
	}

	DFS(0, 0);

	cout << ans;

	return 0;
}
/*
https://www.acmicpc.net/problem/1963
[����]
�Ҽ��� �������� �����ϴ� â���̴� ���� ���̵� ��й�ȣ�� 4�ڸ� ���Ҽ����� ���س��Ҵ�. 
��� �� â���̴� ģ�� ģ���� ��ȭ�� �������µ�:

������ ���� ��� �ٲ� ���� ���ݾơ�
���� ������ 1033���� �س��µ�... ���� �Ҽ��� �������� ���� ������̾�"
���׷� 8179�� �ء�
����... ���� �� �غ���. �� ������ �� �̻��ؼ� ��й�ȣ�� �� ���� �� �ڸ� �ۿ� �� �ٲ۴� ���̾�. 
���� ��� ���� ù �ڸ��� �ٲٸ� 8033�� �Ǵϱ� �Ҽ��� �ƴ��ݾ�. 
���� �ܰ踦 ���ľ� ���� �� ���� �� ������... ���� ���... 1033 1733 3733 3739 3779 8779 8179ó�� ���̾�.��
����...���� �Ҽ��� ���Ʊ�. �׷� �ƿ� ���α׷��� ¥�� �׷�.
�� �ڸ� �Ҽ� �� ���� �Է¹޾Ƽ� �ٲٴµ� �� �ܰ質 �ʿ����� ����ϰ� ����.��
�������ơ�
�׷���. �׷��� �������� �� ������ Ǯ�� �Ǿ���. �Է��� �׻� �� �ڸ� �Ҽ���(1000 �̻�) �־����ٰ� ��������. 
�־��� �� �Ҽ� A���� B�� �ٲٴ� ���������� �׻� �� �ڸ� �Ҽ����� �����ؾ� �ϰ�, 
���� �ڸ� ������ �Ͽ��� ������ 0039 �� ���� 1000 �̸��� ��й�ȣ�� ������ �ʴ´�.

[�Է�]
ù �ٿ� test case�� �� T�� �־�����. ���� T�ٿ� ���� �� �ٿ� 1�־� �� �ڸ� �Ҽ��� �־�����.

[���]
�� test case�� ���� �� �Ҽ� ������ ��ȯ�� �ʿ��� �ּ� ȸ���� ����Ѵ�. �Ұ����� ��� Impossible�� ����Ѵ�.

[Ǯ��]
1000���� 10000���� �Ҽ��� ���س���(�����佺�׳׽��� ü), �� �ڸ����� �ٲ� �Ҽ��� �Ǵ� ��쿡 BFS�Ѵ�.

 + memset�� ����Ʈ�� �ʱ�ȭ�ϱ� ������ algorithm�� fill�� ����...

*/
#include <iostream>
#include <queue>
#include <cmath>
#include <algorithm>

#define MAX 10001

using namespace std;

int T, A, B;
int prime[MAX];
int visited[MAX];

void eratos() {
	for (int i = 2; i <= sqrt(MAX); i++)
	{
		if (prime[i] == 0)
			continue;

		for (int j = i + i; j <= MAX; j += i)
		{
			prime[j] = 0;
		}
	}
}

int change_num(int num, int i, int j) {
	int res = num;

	switch (i)
	{
	case 0:
		res -= res % 10;
		res += j;
		break;
	case 1:
		res -= ((res % 100) / 10) * 10;
		res += 10 * j;
		break;
	case 2:
		res -= ((res / 100) % 10) * 100;
		res += 100 * j;
		break;
	case 3:
		res -= (res / 1000) * 1000;
		res += 1000 * j;
		break;
	}

	return res;
}

int BFS(int start, int end) {
	queue<pair<int, int>> queue;
	queue.push(pair<int, int>(start, 0));
	visited[start]++;

	while (!queue.empty()) {
		int cur = queue.front().first;
		int cnt = queue.front().second;
		queue.pop();

		if (cur == end) {
			return cnt;
		}

		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				int next = change_num(cur, i, j);

				if (next < 1000 || visited[next] == 1 || prime[next] == 0)
					continue;

				visited[next]++;
				queue.push(pair<int, int>(next, cnt + 1));
			}
		}
	}

	return -1;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	fill(prime, prime + MAX, 1);
	eratos();

	cin >> T;

	while (T-- > 0) {
		fill(visited, visited + MAX, 0);
		cin >> A >> B;

		int res = BFS(A, B);

		if (res >= 0)
			cout << res << "\n";

		else
			cout << "Impossible\n";
	}

	return 0;
}
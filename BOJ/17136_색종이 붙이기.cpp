/*
https://www.acmicpc.net/problem/17136
[����]
<�׸� 1>�� ���� ���簢�� ����� �� �ټ� ������ �����̰� �ִ�. 
�������� ũ��� 1��1, 2��2, 3��3, 4��4, 5��5�� �� �ټ� ������ ������, �� ������ �����̴� 5���� ������ �ִ�.



<�׸� 1>

�����̸� ũ�Ⱑ 10��10�� ���� ���� ���̷��� �Ѵ�. 
���̴� 1��1 ũ���� ĭ���� �������� ������, ������ ĭ���� 0 �Ǵ� 1�� ���� �ִ�. 1�� ���� ĭ�� ��� �����̷� �������� �Ѵ�. 
�����̸� ���� ���� ������ ��� ������ �������� �ȵǰ�, ���ĵ� �� �ȴ�. ��, ĭ�� ���� ��ġ�ϰ� �ٿ��� �Ѵ�. 
0�� ���� ĭ���� �����̰� ������ �� �ȴ�.

���̰� �־����� ��, 1�� ���� ��� ĭ�� ���̴µ� �ʿ��� �������� �ּ� ������ ���غ���.

[�Է�]
�� 10���� �ٿ� ������ �� ĭ�� ���� ���� �־�����.

[���]
��� 1�� ���µ� �ʿ��� �������� �ּ� ������ ����Ѵ�. 1�� ��� ���� ���� �Ұ����� ��쿡�� -1�� ����Ѵ�.

[Ǯ��]
�׸���� �����Ϸ��� �ߴµ�, �׸���� �����ϸ� �ּ� ������ ������ �� ����.
���� ����Ž������ �����ؾ� �ߴ�.
DFS�� �ϸ鼭 backtracking�� ���ָ鼭 ��� ��쿡 ���ؼ� Ȯ���ϰ� �ּҰ��� �����ߴ�.

*/

#include <iostream>
#include <climits>
#include <algorithm>
using namespace std;

int map[10][10];
int confetti[6] = { 0, 5, 5, 5, 5, 5 };
int res = INT_MAX, cnt = 0;

void DFS(int x, int y) {
	if (y == 10) {
		DFS(x + 1, 0);
		return;
	}

	if (x == 10) {
		res = min(res, cnt);
		return;
	}

	if (map[x][y] == 0) {
		DFS(x, y + 1);
		return;
	}

	for (int i = 5; i >= 1; i--) {
		if (confetti[i] == 0 || x + i > 10 || y + i > 10)
			continue;

		bool flag = true;

		for (int j = 0; j < i; j++)
		{
			for (int k = 0; k < i; k++)
			{
				if (map[x + j][y + k] == 0) {
					flag = false;
					goto JUMP;
				}
			}
		}

	JUMP: if (!flag)
		continue;

		  for (int j = 0; j < i; j++)
		  {
			  for (int k = 0; k < i; k++)
			  {
				  map[x + j][y + k] = 0;
			  }
		  }

		  confetti[i]--;
		  cnt++;

		  DFS(x, y + i);

		  // backtracking
		  for (int j = 0; j < i; j++)
		  {
			  for (int k = 0; k < i; k++)
			  {
				  map[x + j][y + k] = 1;
			  }
		  }

		  confetti[i]++;
		  cnt--;
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	for (int i = 0; i < 10; i++)
	{
		for (int j = 0; j < 10; j++)
		{
			cin >> map[i][j];
		}
	}

	DFS(0, 0);

	if (res != INT_MAX)
		cout << res;

	else
		cout << -1;

	return 0;
}
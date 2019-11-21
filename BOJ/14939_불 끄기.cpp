/*
https://www.acmicpc.net/problem/14939
[����]
���� 100���� 10��10 ���簢�� ������� �þ �ִ�. 
������ �޸� ����ġ�� ������ �� ������ ��, �Ʒ�, ����, �����ʿ� �ִ� ������ ���µ� �ٲ��. 
���� 100���� ���°� �־����� ��� ������ ���� ���� �ּ������� ������ �ϴ� ����ġ�� ������ ����϶�

[�Է�]
10�ٿ� 10���ھ� �Է��� �־�����. #�� ���� ������ O(�빮�� ���ĺ� o)�� ���� ������. #�� O�ܿ��� �Է����� �־����� �ʴ´�.

[���]
��� ������ ���� ���� �ּ������� ������ �ϴ� ����ġ�� ������ ����϶�. �Ұ����ϸ� -1�� ����϶�.

[Ǯ��]
ù ���� ��� ���(2��)�� �����ϸ�, �� �Ʒ��ٺ��� �� ���� ������ �����ִ����� ���� �׸����ϰ� ������ �� �� �ִ�.

*/

#include <iostream>
#include <algorithm>
using namespace std;

char map[10][10];
int arr[10][10];
int copy_arr[10][10];
int dx[]{ -1, 0, 1, 0 };
int dy[]{ 0, -1, 0, 1 };

void change(int x, int y) {
	copy_arr[x][y] ^= 1;

	for (int i = 0; i < 4; i++)
	{
		int nx = x + dx[i];
		int ny = y + dy[i];

		if (nx < 0 || ny < 0 || nx >= 10 || ny >= 10)
			continue;

		copy_arr[nx][ny] ^= 1;
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	int ans = 101;

	for (int i = 0; i < 10; i++)
	{
		for (int j = 0; j < 10; j++)
		{
			cin >> map[i][j];

			if (map[i][j] == 'O')
				arr[i][j] = 1;
		}
	}

	for (int i = 0; i < (1 << 10); i++)
	{
		for (int j = 0; j < 10; j++)
		{
			for (int k = 0; k < 10; k++)
			{
				copy_arr[j][k] = arr[j][k];
			}
		}

		int cnt = 0;

		for (int j = 0; j < 10; j++)
		{
			if ((1 << j) & i) {
				cnt++;
				change(0, j);
			}
		}

		for (int j = 1; j < 10; j++)
		{
			for (int k = 0; k < 10; k++)
			{
				if (copy_arr[j - 1][k]) {
					cnt++;
					change(j, k);
				}
			}
		}

		int flag = 0;

		for (int j = 0; j < 10; j++) {
			for (int k = 0; k < 10; k++) {
				flag += copy_arr[j][k];
			}
		}

		if (!flag)
			ans = min(ans, cnt);
	}

	return !(cout << (ans == 101 ? -1 : ans));
}
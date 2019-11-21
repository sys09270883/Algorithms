/*
https://www.acmicpc.net/problem/14939
[문제]
전구 100개가 10×10 정사각형 모양으로 늘어서 있다. 
전구에 달린 스위치를 누르면 그 전구와 위, 아래, 왼쪽, 오른쪽에 있는 전구의 상태도 바뀐다. 
전구 100개의 상태가 주어지면 모든 전구를 끄기 위해 최소한으로 눌러야 하는 스위치의 개수를 출력하라

[입력]
10줄에 10글자씩 입력이 주어진다. #은 꺼진 전구고 O(대문자 알파벳 o)는 켜진 전구다. #과 O외에는 입력으로 주어지지 않는다.

[출력]
모든 전구를 끄기 위해 최소한으로 눌러야 하는 스위치의 개수를 출력하라. 불가능하면 -1를 출력하라.

[풀이]
첫 줄의 모든 경우(2ⁿ)을 결정하면, 그 아래줄부터 윗 줄의 전구가 켜져있는지에 따라서 그리디하게 결정을 할 수 있다.

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
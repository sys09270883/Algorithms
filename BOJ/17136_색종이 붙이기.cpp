/*
https://www.acmicpc.net/problem/17136
[문제]
<그림 1>과 같이 정사각형 모양을 한 다섯 종류의 색종이가 있다. 
색종이의 크기는 1×1, 2×2, 3×3, 4×4, 5×5로 총 다섯 종류가 있으며, 각 종류의 색종이는 5개씩 가지고 있다.



<그림 1>

색종이를 크기가 10×10인 종이 위에 붙이려고 한다. 
종이는 1×1 크기의 칸으로 나누어져 있으며, 각각의 칸에는 0 또는 1이 적혀 있다. 1이 적힌 칸은 모두 색종이로 덮여져야 한다. 
색종이를 붙일 때는 종이의 경계 밖으로 나가서는 안되고, 겹쳐도 안 된다. 또, 칸의 경계와 일치하게 붙여야 한다. 
0이 적힌 칸에는 색종이가 있으면 안 된다.

종이가 주어졌을 때, 1이 적힌 모든 칸을 붙이는데 필요한 색종이의 최소 개수를 구해보자.

[입력]
총 10개의 줄에 종이의 각 칸에 적힌 수가 주어진다.

[출력]
모든 1을 덮는데 필요한 색종이의 최소 개수를 출력한다. 1을 모두 덮는 것이 불가능한 경우에는 -1을 출력한다.

[풀이]
그리디로 접근하려고 했는데, 그리디로 접근하면 최소 개수를 보장할 수 없다.
따라서 완전탐색으로 접근해야 했다.
DFS를 하면서 backtracking을 해주면서 모든 경우에 대해서 확인하고 최소값을 갱신했다.

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
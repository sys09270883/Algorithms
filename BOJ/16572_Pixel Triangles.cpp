/*
https://www.acmicpc.net/problem/16572
[문제]
2000×2000 크기의 격자 판이 주어지며, 각 픽셀(칸)은 행과 열을 이용해서 위치가 표시된다.
가장 왼쪽 열이 1열, 가장 위쪽 행이 1행이다.

이때 픽셀 삼각형의 정의는 다음과 같다.

x행, y열의 픽셀은 (x,y)으로 표현되고, 픽셀 삼각형은 몇 개의 픽셀로 이루어져 있다.
픽셀 삼각형은 3개의 자연수 A, B, C에 대해서  으로 표현된다.
격자 위의 픽셀 삼각형을 구성하는 픽셀은 항상 격자 판에 존재함이 보장된다.
격자 위의 픽셀들로 구성된 픽셀 삼각형이 n개 주어졌을 때, 픽셀 삼각형들이 덮는 픽셀의 개수를 출력해라.

단, 서로 다른 픽셀 삼각형은 같은 픽셀을 공유 할 수 있다.

예를 들어, n = 3 이고 주어지는 3개의 픽셀 삼각형이  일 때 격자의 상태를 묘사하면 다음과 같다.



Figure: 격자 일부분의 상태

이때 최종적으로 덮어지는 픽셀의 개수는 9가 된다.

[입력]
첫 번째 줄에 픽셀 삼각형의 개수 n이 주어진다. 
이후 n개의 줄에 걸쳐서 픽셀 삼각형에 대한 정보를 나타내는 세 정수 A, B, C가 주어진다.

[출력]
덮어지는 픽셀의 개수를 출력한다.

[풀이]
주어진 좌표에서 C - 1만큼 전파시키면 되는 문제였다.
dp로 해당 값이 0이 될 때까지 오른쪽과 아래쪽을 갱신한다.
 + 문제의 식을 이해를 잘 못해서 빙빙 꼬아서 해석했다...
 + ㅜㅜ

*/

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int map[2001][2001];

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int n, A, B, C, ans = 0;
	cin >> n;

	for (int i = 0; i < n; i++)
	{
		cin >> A >> B >> C;
		map[A][B] = max(map[A][B], C);
	}

	for (int i = 1; i < 2001; i++)
	{
		for (int j = 1; j < 2001; j++)
		{
			if (!map[i][j])
				continue;

			map[i][j + 1] = max(map[i][j + 1], map[i][j] - 1);
			map[i + 1][j] = max(map[i + 1][j], map[i][j] - 1);
			ans++;
		}
	}

	return !(cout << ans);
}
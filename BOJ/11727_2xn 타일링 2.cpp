/*
https://www.acmicpc.net/problem/11727
[문제]
2×n 직사각형을 2×1과 2×2 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.

아래 그림은 2×17 직사각형을 채운 한가지 예이다.

[입력]
첫째 줄에 n이 주어진다. (1 ≤ n ≤ 1,000)

[출력]
첫째 줄에 2×n 크기의 직사각형을 채우는 방법의 수를 10,007로 나눈 나머지를 출력한다.

[풀이]
i번째 타일은 i - 1번째 타일에 1 * 2 타일 한개를 붙이는 경우와 
i - 2번째 타일에 2 * 1 타일 2개와 2 * 2타일 한개를 붙이는 경우로 나뉜다.

dp[i] = dp[i - 1] + dp[i - 2] * 2 이다.
*/
#include <iostream>

using namespace std;

const int MOD = 10007;
int dp[1001];

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int n;
	cin >> n;

	dp[1] = 1;
	dp[2] = 3;
	for (size_t i = 3; i <= n; i++)
	{
		dp[i] = (dp[i - 1] + dp[i - 2] * 2) % MOD;
	}

	cout << dp[n];

	return 0;
}
/*
https://www.acmicpc.net/problem/1149
[문제]
RGB거리에 사는 사람들은 집을 빨강, 초록, 파랑중에 하나로 칠하려고 한다. 또한, 그들은 모든 이웃은 같은 색으로 칠할 수 없다는 규칙도 정했다. 집 i의 이웃은 집 i-1과 집 i+1이다.

각 집을 빨강으로 칠할 때 드는 비용, 초록으로 칠할 때 드는 비용, 파랑으로 드는 비용이 주어질 때, 모든 집을 칠할 때 드는 비용의 최솟값을 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 집의 수 N이 주어진다. N은 1,000보다 작거나 같다. 둘째 줄부터 N개의 줄에 각 집을 빨강으로 칠할 때, 초록으로 칠할 때, 파랑으로 칠할 때 드는 비용이 주어진다. 비용은 1,000보다 작거나 같은 자연수이다.

[출력]
첫째 줄에 모든 집을 칠할 때 드는 비용의 최솟값을 출력한다.

[풀이]
최솟값 배열 dp를 bottom-up으로 갱신한다.
dp[1][0] = arr[1][0], dp[1][1] = arr[1][1], dp[1][2] = arr[1][2]로 초기화 하고,
i번째 R의 최소값은 i-1번째 최소값 + R의 값으로 갱신한다.
다른 값들도 마찬가지로 갱신하면서 최종적으로 N번째 행의 최솟값을 출력한다.

*/
#include <iostream>
#include <algorithm>

using namespace std;

int arr[1001][3];
int dp[1001][3];
int N;

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N;
	for (int i = 1; i <= N; i++)
	{
		cin >> arr[i][0] >> arr[i][1] >> arr[i][2];
	}

	for (int i = 1; i <= N; i++)
	{
		dp[i][0] = min(dp[i - 1][1], dp[i - 1][2]) + arr[i][0];
		dp[i][1] = min(dp[i - 1][0], dp[i - 1][2]) + arr[i][1];
		dp[i][2] = min(dp[i - 1][0], dp[i - 1][1]) + arr[i][2];
	}

	cout << min(min(dp[N][0], dp[N][1]), dp[N][2]);
}

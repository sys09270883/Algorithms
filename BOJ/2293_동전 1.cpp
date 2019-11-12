/*
https://www.acmicpc.net/problem/2293
[문제]
n가지 종류의 동전이 있다. 각각의 동전이 나타내는 가치는 다르다. 
이 동전을 적당히 사용해서, 그 가치의 합이 k원이 되도록 하고 싶다. 그 경우의 수를 구하시오. 
각각의 동전은 몇 개라도 사용할 수 있다.

사용한 동전의 구성이 같은데, 순서만 다른 것은 같은 경우이다.

[입력]
첫째 줄에 n, k가 주어진다. (1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000) 다음 n개의 줄에는 각각의 동전의 가치가 주어진다. 
동전의 가치는 100,000보다 작거나 같은 자연수이다.

[출력]
첫째 줄에 경우의 수를 출력한다. 경우의 수는 2^31보다 작다.

[풀이]
dp[k]는 주어진 동전들로 k원을 만드는 경우를 저장하는 배열이라 하자.

주어진 예시 (1, 2, 5)의 동전들이 있을 때
dp[k] = dp[k - 1] + dp[k - 2] + dp[k - 5]이며,
dp[k] = dp[k - coin[1]] + dp[k - coin[2]] + dp[k - coin[3]]처럼 표현할 수 있다.

이 점화식을 bottom-up으로 구현하면 된다.

*/

#include <iostream>
#include <string>
#include <vector>

using namespace std;

int coin[101], dp[10001];

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n, k;
	cin >> n >> k;

	dp[0] = 1;

	for (int i = 1; i <= n; i++)
	{
		cin >> coin[i];
	}

	for (int i = 1; i <= n; i++)
	{
		for (int j = coin[i]; j <= k; j++)
		{
			dp[j] += dp[j - coin[i]];
		}
	}

	cout << dp[k];

	return 0;
}
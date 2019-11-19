/*
https://www.acmicpc.net/problem/2294
[문제]
n가지 종류의 동전이 있다. 이 동전들을 적당히 사용해서, 그 가치의 합이 k원이 되도록 하고 싶다. 
그러면서 동전의 개수가 최소가 되도록 하려고 한다. 각각의 동전은 몇 개라도 사용할 수 있다.

사용한 동전의 구성이 같은데, 순서만 다른 것은 같은 경우이다.

[입력]
첫째 줄에 n, k가 주어진다. 
(1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000) 다음 n개의 줄에는 각각의 동전의 가치가 주어진다. 
동전의 가치는 100,000보다 작거나 같은 자연수이다. 가치가 같은 동전이 여러 번 주어질 수도 있다.

[출력]
첫째 줄에 사용한 동전의 최소 개수를 출력한다. 불가능한 경우에는 -1을 출력한다.

[풀이]
dp[i]을 i원을 만들기 위한 동전의 최소 개수이다.
주어진 예제에서 보면
dp[i]는 dp[i - 1] + 1, dp[i - 5] + 1, dp[i - 12] + 1의 최솟값이다.
따라서 이를 일반화시켜서 dp를 구성한다.

 + 주어진 입력이 k가 클 경우를 예외처리해야 한다.

*/

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

const int INF = 987654321;
int coin[101], dp[10001];

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n, k;
	cin >> n >> k;

	fill(dp, dp + 10001, INF);

	for (int i = 1; i <= n; i++)
	{
		cin >> coin[i];

		if (coin[i] <= k)
			dp[coin[i]] = 1;
	}

	for (int i = 1; i <= k; i++)
	{
		for (int j = 1; j <= n; j++)
		{
			if (i > coin[j]) {
				dp[i] = min(dp[i], dp[i - coin[j]] + 1);
			}
		}
	}

	if (dp[k] == INF)
		cout << -1;

	else
		cout << dp[k];

	return 0;
}
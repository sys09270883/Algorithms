/*
https://www.acmicpc.net/problem/11727
[����]
2��n ���簢���� 2��1�� 2��2 Ÿ�Ϸ� ä��� ����� ���� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

�Ʒ� �׸��� 2��17 ���簢���� ä�� �Ѱ��� ���̴�.

[�Է�]
ù° �ٿ� n�� �־�����. (1 �� n �� 1,000)

[���]
ù° �ٿ� 2��n ũ���� ���簢���� ä��� ����� ���� 10,007�� ���� �������� ����Ѵ�.

[Ǯ��]
i��° Ÿ���� i - 1��° Ÿ�Ͽ� 1 * 2 Ÿ�� �Ѱ��� ���̴� ���� 
i - 2��° Ÿ�Ͽ� 2 * 1 Ÿ�� 2���� 2 * 2Ÿ�� �Ѱ��� ���̴� ���� ������.

dp[i] = dp[i - 1] + dp[i - 2] * 2 �̴�.
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
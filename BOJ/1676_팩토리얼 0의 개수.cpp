/*
https://www.acmicpc.net/problem/1676
[����]
N!���� �ڿ������� ó�� 0�� �ƴ� ���ڰ� ���� ������ 0�� ������ ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� N�� �־�����. (0 �� N �� 500)

[���]
ù° �ٿ� ���� 0�� ������ ����Ѵ�.

[Ǯ��]
N������ ������ 2�� �μ� ������ 5�� �μ� ������ �� ���ϰ�, �� ���� �ּڰ��� ����Ѵ�.

*/
#include <iostream>
#include <algorithm>

using namespace std;

int prime_factor_2(int N) {
	int tmp = N, cnt = 0;

	while (1) {
		if (tmp % 2 != 0)
			break;

		tmp /= 2;
		cnt++;
	}

	return cnt;
}

int prime_factor_5(int N) {
	int tmp = N, cnt = 0;

	while (1) {
		if (tmp % 5 != 0)
			break;

		tmp /= 5;
		cnt++;
	}

	return cnt;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int N, two = 0, five = 0;
	cin >> N;

	for (size_t i = 1; i <= N; i++)
	{
		two += prime_factor_2(i);
		five += prime_factor_5(i);
	}

	cout << min(two, five);

	return 0;
}
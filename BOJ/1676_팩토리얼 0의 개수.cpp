/*
https://www.acmicpc.net/problem/1676
[문제]
N!에서 뒤에서부터 처음 0이 아닌 숫자가 나올 때까지 0의 개수를 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 N이 주어진다. (0 ≤ N ≤ 500)

[출력]
첫째 줄에 구한 0의 개수를 출력한다.

[풀이]
N까지의 수에서 2의 인수 개수와 5의 인수 개수를 다 더하고, 그 둘의 최솟값을 출력한다.

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
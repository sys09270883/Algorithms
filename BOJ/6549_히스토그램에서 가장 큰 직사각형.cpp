/*
https://www.acmicpc.net/problem/6549
[문제]
히스토그램은 직사각형 여러 개가 아래쪽으로 정렬되어 있는 도형이다. 
각 직사각형은 같은 너비를 가지고 있지만, 높이는 서로 다를 수도 있다. 
예를 들어, 왼쪽 그림은 높이가 2, 1, 4, 5, 1, 3, 3이고 너비가 1인 직사각형으로 이루어진 히스토그램이다.

히스토그램에서 가장 넓이가 큰 직사각형을 구하는 프로그램을 작성하시오.

[입력]
입력은 테스트 케이스 여러 개로 이루어져 있다. 
각 테스트 케이스는 한 줄로 이루어져 있고, 직사각형의 수 n이 가장 처음으로 주어진다. 
(1 ≤ n ≤ 100,000) 그 다음 n개의 정수 h1, ..., hn (0 ≤ hi ≤ 1,000,000,000)가 주어진다. 
이 숫자들은 히스토그램에 있는 직사각형의 높이이며, 왼쪽부터 오른쪽까지 순서대로 주어진다. 
모든 직사각형의 너비는 1이고, 입력의 마지막 줄에는 0이 하나 주어진다.

[출력]
각 테스트 케이스에 대해서, 히스토그램에서 가장 넓이가 큰 직사각형의 넓이를 출력한다.

[풀이]
구간에 대해서 중점을 잡고 좌, 우로 나눈다.
히스토그램의 가로 길이가 1일 때까지 분할하고 합치면서 최대값을 갱신한다.
나누는 과정에서 이분탐색으로 높이를 찾고, 최대값을 갱신한다.
 + 수의 범위에 유의해야 한다.

*/
#include <iostream>
#include <cmath>
#include <vector>
#include <algorithm>

using namespace std;

vector<long long> h;

long long solve(int left, int right) {
	if (left == right)
		return h[left];

	int mid = (left + right) / 2;

	long long ret = max(solve(left, mid), solve(mid + 1, right));

	long long low = mid, high = mid + 1;
	long long height = min(h[low], h[high]);

	ret = max(ret, height * 2);

	while (left < low || high < right) {
		if (high < right && (low == left || h[low - 1] < h[high + 1]))
			height = min(height, h[++high]);

		else
			height = min(height, h[--low]);

		ret = max(ret, height * (high - low + 1));
	}

	return ret;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int N, tmp;
	cin >> N;

	while (N) {
		h.clear();
		for (int i = 0; i < N; i++)
		{
			cin >> tmp;
			h.push_back(tmp);
		}

		cout << solve(0, N - 1) << '\n';
		cin >> N;
	}

	return 0;
}
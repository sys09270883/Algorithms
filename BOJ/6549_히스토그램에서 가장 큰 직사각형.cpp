/*
https://www.acmicpc.net/problem/6549
[����]
������׷��� ���簢�� ���� ���� �Ʒ������� ���ĵǾ� �ִ� �����̴�. 
�� ���簢���� ���� �ʺ� ������ ������, ���̴� ���� �ٸ� ���� �ִ�. 
���� ���, ���� �׸��� ���̰� 2, 1, 4, 5, 1, 3, 3�̰� �ʺ� 1�� ���簢������ �̷���� ������׷��̴�.

������׷����� ���� ���̰� ū ���簢���� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
�Է��� �׽�Ʈ ���̽� ���� ���� �̷���� �ִ�. 
�� �׽�Ʈ ���̽��� �� �ٷ� �̷���� �ְ�, ���簢���� �� n�� ���� ó������ �־�����. 
(1 �� n �� 100,000) �� ���� n���� ���� h1, ..., hn (0 �� hi �� 1,000,000,000)�� �־�����. 
�� ���ڵ��� ������׷��� �ִ� ���簢���� �����̸�, ���ʺ��� �����ʱ��� ������� �־�����. 
��� ���簢���� �ʺ�� 1�̰�, �Է��� ������ �ٿ��� 0�� �ϳ� �־�����.

[���]
�� �׽�Ʈ ���̽��� ���ؼ�, ������׷����� ���� ���̰� ū ���簢���� ���̸� ����Ѵ�.

[Ǯ��]
������ ���ؼ� ������ ��� ��, ��� ������.
������׷��� ���� ���̰� 1�� ������ �����ϰ� ��ġ�鼭 �ִ밪�� �����Ѵ�.
������ �������� �̺�Ž������ ���̸� ã��, �ִ밪�� �����Ѵ�.
 + ���� ������ �����ؾ� �Ѵ�.

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
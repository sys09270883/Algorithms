/*
https://www.acmicpc.net/problem/5525
[����]
N+1���� I�� N���� O�� �̷���� ������, I�� O�� ����� ������ ���ڿ��� PN�̶�� �Ѵ�.

P1 IOI
P2 IOIOI
P3 IOIOIOI
PN IOIOI...OI (O�� N��)
I�� O�θ� �̷���� ���ڿ� S�� ���� N�� �־����� ��, S�ȿ� PN�� �� ���� ���ԵǾ� �ִ��� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� N�� �־�����. ��° �ٿ��� S�� ���� M�� �־�����, ��° �ٿ� S�� �־�����. (1 �� N �� 1,000,000, 2N+1 �� M �� 1,000,000)

[���]
S�� PN�� �� ���� ���ԵǾ� �ִ��� ����Ѵ�.

[Ǯ��]
kmp �˰��� �Թ�����.
�ܾ �˻��� ��, �ִ��� ������ �̿��ϸ鼭 �ؾ� �Ѵ�.
���Ʈ �����δ� O(N * M), KMP�� O(N + M)�̴�.

 + kmp�� ���ϴµ� �ʿ��� failure function�� ���ϴ� ��İ� kmp�� ���ϴ� ����� ��Ȯ�� �Ȱ���...
 + ���λ� �迭�� ���̻� �迭�� ���ϸ鼭 pi(failure function)�� �����Ѵ�.

*/
#include <iostream>
#include <string>
#include <vector>

using namespace std;

vector<int> get_pi(string p) {
	size_t len = p.length(), j = 0;

	vector<int> pi(len);

	for (size_t i = 1; i < len; i++)
	{
		while (j > 0 && p[i] != p[j]) {
			j = pi[j - 1];
		}

		if (p[i] == p[j])
			pi[i] = ++j;
	}

	return pi;
}

size_t kmp(string s, string p) {
	vector<int> ans;
	auto pi = get_pi(p);
	int len_s = s.length(), len_p = p.length(), j = 0;

	for (int i = 0; i < len_s; i++)
	{
		while (j > 0 && s[i] != p[j]) {
			j = pi[j - 1];
		}

		if (s[i] == p[j]) {
			if (j == len_p - 1) {
				ans.push_back(i - len_p + 1);
				j = pi[j];
			}

			else
				j++;
		}
	}

	return ans.size();
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int N, M, cnt = 0;
	string P = "IOI", S;

	cin >> N >> M >> S;

	if (N > 1) {
		for (int i = 0; i < N - 1; i++)
		{
			P += "OI";
		}
	}

	cout << kmp(S, P);

	return 0;
}
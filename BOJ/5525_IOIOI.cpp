/*
https://www.acmicpc.net/problem/5525
[문제]
N+1개의 I와 N개의 O로 이루어져 있으면, I와 O이 교대로 나오는 문자열을 PN이라고 한다.

P1 IOI
P2 IOIOI
P3 IOIOIOI
PN IOIOI...OI (O가 N개)
I와 O로만 이루어진 문자열 S와 정수 N이 주어졌을 때, S안에 PN이 몇 군데 포함되어 있는지 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 N이 주어진다. 둘째 줄에는 S의 길이 M이 주어지며, 셋째 줄에 S가 주어진다. (1 ≤ N ≤ 1,000,000, 2N+1 ≤ M ≤ 1,000,000)

[출력]
S에 PN이 몇 군데 포함되어 있는지 출력한다.

[풀이]
kmp 알고리즘 입문문제.
단어를 검색할 때, 최대한 정보를 이용하면서 해야 한다.
브루트 포스로는 O(N * M), KMP는 O(N + M)이다.

 + kmp를 구하는데 필요한 failure function을 구하는 방식과 kmp를 구하는 방식이 정확히 똑같다...
 + 접두사 배열과 접미사 배열을 비교하면서 pi(failure function)를 갱신한다.

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
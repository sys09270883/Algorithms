/*
https://www.acmicpc.net/problem/16570
[문제]
수열 (a1, a2, ⋯, aN) 이 다음의 성질을 가지면 그 수열은 k-앞뒤수열 이라고 한다.

(a1, a2, ⋯, ak) = (aN-k+1, aN-k+2, ⋯ , aN), 1 ≤ k < N

어떤 수열이 k-앞뒤수열일 때, k의 최댓값 k*를 그 수열의 앞뒤계수라고 한다.

우리는 수열의 앞뒤가 맞게 만들기 위해 수열의 연속된 앞부분을 자를 수 있다.

예를 들어 (a1, a2, ⋯, aN) 에서 (a1, a2) 을 제거하면 (a3, a4, ⋯ , aN) 가 된다.

주어진 수열  (A1,A2, ⋯ , AN)의 앞부분을 얼마나 잘라야 앞뒤계수를 최대로 만들 수 있을까? 
단, 그러한 방법은 2가지 이상일 수 있다. 그리고 자르는 방법에는 "아무것도 자르지 않는 것" 도 포함한다.

[입력]
첫번째 줄에 N이 주어진다. (2 ≤ N ≤ 1,000,000)

두번째 줄에 N개의 정수 A1,A2, ⋯ , AN이 공백으로 구분되어 주어진다. (-231 ≤ Ai ≤ 231-1)

[출력]
앞부분을 잘라서 앞뒤수열로 만들 수 있다면
그렇게 자른 후 수열의 앞뒤계수 최댓값과 그렇게 자르는 방법의 수를 공백으로 구분하여 출력한다. 
어떻게 잘라도 앞뒤계수가 존재하지 않으면 -1 을 출력한다.

[풀이]
뒤 수열은 항상 N번째 숫자를 포함하고 있다.
따라서 이 문제는 뒤 수열을 포함하는 문자열 중 가장 긴 수열과 그 수열의 개수를 찾는 문제로 바꿀 수 있다.
뒤 수열을 기준으로 문자를 찾는데 문자열의 길이를 기준으로 parametric search로 찾는다.
최종적으로 KMP를 진행하면서, 뒤 수열의 길이를 parametric search로 정하면서 가장 긴 문자열을 찾는다.

*/

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<int> get_pi(vector<int> p, int start) {
	size_t len = p.size(), j = start;
	vector<int> pi(len - start);

	for (size_t i = start + 1; i < len; i++)
	{
		while (j > start && p[i] != p[j]) {
			j = pi[j - (start + 1)] + start;
		}

		if (p[i] == p[j])
			pi[i - start] = ++j - start;
	}

	return pi;
}

int kmp(vector<int> s, vector<int> p, int start) {
	int cnt = 0;
	auto pi = get_pi(p, start);
	int len_s = s.size(), len_pi = pi.size(), j = start;

	for (int i = 0; i < len_s; i++)
	{
		while (j > start && s[i] != p[j]) {
			j = pi[j - (start + 1)] + start;
		}

		if (s[i] == p[j]) {
			if (j - start == len_pi - 1) {
				cnt++;
				j = pi[j - start] + start;
			}

			else
				j++;
		}
	}

	return cnt;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int N, ans = -1, total = -1;
	vector<int> v;
	cin >> N;
	v.resize(N);
	int l = 1, r = N - 1;

	for (int i = 0; i < N; i++)
	{
		cin >> v[i];
	}


	for (int i = N - 1; i >= 1; i--)
	{
		int cnt = kmp(v, v, i);
		if (cnt > 1) {
			ans = N - i;
			total = cnt - 1;
		}
	}

	return !(ans == -1 ? cout << -1 : cout << ans << ' ' << total);
}
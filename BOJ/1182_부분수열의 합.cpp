/*
https://www.acmicpc.net/problem/1182
[문제]
N개의 정수로 이루어진 수열이 있을 때, 크기가 양수인 부분수열 중에서 
그 수열의 원소를 다 더한 값이 S가 되는 경우의 수를 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 정수의 개수를 나타내는 N과 정수 S가 주어진다. 
(1 ≤ N ≤ 20, |S| ≤ 1,000,000) 둘째 줄에 N개의 정수가 빈 칸을 사이에 두고 주어진다. 주어지는 정수의 절댓값은 100,000을 넘지 않는다.

[출력]
첫째 줄에 합이 S가 되는 부분수열의 개수를 출력한다.

[풀이]
모든 경우에 대해서 부분 수열의 합이 N이 되는 경우를 구해본다. (N이 충분히 작기 때문)
DFS 재귀 호출로 N에 도달할 때까지 값을 증가시키며, N에 도달했을 때 부분합이 같다면 ans를 증가시킨다.

단 N이 0일 경우, 아무것도 선택하지 않는 경우에도 하나가 카운팅되므로 1을 빼준다. (sum이 계속 0일 경우)
*/
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
int N, S, ans;
vector<int> v;

void DFS(int i, int sum) {
	if (i == N) {
		sum == S ? ans++ : ans;
		return;
	}

	DFS(i + 1, sum);
	DFS(i + 1, sum + v[i]);
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> S;
	v.resize(N);
	S == 0 ? ans-- : ans;

	for (size_t i = 0; i < N; i++)
	{
		cin >> v[i];
	}

	DFS(0, 0);

	cout << ans;

	return 0;
}
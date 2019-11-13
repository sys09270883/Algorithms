/*
https://www.acmicpc.net/problem/16637
[문제]
길이가 N인 수식이 있다. 수식은 0보다 크거나 같고, 9보다 작거나 같은 정수와 연산자(+, -, ×)로 이루어져 있다. 
연산자 우선순위는 모두 동일하기 때문에, 수식을 계산할 때는 왼쪽에서부터 순서대로 계산해야 한다. 
예를 들어, 3+8×7-9×2의 결과는 136이다.

수식에 괄호를 추가하면, 괄호 안에 들어있는 식은 먼저 계산해야 한다. 단, 괄호 안에는 연산자가 하나만 들어 있어야 한다. 
예를 들어, 3+8×7-9×2에 괄호를 3+(8×7)-(9×2)와 같이 추가했으면, 식의 결과는 41이 된다. 
하지만, 중첩된 괄호는 사용할 수 없다. 
즉, 3+((8×7)-9)×2, 3+((8×7)-(9×2))은 모두 괄호 안에 괄호가 있기 때문에, 올바른 식이 아니다.

수식이 주어졌을 때, 괄호를 적절히 추가해 만들 수 있는 식의 결과의 최댓값을 구하는 프로그램을 작성하시오. 
추가하는 괄호 개수의 제한은 없으며, 추가하지 않아도 된다.

[입력]
첫째 줄에 수식의 길이 N(1 ≤ N ≤ 19)가 주어진다. 둘째 줄에는 수식이 주어진다. 
수식에 포함된 정수는 모두 0보다 크거나 같고, 9보다 작거나 같다. 
문자열은 정수로 시작하고, 연산자와 정수가 번갈아가면서 나온다. 연산자는 +, -, * 중 하나이다. 
여기서 *는 곱하기 연산을 나타내는 × 연산이다. 항상 올바른 수식만 주어지기 때문에, N은 홀수이다.

[출력]
첫째 줄에 괄호를 적절히 추가해서 얻을 수 있는 결과의 최댓값을 출력한다. 정답은 231보다 작고, -231보다 크다.

[풀이]
dp[i]를 i일 때의 최댓값을 갱신하는 dp로 접근했었는데, 이 경우 음수와 음수의 곱으로 최대가 되는 경우를 갱신할 수 없다.

따라서 DFS 탐색으로 모든 경우의 N - 1에 도달하는 가장 최댓값을 갱신하는 방식으로 접근했다.

주어진 입력의 크기가 작아서 이 경우도 무리없이 가능한 것 같다.

*/

#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <climits>
using namespace std;

int N, ans = INT_MIN;
string input;
vector<bool> visited;

int calc(int a, char op, int b) {
	switch (op)
	{
	case '+':
		return a + b;
	case '-':
		return a - b;
	case '*':
		return a * b;
	}
}

void DFS(int idx, int val) {
	if (idx == N - 1) {
		ans = max(ans, val);
		return;
	}

	if (idx + 2 < N) {
		DFS(idx + 2, calc(val, input[idx + 1], input[idx + 2] - '0'));
	}

	if (idx + 4 < N) {
		DFS(idx + 4, calc(val, input[idx + 1],
			calc(input[idx + 2] - '0', input[idx + 3], input[idx + 4] - '0')));
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N;
	cin >> input;

	if (N == 1) {
		cout << input;
		return 0;
	}

	visited.resize(N, false);

	DFS(0, input[0] - '0');

	cout << ans;

	return 0;
}
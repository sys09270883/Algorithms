/*
https://www.acmicpc.net/problem/1963
[문제]
소수를 유난히도 좋아하는 창영이는 게임 아이디 비밀번호를 4자리 ‘소수’로 정해놓았다. 
어느 날 창영이는 친한 친구와 대화를 나누었는데:

“이제 슬슬 비번 바꿀 때도 됐잖아”
“응 지금은 1033으로 해놨는데... 다음 소수를 무엇으로 할지 고민중이야"
“그럼 8179로 해”
“흠... 생각 좀 해볼게. 이 게임은 좀 이상해서 비밀번호를 한 번에 한 자리 밖에 못 바꾼단 말이야. 
예를 들어 내가 첫 자리만 바꾸면 8033이 되니까 소수가 아니잖아. 
여러 단계를 거쳐야 만들 수 있을 것 같은데... 예를 들면... 1033 1733 3733 3739 3779 8779 8179처럼 말이야.”
“흠...역시 소수에 미쳤군. 그럼 아예 프로그램을 짜지 그래.
네 자리 소수 두 개를 입력받아서 바꾸는데 몇 단계나 필요한지 계산하게 말야.”
“귀찮아”
그렇다. 그래서 여러분이 이 문제를 풀게 되었다. 입력은 항상 네 자리 소수만(1000 이상) 주어진다고 가정하자. 
주어진 두 소수 A에서 B로 바꾸는 과정에서도 항상 네 자리 소수임을 유지해야 하고, 
‘네 자리 수’라 하였기 때문에 0039 와 같은 1000 미만의 비밀번호는 허용되지 않는다.

[입력]
첫 줄에 test case의 수 T가 주어진다. 다음 T줄에 걸쳐 각 줄에 1쌍씩 네 자리 소수가 주어진다.

[출력]
각 test case에 대해 두 소수 사이의 변환에 필요한 최소 회수를 출력한다. 불가능한 경우 Impossible을 출력한다.

[풀이]
1000부터 10000까지 소수를 구해놓고(에라토스테네스의 체), 각 자리수를 바꿔 소수가 되는 경우에 BFS한다.

 + memset은 바이트를 초기화하기 때문에 algorithm에 fill을 쓰자...

*/
#include <iostream>
#include <queue>
#include <cmath>
#include <algorithm>

#define MAX 10001

using namespace std;

int T, A, B;
int prime[MAX];
int visited[MAX];

void eratos() {
	for (int i = 2; i <= sqrt(MAX); i++)
	{
		if (prime[i] == 0)
			continue;

		for (int j = i + i; j <= MAX; j += i)
		{
			prime[j] = 0;
		}
	}
}

int change_num(int num, int i, int j) {
	int res = num;

	switch (i)
	{
	case 0:
		res -= res % 10;
		res += j;
		break;
	case 1:
		res -= ((res % 100) / 10) * 10;
		res += 10 * j;
		break;
	case 2:
		res -= ((res / 100) % 10) * 100;
		res += 100 * j;
		break;
	case 3:
		res -= (res / 1000) * 1000;
		res += 1000 * j;
		break;
	}

	return res;
}

int BFS(int start, int end) {
	queue<pair<int, int>> queue;
	queue.push(pair<int, int>(start, 0));
	visited[start]++;

	while (!queue.empty()) {
		int cur = queue.front().first;
		int cnt = queue.front().second;
		queue.pop();

		if (cur == end) {
			return cnt;
		}

		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				int next = change_num(cur, i, j);

				if (next < 1000 || visited[next] == 1 || prime[next] == 0)
					continue;

				visited[next]++;
				queue.push(pair<int, int>(next, cnt + 1));
			}
		}
	}

	return -1;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	fill(prime, prime + MAX, 1);
	eratos();

	cin >> T;

	while (T-- > 0) {
		fill(visited, visited + MAX, 0);
		cin >> A >> B;

		int res = BFS(A, B);

		if (res >= 0)
			cout << res << "\n";

		else
			cout << "Impossible\n";
	}

	return 0;
}
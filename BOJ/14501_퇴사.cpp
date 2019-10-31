/*
https://www.acmicpc.net/problem/14501
[문제]
상담원으로 일하고 있는 백준이는 퇴사를 하려고 한다.

오늘부터 N+1일째 되는 날 퇴사를 하기 위해서, 남은 N일 동안 최대한 많은 상담을 하려고 한다.

백준이는 비서에게 최대한 많은 상담을 잡으라고 부탁을 했고, 비서는 하루에 하나씩 서로 다른 사람의 상담을 잡아놓았다.

각각의 상담은 상담을 완료하는데 걸리는 기간 Ti와 상담을 했을 때 받을 수 있는 금액 Pi로 이루어져 있다.

N = 7인 경우에 다음과 같은 상담 일정표를 보자.

1일	2일	3일	4일	5일	6일	7일
Ti	3	5	1	1	2	4	2
Pi	10	20	10	20	15	40	200
1일에 잡혀있는 상담은 총 3일이 걸리며, 상담했을 때 받을 수 있는 금액은 10이다. 
5일에 잡혀있는 상담은 총 2일이 걸리며, 받을 수 있는 금액은 15이다.

상담을 하는데 필요한 기간은 1일보다 클 수 있기 때문에, 모든 상담을 할 수는 없다. 
예를 들어서 1일에 상담을 하게 되면, 2일, 3일에 있는 상담은 할 수 없게 된다. 
2일에 있는 상담을 하게 되면, 3, 4, 5, 6일에 잡혀있는 상담은 할 수 없다.

또한, N+1일째에는 회사에 없기 때문에, 6, 7일에 있는 상담을 할 수 없다.

퇴사 전에 할 수 있는 상담의 최대 이익은 1일, 4일, 5일에 있는 상담을 하는 것이며, 이때의 이익은 10+20+15=45이다.

상담을 적절히 했을 때, 백준이가 얻을 수 있는 최대 수익을 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 N (1 ≤ N ≤ 15)이 주어진다.

둘째 줄부터 N개의 줄에 Ti와 Pi가 공백으로 구분되어서 주어지며, 1일부터 N일까지 순서대로 주어진다.
(1 ≤ Ti ≤ 5, 1 ≤ Pi ≤ 1,000)

[출력]
첫째 줄에 백준이가 얻을 수 있는 최대 이익을 출력한다.

[풀이]
BFS로 전체 경우를 확인한다.
큐에서 해당 날짜에서 일을 진행하는 경우와 일을 진행하지 않고 다음 날 일을 하는 경우를 넣어준다.
큐에서 값을 빼내면서 최댓값을 갱신하고 최댓값을 리턴한다.

 + 이렇게 할 경우 불필요한 연산이 있지만 N의 크기가 작기 때문에 무시할 만한 정도인 듯 하다.
 + N이 클 경우, 날짜가 더 많은데 최댓값이 더 작은 경우를 제외해서 넣어줘야 할 듯하다.
*/

#include <iostream>
#include <cmath>
#include <vector>
#include <queue>

using namespace std;

struct Node {
	int day, price;

	Node() {}
	Node(int day, int price) : day(day), price(price) {}
};

int N, ans;
vector<Node> arr;

int BFS() {
	queue<Node> q;
	q.push(Node(1, 0));
	int ans = 0;

	while (!q.empty()) {
		Node tmp = q.front();
		q.pop();
		int cur_day = tmp.day;
		int cur_price = tmp.price;

		ans = max(cur_price, ans);

		if (cur_day == N + 1)
			continue;

		int next_day = cur_day + arr[cur_day].day;
		int next_price = cur_price + arr[cur_day].price;

		if (next_day <= N + 1)
			q.push(Node(next_day, next_price));

		next_day = cur_day + 1;
		next_price = cur_price;

		if (next_day <= N + 1)
			q.push(Node(next_day, next_price));
	}

	return ans;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N;

	arr.resize(N + 1);

	for (int i = 1; i <= N; i++)
	{
		int t, p;
		cin >> t >> p;
		arr[i] = Node(t, p);
	}

	cout << BFS();

	return 0;
}
/*
https://www.acmicpc.net/problem/12761
[����]
���Կ� �̴ֹ� ������ ���� �� �ٸ� �����ִ�. 
���� ��ȣ�� 0 ���� 100,000 ���� �����ϰ� ���Դ� �� �� ����, �̴ֹ� �� �� ���� ��ġ�ϰ� �ִ�. 
���Դ� �ֹ̰� �ʹ� ����ͱ� ������ �ִ��� ���� �ֹ̿��� ���� ����  ��ŭ�� ���� ���� ��ī�� ������ �����Դ�. 
���԰� ���� �ٸ��� �ǳʴ� ��Ģ�� �� ����ε�, �� �Ͽ� �̵��� �� �ִ� �Ÿ��� �̷��ϴ�. 
�� ��ġ���� +1ĭ, -1ĭ�� �̵��� �� �ְ�, ��ī�� ������ �̿��� �� ��ġ���� �� ��ŭ �¿�� ������ �� ������, 
���������� ���� ��� �� ��ġ�� �質 ���� ��ġ�� �̵��� �� �� �ִ�. 
���� ��� ���� ���԰� 7�� �� ���� �ְ� ��ī�� ������ ���� 8�̸� �׳� ������ �ؼ� 15�� ���� �� ���� �ְ�, 
���������� ���� ��� 56�� ���� �� ���� �ִٴ� ���̴�. 
�־��� 8������ ��� �� ������ ����� ��� �ִ��� ���� ���԰� �̸ֹ� ���� �� �ְ� ��������. 
��, �̵� �������� 100,000���� ũ�ų� 0���� ���� ��ȣ�� ���� �������� �����Ƿ� �� �� ����, 
���� ����� ��� ����ص� �Ǹ� �׻� ������ �� �ִ� ���̽��� �־�����.

[�Է�]
�Է��� ù �ٿ� ��ī�� ������ �� �� , �׸��� ������ ������ġ , �ֹ��� ���� ��ġ �� �־�����. (��,  �̰�  )

[���]
���԰� �ֹ̿��� �����ϱ� ���� �ּ����� �̵� Ƚ���� ����϶�.

[Ǯ��]
8���� ����� ���ؼ� BFS�� Ž���Ѵ�.

*/
#include <iostream>
#include <queue>

using namespace std;

int A, B, N, M;
bool visited[100001];

struct Node {
	int cur, cnt;

	Node(int cur, int cnt) : cur(cur), cnt(cnt) {}
};

int BFS() {
	queue<Node> queue;
	queue.push(Node(N, 0));
	visited[N] = true;

	while (!queue.empty()) {
		Node tmp = queue.front();
		int cur = tmp.cur;
		int cnt = tmp.cnt;
		queue.pop();

		if (cur == M)
			return cnt;

		for (size_t i = 0; i < 8; i++)
		{
			int next;
			switch (i) {
			case 0:
				next = cur + A;
				break;
			case 1:
				next = cur + B;
				break;
			case 2:
				next = cur * A;
				break;
			case 3:
				next = cur * B;
				break;
			case 4:
				next = cur + 1;
				break;
			case 5:
				next = cur - 1;
				break;
			case 6:
				next = cur - A;
				break;
			case 7:
				next = cur - B;
				break;
			}

			if (next < 0 || next > 1e5)
				continue;

			if (visited[next])
				continue;

			visited[next] = true;
			queue.push(Node(next, cnt + 1));
		}
	}

	return -1;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> A >> B >> N >> M;

	cout << BFS();

	return 0;
}
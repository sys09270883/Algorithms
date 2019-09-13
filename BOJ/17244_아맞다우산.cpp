/*
https://www.acmicpc.net/problem/17244
[문제]

경재씨는 저녁 약속을 가기 전 챙기지 않은 물건들이 있는 지 확인하고 있다. 
필요한 물건은 전부 챙긴 것 같았고 외출 후 돌아오는 길에 경재씨는 외쳤다.

"아 맞다 우산!!!"

경재 씨는 매번 외출하고 나서야 어떤 물건을 집에 놓고 왔다는 것을 떠올릴 때마다 자책감에 시달리는 것이 너무 싫었다.

외출이 잦은 경재 씨는 반복되는 일을 근절하기 위해 꼭 챙겨야 할 물건들을 정리해보았다. 
하지만 지갑, 스마트폰, 우산, 차 키, 이어폰, 시계, 보조 배터리 등 종류와 개수가 너무 많았다.

평소 불필요한 움직임을 아주 싫어하는 경재 씨는 이 물건들을 최대한 빠르게 챙겨서 외출하는 이동 경로를 알고 싶었다.

경재 씨는 한 걸음에 상하좌우에 인접한 칸으로만 움직일 수 있다.

경재 씨를 위해 집을 위에서 바라본 모습과 챙겨야 할 물건들의 위치들을 알고 있을 때, 
물건을 모두 챙겨서 외출하기까지 최소 몇 걸음이 필요한지 구하는 프로그램을 작성하자.

[입력]
첫 번째 줄에는 집의 가로 길이 N과 세로 길이 M이 입력된다. (3 ≤ N, M ≤ 50)

두 번째 줄부터는 집의 구조가 예제 입력과 같이 주어진다.

비어있는 곳은 '.'로 벽은 '#'로 입력된다. 
경재 씨의 현재 위치는 S, 나가는 문의 위치는 E, 챙겨야 하는 물건은 종류에 상관없이 X로 입력된다.

챙겨야 하는 물건은 최대 5개까지 있을 수 있다. 집은 언제나 벽으로 둘러싸여 있고, 나가는 문은 언제나 하나이다.

[출력]
S에서 출발하여 모든 물건을 챙겨서 E까지 도착할 수 있는 최소 시간을 출력한다. 모든 물건을 챙길 수 없는 경우는 주어지지 않는다.

[풀이]
전형적인 BFS 문제인데 물건을 다 챙겨야 탈출할 수 있다.
물건이 5개 밖에 안되므로 비트마스크로 표현이 가능하다.

입력을 받으면서 'X'일 때 인덱싱한다.

ex)
7 6
#######
#SX..X#
#..####
#..X..#
#...X.#
#####E#

7 6
#######
#S0..1#
#..####
#..2..#
#...3.#
#####E#

BFS를 하면서 해당 숫자를 만나면 비트를 1로 바꾸고, 'E'이면서 모든 물건을 가져왔으면 그 때까지의 거리를 출력한다.

*/
#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int N, M, start_x, start_y, end_x, end_y, idx_thing = 0;
char map[51][51];
bool visited[51][51][1 << 5];
int dx[] = { -1, 0, 1, 0 };
int dy[] = { 0, -1, 0, 1 };

typedef struct Node {
	int y, x, cnt, bit;

	Node(int y, int x, int cnt, int bit) : y(y), x(x), cnt(cnt), bit(bit) {}
};

int BFS() {
	queue<Node> queue;
	queue.push(Node(start_y, start_x, 0, 0));
	visited[start_y][start_x][0] = true;

	while (!queue.empty()) {
		Node cur = queue.front();
		queue.pop();

		int y = cur.y;
		int x = cur.x;
		int cnt = cur.cnt;
		int bit = cur.bit;

		if (y == end_y && x == end_x)
			return cnt;

		for (size_t i = 0; i < 4; i++)
		{
			int ny = y + dy[i];
			int nx = x + dx[i];
			int nbit = bit;

			if (ny < 0 || nx < 0 || ny >= M || nx >= N)
				continue;

			if (visited[ny][nx][nbit] || map[ny][nx] == '#')
				continue;

			if (map[ny][nx] == 'E' && nbit != (1 << idx_thing) - 1)
				continue;

			if (map[ny][nx] >= '0' && map[ny][nx] < '5')
				nbit |= (1 << map[ny][nx] - '0');

			visited[ny][nx][nbit] = true;
			queue.push(Node(ny, nx, cnt + 1, nbit));
		}
	}

	return -1;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M;

	for (size_t i = 0; i < M; i++)
	{
		for (size_t j = 0; j < N; j++)
		{
			cin >> map[i][j];

			if (map[i][j] == 'S') {
				start_y = i;
				start_x = j;
			}

			else if (map[i][j] == 'E') {
				end_y = i;
				end_x = j;
			}

			else if (map[i][j] == 'X') {
				map[i][j] = '0' + idx_thing++;
			}
		}
	}

	cout << BFS();

	return 0;
}

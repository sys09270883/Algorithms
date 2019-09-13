/*
https://www.acmicpc.net/problem/17244
[����]

���羾�� ���� ����� ���� �� ì���� ���� ���ǵ��� �ִ� �� Ȯ���ϰ� �ִ�. 
�ʿ��� ������ ���� ì�� �� ���Ұ� ���� �� ���ƿ��� �濡 ���羾�� ���ƴ�.

"�� �´� ���!!!"

���� ���� �Ź� �����ϰ� ������ � ������ ���� ���� �Դٴ� ���� ���ø� ������ ��å���� �ô޸��� ���� �ʹ� �Ⱦ���.

������ ���� ���� ���� �ݺ��Ǵ� ���� �����ϱ� ���� �� ì�ܾ� �� ���ǵ��� �����غ��Ҵ�. 
������ ����, ����Ʈ��, ���, �� Ű, �̾���, �ð�, ���� ���͸� �� ������ ������ �ʹ� ���Ҵ�.

��� ���ʿ��� �������� ���� �Ⱦ��ϴ� ���� ���� �� ���ǵ��� �ִ��� ������ ì�ܼ� �����ϴ� �̵� ��θ� �˰� �;���.

���� ���� �� ������ �����¿쿡 ������ ĭ���θ� ������ �� �ִ�.

���� ���� ���� ���� ������ �ٶ� ����� ì�ܾ� �� ���ǵ��� ��ġ���� �˰� ���� ��, 
������ ��� ì�ܼ� �����ϱ���� �ּ� �� ������ �ʿ����� ���ϴ� ���α׷��� �ۼ�����.

[�Է�]
ù ��° �ٿ��� ���� ���� ���� N�� ���� ���� M�� �Էµȴ�. (3 �� N, M �� 50)

�� ��° �ٺ��ʹ� ���� ������ ���� �Է°� ���� �־�����.

����ִ� ���� '.'�� ���� '#'�� �Էµȴ�. 
���� ���� ���� ��ġ�� S, ������ ���� ��ġ�� E, ì�ܾ� �ϴ� ������ ������ ������� X�� �Էµȴ�.

ì�ܾ� �ϴ� ������ �ִ� 5������ ���� �� �ִ�. ���� ������ ������ �ѷ��ο� �ְ�, ������ ���� ������ �ϳ��̴�.

[���]
S���� ����Ͽ� ��� ������ ì�ܼ� E���� ������ �� �ִ� �ּ� �ð��� ����Ѵ�. ��� ������ ì�� �� ���� ���� �־����� �ʴ´�.

[Ǯ��]
�������� BFS �����ε� ������ �� ì�ܾ� Ż���� �� �ִ�.
������ 5�� �ۿ� �ȵǹǷ� ��Ʈ����ũ�� ǥ���� �����ϴ�.

�Է��� �����鼭 'X'�� �� �ε����Ѵ�.

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

BFS�� �ϸ鼭 �ش� ���ڸ� ������ ��Ʈ�� 1�� �ٲٰ�, 'E'�̸鼭 ��� ������ ���������� �� �������� �Ÿ��� ����Ѵ�.

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

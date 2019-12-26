/*
https://www.acmicpc.net/problem/16572
[����]
2000��2000 ũ���� ���� ���� �־�����, �� �ȼ�(ĭ)�� ��� ���� �̿��ؼ� ��ġ�� ǥ�õȴ�.
���� ���� ���� 1��, ���� ���� ���� 1���̴�.

�̶� �ȼ� �ﰢ���� ���Ǵ� ������ ����.

x��, y���� �ȼ��� (x,y)���� ǥ���ǰ�, �ȼ� �ﰢ���� �� ���� �ȼ��� �̷���� �ִ�.
�ȼ� �ﰢ���� 3���� �ڿ��� A, B, C�� ���ؼ�  ���� ǥ���ȴ�.
���� ���� �ȼ� �ﰢ���� �����ϴ� �ȼ��� �׻� ���� �ǿ� �������� ����ȴ�.
���� ���� �ȼ���� ������ �ȼ� �ﰢ���� n�� �־����� ��, �ȼ� �ﰢ������ ���� �ȼ��� ������ ����ض�.

��, ���� �ٸ� �ȼ� �ﰢ���� ���� �ȼ��� ���� �� �� �ִ�.

���� ���, n = 3 �̰� �־����� 3���� �ȼ� �ﰢ����  �� �� ������ ���¸� �����ϸ� ������ ����.



Figure: ���� �Ϻκ��� ����

�̶� ���������� �������� �ȼ��� ������ 9�� �ȴ�.

[�Է�]
ù ��° �ٿ� �ȼ� �ﰢ���� ���� n�� �־�����. 
���� n���� �ٿ� ���ļ� �ȼ� �ﰢ���� ���� ������ ��Ÿ���� �� ���� A, B, C�� �־�����.

[���]
�������� �ȼ��� ������ ����Ѵ�.

[Ǯ��]
�־��� ��ǥ���� C - 1��ŭ ���Ľ�Ű�� �Ǵ� ��������.
dp�� �ش� ���� 0�� �� ������ �����ʰ� �Ʒ����� �����Ѵ�.
 + ������ ���� ���ظ� �� ���ؼ� ���� ���Ƽ� �ؼ��ߴ�...
 + �̤�

*/

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int map[2001][2001];

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int n, A, B, C, ans = 0;
	cin >> n;

	for (int i = 0; i < n; i++)
	{
		cin >> A >> B >> C;
		map[A][B] = max(map[A][B], C);
	}

	for (int i = 1; i < 2001; i++)
	{
		for (int j = 1; j < 2001; j++)
		{
			if (!map[i][j])
				continue;

			map[i][j + 1] = max(map[i][j + 1], map[i][j] - 1);
			map[i + 1][j] = max(map[i + 1][j], map[i][j] - 1);
			ans++;
		}
	}

	return !(cout << ans);
}
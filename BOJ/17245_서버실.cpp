/*
https://www.acmicpc.net/problem/17245
[문제]
서버실은 여러 대의 서버 컴퓨터들을 안정적으로 운영할 수 있는 환경을 유지하기 위해 설치된 공간을 말한다.

이 회사의 서버실은 N×N 칸으로 구분되어 있고, 각 칸마다 서버 랙이 있어 컴퓨터를 여러 대 쌓을 수 있다. 
서버가 과열되지 않도록 서버실에는 언제나 냉방기가 작동하고 있다. 
그런데 회사가 경제적으로 어려움에 처한 나머지, 서버실의 운영 비용을 줄이기 위해 
서버실 내의 컴퓨터 중 절반만 정상적으로 관리하기로 하였다.

냉방기에서 나온 차가운 공기는 서버실의 아래쪽부터 서서히 차오른다. 
1분마다 컴퓨터 한 대의 높이만큼 방을 채운다. 
이 회사의 서버 컴퓨터는 환경에 매우 민감하여 차가운 공기를 받아야만 동작하고 그렇지 못하면 장애를 일으킨다.

서버실의 컴퓨터 중 절반 이상이 켜지려면 몇 분이 필요할까?

[입력]
정수 N이 주어진다. (1 ≤ N ≤ 1000)

N×N개의 각 칸에 컴퓨터가 몇 대 쌓여있는지가 입력된다. 한 칸에는 최대 10,000,000대까지 쌓여있을 수 있다.

[출력]
몇 분이 지나야 전체 컴퓨터의 절반 이상이 장애를 일으키지 않고 동작할 수 있는지 출력한다.

[풀이]
이분탐색 또는 높이 집합에 최대 높이부터 차례대로 비교해서 해결 가능하다.
풀이는 이분탐색으로 했다.

*/

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

typedef long long LL;

vector<vector<int>> v;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	LL total = 0;
	int N, l = 0, r = 1e7;
	cin >> N;

	for (size_t i = 0; i < N; i++)
	{
		vector<int> row;
		row.resize(N);
		v.push_back(row);
	}

	for (size_t i = 0; i < N; i++)
	{
		for (size_t j = 0; j < N; j++)
		{
			cin >> v[i][j];
			total += v[i][j];
		}
	}

	while (l <= r) {
		int mid = (l + r) / 2;
		LL tmp = 0;

		for (size_t i = 0; i < N; i++)
		{
			for (size_t j = 0; j < N; j++)
			{
				tmp += min(mid, v[i][j]);
			}
		}

		if (tmp >= (total + 1) / 2)
			r = mid - 1;

		else
			l = mid + 1;
	}

	cout << r + 1;

	return 0;
}
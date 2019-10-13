/*
[문제]
"I love Croatia"는 네델란드의 인기 티비 프로그램 "I love my country"의 포맷 라이센스를 수입해 만든 크로아티아의 티비쇼이다.
이 티비쇼에서 가장 인기있는 게임은 "Happy Birthday"이며, 이 게임에 대한 문제를 풀게 된다.

플레이어 8명이 오른쪽 그림과 같이 원을 이루어서 앉아있다. 
한 사람은 게임이 시작한지 3분 30초가 지나면 터지는 폭탄을 들고 있다. 
폭탄을 들고있는 사람에게 질문을 하면서 게임은 시작된다. 
어떤 문제의 정답을 맞추지 못한 경우나, 문제를 스킵한 경우에는 그 즉시 다음 문제를 받게 된다. 
문제의 정답을 맞춘 경우에는 폭탄을 바로 왼쪽에 있는 플레이어에게 넘겨주고, 넘겨받은 플레이어에게 다음 질문이 나가게 된다.

게임이 시작했을 때 폭탄을 들고 있는 사람의 번호와 처음 N개의 질문을 대답할때까지 걸린 시간이 주어진다. 
이때, 폭탄을 터뜨린 사람의 번호를 구하는 프로그램을 작성하시오.

각 사람이 질문을 듣고 대답할 때까지 걸린 시간과, 그 사람의 대답이 맞았는지(T), 틀렸는지(N), 스킵했는지(P)가 주어진다. 
정답을 말하는 시간, 질문을 듣는 시간은 모두 0이라고 가정하며, 박스를 전달하는 시간도 0이라고 가정한다. 
항상 사람이 폭탄을 들고있었을 때 터지는 입력만 주어진다.

[입력]
첫째 줄에 게임이 시작했을 때 폭탄을 들고 있는 사람의 번호 K (1 ≤ K ≤ 8)가 주어진다.

둘째 줄에는 질문의 개수 N (1 ≤ N ≤ 100)이 주어진다.

다음 N개 줄에는 i번째 질문을 대답하기까지 걸린 시간(단위: 초) T (1 ≤ T ≤ 100), 그 플레이어의 대답 Z (T, N, P중 하나) 가 주어진다.

[출력]
폭탄을 터뜨린 사람의 번호를 출력한다.

[풀이]
문제의 조건에 따라 정직하게 구현하는 되는 문제였다.

 + 입력을 다 받은 후에 출력을 해야 하는 줄 알았는데 상관이 없는 것 같다. 입력을 다 안받아도 되는 듯?
*/
#include <iostream>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int K, N, time_limit = 210;

	cin >> K >> N;

	for (size_t i = 0; i < N; i++)
	{
		int T;
		char Z;
		cin >> T >> Z;

		time_limit -= T;

		if (time_limit <= 0)
			break;

		if (Z == 'T')
			K++;

		if ((K %= 8) == 0)
			K = 8;
	}

	cout << K;

	return 0;
}

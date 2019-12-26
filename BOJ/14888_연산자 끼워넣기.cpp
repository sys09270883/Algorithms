#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;
const int INF = 1e9;
vector<int> v;
int N, MAX = -INF, MIN = INF;

void func(int sum, int idx, int plus, int minus, int mul, int div) {
	if (plus + minus + mul + div == 0) {
		MIN = min(MIN, sum);
		MAX = max(MAX, sum);
		return;
	}

	if (plus > 0) {
		func(sum + v[idx], idx + 1, plus - 1, minus, mul, div);
	}

	if (minus > 0) {
		func(sum - v[idx], idx + 1, plus, minus - 1, mul, div);
	}

	if (mul > 0) {
		func(sum * v[idx], idx + 1, plus, minus, mul - 1, div);
	}

	if (div > 0) {
		func(sum / v[idx], idx + 1, plus, minus, mul, div - 1);
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	int plus, minus, mul, div;
	cin >> N;
	v.resize(N);
	for (int i = 0; i < N; i++)
	{
		cin >> v[i];
	}
	cin >> plus >> minus >> mul >> div;
	func(v[0], 1, plus, minus, mul, div);
	return !(cout << MAX << '\n' << MIN);
}
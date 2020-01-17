#include <bits/stdc++.h>
using namespace std;

int N;
vector<vector<int>> v;

void func(int x, int y, int diff) {
	if (diff == 1) {
		cout << v[x][y];
		return;
	}

	bool zero = true, one = true;
	for (int i = x; i < x + diff; i++)
	{
		for (int j = y; j < y + diff; j++)
		{
			if (v[i][j] == 1)
				zero = false;
			else if (v[i][j] == 0)
				one = false;
		}
	}

	if (zero)
		cout << 0;
	else if (one)
		cout << 1;
	else {
		cout << '(';
		diff >>= 1;
		func(x, y, diff);
		func(x, y + diff, diff);
		func(x + diff, y, diff);
		func(x + diff, y + diff, diff);
		cout << ')';
	}
	return;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cin >> N;
	for (int i = 0; i < N; i++)
	{
		vector<int> row(N);
		v.push_back(row);
	}
	for (int i = 0; i < N; i++)
	{
		string s;
		cin >> s;
		for (int j = 0; j < N; j++)
		{
			v[i][j] = s[j] - '0';
		}
	}

	func(0, 0, N);
	return 0;
}
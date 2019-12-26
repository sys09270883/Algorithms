#include <iostream>
#include <vector>
#include <algorithm>
#include <functional>
using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	int N, A, B, k, ans = -1, sum;
	cin >> N >> A >> B;
	vector<int> a(A + 1, 0), b(B + 1, 0);
	for (int i = 1; i < A + 1; i++)
	{
		cin >> a[i];
	}
	for (int i = 1; i < B + 1; i++)
	{
		cin >> b[i];
	}
	sort(a.begin() + 1, a.end(), greater<int>());
	sort(b.begin() + 1, b.end(), greater<int>());
	for (int i = 0; i < N / 2 + 1; i++)
	{
		k = N - 2 * i;
		if (k > A || i > B)
			continue;
		sum = 0;
		for (int j = 1; j < k + 1; j++)
		{
			sum += a[j];
		}
		for (int j = 1; j < i + 1; j++)
		{
			sum += b[j];
		}
		ans = max(ans, sum);
	}
	return !(cout << ans);
}
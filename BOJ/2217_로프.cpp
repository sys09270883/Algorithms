#include <iostream>
#include <queue>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int N, tmp, max, i = 1;
	priority_queue<int> pq;
	vector<int> v;
	
	cin >> N;
	for (int i = 0; i < N; i++)
	{
		cin >> tmp;
		pq.push(tmp);
	}
	max = pq.top();
	pq.pop();

	while (!pq.empty()) {
		if (max < ++i * pq.top()) {
			max = i * pq.top();
		}
		pq.pop();
	}
	
	cout << max;

	return 0;
}
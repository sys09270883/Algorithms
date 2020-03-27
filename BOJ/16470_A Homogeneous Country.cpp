#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

unordered_map<string, int> mp;
string s;
double res = 1;
int sz;

int main() {
    FASTIO
	while (cin >> s) {
        sz++;
		if (mp.find(s) == mp.end())
            mp[s] = 0;
        mp[s]++;
	}
    for (auto& i : mp) {
        res -= ((double)i.second / sz) * ((double)i.second / sz);
    }
	cout << res;
}
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

int K, sz;
vector<int> v;
vector<vector<int>> depth;

void func(int s, int e, int d) {
    if (s == e)
        return;
    depth[d++].pb(v[s + e >> 1]);
    int m = s + e >> 1;
    func(s, m, d);
    func(m + 1, e, d);
}

int main() {
    FASTIO
    cin >> K;
    depth.resize(K + 1, vector<int>());
    sz = 1 << K;
    v.resize(sz);
    for (int i = 1; i < sz; i++) {
        cin >> v[i];
    }
    func(1, sz, 1);
    for (int i = 1; i < K + 1; i++) {
        for (auto& j : depth[i]) {
            cout << j << ' ';
        }
        cout << endl;
    }
}
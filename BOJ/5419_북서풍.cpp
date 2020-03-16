#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

int T, N;
vector<pii> v;
vector<int> idx;
vector<ll> tree;
ll res;

bool cmp(const pii& p1, const pii& p2) {
    if (p1.first == p2.first)
        return p2.second < p1.second;
    return p1.first < p2.first;
}

void resize() {
    res = 0;
    v.clear();
    tree.clear();
    idx.clear();
    v.resize(N + 1);
    tree.resize(1 << (int)ceil(log2(N)) + 1);
}

ll update(int i, int n, int s, int e) {
    if (i > e || i < s)
        return tree[n];
    if (s == e)
        return ++tree[n];
    int m = s + e >> 1;
    return tree[n] = update(i, n << 1, s, m) + update(i, n << 1 | 1, m + 1, e);
}

ll query(int l, int r, int n, int s, int e) {
    if (l > e || r < s)
        return 0;
    if (l <= s && e <= r)
        return tree[n];
    int m = s + e >> 1;
    return query(l, r, n << 1, s, m) + query(l, r, n << 1 | 1, m + 1, e);
}

int main() {
    FASTIO
    cin >> T;
    while (T--) {
        cin >> N;
        resize();
        for (int i = 1; i < N + 1; i++) {
            int a, b;
            cin >> a >> b;
            v[i] = {a, b};
            idx.pb(b);
        }
        sort(v.begin() + 1, v.end(), cmp);
        sort(all(idx));
        idx.erase(unique(all(idx)), idx.end());
        for (int i = 1; i < N + 1; i++) {
            int tar = lower_bound(all(idx), v[i].second) - idx.begin() + 1;
            res += query(tar, N, 1, 1, N);
            update(tar, 1, 1, N);
        }
        cout << res << endl;
    }
}
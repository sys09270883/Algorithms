#include <bits/stdc++.h>
using namespace std;
using ll = long long;
using ull = unsigned long long;
using pii = pair<int, int>;
using pll = pair<ll, ll>;
using pli = pair<ll, int>;
#pragma GCC optimize("Ofast")
#pragma GCC optimize("unroll-loops")
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define rall(x) (x).rbegin(), (x).rend()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

int N, Q;
vector<ll> tree;

ll update(int i, int d, int n, int s, int e) {
    if (i > e || i < s)
        return tree[n];
    if (s == e)
        return tree[n] = s & 1 ? tree[n] + d : tree[n] - d;
    int m = s + e >> 1;
    return tree[n] = update(i, d, n << 1, s, m) + update(i, d, n << 1 | 1, m + 1, e);
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
    cin >> N >> Q;
    tree.resize(1 << (int)ceil(log2(N)) + 1);
    for (int i = 1; i < N + 1; i++) {
        int a;
        cin >> a;
        update(i, a, 1, 1, N);
    }
    for (int i = 0; i < Q; i++) {
        int q, a, b;
        cin >> q >> a >> b;
        if (q == 1)
            cout << abs(query(a, b, 1, 1, N)) << endl;
        else
            update(a, b, 1, 1, N);
    }
}

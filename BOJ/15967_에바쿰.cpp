#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
typedef long long ll;
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>

int N, Q1, Q2, H;
vector<ll> tree, lazy;

void updateLazy(int n, int s, int e) {
    if (lazy[n]) {
        if (s ^ e) {
            lazy[n << 1] += lazy[n];
            lazy[n << 1 | 1] += lazy[n];
        }
        tree[n] += (e - s + 1) * lazy[n];
        lazy[n] = 0;
    }
}

ll update(int l, int r, ll d, int n, int s, int e) {
    updateLazy(n, s, e);
    if (l > e || r < s)
        return tree[n];
    if (l <= s && e <= r) {
        if (s ^ e) {
            lazy[n << 1] += d;
            lazy[n << 1 | 1] += d;
        }
        return tree[n] += (e - s + 1) * d;
    }
    int m = s + e >> 1;
    return tree[n] = update(l, r, d, n << 1, s, m) + update(l, r, d, n << 1 | 1, m + 1, e);
}

ll query(int l, int r, int n, int s, int e) {
    updateLazy(n, s, e);
    if (l > e || r < s)
        return 0;
    if (l <= s && e <= r)
        return tree[n];
    int m = s + e >> 1;
    return query(l, r, n << 1, s, m) + query(l, r, n << 1 | 1, m + 1, e);
}

int main() {
    FASTIO
    cin >> N >> Q1 >> Q2;
    H = 1 << (int)ceil(log2(N)) + 1;
    tree.resize(H);
    lazy.resize(H);
    for (int i = 1; i < N + 1; i++) {
        int a;
        cin >> a;
        update(i, i, a, 1, 1, N);
    }
    for (int i = 0; i < Q1 + Q2; i++) {
        int q, a, b, c;
        cin >> q >> a >> b;
        if (q == 1)
            cout << query(a, b, 1, 1, N) << endl;
        else {
            cin >> c;
            update(a, b, c, 1, 1, N);
        }
    }
}
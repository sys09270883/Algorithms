#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;
typedef pair<ll, int> pli;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

int N, M, H;
vector<ll> v, diff, tree, lazy;

void resize() {
    v.resize(N + 1), diff.resize(N + 1);
    H = 1 << (int)ceil(log2(N)) + 1;
    tree.resize(H), lazy.resize(H);
}

ll init(int n, int s, int e) {
    if (s == e)
        return tree[n] = diff[s];
    int m = s + e >> 1;
    return tree[n] = init(n << 1, s, m) + init(n << 1 | 1, m + 1, e);
}

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

ll update(int l, int r, int v, int n, int s, int e) {
    updateLazy(n, s, e);
    if (l > e || r < s)
        return tree[n];
    if (l <= s && e <= r) {
        lazy[n] = v;
        updateLazy(n, s, e);
        return tree[n];
    }
    int m = s + e >> 1;
    return tree[n] = update(l, r, v, n << 1, s, m) + update(l, r, v, n << 1 | 1, m + 1, e);
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
    cin >> N;
    resize();
    for (int i = 1; i < N + 1; i++) {
        cin >> v[i];
        diff[i] = v[i] - v[i - 1];
    }
    init(1, 1, N);
    cin >> M;
    for (int i = 0; i < M; i++) {
        int q, a, b;
        cin >> q >> a;
        if (q == 1) {
            cin >> b;
            update(a, b, 1, 1, 1, N);
            update(b + 1, b + 1, -(b - a + 1), 1, 1, N);
        }
        else
            cout << query(1, a, 1, 1, N) << endl;
    }
}
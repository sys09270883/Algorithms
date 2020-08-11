#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;
typedef pair<ll, int> pli;
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

int N;
ll res;
vector<int> v, tree, l, r;

int update(int i, int d, int n, int s, int e) {
    if (i > e || i < s) {
        return tree[n];
    }
    if (s == e) {
        return tree[n] += d;
    }
    int m = s + e >> 1;
    return tree[n] = update(i, d, n << 1, s, m) + update(i, d, n << 1 | 1, m + 1, e);
}

int query(int l, int r, int n, int s, int e) {
    if (l > e || r < s) {
        return 0;
    }
    if (l <= s && e <= r) {
        return tree[n];
    }
    int m = s + e >> 1;
    return query(l, r, n << 1, s, m) + query(l, r, n << 1 | 1, m + 1, e);
}

void resize() {
    v.resize(N + 1);
    l.resize(N + 1);
    r.resize(N + 1);
    tree.resize(1 << (int)ceil(log2(N)) + 1);
}

int main() {
    FASTIO
    cin >> N;
    resize();
    for (int i = 1; i < N + 1; i++) {
        cin >> v[i];
    }
    for (int i = 1; i < N + 1; i++) {
        l[i] = query(v[i] + 1, N, 1, 1, N);
        update(v[i], 1, 1, 1, N);
    }
    fill(all(tree), 0);
    for (int i = N; i > 0; i--) {
        r[i] = query(1, v[i] - 1, 1, 1, N);
        update(v[i], 1, 1, 1, N);
    }
    for (int i = 1; i < N + 1; i++) {
        res += 1ll * l[i] * r[i];
    }
    cout << res;
}
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
vector<int> v, tree, lazy;

void resize() {
    v.resize(N);
    H = 1 << (int)ceil(log2(N)) + 1;
    tree.resize(H), lazy.resize(H);
}

int init(int n, int s, int e) {
    if (s == e)
        return tree[n] = v[s];
    int m = s + e >> 1;
    return init(n << 1, s, m) ^ init(n << 1 | 1, m + 1 , e);
}

void updateLazy(int n, int s, int e) {
    if (lazy[n]) {
        if (s ^ e) {
            lazy[n << 1] ^= lazy[n];
            lazy[n << 1 | 1] ^= lazy[n];
        }
        tree[n] ^= lazy[n] * ((e - s + 1) & 1);
        lazy[n] = 0;
    }
}

int update(int l, int r, int v, int n, int s, int e) {
    updateLazy(n, s, e);
    if (l > e || r < s)
        return tree[n];
    if (l <= s && e <= r) {
        if (s ^ e) {
            lazy[n << 1] ^= v;
            lazy[n << 1 | 1] ^= v;
        }
        return tree[n] ^= v * ((e - s + 1) & 1);
    }
    int m = s + e >> 1;
    return tree[n] = update(l, r, v, n << 1, s, m) ^ update(l, r, v, n << 1 | 1, m + 1, e);
}

int query(int i, int n, int s, int e) {
    updateLazy(n, s, e);
    if (i > e || i < s)
        return 0;
    if (s == e)
        return tree[n];
    int m = s + e >> 1;
    return query(i, n << 1, s, m) ^ query(i, n << 1 | 1, m + 1, e);
}

int main() {
    FASTIO
    cin >> N;
    resize();
    for (auto& i : v) {
        cin >> i;
    }
    init(1, 0, N - 1);
    cin >> M;
    for (int i = 0; i < M; i++) {
        int q, a, b, c;
        cin >> q >> a;
        if (q == 1) {
            cin >> b >> c;
            update(a, b, c, 1, 0, N - 1);
        }
        else {
            cout << query(a, 1, 0, N - 1) << endl;
        }
    }
}
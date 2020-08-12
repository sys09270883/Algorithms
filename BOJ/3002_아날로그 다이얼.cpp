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

const int MOD = 10;
int N, M, H;
vector<int> v, lazy;
vector<vector<int>> tree;

void resize() {
    v.resize(N + 1);
    H = 1 << (int)ceil(log2(N)) + 1;
    tree.resize(H, vector<int>(10));
    lazy.resize(H);
}

void propagate(int n, int s, int e) {
    if (lazy[n]) {
        static int tmp[10] = {0, };
        for (int i = 0; i < 10; i++) {
            tmp[(i + lazy[n]) % 10] = tree[n][i];
        }
        for (int i = 0; i < 10; i++) {
            tree[n][i] = tmp[i];
        }
        if (s ^ e) {
            lazy[n << 1] += lazy[n];
            lazy[n << 1 | 1] += lazy[n];
        }
        lazy[n] = 0;
    }
}

void init(int n, int s, int e) {
    if (s == e) {
        tree[n][v[s]]++;
        return;
    }
    int m = s + e >> 1;
    init(n << 1, s, m);
    init(n << 1 | 1, m + 1, e);
    for (int i = 0; i < 10; i++) {
        tree[n][i] = tree[n << 1][i] + tree[n << 1 | 1][i];
    }
}

void update(int l, int r, int n, int s, int e) {
    propagate(n, s, e);
    if (l > e || r < s) {
        return;
    }
    if (l <= s && e <= r) {
        lazy[n] = 1;
        propagate(n, s, e);
        return;
    }
    int m = s + e >> 1;
    update(l, r, n << 1, s, m);
    update(l, r, n << 1 | 1, m + 1, e);
    for (int i = 0; i < 10; i++) {
        tree[n][i] = tree[n << 1][i] + tree[n << 1 | 1][i];
    }
}

int sum(int n) {
    int ret = 0;
    for (int i = 0; i < 10; i++) {
        ret += tree[n][i] * i;
    }
    return ret;
}

int query(int l, int r, int n, int s, int e) {
    propagate(n, s, e);
    if (l > e || r < s) {
        return 0;
    }
    if (l <= s && e <= r) {
        return sum(n);
    }
    int m = s + e >> 1;
    return query(l, r, n << 1, s, m) + query(l, r, n << 1 | 1, m + 1, e);
}

int main() {
    FASTIO
    cin >> N >> M;
    resize();
    string s;
    cin >> s;
    for (int i = 1; i < N + 1; i++) {
        v[i] = s[i - 1] - '0';
    }
    init(1, 1, N);
    for (int i = 0; i < M; i++) {
        int a, b;
        cin >> a >> b;
        cout << query(a, b, 1, 1, N) << endl;
        update(a, b, 1, 1, N);
    }
}
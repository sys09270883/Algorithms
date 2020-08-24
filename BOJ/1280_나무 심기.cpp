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

const int MOD = 1e9 + 7, MAX = 2e5;
int N;
ll res = 1;
vector<int> v;
vector<pll> tree;

auto query_cnt(int l, int r, int n, int s, int e) -> ll {
    if (l > e || r < s) {
        return 0;
    }
    if (l <= s && e <= r) {
        return tree[n].ft;
    }
    int m = s + e >> 1;
    return query_cnt(l, r, n << 1, s, m) + query_cnt(l, r, n << 1 | 1, m + 1, e);
}

auto query_sum(int l, int r, int n, int s, int e) -> ll {
    if (l > e || r < s) {
        return 0;
    }
    if (l <= s && e <= r) {
        return tree[n].sd;
    }
    int m = s + e >> 1;
    return query_sum(l, r, n << 1, s, m) + query_sum(l, r, n << 1 | 1, m + 1, e);
}

auto update(int i, int n, int s, int e) {
    if (i > e || i < s) {
        return;
    }
    tree[n].ft++;
    tree[n].sd += i;
    if (s == e) {
        return;
    }
    int m = s + e >> 1;
    update(i, n << 1, s, m);
    update(i, n << 1 | 1, m + 1, e);
}

auto resize() {
    v.resize(N);
    tree.resize(1 << (int)ceil(log2(MAX)) + 1);
}

auto main() -> int {
    FASTIO
    cin >> N;
    resize();
    for (auto& i : v) {
        cin >> i;
    }
    for (int i = 0; i < N; i++) {
        update(v[i], 1, 0, MAX);
        if (!i) {
            continue;
        }
        ll left = query_cnt(0, v[i], 1, 0, MAX) * v[i] - query_sum(0, v[i], 1, 0, MAX);
        ll right = query_sum(v[i] + 1, MAX, 1, 0, MAX) - query_cnt(v[i] + 1, MAX, 1, 0, MAX) * v[i];
        res *= (left % MOD + right % MOD) % MOD;
        res %= MOD;
    }
    cout << res;
}
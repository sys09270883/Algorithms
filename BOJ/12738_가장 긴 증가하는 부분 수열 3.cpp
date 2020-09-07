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
#define pf push_front
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define rall(x) (x).rbegin(), (x).rend()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

const int MAX = 1e6;
int N;
vector<int> tree;
vector<pii> v;

int update(int i, int v, int n, int s, int e) {
    if (i > e || i < s) {
        return tree[n];
    }
    if (s == e) {
        return tree[n] = max(tree[n], v);
    }
    int m = s + e >> 1;
    return tree[n] = max(update(i, v, n << 1, s, m), update(i, v, n << 1 | 1, m + 1, e));
}

int query(int l, int r, int n, int s, int e) {
    if (l > e || r < s) {
        return 0;
    }
    if (l <= s && e <= r) {
        return tree[n];
    }
    int m = s + e >> 1;
    return max(query(l, r, n << 1, s, m), query(l, r, n << 1 | 1, m + 1, e));
}

int main() {
    FASTIO
    cin >> N;
    v.resize(N);
    tree.resize(1 << (int)ceil(log2(MAX)) + 1);
    for (int i = 1; i < N + 1; i++) {
        int a;
        cin >> a;
        v.pb({a, i});
    }
    sort(all(v), [](pii &p1, pii &p2) {
        if (p1.ft == p2.ft) {
            return p1.sd > p2.sd;
        }
        return p1.ft < p2.ft;
    });
    for (auto& i : v) {
        int val = query(1, i.sd, 1, 1, MAX) + 1;
        update(i.sd, val, 1, 1, MAX);
    }
    cout << tree[1];
}
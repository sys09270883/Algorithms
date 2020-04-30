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

int N, H, sz;
vector<pii> qry;
vector<int> idx, tree, lazy;

void resize() {
    sz = idx.size();
    H = 1 << (int)ceil(log2(sz)) + 1;
    tree.assign(H, 0), lazy.assign(H, 0);
}

void updateLazy(int n, int s, int e) {
    if (lazy[n]) {
        if (s ^ e) {
            lazy[n << 1] = max(lazy[n << 1], lazy[n]);
            lazy[n << 1 | 1] = max(lazy[n << 1 | 1], lazy[n]);
        }
        tree[n] = max(lazy[n], tree[n]);
        lazy[n] = 0;
    }
}

int update(int l, int r, int v, int n, int s, int e) {
    updateLazy(n, s, e);
    if (l > e || r < s)
        return tree[n];
    if (l <= s && e <= r) {
        lazy[n] = max(lazy[n], v);
        updateLazy(n, s, e);
        return tree[n];
    }
    int m = s + e >> 1;
    return tree[n] = max(update(l, r, v, n << 1, s, m), update(l, r, v, n << 1 | 1, m + 1, e));
}

int query(int l, int r, int n, int s, int e) {
    updateLazy(n, s, e);
    if (l > e || r < s)
        return 0;
    if (l <= s && e <= r)
        return tree[n];
    int m = s + e >> 1;
    return max(query(l, r, n << 1, s, m), query(l, r, n << 1 | 1, m + 1, e));
}

int main() {
    FASTIO
    cin >> N;
    qry.resize(N);
    for (int i = 0; i < N; i++) {
        cin >> qry[i].ft >> qry[i].sd;
        idx.pb(qry[i].sd), idx.pb(qry[i].ft + qry[i].sd - 1);
    }
    cpr(idx)
    resize();
    for (auto& q : qry) {
        auto l = lower_bound(all(idx), q.sd) - idx.begin();
        auto r = lower_bound(all(idx), q.ft + q.sd - 1) - idx.begin();
        auto h = query(l, r, 1, 0, sz - 1);
        update(l, r, h + 1, 1, 0, sz - 1);
    }
    cout << query(0, sz - 1, 1, 0, sz - 1);
}
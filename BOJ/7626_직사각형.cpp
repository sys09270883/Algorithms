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

struct Point {
    ll x, y1, y2, end;

    Point(ll x, ll y1, ll y2, ll end): x(x), y1(y1), y2(y2), end(end) {}
};

int N, H, SZ;
vector<ll> idx, tree, lazy;
vector<Point> qry;
ll res;

auto resize() {
    SZ = idx.size() - 1;
    H = 1 << (int)ceil(log2(SZ)) + 1;
    tree.resize(H), lazy.resize(H);
}

auto get(int n, int s, int e) -> ll {
    return lazy[n] ? idx[e + 1] - idx[s] : tree[n];
}

auto update(int l, int r, ll d, int n, int s, int e) -> ll {
    if (l > e || r < s) {
        return get(n, s, e);
    }
    if (l <= s && e <= r) {
        lazy[n] += d;
        return get(n, s, e);
    }
    int m = s + e >> 1;
    tree[n] = update(l, r, d, n << 1, s, m) + update(l, r, d, n << 1 | 1, m + 1, e);
    return get(n, s, e);
}

auto main() -> int {
    FASTIO
    cin >> N;
    for (int i = 0; i < N; i++) {
        int x1, x2, y1, y2;
        cin >> x1 >> x2 >> y1 >> y2;
        idx.pb(y1), idx.pb(y2);
        qry.pb({x1, y1, y2, 1});
        qry.pb({x2, y1, y2, -1});
    }
    cpr(idx)
    sort(all(qry), [](const Point &p1, const Point &p2) {
        if (p1.x == p2.x) {
            return p1.end > p2.end;
        }
        return p1.x < p2.x;
    });
    resize();
    ll x = qry[0].x;
    for (auto& i : qry) {
        res += (i.x - x) * tree[1];
        auto y1 = lower_bound(all(idx), i.y1) - idx.begin();
        auto y2 = lower_bound(all(idx), i.y2) - idx.begin();
        update(y1, y2 - 1, i.end, 1, 0, SZ);
        x = i.x;
    }
    cout << res;
}
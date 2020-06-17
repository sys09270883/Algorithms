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

struct Point {
    int x, y1, y2, end;

    Point(int x, int y1, int y2, int end): x(x), y1(y1), y2(y2), end(end) {}

    bool operator<(const Point &p) const {
        if (x == p.x)
            return end > p.end;
        return x < p.x;
    }
};

const int MAX = 1e5, HALF = 5e4;
int N, x, H;
ll res;
vector<Point> v;
vector<int> tree, lazy;

int get(int n, int s, int e) {
    return lazy[n] ? e - s + 1 : tree[n];
}

int update(int l, int r, int d, int n, int s, int e) {
    if (l > e || r < s)
        return get(n, s, e);
    if (l <= s && e <= r) {
        lazy[n] += d;
        return get(n, s, e);
    }
    int m = s + e >> 1;
    tree[n] = update(l, r, d, n << 1, s, m) + update(l, r, d, n << 1 | 1, m + 1, e);
    return get(n, s, e);
}

void resize() {
    H = 1 << (int)ceil(log2(MAX)) + 1;
    tree.resize(H), lazy.resize(H);
}

int main() {
    FASTIO
    cin >> N;
    resize();
    for (int i = 0; i < N; i++) {
        int x1, y1, x2, y2;
        cin >> x1 >> y1 >> x2 >> y2;
        v.pb({x1, y1 + HALF, y2 + HALF - 1, 1});
        v.pb({x2, y1 + HALF, y2 + HALF - 1, -1});
    }
    sort(all(v));
    x = v[0].x;
    for (auto& i : v) {
        res += 1ll * (i.x - x) * tree[1];
        update(i.y1, i.y2, i.end, 1, 0, MAX);
        x = i.x;
    }
    cout << res;
}
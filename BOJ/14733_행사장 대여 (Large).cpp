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
int N, px, H;
ll res;
vector<Point> v;
vector<int> tree, cnt;

void update(int l, int r, int d, int n, int s, int e) {
    if (l > e || r < s)
        return;
    if (l <= s && e <= r)
        cnt[n] += d;
    else {
        int m = s + e >> 1;
        update(l, r, d, n << 1, s, m);
        update(l, r, d, n << 1 | 1, m + 1, e);
    }
    if (cnt[n])
        tree[n] = e - s + 1;
    else {
        if (s ^ e)
            tree[n] = tree[n << 1] + tree[n << 1 | 1];
        else
            tree[n] = 0;
    }
}

void resize() {
    H = 1 << (int)ceil(log2(MAX)) + 1;
    tree.resize(H), cnt.resize(H);
}

int main() {
    FASTIO
    cin >> N;
    resize();
    for (int i = 0; i < N; i++) {
        int x1, y1, x2, y2;
        cin >> x1 >> y1 >> x2 >> y2;
        v.pb({x1 + HALF, y1 + HALF, y2 + HALF, 1});
        v.pb({x2 + HALF, y1 + HALF, y2 + HALF, -1});
    }
    sort(all(v));
    px = v[0].x;
    for (auto& i : v) {
        res += (i.x - px) * tree[1];
        update(i.y1, i.y2 - 1, i.end, 1, 0, MAX);
        px = i.x;
    }
    cout << res;
}
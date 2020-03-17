#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

struct Point {
    int x, y1, y2, isEnd;
    
    Point(int x, int y1, int y2, int isEnd) : x(x), y1(y1), y2(y2), isEnd(isEnd){}

    bool operator<(const Point& p) const {
        if (this->x == p.x)
            return this->isEnd > p.isEnd;
        return this->x < p.x;
    }
};

const int MAX = 3e4;
int N, H, px, res;
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
        if (s == e)
            tree[n] = 0;
        else
            tree[n] = tree[n << 1] + tree[n << 1 | 1];
    }
}

int main() {
    FASTIO
    cin >> N;
    H = 1 << (int)ceil(log2(MAX)) + 1;
    tree.resize(H);
    cnt.resize(H);
    for (int i = 0; i < N; i++) {
        int x1, y1, x2, y2;
        cin >> x1 >> y1 >> x2 >> y2;
        v.pb({x1, y1, y2, 1});
        v.pb({x2, y1, y2, -1});
    }
    sort(v.begin(), v.end());
    px = v[0].x;
    for (auto& i : v) {
        res += (i.x - px) * tree[1];
        update(i.y1, i.y2 - 1, i.isEnd, 1, 0, MAX);
        px = i.x;
    }
    cout << res;
}
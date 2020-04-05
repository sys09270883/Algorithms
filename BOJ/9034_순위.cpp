#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());
#define cprcmp(x, y) sort(all(x), (y)), (x).erase(unique(all(x)), (x).end());

struct Query {
    char q;
    int a, b;

    Query(char q, int a, int b): q(q), a(a), b(b) {}
};

int T, N, M, sz;
vector<Query> qry;
vector<int> v, idx, tree;

void clear() {
    v.clear();
    idx = {0};
    tree.clear();
    qry.clear();
    v.resize(N + 1);
}

int update(int i, int d, int n, int s, int e) {
    if (i > e || i < s)
        return tree[n];
    if (s == e)
        return tree[n] += d;
    int m = s + e >> 1;
    return tree[n] = update(i, d, n << 1, s, m) + update(i, d, n << 1 | 1, m + 1, e);
}

int query(int l, int r, int n, int s, int e) {
    if (l > e || r < s)
        return 0;
    if (l <= s && e <= r)
        return tree[n];
    int m = s + e >> 1;
    return query(l, r, n << 1, s, m) + query(l, r, n << 1 | 1, m + 1, e);
}

int main() {
    FASTIO
    cin >> T;
    while (T--) {
        cin >> N >> M;
        clear();
        for (int i = 0; i < M; i++) {
            char q;
            int a, b = -1;
            cin >> q >> a;
            if (q == 'R')
                cin >> b;
            qry.pb({q, a, b});
        }
        for (auto& q : qry) {
            if (q.q == 'R')
                idx.pb(v[q.a] += q.b);
        }
        cpr(idx)
        fill(all(v), 0);
        sz = idx.size();
        tree.resize(1 << (int)ceil(log2(sz)) + 1);
        for (auto& q : qry) {
            if (q.q == 'Q') {
                int pos = upper_bound(all(idx), v[q.a]) - idx.begin() + 1;
                cout << 1 + query(pos, sz, 1, 1, sz) << endl;
            }
            else {
                int pos = lower_bound(all(idx), v[q.a]) - idx.begin() + 1;
                update(pos, -1, 1, 1, sz);
                v[q.a] += q.b;
                pos = lower_bound(all(idx), v[q.a]) - idx.begin() + 1;
                update(pos, 1, 1, 1, sz);
            }
        }
    }
}
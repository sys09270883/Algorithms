#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()
#define compress(x) sort(all(x)), x.erase(unique(all(x)), x.end());

struct Query {
    int q;
    string a, b;

    Query(int q, string a, string b): q(q), a(a), b(b) {}
};

bool cmp(string s1, string s2) {
    if (s1.size() == s2.size()) {
        int sz = s1.size();
        for (int i = 0; i < sz; i++) {
            if (s1[i] == s2[i])
                continue;
            return s1[i] < s2[i];
        }
    }
    return s1.size() < s2.size();
}

int N, sz;
vector<Query> qry;
vector<string> idx;
vector<ll> tree;

void resize() {
    sz = idx.size();
    tree.resize(1 << (int)ceil(log2(sz)) + 1);
}

ll update(int i, int d, int n, int s, int e) {
    if (i > e || i < s)
        return tree[n];
    if (s == e)
        return tree[n] += d;
    int m = s + e >> 1;
    return tree[n] = update(i, d, n << 1, s, m) + update(i, d, n << 1 | 1, m + 1, e);
}

ll remove(int i, int n, int s, int e) {
    if (i > e || i < s)
        return tree[n];
    if (s == e)
        return tree[n] = 0;
    int m = s + e >> 1;
    return tree[n] = remove(i, n << 1, s, m) + remove(i, n << 1 | 1, m + 1, e);
}

ll query(int l, int r, int n, int s, int e) {
    if (l > e || r < s)
        return 0;
    if (l <= s && e <= r)
        return tree[n];
    int m = s + e >> 1;
    return query(l, r, n << 1, s, m) + query(l, r, n << 1 | 1, m + 1, e);
}

int main() {
    FASTIO
    cin >> N;
    for (int i = 0; i < N; i++) {
        int q;
        string a, b;
        cin >> q >> a;
        if (q != 3)
            cin >> b;
        qry.pb({q, a, b});
        idx.pb(a);
        if (q == 2)
            idx.pb(b);
    }
    sort(all(idx), cmp);
    idx.erase(unique(all(idx)), idx.end());
    resize();
    for (auto& q : qry) {
        int a = lower_bound(all(idx), q.a, cmp) - idx.begin(), b;
        if (q.q == 1) {
            b = stoi(q.b);
            update(a, b, 1, 0, sz - 1);
            cout << query(0, sz - 1, 1, 0, sz - 1) << ' ';
        }
        else if (q.q == 2) {
            b = lower_bound(all(idx), q.b, cmp) - idx.begin();
            cout << query(a, b, 1, 0, sz - 1) << ' ';
        }
        else {
            remove(a, 1, 0, sz - 1);
            cout << query(0, sz - 1, 1, 0, sz - 1) << ' ';
        }
    }
}
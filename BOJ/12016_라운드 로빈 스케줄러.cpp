#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define pli pair<ll, int>
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());
#define cprcmp(x, y) sort(all(x), (y)), (x).erase(unique(all(x)), (x).end());
#define Matrix vector<vector<int>>
#define Matrixll vector<vector<ll>>

int N;
ll done;
vector<pii> qry;
vector<int> tree;
vector<ll> res;

int update(int i, int v, int n, int s, int e) {
    if (i > e || i < s)
        return tree[n];
    if (s == e)
        return tree[n] = v;
    int m = s + e >> 1;
    return tree[n] = update(i, v, n << 1, s, m) + update(i, v, n << 1 | 1, m + 1, e);
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
    cin >> N;
    tree.resize(1 << (int)ceil(log2(N)) + 1);
    res.resize(N + 1);
    for (int i = 1; i < N + 1; i++) {
        int a;
        cin >> a;
        update(i, 1, 1, 1, N);
        qry.pb({a, i});
    }
    sort(all(qry));
    for (auto& q : qry) {
        res[q.sd] = (ll)query(1, q.sd, 1, 1, N) * q.ft + (ll)query(q.sd + 1, N, 1, 1, N) * (q.ft - 1) + done;
        done += q.ft;
        update(q.sd, 0, 1, 1, N);
    }
    for (int i = 1; i < N + 1; i++) {
        cout << res[i] << endl;
    }
}
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());
#define cprcmp(x, y) sort(all(x), (y)), (x).erase(unique(all(x)), (x).end());
#define Matrix vector<vector<int>>
#define Matrixll vector<vector<ll>>

const ll INF = LLONG_MAX >> 1;
int T, N, X;
ll res;
vector<int> v;
vector<ll> sum, tree;

void resize() {
    v.resize(N + 1);
    sum.assign(N + 1, 0);
    tree.assign(1 << (int)ceil(log2(N)) + 1, INF);
    res = INF;
}

ll update(int i, ll v, int n, int s, int e) {
    if (i > e || i < s)
        return tree[n];
    if (s == e)
        return tree[n] = v;
    int m = s + e >> 1;
    return tree[n] = min(update(i, v, n << 1, s, m), update(i, v, n << 1 | 1, m + 1, e));
}

int query(ll v, int n, int s, int e) {
    if (tree[n] > v)
        return -1;
    if (s == e)
        return s;
    int m = s + e >> 1;
    if (tree[n << 1 | 1] <= v)
        return query(v, n << 1 | 1, m + 1, e);
    return query(v, n << 1, s, m);
}

int main() {
    FASTIO
    cin >> T;
    while (T--) {
        cin >> N >> X;
        resize();
        for (int i = 1; i < N + 1; i++) {
            cin >> v[i];
            if (v[i] >= X)
                res = 1;
            sum[i] = sum[i - 1] + v[i];
        }
        for (int i = 1; i < N + 1; i++) {
            int j = query(sum[i] - X, 1, 1, N);
            if (j != -1)
                res = res > i - j ? i - j : res;
            update(i, sum[i], 1, 1, N);
        }
        cout << (res == INF ? -1 : res) << endl;
    }
}
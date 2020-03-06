#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
typedef long long ll;
#define endl '\n'
#define pb push_back
#define pii pair<int, int>

int N, M, H, o;
vector<int> l, r;
vector<ll> tree;
vector<vector<int>> adj;

void DFS(int cur) {
    l[cur] = ++o;
    for (auto& next : adj[cur]) {
        DFS(next);
    }
    r[cur] = o;
}

ll update(int i, int d, int n, int s, int e) {
    if (i > e || i < s)
        return tree[n];
    if (s == e)
        return tree[n] += d;
    int m = s + e >> 1;
    return tree[n] = update(i, d, n << 1, s, m) + update(i, d, n << 1 | 1, m + 1, e);
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
    cin >> N >> M;
    adj.resize(N + 1, vector<int>());
    l.resize(N + 1);
    r.resize(N + 1) ;
    H = 1 << (int)ceil(log2(N)) + 1;
    tree.resize(H);
    int x;
    cin >> x;
    for (int i = 2; i < N + 1; i++) {
        cin >> x;
        adj[x].pb(i);
    }
    DFS(1);
    for (int i = 0; i < M; i++) {
        int q, a, b;
        cin >> q >> a;
        if (q == 1) {
            cin >> b;
            update(l[a], b, 1, 1, N);
        }
        else if (q == 2)
            cout << query(l[a], r[a], 1, 1, N) << endl;
    }
}
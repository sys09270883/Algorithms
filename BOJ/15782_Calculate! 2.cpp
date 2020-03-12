#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define all(x) (x).begin(), (x).end()

int N, M, H, o;
vector<int> tree, lazy, l, r;
vector<vector<int>> adj;
vector<bool> vis;

void resize() {
    l.resize(N + 1);
    r.resize(N + 1);
    vis.resize(N + 1);
    adj.resize(N + 1, vector<int>());
    H = 1 << (int)ceil(log2(N)) + 1;
    tree.resize(H);
    lazy.resize(H);
}

void DFS(int cur) {
    vis[cur] = true;
    l[cur] = ++o;
    for (auto& next : adj[cur]) {
        if (!vis[next])
            DFS(next);
    }
    r[cur] = o;
}

void updateLazy(int n, int s, int e) {
    if (lazy[n]) {
        if (s ^ e) {
            lazy[n << 1] ^= lazy[n];
            lazy[n << 1 | 1] ^= lazy[n]; 
        }
        tree[n] ^= ((e - s + 1) % 2) * lazy[n];
        lazy[n] = 0;
    }
}

int update(int l, int r, int d, int n, int s, int e) {
    updateLazy(n, s, e);
    if (l > e || r < s)
        return tree[n];
    if (l <= s && e <= r) {
        if (s ^ e) {
            lazy[n << 1] ^= d;
            lazy[n << 1 | 1] ^= d;
        }
        return tree[n] ^= ((e - s + 1) & 1) * d;
    }
    int m = s + e >> 1;
    return tree[n] = update(l, r, d, n << 1, s, m) ^ update(l, r, d, n << 1 | 1, m + 1, e);
}

int query(int l, int r, int n, int s, int e) {
    updateLazy(n, s, e);
    if (l > e || r < s)
        return 0;
    if (l <= s && e <= r)
        return tree[n];
    int m = s + e >> 1;
    return query(l, r, n << 1, s, m) ^ query(l, r, n << 1 | 1, m + 1, e);
}

int main() {
    FASTIO
    cin >> N >> M;
    resize();
    for (int i = 0; i < N - 1; i++) {
        int a, b;
        cin >> a >> b;
        adj[a].pb(b);
        adj[b].pb(a);
    }
    DFS(1);
    for (int i = 1; i < N + 1; i++) {
        int x;
        cin >> x;
        update(l[i], l[i], x, 1, 1, N);
    }
    for (int i = 0; i < M; i++) {
        int q, a, b;
        cin >> q >> a;
        if (q == 1)
            cout << query(l[a], r[a], 1, 1, N) << endl;
        else if (q == 2) {
            cin >> b;
            update(l[a], r[a], b, 1, 1, N);
        }
    }
}
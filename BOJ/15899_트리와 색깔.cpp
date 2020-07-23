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
#define rall(x) (x).rbegin(), (x).rend()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

const int MOD = 1e9 + 7;
int N, M, C, H, o;
vector<vector<int>> adj, seg_tree;
vector<int> l, r, color;
vector<bool> vis;
ll res;

void DFS(int cur) {
    vis[cur] = true;
    l[cur] = ++o;
    for (auto& next : adj[cur]) {
        if (!vis[next])
            DFS(next);
    }
    r[cur] = o;
}

void resize() {
    color.resize(N + 1);
    adj.resize(N + 1, vector<int>());
    l.resize(N + 1);
    r.resize(N + 1);
    vis.resize(N + 1);
    H = 1 << (int)ceil(log2(N)) + 1;
    seg_tree.resize(H);
}

void update(int i, int v, int n, int s, int e) {
    if (i > e || i < s)
        return;
    seg_tree[n].pb(v);
    if (s == e)
        return;
    int m = s + e >> 1;
    update(i, v, n << 1, s, m);
    update(i, v, n << 1 | 1, m + 1, e);
}

int query(int l, int r, int c, int n, int s, int e) {
    if (l > e || r < s)
        return 0;
    if (l <= s && e <= r)
        return upper_bound(all(seg_tree[n]), c) - seg_tree[n].begin();
    int m = s + e >> 1;
    return query(l, r, c, n << 1, s, m) + query(l, r, c, n << 1 | 1, m + 1, e);
}

int main() {
    FASTIO
    cin >> N >> M >> C;
    resize();
    for (int i = 1; i < N + 1; i++) {
        cin >> color[i];
    }
    for (int i = 0; i < N - 1; i++) {
        int a, b;
        cin >> a >> b;
        adj[a].pb(b);
        adj[b].pb(a);
    }
    DFS(1);
    for (int i = 1; i < N + 1; i++) {
        update(l[i], color[i], 1, 1, N);
    }
    for (int i = 0; i < H; i++) {
        sort(all(seg_tree[i]));
    }
    for (int i = 0; i < M; i++) {
        int a, b;
        cin >> a >> b;
        res += query(l[a], r[a], b, 1, 1, N);
        res %= MOD;
    }
    cout << res;
}
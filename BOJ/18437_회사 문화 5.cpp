#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
typedef long long ll;
#define endl '\n'
#define pb push_back

vector<int> l, r, tree, lazy;
vector<vector<int>> adj;
int N, M, H, o;

void resize() {
    l.resize(N + 1);
    r.resize(N + 1);
    adj.resize(N + 1, vector<int>());
    H = 1 << (int)ceil(log2(N)) + 1;
    tree.resize(H);
    lazy.resize(H, -1);
}

void DFS(int cur) {
    l[cur] = ++o;
    for (auto& next : adj[cur]) {
        DFS(next);
    }
    r[cur] = o;
}

int init(int n, int s, int e) {
    if (s == e)
        return tree[n] = 1;
    int m = s + e >> 1;
    return tree[n] = init(n << 1, s, m) + init(n << 1 | 1, m + 1, e);
}

void updateLazy(int n, int s, int e) {
    if (lazy[n] != -1) {
        if (s != e) {
            lazy[n << 1] = lazy[n];
            lazy[n << 1 | 1] = lazy[n];
        }
        tree[n] = (e - s + 1) * lazy[n];
        lazy[n] = -1;
    }
}

int update(int l, int r, int v, int n, int s, int e) {
    updateLazy(n, s, e);
    if (l > e || r < s)
        return tree[n];
    if (l <= s && e <= r) {
        lazy[n] = v;
        updateLazy(n, s, e);
        return tree[n];
    }
    int m = s + e >> 1;
    return tree[n] = update(l, r, v, n << 1, s, m) + update(l, r, v, n << 1 | 1, m + 1, e);
}

int query(int l, int r, int n, int s, int e) {
    updateLazy(n, s, e);
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
    resize();
    int x;
    cin >> x;
    for (int i = 2; i < N + 1; i++) {
        cin >> x;
        adj[x].pb(i);
    }
    DFS(1);
    init(1, 1, N);
    cin >> M;
    for (int i = 0; i < M; i++) {
        int q, a;
        cin >> q >> a;
        if (q == 1)
            update(l[a] + 1, r[a], 1, 1, 1, N);
        else if (q == 2)
            update(l[a] + 1, r[a], 0, 1, 1, N);
        else if (q == 3)
            cout << query(l[a] + 1, r[a], 1, 1, N) << endl;
    }
}
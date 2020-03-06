#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
typedef long long ll;
#define endl '\n'
#define pb push_back
#define pii pair<int, int>

int N, M, H, o;
vector<vector<int>> adj;
vector<int> ntree, nlazy, ytree, ylazy, l, r;

void DFS(int cur) {
    l[cur] = ++o;
    for (auto& next : adj[cur]) {
        DFS(next);
    }
    r[cur] = o;
}

void updateLazy(vector<int>& lazy, vector<int>& tree, int n, int s, int e) {
    if (lazy[n]) {
        if (s ^ e) {
            lazy[n << 1] += lazy[n];
            lazy[n << 1 | 1] += lazy[n]; 
        }
        tree[n] += (e - s + 1) * lazy[n];
        lazy[n] = 0;
    }
}

int update(vector<int>& lazy, vector<int>& tree, int l, int r, int d, int n, int s, int e) {
    updateLazy(lazy, tree, n, s, e);
    if (l > e || r < s)
        return tree[n];
    if (l <= s && e <= r) {
        if (s ^ e) {
            lazy[n << 1] += d;
            lazy[n << 1 | 1] += d;
        }
        return tree[n] += (e - s + 1) * d;
    }
    int m = s + e >> 1;
    return tree[n] = update(lazy, tree, l, r, d, n << 1, s, m) + update(lazy, tree, l, r, d, n << 1 | 1, m + 1, e);
}

int query(vector<int>& lazy, vector<int>& tree, int l, int r, int n, int s, int e) {
    updateLazy(lazy, tree, n, s, e);
    if (l > e || r < s)
        return 0;
    if (l <= s && e <= r)
        return tree[n];
    int m = s + e >> 1;
    return query(lazy, tree, l, r, n << 1, s, m) + query(lazy, tree, l, r, n << 1 | 1, m + 1, e);
}

int main() {
    FASTIO
    cin >> N >> M;
    adj.resize(N + 1, vector<int>());
    H = 1 << (int)ceil(log2(N)) + 1;
    ntree.resize(H);
    nlazy.resize(H);
    ytree.resize(H);
    ylazy.resize(H);
    l.resize(N + 1);
    r.resize(N + 1);
    int x;
    cin >> x;
    for (int i = 2; i < N + 1; i++) {
        cin >> x;
        adj[x].pb(i);
    }
    DFS(1);
    int f = 1;
    for (int i = 0; i < M; i++) {
        int q, a, b;
        cin >> q;
        if (q == 1) {
            cin >> a >> b;
            if (f)
                update(nlazy, ntree, l[a], r[a], b, 1, 1, N);
            else
                update(ylazy, ytree, l[a], l[a], b, 1, 1, N);
        }
        else if (q == 2) {
            cin >> a;
            int sum = query(nlazy, ntree, l[a], l[a], 1, 1, N);
            int ysum = query(ylazy, ytree, l[a], r[a], 1, 1, N);
            cout << sum + ysum << endl;
        }
        else if (q == 3)
            f ^= 1;
    }
}
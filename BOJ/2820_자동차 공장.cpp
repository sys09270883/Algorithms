#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
typedef long long ll;
#define endl '\n'
#define pb push_back

vector<int> l, r, arr, order, tree, lazy;
vector<vector<int>> adj;
int N, M, H, o;

void resize() {
    l.resize(N + 1);
    r.resize(N + 1);
    arr.resize(N + 1);
    order.resize(N + 1);
    adj.resize(N + 1, vector<int>());
    H = 1 << (int)ceil(log2(N)) + 1;
    tree.resize(H);
    lazy.resize(H);
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
        return tree[n] = order[s];
    int m = s + e >> 1;
    return tree[n] = init(2 * n, s, m) + init(2 * n + 1, m + 1, e);
}

void updateLazy(int n, int s, int e) {
    if (lazy[n]) {
        if (s != e) {
            lazy[2 * n] += lazy[n];
            lazy[2 * n + 1] += lazy[n];
        }
        tree[n] += (e - s + 1) * lazy[n];
        lazy[n] = 0;
    }
}

int update(int l, int r, int d, int n, int s, int e) {
    updateLazy(n, s, e);
    if (l > e || r < s)
        return tree[n];
    if (l <= s && e <= r) {
        if (s != e) {
            lazy[2 * n] += d;
            lazy[2 * n + 1] += d;
        }
        return tree[n] += (e - s + 1) * d;
    }
    int m = s + e >> 1;
    return tree[n] = update(l, r, d, 2 * n, s, m) + update(l, r, d, 2 * n + 1, m + 1,e);
}

int query(int i, int n, int s, int e) {
    updateLazy(n, s, e);
    if (i > e || i < s)
        return 0;
    if (s == e)
        return tree[n];
    int m = s + e >> 1;
    return query(i, 2 * n, s, m) + query(i, 2 * n + 1, m + 1, e);
}

int main() {
    FASTIO
    cin >> N >> M;
    resize();
    cin >> arr[1];
    for (int i = 2; i < N + 1; i++) {
        int a;
        cin >> arr[i] >> a;
        adj[a].pb(i);        
    }
    int o = 0;
    DFS(1);
    for (int i = 1; i < N + 1; i++) {
        order[l[i]] = arr[i];
    }
    init(1, 1, N);
    for (int i = 0; i < M; i++) {
        char c;
        int a, b;
        cin >> c;
        if (c == 'p') {
            cin >> a >> b;
            update(l[a] + 1, r[a], b, 1, 1, N);
        }
        else if (c == 'u') {
            cin >> a;
            cout << query(l[a], 1, 1, N) << endl;
        }
    }
}
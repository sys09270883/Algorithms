#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

int N, M, H;
vector<int> v;
vector<vector<int>> tree;

void update(int i, int v, int n, int s, int e) {
    if (i > e || i < s)
        return;
    tree[n].pb(v);
    if (s == e)
        return;
    int m = s + e >> 1;
    update(i, v, n << 1, s, m);
    update(i, v, n << 1 | 1, m + 1, e);
}

int query(int l, int r, int k, int n, int s, int e) {
    if (l > e || r < s)
        return 0;
    if (l <= s && e <= r)
        return tree[n].end() - upper_bound(tree[n].begin(), tree[n].end(), k);
    int m = s + e >> 1;
    return query(l, r, k, n << 1, s, m) + query(l, r, k, n << 1 | 1, m + 1, e);    
}

int main() {
    FASTIO
    cin >> N;
    v.resize(N + 1);
    H = 1 << (int)ceil(log2(N)) + 1;
    tree.resize(H, vector<int>());
    for (int i = 1; i < N + 1; i++) {
        cin >> v[i];
        update(i, v[i], 1, 1, N);
    }
    for (int i = 0; i < H; i++) {
        sort(tree[i].begin(), tree[i].end());
    }
    cin >> M;
    for (int i = 0; i < M; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        cout << query(a, b, c, 1, 1, N) << endl;
    }
}
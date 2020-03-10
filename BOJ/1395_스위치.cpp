#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
typedef long long ll;
#define endl '\n'
#define pb push_back
#define pii pair<int, int>

int N, M, H;
vector<int> tree, lazy;

void updateLazy(int n, int s, int e) {
    if (lazy[n]) {
        if (s ^ e) {
            lazy[n << 1] = !lazy[n << 1];
            lazy[n << 1 | 1] = !lazy[n << 1 | 1];
        }
        tree[n] = e - s + 1 - tree[n];
        lazy[n] = 0;
    }
}

int update(int l, int r, int n, int s, int e) {
    updateLazy(n, s, e);
    if (l > e || r < s)
        return tree[n];
    if (l <= s && e <= r) {
        if (s ^ e) {
            lazy[n << 1] = !lazy[n << 1];
            lazy[n << 1 | 1] = !lazy[n << 1 | 1];
        }
        return tree[n] = e - s + 1 - tree[n];
    }
    int m = s + e >> 1;
    return tree[n] = update(l, r, n << 1, s, m) + update(l, r, n << 1 | 1, m + 1, e);
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
    cin >> N >> M;
    H = 1 << (int)ceil(log2(N)) + 1;
    tree.resize(H);
    lazy.resize(H);
    for (int i = 0; i < M; i++) {
        int q, a, b;
        cin >> q >> a >> b;
        if (q == 0)
            update(a, b, 1, 1, N);
        else if (q == 1)
            cout << query(a, b, 1, 1, N) << endl;
    }
}
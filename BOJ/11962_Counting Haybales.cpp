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
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

const ll MAX = LLONG_MAX;
int N, M, H;
vector<ll> v, lazy;
vector<pli> tree;

void resize() {
    v.resize(N + 1);
    H = 1 << (int)ceil(log2(N)) + 1;
    tree.resize(H), lazy.resize(H);
}

void init(int n, int s, int e) {
    if (s == e) {
        tree[n] = {v[s], v[s]};
        return;
    }
    int m = s + e >> 1;
    init(n << 1, s, m), init(n << 1 | 1, m + 1, e);
    tree[n].ft = tree[n << 1].ft + tree[n << 1 | 1].ft;
    tree[n].sd = min(tree[n << 1].sd, tree[n << 1 | 1].sd);
}

void updateLazy(int n, int s, int e) {
    if (lazy[n]) {
        if (s ^ e) {
            lazy[n << 1] += lazy[n];
            lazy[n << 1 | 1] += lazy[n];
        }
        tree[n].ft += lazy[n] * (e - s + 1);
        tree[n].sd += lazy[n];
        lazy[n] = 0;
    }
}

void update(int l, int r, ll d, int n, int s, int e) {
    updateLazy(n, s, e);
    if (l > e || r < s)
        return;
    if (l <= s && e <= r) {
        lazy[n] += d;
        updateLazy(n, s, e);
        return;
    }
    int m = s + e >> 1;
    update(l, r, d, n << 1, s, m);
    update(l, r, d, n << 1 | 1, m + 1, e);
    tree[n].ft = tree[n << 1].ft + tree[n << 1 | 1].ft;
    tree[n].sd = min(tree[n << 1].sd, tree[n << 1 | 1].sd);
}

ll sumQuery(int l, int r, int n, int s, int e) {
    updateLazy(n, s, e);
    if (l > e || r < s)
        return 0;
    if (l <= s && e <= r)
        return tree[n].ft;
    ll ret = 0;
    int m = s + e >> 1;
    ret += sumQuery(l, r, n << 1, s, m) + sumQuery(l, r, n << 1 | 1, m + 1, e);
    tree[n].ft = tree[n << 1].ft + tree[n << 1 | 1].ft;
    tree[n].sd = min(tree[n << 1].sd, tree[n << 1 | 1].sd);
    return ret;
}

ll minQuery(int l, int r, int n, int s, int e) {
    updateLazy(n, s, e);
    if (l > e || r < s)
        return MAX;
    if (l <= s && e <= r)
        return tree[n].sd;
    ll ret = MAX;
    int m = s + e >> 1;
    ret = min(minQuery(l, r, n << 1, s, m), minQuery(l, r, n << 1 | 1, m + 1, e));
    tree[n].ft = tree[n << 1].ft + tree[n << 1 | 1].ft;
    tree[n].sd = min(tree[n << 1].sd, tree[n << 1 | 1].sd);
    return ret;
}

int main() {
    FASTIO
    cin >> N >> M;
    resize();
    for (int i = 1; i < N + 1; i++) {
        cin >> v[i];
    }
    init(1, 1, N);
    for (int i = 0; i < M; i++) {
        char q;
        int a, b, c;
        cin >> q >> a >> b;
        if (q == 'M') {
            cout << minQuery(a, b, 1, 1, N) << endl;
        }
        else if (q == 'P') {
            cin >> c;
            update(a, b, c, 1, 1, N);
        }
        else {
            cout << sumQuery(a, b, 1, 1, N) << endl;
        }
    }
}
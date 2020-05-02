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

int N, M, H;
vector<int> v;
vector<pll> tree;

void resize() {
    v.resize(N + 1);
    tree.resize(1 << (int)ceil(log2(N)) + 1);
}

void init(int n, int s, int e) {
    if (s == e) {
        tree[n] = {v[s], 0};
        return;
    }
    int m = s + e >> 1;
    init(n << 1, s, m), init(n << 1 | 1, m + 1, e);
}

void update(int l, int r, int n, int s, int e) {
    if (l > e || r < s)
        return;
    if (l <= s && e <= r) {
        tree[n].ft += s - l + 1;
        tree[n].sd++;
        return;
    }
    int m = s + e >> 1;
    update(l, r, n << 1, s, m), update(l, r, n << 1 | 1, m + 1, e);
}

ll query(int i, int n, int s, int e) {
    if (i > e || i < s)
        return 0;
    if (s == e)
        return tree[n].ft;
    int m = s + e >> 1;
    return query(i, n << 1, s, m) + query(i, n << 1 | 1, m + 1, e) 
                + tree[n].ft + tree[n].sd * (i - s);
}

int main() {
    FASTIO
    cin >> N;
    resize();
    for (int i = 1; i < N + 1; i++) {
        cin >> v[i];
    }
    init(1, 1, N);
    cin >> M;
    for (int i = 0; i < M; i++) {
        int q, a, b;
        cin >> q >> a;
        if (q == 1) {
            cin >> b;
            update(a, b, 1, 1, N);
        }
        else
            cout << query(a, 1, 1, N) << endl;
    }
}
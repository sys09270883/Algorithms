#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
typedef long long ll;
#define endl '\n'
#define pb push_back
#define pii pair<int, int>

const int MAX = (int)5e5;
int N;
ll res;
vector<pii> p;
vector<ll> tree(1 << (int)ceil(log2(MAX)) + 1);

int update(int i, int v, int n, int s, int e) {
    if (i > e || i < s)
        return tree[n];
    if (s == e)
        return tree[n] = v;
    int m = s + e >> 1;
    return tree[n] = update(i, v, n << 1, s, m) + update(i, v, n << 1 | 1, m + 1, e);
}

int query(int l, int r, int n, int s, int e) {
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
    p.resize(N + 1);
    for (int i = 1; i < N + 1; i++) {
        int x;
        cin >> x;
        p[i] = {x, i};
    }
    sort(p.begin() + 1, p.end());
    for (int i = 1; i < N + 1; i++) {
        res += query(p[i].second + 1, N, 1, 1, N);
        update(p[i].second, 1, 1, 1, N);
    }
    cout << res;
}
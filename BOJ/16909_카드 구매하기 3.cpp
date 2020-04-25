#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define pli pair<ll, int>
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

int N, H;
vector<int> v, mintree, maxtree;

void resize() {
    v.resize(N + 1);
    H = 1 << (int)ceil(log2(N)) + 1;
    mintree.resize(H), maxtree.resize(H);
}

int getIdx(int a, int b, bool f) {
    if (a == -1)
        return b;
    if (b == -1)
        return a;
    if (f)
        return v[a] > v[b] ? a : b;
    return v[a] < v[b] ? a : b;
}

void init(int n, int s, int e) {
    if (s == e) {
        maxtree[n] = s, mintree[n] = s;
        return;
    }
    int m = s + e >> 1;
    init(n << 1, s, m), init(n << 1 | 1, m + 1, e);
    maxtree[n] = getIdx(maxtree[n << 1], maxtree[n << 1 | 1], true);
    mintree[n] = getIdx(mintree[n << 1], mintree[n << 1 | 1], false);
}

int query(int l, int r, int n, int s, int e, bool f) {
    if (l > e || r < s)
        return -1;
    if (l <= s && e <= r)
        return f ? maxtree[n] : mintree[n];
    int m = s + e >> 1;
    return getIdx(query(l, r, n << 1, s, m, f), query(l, r, n << 1 | 1, m + 1, e, f), f);
}

ll get(int l, int r, bool f) {
    int k = query(l, r, 1, 1, N, f);
    ll ret = (ll)v[k] * (k - l + 1) * (r - k + 1);
    if (l < k)
        ret += get(l, k - 1, f);
    if (k < r)
        ret += get(k + 1, r, f);
    return ret;
}

int main() {
    FASTIO
    cin >> N;
    resize();
    for (int i = 1; i < N + 1; i++) {
        cin >> v[i];
    }
    init(1, 1, N);
    cout << get(1, N, true) - get(1, N, false);
}
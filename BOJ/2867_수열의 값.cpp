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

int N, H;
vector<int> v, mnt, mxt;

void resize() {
    v.resize(N + 1);
    H = 1 << (int)ceil(log2(N)) + 1;
    mnt.resize(H), mxt.resize(H);
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
        mxt[n] = s, mnt[n] = s;
        return;
    }
    int m = s + e >> 1;
    init(n << 1, s, m), init(n << 1 | 1, m + 1, e);
    mxt[n] = getIdx(mxt[n << 1], mxt[n << 1 | 1], true);
    mnt[n] = getIdx(mnt[n << 1], mnt[n << 1 | 1], false);
}

int query(int l, int r, int n, int s, int e, bool f) {
    if (l > e || r < s)
        return -1;
    if (l <= s && e <= r)
        return f ? mxt[n] : mnt[n];
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
    cout << get(1, N, 1) - get(1, N, 0);
}
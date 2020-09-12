#include <bits/stdc++.h>
using namespace std;
using ll = long long;
using ull = unsigned long long;
using pii = pair<int, int>;
using pll = pair<ll, ll>;
using pli = pair<ll, int>;
#pragma GCC optimize("Ofast")
#pragma GCC optimize("unroll-loops")
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define rall(x) (x).rbegin(), (x).rend()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

int N;
vector<int> v, tree;
vector<ll> sum;

int get(int a, int b) {
    return a && b ? v[a] < v[b] ? a : b : max(a, b);
}

int init(int n, int s, int e) {
    if (s == e)
        return tree[n] = s;
    int m = s + e >> 1;
    return tree[n] = get(init(n << 1, s, m), init(n << 1 | 1, m + 1, e));
}

int min(int l, int r, int n, int s, int e) {
    if (l > e || r < s)
        return 0;
    if (l <= s && e <= r)
        return tree[n];
    int m = s + e >> 1;
    return get(min(l, r, n << 1, s, m), min(l, r, n << 1 | 1, m + 1, e));
}

ll func(int l, int r) {
    int k = min(l, r, 1, 1, N);
    ll ret = v[k] * (sum[r] - sum[l - 1]);
    if (l < k)
        ret = max(ret, func(l, k - 1));
    if (k < r)
        ret = max(ret, func(k + 1, r));
    return ret;
}

int main() {
    FASTIO
    cin >> N;
    v.resize(N + 1);
    sum.resize(N + 1);
    tree.resize(1 << (int)ceil(log2(N)) + 1);
    for (int i = 1; i < N + 1; i++) {
        cin >> v[i];
        sum[i] = sum[i - 1] + v[i];
    }
    init(1, 1, N);
    cout << func(1, N);
}
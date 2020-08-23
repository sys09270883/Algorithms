#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;
typedef pair<ll, int> pli;
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

int N, K, mod;
vector<int> tree, res;

int update(int i, int v, int n, int s, int e) {
    if (i > e || i < s) {
        return tree[n];
    }
    if (s == e) {
        return tree[n] = v;
    }
    int m = s + e >> 1;
    return tree[n] = update(i, v, n << 1, s, m) + update(i, v, n << 1 | 1, m + 1, e);
}

int kth(int k, int n, int s, int e) {
    if (s == e) {
        return s;
    }
    int m = s + e >> 1;
    if (tree[n << 1] >= k) {
        return kth(k, n << 1, s, m);
    }
    return kth(k - tree[n << 1], n << 1 | 1, m + 1, e);
}

int sum(int l, int r, int n, int s, int e) {
    if (l > e || r < s) {
        return 0;
    }
    if (l <= s && e <= r) {
        return tree[n];
    }
    int m = s + e >> 1;
    return sum(l, r, n << 1, s, m) + sum(l, r, n << 1 | 1, m + 1, e);
}

int main() {
    FASTIO
    cin >> N >> K;
    tree.resize(1 << (int)ceil(log2(N)) + 1);
    for (int i = 1; i < N + 1; i++) {
        update(i, 1, 1, 1, N);
    }
    res.pb(K);
    update(K, 0, 1, 1, N);
    mod = N - 1;
    for (int i = 2; i < N + 1; i++, mod--) {
        int k = (sum(1, res.back(), 1, 1, N) + K) % mod;
        res.pb(kth(k ? k : mod, 1, 1, N));
        update(res.back(), 0, 1, 1, N);
    }
    cout << "<";
    for (int i = 0; i < res.size(); i++) {
        if (i == res.size() - 1) {
            cout << res[i];
        } else {
            cout << res[i] << ", ";
        }
    }
    cout << ">";
}
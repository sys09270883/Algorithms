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

const int MAX = 65535;
int N, K;
vector<int> v, tree;
ll res;

int update(int i, int d, int n, int s, int e) {
    if (i > e || i < s)
        return tree[n];
    if (s == e)
        return tree[n] += d;
    int m = s + e >> 1;
    return tree[n] = update(i, d, n << 1, s, m) + update(i, d, n << 1 | 1, m + 1, e);
}

int query(int k, int n, int s, int e) {
    if (s == e)
        return s;
    int m = s + e >> 1;
    if (tree[n << 1] >= k)
        return query(k, n << 1, s, m);
    return query(k - tree[n << 1], n << 1 | 1, m + 1, e);
}

int main() {
    FASTIO
    cin >> N >> K;
    v.resize(N + 1);
    tree.resize(1 << (int)ceil(log2(MAX)) + 1);
    for (int i = 1; i < N + 1; i++) {
        cin >> v[i];
        update(v[i], 1, 1, 0, MAX);
        if (i >= K) {
            if (i ^ K)
                update(v[i - K], -1, 1, 0, MAX);
            res += (ll)query((K + 1) / 2, 1, 0, MAX);
        }
    }
    cout << res;
}
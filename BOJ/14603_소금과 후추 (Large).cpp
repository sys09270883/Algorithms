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

int M, N, K, W;
vector<vector<int>> v, res;
vector<int> tree;

void resize() {
    v.resize(M, vector<int>(N));
    tree.resize(1 << (int)ceil(log2(K)) + 1);
}

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
    cin >> M >> N >> K >> W;
    resize();
    for (auto& i : v) {
        for (auto& j : i) {
            cin >> j;
        }
    }
    for (int i = 0; i < W; i++) {
        for (int j = 0; j < W; j++) {
            update(v[i][j], 1, 1, 0, K);
        }
    }
    for (int i = 0; i < M - W + 1; i++) {
        if (!(i & 1)) {
            vector<int> row;
            if (i) {
                for (int j = 0; j < W; j++) {
                    update(v[i - 1][j], -1, 1, 0, K);
                    update(v[i + W - 1][j], 1, 1, 0, K);
                }
            }
            for (int j = 0; j < N - W + 1; j++) {
                if (j) {
                    for (int k = i; k < i + W; k++) {
                        update(v[k][j - 1], -1, 1, 0, K);
                        update(v[k][j + W - 1], 1, 1, 0, K);
                    }
                }
                row.pb(query((W * W + 1) / 2, 1, 0, K));
            }
            res.pb(row);
        }
        else {
            vector<int> row;
            for (int j = N - W; j < N; j++) {
                update(v[i - 1][j], -1, 1, 0, K);
                update(v[i + W - 1][j], 1, 1, 0, K);
            }
            for (int j = N - W; j >= 0; j--) {
                if (j < N - W) {
                    for (int k = i; k < i + W; k++) {
                        update(v[k][j + W], -1, 1, 0, K);
                        update(v[k][j], 1, 1, 0, K);
                    }
                }
                row.pb(query((W * W + 1) / 2, 1, 0, K));
            }
            reverse(all(row));
            res.pb(row);
        }
    }
    for (auto& i : res) {
        for (auto& j : i) {
            cout << j << ' ';
        }
        cout << endl;
    }
}

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

int N, s;
ll d, g;
vector<vector<int>> adj;
vector<int> csz;

ll nCr(ll n, ll r) {
    if (n < r) {
        return 0;
    }
    else if (r == 2) {
        return n * (n - 1) >> 1;
    }
    return n * (n - 1) * (n - 2) / 6;
}

void DFS(int cur, int prev) {
    for (auto& next : adj[cur]) {
        if (next != prev) {
            csz[cur]++;
            DFS(next, cur);
        }
    }
    if (prev > 0) {
        g += nCr(csz[cur], 2);
        for (auto& next : adj[cur]) {
            if (next != prev) {
                d += csz[next];
            }
        }
    }
    g += nCr(csz[cur], 3);
    for (auto& next : adj[cur]) {
        if (next != prev) {
            d += csz[next] * (csz[cur] - 1);
        }
    }
}

int main() {
    FASTIO
    cin >> N;
    adj.resize(N + 1, vector<int>());
    csz.resize(N + 1);
    for (int i = 0; i < N - 1; i++) {
        int u, v;
        cin >> u >> v;
        if (!s) {
            s = u;
        }
        adj[u].pb(v);
        adj[v].pb(u);
    }
    DFS(s, -1);
    cout << (d > 3 * g ? "D" : (d < 3 * g ? "G" : "DUDUDUNGA"));
}
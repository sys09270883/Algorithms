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

int N, S, D;
vector<vector<int>> adj;
vector<bool> vis;

int DFS(int cur, int prev) {
    int ret = 1, max_child = 0;
    for (auto& next : adj[cur]) {
        if (next != prev) {
            int child = DFS(next, cur);
            max_child = max(max_child, child);
            if (child >= D) {
                vis[cur] = true;
            }
        }
    }
    return ret += max_child;
}

int get() {
    int ret = 0;
    for (int i = 1; i < N + 1; i++) {
        if (vis[i])
            ret++;
    }
    return ret;
}

int main() {
    FASTIO
    cin >> N >> S >> D;
    adj.resize(N + 1, vector<int>());
    vis.resize(N + 1);
    for (int i = 0; i < N - 1; i++) {
        int a, b;
        cin >> a >> b;
        adj[a].pb(b);
        adj[b].pb(a);
    }
    DFS(S, -1);
    cout << (D == 0 ? (N - 1) * 2 : max(get() * 2 - 2, 0));
}

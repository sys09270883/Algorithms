#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
typedef long long ll;
#define endl '\n'
#define pb push_back
#define pii pair<int, int>

int N, M, res;
vector<vector<int>> adj;
vector<bool> vis;

int DFS(int cur) {
    if (vis[cur])
        return 0;
    vis[cur] = true;
    int ret = adj[cur].size() & 1;
    for (auto& next : adj[cur]) {
        ret += DFS(next);
    }
    return ret;
}

int main() {
    FASTIO
    cin >> N >> M;
    adj.resize(N + 1, vector<int>());
    vis.resize(N + 1);
    for (int i = 0; i < M; i++) {
        int a, b;
        cin >> a >> b;
        adj[a].pb(b);
        adj[b].pb(a);
    }
    for (int i = 1; i < N + 1; i++) {
        if (adj[i].empty())
            continue;
        if (!vis[i])
            res += max(1, DFS(i) >> 1);
    }
    cout << res;
}
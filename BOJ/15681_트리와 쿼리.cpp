#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()
#define compress(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());
#define compress(x, y) sort(all(x), (y)), (x).erase(unique(all(x)), (x).end());

int N, R, Q;
vector<int> dp;
vector<vector<int>> adj;
vector<bool> vis;

void DFS(int cur) {
    dp[cur] = 1;
    vis[cur] = true;
    for (auto& next : adj[cur]) {
        if (!vis[next]) {
            DFS(next);
            dp[cur] += dp[next];
        }
    }
}

int main() {
    FASTIO
    cin >> N >> R >> Q;
    dp.resize(N + 1);
    vis.resize(N + 1);
    adj.resize(N + 1, vector<int>());
    for (int i = 0; i < N - 1; i++) {
        int a, b;
        cin >> a >> b;
        adj[a].pb(b);
        adj[b].pb(a);
    }
    DFS(R);
    for (int i = 0; i < Q; i++) {
        int a;
        cin >> a;
        cout << dp[a] << endl;
    }
}
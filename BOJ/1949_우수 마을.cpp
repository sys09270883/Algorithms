#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());
#define cprcmp(x, y) sort(all(x), (y)), (x).erase(unique(all(x)), (x).end());

int N;
vector<int> v;
vector<bool> vis;
vector<vector<int>> adj, child, dp;

void resize() {
    v.resize(N + 1);
    dp.resize(N + 1, vector<int>(3, -1));
    adj.resize(N + 1, vector<int>());
    child.resize(N + 1, vector<int>());
    vis.resize(N + 1);
}

void preprocess(int cur) {
    vis[cur] = true;
    for (auto& next : adj[cur]) {
        if (!vis[next]) {
            child[cur].pb(next);
            preprocess(next);
        }
    }
}

int DFS(int cur, int flag, int prev) {
    int &ret = dp[cur][flag];
    if (ret != -1)
        return ret;
    ret = 0;
    if (flag) {
        for (auto& next : child[cur]) {
            if (next != prev)
                ret += DFS(next, 0, cur);
        }
        ret += v[cur];
    }
    else {
        for (auto& next : child[cur]) {
            if (next != prev)
                ret += max(DFS(next, 0, cur), DFS(next, 1, cur));
        }
    }
    return ret;
}

int main() {
    FASTIO
    cin >> N;
    resize();
    for (int i = 1; i < N + 1; i++) {
        cin >> v[i];
    }
    for (int i = 0; i < N - 1; i++) {
        int a, b;
        cin >> a >> b;
        adj[a].pb(b);
        adj[b].pb(a);
    }
    preprocess(1);
    cout << max(DFS(1, 0, 0), DFS(1, 1, 0));
}
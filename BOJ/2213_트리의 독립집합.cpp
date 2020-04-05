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
vector<int> v, res;
vector<bool> vis;
vector<vector<int>> adj, child, dp;

void resize() {
    v.resize(N + 1);
    dp.resize(N + 1, vector<int>(2, -1));
    adj.resize(N + 1, vector<int>());
    child.resize(N + 1, vector<int>());
    vis.resize(N + 1);
}

void preprocess(int cur, int par) {
    vis[cur] = true;
    if (cur != 1)
        child[par].pb(cur);
    for (auto& next : adj[cur]) {
        if (!vis[next])
            preprocess(next, cur);
    }
}

int DFS(int cur, int flag) {
    int &ret = dp[cur][flag];
    if (ret != -1)
        return ret;
    ret = 0;
    if (flag) {
        for (auto& next : child[cur]) {
            ret += DFS(next, 0);
        }
        ret += v[cur];
    }
    else {
        for (auto& next : child[cur]) {
            ret += max(DFS(next, 0), DFS(next, 1));
        }
    }
    return ret;
}

void trace(int cur, int flag) {
    if (flag) {
        res.pb(cur);
        for (auto& next : child[cur]) {
            trace(next, 0);
        }
    }
    else {
        for (auto& next : child[cur]) {
            if (dp[next][1] >= dp[next][0])
                trace(next, 1);
            else
                trace(next, 0);
        }
    }
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
    preprocess(1, 0);
    int a = DFS(1, 0), b = DFS(1, 1);
    if (a >= b)
        trace(1, 0), cout << a << endl;
    else
        trace(1, 1), cout << b << endl;
    sort(all(res));
    for (auto& i : res) {
        cout << i << ' ';
    }
}
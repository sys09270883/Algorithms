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

int N;
vector<vector<pii>> adj;
vector<bool> vis;

pii BFS(int start, int exc = 0) {
    fill(all(vis), false);
    queue<pii> q;
    q.push({start, 0});
    vis[start] = true;
    pii ret = {start, 0};
    while (q.size()) {
        auto tmp = q.front();
        q.pop();
        int cur = tmp.ft;
        int dist = tmp.sd;
        if (exc && cur == exc) {
            continue;
        }
        if (ret.sd < dist) {
            ret = {cur, dist};
        }
        for (auto& n : adj[cur]) {
            int next = n.ft;
            int ndist = n.sd;
            if (!vis[next]) {
                vis[next] = true;
                q.push({next, dist + ndist});
            }
        }
    }
    return ret;
}

int main() {
    FASTIO
    cin >> N;
    adj.resize(N + 1, vector<pii>());
    vis.resize(N + 1);
    for (int i = 0; i < N - 1; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        adj[a].pb({b, c});
        adj[b].pb({a, c});
    }
    int a = BFS(1).ft;
    int b = BFS(a).ft;
    cout << max(BFS(a, b).sd, BFS(b, a).sd);
}
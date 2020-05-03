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

int N;
vector<vector<ll>> adj;
vector<bool> vis;

void resize() {
    adj.resize(N + 1, vector<ll>());
    vis.resize(N + 1);
}

ll DFS(int cur, int h) {
    ll ret = 0;
    bool leaf = true;
    vis[cur] = true;
    for (auto& next : adj[cur]) {
        if (!vis[next]) {
            leaf = false;
            ret += DFS(next, h + 1);
        }
    }
    if (leaf)
        return ret = h;
    return ret;
}

int main() {
    FASTIO
    cin >> N;
    resize();
    for (int i = 0; i < N - 1; i++) {
        int a, b;
        cin >> a >> b;
        adj[a].pb(b);
        adj[b].pb(a);
    }
    cout << (DFS(1, 0) & 1 ? "Yes" : "No");
}
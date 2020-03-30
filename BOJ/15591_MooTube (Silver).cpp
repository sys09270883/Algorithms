#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

const int INF = 987654321;
int N, Q;
vector<vector<pii>> adj;
vector<bool> vis;

void resize() {
    adj.resize(N + 1, vector<pii>());
    vis.resize(N + 1);
}

int BFS(int s, int k) {
    queue<int> q;
    q.push(s);
    vis[s] = true;
    int ret = 0;
    while (!q.empty()) {
        auto cur = q.front();
        q.pop();
        for (auto& n : adj[cur]) {
            auto next = n.first;
            auto nd = n.second;
            if (!vis[next] && nd >= k) {
                vis[next] = true;
                q.push(next);
                ret++;
            }
        }
    }
    return ret;
}

int main() {
    FASTIO
    cin >> N >> Q;
    resize();
    for (int i = 0; i < N - 1; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        adj[a].pb({b, c});
        adj[b].pb({a, c});
    }
    for (int i = 0; i < Q; i++) {
        fill(all(vis), false);
        int k, v;
        cin >> k >> v;
        cout << BFS(v, k) << endl;
    }
}
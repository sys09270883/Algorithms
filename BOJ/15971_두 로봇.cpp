#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

struct Node {
    int idx, sum, maxe;
    
    Node(int idx, int sum, int maxe) : idx(idx), sum(sum), maxe(maxe) {}
};

int N, r1, r2;
vector<vector<pii>> adj;
vector<bool> vis;

int BFS() {
    queue<Node> q;
    q.push({r1, 0, 0});
    vis[r1] = true;
    while (!q.empty()) {
        auto& tmp = q.front();
        q.pop();
        int cur = tmp.idx;
        int sum = tmp.sum;
        int maxe = tmp.maxe;
        if (cur == r2)
            return sum - maxe;
        for (auto& n : adj[cur]) {
            int next = n.first;
            int nd = n.second;
            if (vis[next])
                continue;
            vis[next] = true;
            q.push({next, sum + nd, max(maxe, nd)});
        }
    }
    return -1;
}

int main() {
    FASTIO
    cin >> N >> r1 >> r2;
    adj.resize(N + 1, vector<pii>());
    vis.resize(N + 1);
    for (int i = 0; i < N - 1; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        adj[a].pb({b, c});
        adj[b].pb({a, c});
    }
    cout << BFS();
}
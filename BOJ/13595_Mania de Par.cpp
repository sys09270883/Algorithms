#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());
#define cprcmp(x, y) sort(all(x), (y)), (x).erase(unique(all(x)), (x).end());
#define Matrix vector<vector<int>>
#define Matrixll vector<vector<ll>>

struct Node {
    int idx, dist;

    Node(int idx, int dist): idx(idx), dist(dist) {}

    bool operator<(const Node &n) const {
        return dist > n.dist;
    }
};

const int INF = 987654321;
int N, M;
vector<vector<Node>> adj;

int dijkstra() {
    vector<vector<int>> dists(N + 1, vector<int>(2, INF));
    priority_queue<Node> pq;
    pq.push({1, dists[1][0] = 0});
    while (!pq.empty()) {
        auto tmp = pq.top();
        pq.pop();
        int cur = tmp.idx;
        int cd = tmp.dist;
        if (cd > dists[cur][0] && cd > dists[cur][1])
            continue;
        for (auto& n : adj[cur]) {
            int next = n.idx;
            int nd = n.dist;
            if (dists[next][0] > dists[cur][1] + nd) {
                dists[next][0] = dists[cur][1] + nd;
                pq.push({next, dists[next][0]});
            }
            if (dists[next][1] > dists[cur][0] + nd) {
                dists[next][1] = dists[cur][0] + nd;
                pq.push({next, dists[next][1]});
            }
        }
    }
    return (dists[N][0] == INF ? -1 : dists[N][0]);
}

int main() {
    FASTIO
    cin >> N >> M;
    adj.resize(N + 1, vector<Node>());
    for (int i = 0; i < M; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        adj[a].pb({b, c});
        adj[b].pb({a, c});
    }
    cout << dijkstra();
}
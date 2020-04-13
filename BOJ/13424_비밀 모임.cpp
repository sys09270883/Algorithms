#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
typedef long long ll;
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>

struct Node {
    int idx, dist;
    Node(int idx, int dist) : idx(idx), dist(dist) {}
    bool operator<(const Node& n) const {
        return this->dist > n.dist;
    }
};

vector<vector<Node>> adj;
vector<vector<int>> dists;
const int INF = 987654321;
int N, M, K, minv, mini;

void dijkstra(int idx, int n) {
    priority_queue<Node> pq;
    pq.push({n, dists[idx][n] = 0});
    while (!pq.empty()) {
        int cur = pq.top().idx;
        int cd = pq.top().dist;
        pq.pop();
        if (cd > dists[idx][cur])
            continue;
        for (auto& n : adj[cur]) {
            int next = n.idx;
            int nd = n.dist;
            if (dists[idx][next] > dists[idx][cur] + nd)
                pq.push({next, dists[idx][next] = dists[idx][cur] + nd});
        }
    }
}

int main() {
    FASTIO
    int T;
    cin >> T;
    while (T--) {
        minv = INF, mini = -1;
        adj.clear();
        dists.clear();
        cin >> N >> M;
        adj.resize(N + 1, vector<Node>());
        for (int i = 0; i < M; i++) {
            int a, b, c;
            cin >> a >> b >> c;
            adj[a].pb({b, c});
            adj[b].pb({a, c});
        }
        cin >> K;
        dists.resize(K, vector<int>(N + 1, INF));
        for (int i = 0; i < K; i++) {
            int n;
            cin >> n;
            dijkstra(i, n);
        }
        for (int i = 1; i < N + 1; i++) {
            int sum = 0;
            for (int j = 0; j < K; j++) {
                sum += dists[j][i];
            }
            if (minv > sum) {
                minv = sum;
                mini = i;
            }
        }
        cout << mini << endl;
    }
}

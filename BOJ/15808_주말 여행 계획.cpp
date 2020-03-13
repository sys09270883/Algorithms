#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

struct Node {
    int idx;
    ll dist;
    Node(int idx, ll dist) : idx(idx), dist(dist) {}
    bool operator<(const Node& n) const {
        return this->dist < n.dist;
    }
};

const ll INF = 987654321987654321;
int N, P, Q;
ll res = -INF;
vector<vector<Node>> adj;
vector<ll> dists;
priority_queue<Node> pq;

void dijkstra() {
    while (!pq.empty()) {

        int cur = pq.top().idx;
        ll d = pq.top().dist;
        pq.pop();
        if (d > dists[cur])
            continue;
        for (auto& n : adj[cur]) {
            int next = n.idx;
            ll nd = n.dist;
            if (dists[next] > dists[cur] + nd) {
                pq.push({next, dists[next] = dists[cur] + nd});
            }
        }
    }
}

int main() {
    FASTIO
    cin >> N;
    adj.resize(N + 1, vector<Node>());
    dists.resize(N + 1, INF);
    for (int i = 1; i < N + 1; i++) {
        for (int j = 1; j < N + 1; j++) {
            int x;
            cin >> x;
            if (x)
                adj[i].pb({j, x});
        }
    }
    cin >> P >> Q;
    for (int i = 0; i < P; i++) {
        int a, b;
        cin >> a >> b;
        dists[a] = -b;
        pq.push({a, dists[a]});
    }
    dijkstra();
    for (int i = 0; i < Q; i++) {
        int a, b;
        cin >> a >> b;
        res = max(res, b - dists[a]);
    }
    cout << res;
}
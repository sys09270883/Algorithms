#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define pli pair<ll, int>
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());
#define cprcmp(x, y) sort(all(x), (y)), (x).erase(unique(all(x)), (x).end());
#define Matrix vector<vector<int>>
#define Matrixll vector<vector<ll>>

struct Node {
    int idx, cost;

    Node(int idx, int cost): idx(idx), cost(cost) {}
};

const int INF = 987654321;
int T, N, M, W;
vector<int> dists;
vector<vector<Node>> adj;

string bellmanFord() {
    dists[1] = 0;
    for (int i = 1; i < N + 1; i++) {
        for (int cur = 1; cur < N + 1; cur++) {
            if (cur == INF)
                continue;
            for (auto& n : adj[cur]) {
                int next = n.idx;
                int nc = n.cost;
                if (dists[next] > dists[cur] + nc) {
                    dists[next] = dists[cur] + nc;
                    if (i == N)
                        return "YES";
                }
            }
        }
    }
    return "NO";
}

int main() {
    FASTIO
    cin >> T;
    while (T--) {
        cin >> N >> M >> W;
        dists.assign(N + 1, INF);
        adj.assign(N + 1, vector<Node>());
        for (int i = 0; i < M; i++) {
            int a, b, c;
            cin >> a >> b >> c;
            adj[a].pb({b, c});
            adj[b].pb({a, c});
        }
        for (int i = 0; i < W; i++) {
            int a, b, c;
            cin >> a >> b >> c;
            adj[a].pb({b, -c});
        }
        cout << bellmanFord() << endl;
    }
}
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
    int idx, dist;

    Node(int idx, int dist) : idx(idx), dist(dist) {}
};

const int INF = 987654321;
int T, N, M;
vector<vector<Node>> adj;
vector<vector<int>> radj;
vector<int> dists;
vector<bool> cycle;

void resize() {
    adj.clear();
    radj.clear();
    dists.clear();
    cycle.clear();
    adj.resize(N, vector<Node>());
    radj.resize(N, vector<int>());
    dists.resize(N, INF);
    cycle.resize(N);
}

bool bellman() {
    queue<int> q;
    q.push(0);
    cycle[0] = true;
    while (!q.empty()) {
        auto cur = q.front();
        q.pop();
        for (auto& next : radj[cur]) {
            if (cycle[next])
                continue;
            cycle[next] = true;
            q.push(next);
        }
    }
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            for (auto& n : adj[j]) {
                int next = n.idx;
                int nd = n.dist;
                if (dists[next] > dists[j] + nd) {
                    dists[next] = dists[j] + nd;
                    if (cycle[next] && i == N - 1)
                        return true;
                }
            }
        }
    }
    return false;
}

int main() {
    FASTIO
    cin >> T;
    for (int i = 1; i < T + 1; i++) {
        cin >> N >> M;
        cout << "Case #" << i << ": ";
        resize();
        while (M--) {
            int a, b, c;
            cin >> a >> b >> c;
            adj[a].pb({b, c});
            radj[b].pb(a);
        }
        if (!bellman())
            cout << "not ";
        cout << "possible\n";
    }
}
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
#define Matrix vector<vector<int>>
#define Matrixll vector<vector<ll>>

struct Edge {
    int a, b, c;

    Edge(int a, int b, int c): a(a), b(b), c(c) {}
};

const int INF = 987654321;
int N, M;
vector<vector<pii>> adj;
vector<Edge> edges;
vector<int> costs;

bool bellmanFord() {
    costs.assign(N + 1, INF);
    costs[1] = 0;
    bool cycle = false;
    for (int i = 0; i < N; i++) {
        for (int cur = 1; cur < N + 1; cur++) {
            if (costs[cur] == INF)
                continue;
            for (auto& n : adj[cur]) {
                int next = n.first;
                int ncost = n.second;
                if (costs[next] > costs[cur] + ncost) {
                    costs[next] = costs[cur] + ncost;
                    if (i == N - 1)
                        cycle = true;
                }
            }
        }
    }
    return cycle;
}

int main() {
    FASTIO
    cin >> N >> M;
    adj.resize(N + 1, vector<pii>());
    for (int i = 0; i < M; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        adj[a].pb({b, c});
    }
    if (bellmanFord())
        return !(cout << -1);
    for (int i = 2; i < N + 1; i++) {
        cout << (costs[i] == INF ? -1 : costs[i]) << endl;
    }
}
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

int T, V, E;
bool flag;
vector<vector<int>> adj;
vector<int> color;

void DFS(int cur, int clr) {
    if (!flag)
        return;
    color[cur] = clr;
    for (auto& next : adj[cur]) {
        if (color[next] == clr) {
            flag = false;
            return;
        }
        else if (color[next] == -1)
            DFS(next, clr ^ 1);
    }
}

int main() {
    FASTIO
    cin >> T;
    while (T--) {
        cin >> V >> E;
        flag = true;
        adj.clear();
        color.clear();
        adj.resize(V + 1, vector<int>());
        color.resize(V + 1, -1);
        for (int i = 0; i < E; i++) {
            int a, b;
            cin >> a >> b;
            adj[a].pb(b);
            adj[b].pb(a);
        }
        for (int i = 1; i < V + 1; i++) {
            if (color[i] == -1)
                DFS(i, 1);
        }
        cout << (flag ? "YES" : "NO") << endl;
    }
}
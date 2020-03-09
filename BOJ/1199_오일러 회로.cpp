#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
typedef long long ll;
#define endl '\n'
#define pb push_back
#define pii pair<int, int>

int N, res;
vector<vector<int>> adj;
vector<int> ind;

void DFS(int cur) {
    for (int i = 1; i < N + 1; i++) {
        while (adj[cur][i]) {
            adj[cur][i]--;
            adj[i][cur]--;
            DFS(i);
        }
    }
    cout << cur << ' ';
}

int main() {
    FASTIO
    cin >> N;
    adj.resize(N + 1, vector<int>(N + 1));
    ind.resize(N + 1);
    for (int i = 1; i < N + 1; i++) {
        for (int j = 1; j < N + 1; j++) {
            cin >> adj[i][j];
            ind[i] += adj[i][j];
            ind[j] += adj[i][j];
        }
    }
    for (int i = 1; i < N + 1; i++) {
        ind[i] >>= 1;
        if (ind[i] & 1) {
            cout << -1;
            return 0;
        }
    }
    DFS(1);
}
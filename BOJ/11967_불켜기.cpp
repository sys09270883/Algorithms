#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

int N, M;
int dx[] = {-1, 0, 1, 0}, dy[] = {0, -1, 0, 1};
vector<vector<int>> arr;
vector<vector<bool>> vis, tvis, tot;
vector<pii> adj[101][101];

void resize() {
    arr.resize(N + 1, vector<int>(N + 1));
    vis.resize(N + 1, vector<bool>(N + 1));
    tvis.resize(N + 1, vector<bool>(N + 1));
    tot.resize(N + 1, vector<bool>(N + 1));
}

bool BFS() {
    bool ret = false;
    queue<pii> q;
    q.push({1, 1});
    vis[1][1] = true;
    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();
        if (!tvis[x][y] && !adj[x][y].empty()) {
            vector<pii>::iterator it;
            for (it = adj[x][y].begin(); it != adj[x][y].end(); it++) {
                tot[it->first][it->second] = true;
            }
            tvis[x][y] = true;
            ret = true;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 1 || ny < 1 || nx > N || ny > N || !tot[nx][ny] || vis[nx][ny])
                continue;
            vis[nx][ny] = true;
            q.push({nx, ny});
        }
    }
    return ret;
}

int count() {
    int ret = 0;
    for (int i = 1; i < N + 1; i++) {
        for (int j = 1; j < N + 1; j++) {
            if (tot[i][j])
                ret++;
        }
    }
    return ret;
}

int main() {
    FASTIO
    cin >> N >> M;
    resize();
    for (int i = 0; i < M; i++) {
        int a, b, c, d;
        cin >> a >> b >> c >> d;
        adj[a][b].pb({c, d});
    }
    tot[1][1] = true;
    while (true) {
        for (auto& row : vis) {
            fill(row.begin(), row.end(), false);
        }
        if (!BFS())
            break;
    }
    cout << count();
}
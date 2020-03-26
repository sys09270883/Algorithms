#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
typedef long long ll;
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) x.begin(), x.end()

int N, K, R, res;
int dx[] = {-1, 0, 1, 0}, dy[] = {0, -1, 0, 1};
map<pii, set<pii>> mp;
vector<pii> cow;
vector<vector<bool>> vis;

void DFS(pii p) {
    vis[p.first][p.second] = true;
    for (int i = 0; i < 4; i++) {
        int nx = p.first + dx[i];
        int ny = p.second + dy[i];
        if (nx < 1 || ny < 1 || nx > N || ny > N || vis[nx][ny])
            continue;
        pii np(nx, ny);
        if (mp[p].find(np) != mp[p].end())
            continue;
        DFS(np);
    }
}

int main() {
    FASTIO
    cin >> N >> K >> R;
    for (int i = 0; i < R; i++) {
        int a, b, c, d;
        cin >> a >> b >> c >> d;
        mp[{a, b}].insert({c, d});
        mp[{c, d}].insert({a, b});
    }
    vis.resize(N + 1, vector<bool>(N + 1));
    for (int i = 0; i < K; i++) {
        int a, b;
        cin >> a >> b;
        cow.pb({a, b});
    }
    for (int i = 0; i < K; i++) {
        for (auto& row : vis) {
            fill(row.begin(), row.end(), false);
        }
        DFS(cow[i]);
        for (int j = i + 1; j < K; j++) {
            if (!vis[cow[j].first][cow[j].second])
                res++;
        }
    }
    cout << res;
}
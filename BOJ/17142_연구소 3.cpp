#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;
typedef pair<ll, int> pli;
#pragma GCC optimize("Ofast")
#pragma GCC optimize("unroll-loops")
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define rall(x) (x).rbegin(), (x).rend()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

struct Node {
    pii p;
    int t;

    Node(pii p, int t): p(p), t(t) {}
};

const int INF = 987654321;
int N, M, res = INF;
vector<vector<int>> arr, v;
vector<pii> virus, candidate;
vector<bool> vis;
int dx[] = {-1, 0, 1, 0};
int dy[] = {0, -1, 0, 1};
int cache[51][51][51];

int BFS() {
    queue<Node> q;
    for (auto& i : v) {
        fill(all(i), INF);
    }
    memset(cache, 0, sizeof(cache));
    for (auto& i : candidate) {
        q.push({{i.ft, i.sd}, 0});
        v[i.ft][i.sd] = 0;
    }
    while (q.size()) {
        auto tmp = q.front();
        q.pop();
        int x = tmp.p.ft;
        int y = tmp.p.sd;
        int t = tmp.t;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            int nt = t + 1;
            if (nx < 0 || ny < 0 || nx >= N || ny >= N || arr[nx][ny] == 1 || v[nx][ny] != INF) {
                continue;
            }
            if (arr[nx][ny] == 2) {
                v[nx][ny] = v[x][y];
                cache[nx][ny][nt] = cache[x][y][t] + 1;
            } else {
                v[nx][ny] = v[x][y] + cache[x][y][t] + 1;
            }
            q.push({{nx, ny}, t + 1});
        }
    }
    int ret = -1;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (arr[i][j] == 1) {
                continue;
            }
            ret = max(ret, v[i][j]);
        }
    }
    return ret;
}

void DFS(int idx) {
    if (candidate.size() == M) {
        res = min(res, BFS());
        return;
    }
    for (int i = idx; i < virus.size(); i++) {
        if (vis[i]) {
            continue;
        }
        vis[i] = true;
        candidate.pb(virus[i]);
        DFS(i);
        vis[i] = false;
        candidate.pop_back();
    }
}

int main() {
    FASTIO
    cin >> N >> M;
    arr.resize(N, vector<int>(N));
    v.resize(N, vector<int>(N));
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> arr[i][j];
            if (arr[i][j] == 2) {
                virus.pb({i, j});
            }
        }
    }
    vis.resize(virus.size());
    DFS(0);
    cout << (res == INF ? -1 : res);
}
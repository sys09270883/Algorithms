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

const int SZ = 51, INF = 987654321;
int N, M, res = INF;
vector<pii> virus, candidate;
bool vis[SZ];
int arr[SZ][SZ], v[SZ][SZ];
int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, -1, 0, 1};

int BFS() {
    queue<pii> q;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            v[i][j] = INF;
        }
    }
    for (auto& i : candidate) {
        q.push({i.ft, i.sd});
        v[i.ft][i.sd] = 0;
    }
    while (q.size()) {
        auto tmp = q.front();
        q.pop();
        int x = tmp.ft;
        int y = tmp.sd;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= N || ny >= N || v[nx][ny] != INF || arr[nx][ny] == 1) {
                continue;
            }
            v[nx][ny] = v[x][y] + 1;
            q.push({nx, ny});
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

void DFS(int idx = 0) {
    if (candidate.size() == M) {
        res = min(res, BFS());
        return;
    }
    for (int i = idx; i < virus.size(); i++) {
        if (vis[i]) {
            continue;
        }
        vis[i] = true;
        candidate.pb({virus[i].ft, virus[i].sd});
        DFS(idx + 1);
        vis[i] = false;
        candidate.pop_back();
    }
}

int main() {
    FASTIO
    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> arr[i][j];
            if (arr[i][j] == 2) {
                virus.pb({i, j});
            }
        }
    }
    DFS();
    cout << (res == INF ? -1 : res);
}
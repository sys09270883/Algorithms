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

const int INF = 987654321;
int W, H;
pii s(-1, -1), e;
vector<vector<char>> v;
vector<vector<int>> vis;
int dx[] = {-1, 0, 1, 0};
int dy[] = {0, -1, 0, 1};

struct Node {
    int x, y, dir, cnt;

    Node(int x, int y, int dir, int cnt) : x(x), y(y), dir(dir), cnt(cnt) {}
};

int BFS() {
    queue<Node> q;
    for (int i = 0; i < 4; i++) {
        q.push({s.ft, s.sd, i, 0});
    }
    vis[s.ft][s.sd] = 0;
    while (q.size()) {
        auto tmp = q.front();
        q.pop();
        int x = tmp.x;
        int y = tmp.y;
        int dir = tmp.dir;
        int cnt = tmp.cnt;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= H || ny >= W || v[nx][ny] == '*') {
                continue;
            }
            int ncnt = cnt + (dir != i);
            if (vis[nx][ny] < ncnt) {
                continue;
            }
            vis[nx][ny] = ncnt;
            q.push({nx, ny, i, ncnt});
        }
    }
    return vis[e.ft][e.sd];
}

int main() {
    FASTIO
    cin >> W >> H;
    v.resize(H, vector<char>(W));
    vis.resize(H, vector<int>(W, INF));
    for (int i = 0; i < H; i++) {
        for (int j = 0; j < W; j++) {
            cin >> v[i][j];
            if (v[i][j] == 'C') {
                if (s.ft == -1) {
                    s = {i, j};
                } else {
                    e = {i, j};
                }
            }
        }
    }
    cout << BFS();
}
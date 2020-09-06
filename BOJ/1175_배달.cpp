#include <bits/stdc++.h>
using namespace std;
using ll = long long;
using ull = unsigned long long;
using pii = pair<int, int>;
using pll = pair<ll, ll>;
using pli = pair<ll, int>;
#pragma GCC optimize("Ofast")
#pragma GCC optimize("unroll-loops")
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pf push_front
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define rall(x) (x).rbegin(), (x).rend()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

struct Node {
    int x, y, cnt, dir, vis_p1, vis_p2;

    Node(int x, int y, int cnt, int dir, int vis_p1, int vis_p2): x(x), y(y), cnt(cnt), dir(dir), vis_p1(vis_p1), vis_p2(vis_p2) {}
};

const int MAX = 51;
int N, M, res, chk;
char arr[MAX][MAX];
bool vis[MAX][MAX][5][2][2];
int dx[] = {-1, 0, 1, 0};
int dy[] = {0, -1, 0, 1};
pii s, p1, p2;

int BFS() {
    queue<Node> q;
    q.push({s.ft, s.sd, 0, -1, 0, 0});
    vis[s.ft][s.sd][4][0][0] = true;
    while (q.size()) {
        auto tmp = q.front();
        q.pop();
        int x = tmp.x;
        int y = tmp.y;
        int cnt = tmp.cnt;
        int dir = tmp.dir;
        int vis_p1 = tmp.vis_p1;
        int vis_p2 = tmp.vis_p2;
        if (vis_p1 && vis_p2) {
            return cnt;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            int ncnt = cnt + 1;
            int ndir = i;
            int nvis_p1 = vis_p1;
            int nvis_p2 = vis_p2;
            if (nx < 0 || ny < 0 || nx >= N || ny >= M || dir == ndir || vis[nx][ny][ndir][vis_p1][vis_p2] || arr[nx][ny] == '#') {
                continue;
            }
            if (nx == p1.ft && ny == p1.sd) {
                nvis_p1 = 1;
            } else if (nx == p2.ft && ny == p2.sd) {
                nvis_p2 = 1;
            }
            vis[nx][ny][ndir][nvis_p1][nvis_p2] = true;
            q.push({nx, ny, ncnt, ndir, nvis_p1, nvis_p2});
        }
    }
    return -1;
}

int main() {
    FASTIO
    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> arr[i][j];
            if (arr[i][j] == 'S') {
                s = {i, j};
            } else if (arr[i][j] == 'C') {
                if (!chk) {
                    p1 = {i, j};
                    chk++;
                } else {
                    p2 = {i, j};
                }
            }
        }
    }
    cout << BFS();
}
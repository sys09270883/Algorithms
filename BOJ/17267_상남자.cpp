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
    int x, y, l, r;

    Node(int x, int y, int l, int r): x(x), y(y), l(l), r(r) {}
};

int N, M, L, R, sx, sy, res;
vector<vector<int>> v;
vector<vector<bool>> vis;
int dx[] = {-1, 0, 1, 0};
int dy[] = {0, -1, 0, 1};

void BFS() {
    deque<Node> dq;
    dq.pb({sx, sy, L, R});
    vis[sx][sy] = true;
    while (dq.size()) {
        auto tmp = dq.front();
        dq.pop_front();
        int x = tmp.x;
        int y = tmp.y;
        int l = tmp.l;
        int r = tmp.r;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            int nl = l;
            int nr = r;
            if (nx < 0 || ny < 0 || nx >= N || ny >= M || vis[nx][ny] || v[nx][ny] == 1) {
                continue;
            }
            if (i == 1) {
                if (--nl < 0) {
                    continue;
                }
            } else if (i == 3) {
                if (--nr < 0) {
                    continue;
                }
            }
            vis[nx][ny] = true;
            i & 1 ? dq.pb({nx, ny, nl, nr}) : dq.pf({nx, ny, nl, nr});
        }
    }
}

int main() {
    FASTIO
    cin >> N >> M >> L >> R;
    v.resize(N, vector<int>(M));
    vis.resize(N, vector<bool>(M));
    for (int i = 0; i < N; i++) {
        string s;
        cin >> s;
        for (int j = 0; j < M; j++) {
            v[i][j] = s[j] - '0';
            if (v[i][j] == 2) {
                sx = i, sy = j;
            }
        }
    }
    BFS();
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            if (vis[i][j]) {
                res++;
            }
        }
    }
    cout << res;
}
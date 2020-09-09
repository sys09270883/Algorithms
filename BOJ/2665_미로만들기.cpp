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
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define rall(x) (x).rbegin(), (x).rend()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

struct Node {
    int x, y, c;

    Node(int x, int y, int c): x(x), y(y), c(c) {}
};

const int MAX = 50;
int N, l, r = MAX, res = MAX;
int arr[MAX][MAX];
bool vis[MAX][MAX][MAX];
int dx[] = {-1, 0, 1, 0};
int dy[] = {0, -1, 0, 1};

bool BFS(int m) {
    queue<Node> q;
    q.push({0, 0, 0});
    memset(vis, false, sizeof(vis));
    vis[0][0][0] = true;
    while (q.size()) {
        auto tmp = q.front();
        q.pop();
        int x = tmp.x;
        int y = tmp.y;
        int c = tmp.c;
        if (c > m) {
            continue;
        }
        if (x == N - 1 && y == N - 1) {
            return true;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            int nc = c;
            if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                continue;
            }
            if (!arr[nx][ny]) {
                nc++;
            }
            if (vis[nx][ny][nc]) {
                continue;
            }
            vis[nx][ny][nc] = true;
            q.push({nx, ny, nc});
        }
    }
    return false;
}

int main() {
    FASTIO
    cin >> N;
    for (int i = 0; i < N; i++) {
        string s;
        cin >> s;
        for (int j = 0; j < N; j++) {
            arr[i][j] = s[j] - '0';
        }
    }
    while (l <= r) {
        int m = l + r >> 1;
        if (BFS(m)) {
            res = m;
            r = m - 1;
        } else {
            l = m + 1;
        }
    }
    cout << res;
}
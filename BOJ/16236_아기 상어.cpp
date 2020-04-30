#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;
typedef pair<ll, int> pli;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

struct Node {
    int x, y, cnt;

    Node(int x, int y, int cnt): x(x), y(y), cnt(cnt) {}
};

struct Shark {
    int sz, cnt, x, y;

    Shark(): sz(2), cnt(0) {}
};

const int INF = 987654321;
int N, res;
int arr[20][20];
bool vis[20][20];
int dx[] = {-1, 0, 1, 0}, dy[] = {0, -1, 0, 1};
Shark sk;

int BFS(int ex, int ey) {
    queue<Node> q;
    memset(vis, 0, sizeof(vis));
    vis[sk.x][sk.y] = 1;
    q.push({sk.x, sk.y, 0});
    while (q.size()) {
        auto tmp = q.front();
        q.pop();
        int x = tmp.x;
        int y = tmp.y;
        int cnt = tmp.cnt;
        if (x == ex && y == ey)
            return cnt;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= N || ny >= N || vis[nx][ny])
                continue;
            if (arr[nx][ny] > sk.sz)
                continue;
            vis[nx][ny] = 1;
            q.push({nx, ny, cnt + 1});
        }
    }
    return -1;
}

int main() {
    FASTIO
    cin >> N;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> arr[i][j];
            if (arr[i][j] == 9) {
                sk.x = i, sk.y = j;
                arr[i][j] = 0;
            }
        }
    }

    while (true) {
        int state = -1, next = INF, sx = -1, sy = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (0 < arr[i][j] && arr[i][j] < sk.sz) {
                    state = BFS(i, j);
                    if (state == -1)
                        continue;
                    else if (state < next) {
                        next = state;
                        sx = i, sy = j;
                    }
                }
            }
        }
        if (next == INF)
            break;
        res += next;
        sk.x = sx, sk.y = sy;
        arr[sk.x][sk.y] = 0;
        sk.cnt++;
        if (sk.cnt == sk.sz)
            sk.sz++, sk.cnt = 0;
    }
    cout << res;
}
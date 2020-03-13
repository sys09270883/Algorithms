#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

struct Node {
    int x, y, t;
    Node(int x, int y, int t) : x(x), y(y), t(t) {}
};

int N, M, K, T;
vector<vector<int>> arr;
vector<pii> virus;
vector<pii> check;
bool vis[301][301][2];
int dx[] = {-2, -2, -1, -1, 1, 1, 2, 2};
int dy[] = {1, -1, 2, -2, 2, -2, 1, -1};

void BFS() {
    queue<Node> q;
    for (auto& i : virus) {
        q.push({i.first, i.second, 0});
        vis[i.first][i.second][0] = true;
    }
    while (!q.empty()) {
        int x = q.front().x;
        int y = q.front().y;
        int t = q.front().t;
        q.pop();
        if (t >= T)
            continue;
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 1 || ny < 1 || nx > N || ny > N || vis[nx][ny][(t + 1) & 1])
                continue;
            vis[nx][ny][(t + 1) & 1] = true;
            q.push({nx, ny, t + 1});
        }
    }
}

int main() {
    FASTIO
    cin >> N >> M >> K >> T;
    arr.resize(N + 1, vector<int>(N + 1));
    for (int i = 0; i < M; i++) {
        int a, b;
        cin >> a >> b;
        virus.pb({a, b});
    }
    for (int i = 0; i < K; i++) {
        int a, b;
        cin >> a >> b;
        check.pb({a, b});
    }
    BFS();
    bool flag = false;
    for (auto& i : check) {
        if (vis[i.first][i.second][T & 1]) {
            flag = true;
            break;
        }
    }
    cout << (flag ? "YES" : "NO");
}
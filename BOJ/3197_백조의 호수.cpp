#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
typedef long long ll;
#define endl '\n'
#define pb push_back
#define pii pair<int, int>

struct Node {
    int x, y;
    Node(int x, int y) : x(x), y(y) {}
};

int R, C, low, high, res = 987654321;
vector<vector<char>> arr;
vector<vector<int>> iarr;
vector<vector<bool>> vis;
vector<Node> swan;
int dx[] = {-1, 0, 1, 0}, dy[] = {0, -1, 0, 1};

bool BFS(int d) {
    queue<Node> q;
    q.push(swan[0]);
    for (auto& row : vis) {
        fill(row.begin(), row.end(), false);
    }
    while (!q.empty()) {
        int x = q.front().x;
        int y = q.front().y;
        q.pop();
        if (x == swan[1].x && y == swan[1].y)
            return true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= R || ny >= C || vis[nx][ny] || iarr[nx][ny] > d)
                continue;
            vis[nx][ny] = true;
            q.push({nx, ny});
        }
    }
    return false;
}

void melt() {
    queue<Node> q;
    for (int i = 0; i < R; i++) {
        for (int j = 0; j < C; j++) {
            if (arr[i][j] == '.' || arr[i][j] == 'L') {
                q.push({i, j});
                iarr[i][j] = 0;
            }
        }
    }
    while (!q.empty()) {
        int x = q.front().x;
        int y = q.front().y;
        q.pop();
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= R || ny >= C || iarr[nx][ny] > -1)
                continue;
            q.push({nx, ny});
            iarr[nx][ny] = iarr[x][y] + 1;
            high = max(high, iarr[nx][ny]);
        }
    }
}

int main() {
    FASTIO
    cin >> R >> C;
    arr.resize(R, vector<char>(C));
    iarr.resize(R, vector<int>(C, -1));
    vis.resize(R, vector<bool>(C));
    string s;
    for (int i = 0; i < R; i++) {
        cin >> s;
        for (int j = 0; j < C; j++) {
            arr[i][j] = s[j];
            if (arr[i][j] == 'L')
                swan.pb({i, j});
        }
    }
    melt();
    while (low <= high) {
        int m = low + high >> 1;
        if (BFS(m)) {
            high = m - 1;
            res = min(res, m);
        }
        else
            low = m + 1;
    }
    cout << res;
}
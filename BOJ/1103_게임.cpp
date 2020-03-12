#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define all(x) (x).begin(), (x).end()
#define next _next

const int INF = 987654321;
int N, M, res, dx[] = {-1, 0, 1, 0}, dy[] = {0, -1, 0, 1};
vector<vector<int>> arr, dp;
vector<vector<bool>> vis;
string s;

int DFS(int x, int y) {
    if (x < 0 || y < 0 || x >= N || y >= M || !arr[x][y])
        return 0;
    if (vis[x][y])
        return INF;
    int& ret = dp[x][y];
    if (ret != -1)
        return ret;
    ret = 0;
    vis[x][y] = true;
    int val = arr[x][y];
    for (int i = 0; i < 4; i++) {
        int nx = x + dx[i] * val;
        int ny = y + dy[i] * val;
        ret = max(ret, DFS(nx, ny) + 1);
    }
    vis[x][y] = false;
    return ret;
}

int main() {
    FASTIO
    cin >> N >> M;
    arr.resize(N, vector<int>(M));
    dp.resize(N, vector<int>(M, -1));
    vis.resize(N, vector<bool>(M));
    for (int i = 0; i < N; i++) {
        cin >> s;
        for (int j = 0; j < M; j++) {
            arr[i][j] = s[j] == 'H' ? 0 : s[j] - '0';
        }
    }
    res = DFS(0, 0);
    cout << (res >= INF ? -1 : res);
}
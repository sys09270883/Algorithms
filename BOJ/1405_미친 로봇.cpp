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

const int MAX = 30;
const double SZ = 1e2;
int N, e, w, s, n, sx = 15, sy = 15;
vector<vector<double>> arr;
vector<vector<bool>> vis;
int dx[] = {0, 0, 1, -1};
int dy[] = {-1, 1, 0, 0};
double p[4];

double DFS(int x, int y, int mv = 0) {
    if (mv >= N) {
        return 1;
    }
    double ret = 0;
    vis[x][y] = true;
    for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        if (!vis[nx][ny]) {
            ret += DFS(nx, ny, mv + 1) * p[i];
        }
    }
    vis[x][y] = false;
    return ret;
}

int main() {
    arr.resize(MAX, vector<double>(MAX));
    vis.resize(MAX, vector<bool>(MAX));
    cin >> N;
    for (int i = 0; i < 4; i++) {
        cin >> p[i];
        p[i] /= SZ;
    }
    cout << setprecision(9) << fixed << DFS(sx, sy);
}

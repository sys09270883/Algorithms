#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

int N, M, K;
vector<vector<ll>> dp;
map<pii, set<pii>> road;
int dx[] = {1, 0};
int dy[] = {0, 1};

ll func(int x, int y) {
    if (x == N && y == M)
        return 1;
    ll &ret = dp[x][y];
    if (ret != -1)
        return ret;
    ret = 0;
    for (int i = 0; i < 2; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        if (nx > N || ny > M)
            continue;
        pii cur(x, y), next(nx, ny);
        if (road.find(next) != road.end() && road[cur].find(next) != road[cur].end())
            continue;
        ret += func(nx, ny);
    }
    return ret;
}

int main() {
    FASTIO
    cin >> N >> M >> K;
    dp.resize(N + 1, vector<ll>(M + 1, -1));
    for (int i = 0; i < K; i++) {
        int x1, y1, x2, y2;
        cin >> x1 >> y1 >> x2 >> y2;
        road[{x1, y1}].insert({x2, y2});
        road[{x2, y2}].insert({x1, y1});
    }
    cout << func(0, 0);
}
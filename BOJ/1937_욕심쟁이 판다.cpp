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

int N, K;
vector<vector<int>> v, dp;
int dx[] = {-1, 0, 1, 0};
int dy[] = {0, -1, 0, 1};

int func(int x, int y) {
    if (dp[x][y]) {
        return dp[x][y];
    }
    dp[x][y] = 1;
    for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        if (nx < 0 || ny < 0 || nx >= N || ny >= N || v[x][y] <= v[nx][ny]) {
            continue;
        }
        dp[x][y] = max(dp[x][y], func(nx, ny) + 1);
    }
    return dp[x][y];
}

int main() {
    FASTIO
    cin >> N;
    v.resize(N, vector<int>(N));
    dp.resize(N, vector<int>(N));
    for (auto& i : v) {
        for (auto& j : i) {
            cin >> j;
        }
    }
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            K = max(K, func(i, j));
        }
    }
    cout << K;
}
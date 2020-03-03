#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
typedef long long ll;
#define endl '\n'
#define pb push_back

int N;
int dx[] = {1, 0}, dy[] = {0, 1};
vector<vector<int>> arr;
vector<vector<ll>> dp;

int main() {
    FASTIO
    cin >> N;
    arr.resize(N, vector<int>(N));
    dp.resize(N, vector<ll>(N));
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> arr[i][j];
        }
    }
    dp[0][0] = 1;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (!arr[i][j])
                continue;
            for (int k = 0; k < 2; k++) {
                int nx = i + dx[k] * arr[i][j];
                int ny = j + dy[k] * arr[i][j];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;
                dp[nx][ny] += dp[i][j];
            }
        }
    }
    cout << dp[N - 1][N - 1];
}
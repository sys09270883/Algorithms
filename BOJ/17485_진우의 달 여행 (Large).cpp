#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
typedef long long ll;

const int INF = 987654321;
int arr[1001][1001], dp[1001][1001][4];
int N, M, res = INF;

int main() {
    FASTIO
    cin >> N >> M;
    fill(&dp[0][0][0], &dp[N - 1][M - 1][4], INF);
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            cin >> arr[i][j];
            if (i == 0)
                dp[i][j][0] = arr[i][j];
        }
    }
    for (int i = 1; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            int& a = arr[i][j];
            if (j == 0) {
                dp[i][j][2] = min(dp[i - 1][j][0], dp[i - 1][j][3]) + a;
                dp[i][j][3] = min(min(dp[i - 1][j + 1][0], dp[i - 1][j + 1][1]), dp[i - 1][j + 1][2]) + a;
            }
            else if (j == M - 1) {
                dp[i][j][1] = min(dp[i - 1][j - 1][0], min(dp[i - 1][j - 1][2], dp[i - 1][j - 1][3])) + a;
                dp[i][j][2] = min(dp[i - 1][j][0], dp[i - 1][j][1]) + a;
            }
            else {
                dp[i][j][1] = min(dp[i - 1][j - 1][0], min(dp[i - 1][j - 1][2], dp[i - 1][j - 1][3])) + a;
                dp[i][j][2] = min(dp[i - 1][j][0], min(dp[i - 1][j][1], dp[i - 1][j][3])) + a;
                dp[i][j][3] = min(min(dp[i - 1][j + 1][0], dp[i - 1][j + 1][1]), dp[i - 1][j + 1][2]) + a;
            }
        }
    }
    for (int i = 0; i < M; i++)
    {
        for (int j = 1; j < 4; j++)
        {
            res = min(dp[N - 1][i][j], res);
        }
    }
    cout << res;
}
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define all(x) (x).begin(), (x).end()
#define next _next

int N, M, res;
vector<vector<int>> arr, dp;
string s;

int main() {
    FASTIO
    cin >> N >> M;
    arr.resize(N + 1, vector<int>(M + 1));
    dp.resize(N + 1, vector<int>(M + 1));
    for (int i = 1; i < N + 1; i++) {
        cin >> s;
        for (int j = 1; j < M + 1; j++) {
            arr[i][j] = s[j - 1] - '0';
            if (arr[i][j])
                dp[i][j] = 1;
        }
    }
    for (int i = 1; i < N + 1; i++) {
        for (int j = 1; j < M + 1; j++) {
            if (dp[i][j]) {
                dp[i][j] = min(dp[i - 1][j - 1], min(dp[i - 1][j], dp[i][j - 1])) + 1;
                res = max(res, dp[i][j]);
            }
        }
    }
    cout << res * res;
}
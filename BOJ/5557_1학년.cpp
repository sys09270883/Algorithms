#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

int N;
vector<int> arr;
ll dp[101][21];

int main() {
    FASTIO
    cin >> N;
    arr.resize(N);
    for (int i = 0; i < N; i++) {
        cin >> arr[i];
    }
    dp[0][arr[0]] = 1;
    for (int i = 1; i < N; i++) {
        for (int j = 0; j < 21; j++) {
            if (j - arr[i] >= 0)
                dp[i][j] += dp[i - 1][j - arr[i]];
            if (j + arr[i] < 21)
                dp[i][j] += dp[i - 1][j + arr[i]];
        }
    }
    cout << dp[N - 2][arr[N - 1]]; 
}
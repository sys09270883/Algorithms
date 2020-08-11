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

const int MOD = 9901;
int N;
vector<vector<int>> dp;

int main() {
    FASTIO
    cin >> N;
    dp.resize(N + 1, vector<int>(3, 1));
    for (int i = 2; i < N + 1; i++) {
        dp[i][0] = dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2];
        dp[i][0] %= MOD;
        dp[i][1] = dp[i - 1][0] + dp[i - 1][2];
        dp[i][1] %= MOD;
        dp[i][2] = dp[i - 1][0] + dp[i - 1][1];
        dp[i][2] %= MOD;
    }
    cout << (dp[N][0] + dp[N][1] + dp[N][2]) % MOD;
}

#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

const int MOD = 1e9;
int N, K;
vector<vector<ll>> dp;

int func(int a, int b) {
    ll &ret = dp[a][b];
    if (ret != -1)
        return ret;
    ret = 1;
    if (b > 1) {
        for (int i = 0; i < a; i++) {
            ret += func(a - i, b - 1);
            ret %= MOD;
        }
    }
    return ret;
}

int main() {
    FASTIO
    cin >> N >> K;
    dp.resize(N + 1, vector<ll>(K + 1, -1));
    cout << func(N, K);
}
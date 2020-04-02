#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()
#define compress(x) sort(all(x)), x.erase(unique(all(x)), x.end())

int N;
vector<int> v, dp;

int main() {
    FASTIO
    cin >> N;
    v.resize(N + 1), dp.resize(N + 1);
    for (int i = 1; i < N + 1; i++) {
        cin >> v[i];
    }
    for (int i = 1; i < N + 1; i++) {
        int minv = 1e6;
        int maxv = -1;
        for (int j = i; j > 0; j--) {
            minv = min(minv, v[j]);
            maxv = max(maxv, v[j]);
            dp[i] = max(dp[j - 1] + maxv - minv, dp[i]);
        }
    }
    cout << dp[N];
}
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

int N;
vector<int> v, dp;

int main() {
    FASTIO
    cin >> N;
    v.resize(N + 1);
    dp.resize(N + 1);
    for (int i = 1; i < N + 1; i++) {
        cin >> v[i];
        dp[i] = v[i];
        for (int j = 1; j < i + 1; j++) {
            if (v[i] > v[j] && dp[i] < dp[j] + v[i])
                dp[i] = dp[j] + v[i];
        }
    }
    cout << *max_element(all(dp));
}
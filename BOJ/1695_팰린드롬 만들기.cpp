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
vector<int> v;
vector<vector<int>> dp;

int func(int l, int r) {
    if (l > r)
        return 0;
    int &ret = dp[l][r];
    if (ret != -1)
        return ret;
    if (v[l] == v[r])
        return ret = func(l + 1, r - 1);
    else
        return ret = min(func(l + 1, r) + 1, func(l, r - 1) + 1);
}

int main() {
    FASTIO
    cin >> N;
    v.resize(N);
    dp.resize(N, vector<int>(N));
    for (auto& i : v) {
        cin >> i;
    }
    for (auto& i : dp) {
        fill(all(i), -1);
    }
    cout << func(0, N - 1);
}
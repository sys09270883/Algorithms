#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

int T, N, v[1001], dp[1001][1001][2];

int func(int l, int r, int flag) {
    int &ret = dp[l][r][flag];
    if (ret != -1)
        return ret;
    if (l >= r) {
        if (!flag)
            return ret = v[l];
        return ret = 0;
    }
    if (!flag)
        return ret = max(func(l + 1, r, !flag) + v[l], func(l, r - 1, !flag) + v[r]);
    else
        return ret = min(func(l + 1, r, !flag), func(l, r - 1, !flag));
}

int main() {
    FASTIO
    cin >> T;
    while (T--) {
        cin >> N;
        memset(dp, -1, sizeof(dp));
        for (int i = 1; i < N + 1; i++) {
            cin >> v[i];
        }
        cout << func(1, N, 0) << endl;
    }
}
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
    v.resize(N);
    for (auto& i : v) {
        cin >> i;
    }
    for (int i = N - 1; i >= 0; i--) {
        if (dp.empty() || dp.back() < v[i])
            dp.pb(v[i]);
        else
            *lower_bound(all(dp), v[i]) = v[i];
    }
    cout << dp.size();
}
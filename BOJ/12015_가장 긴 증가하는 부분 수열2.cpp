#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

int N, idx;
vector<int> dp;

int main() {
    FASTIO
    cin >> N;
    for (int i = 0; i < N; i++) {
        int x;
        cin >> x;
        if (dp.empty() || dp.back() < x)
            dp.pb(x);
        else
            *lower_bound(all(dp), x) = x;
    }
    cout << dp.size() << endl;
}
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

const ll MOD = 1e9 + 7;
ll N, M, A = 1, B = 1;

ll pow(ll a, ll b) {
    ll ret = 1;
    while (b) {
        if (b & 1)
            ret *= a, ret %= MOD;
        b >>= 1;
        a *= a, a %= MOD;
    }
    return ret;
}

int main() {
    FASTIO
    cin >> N >> M;
    for (int i = 1; i < M + 1; i++) {
        A *= (N - i + 1);
        A %= MOD;
        B *= i;
        B %= MOD;
    }
    cout << A * pow(B, MOD - 2) % MOD;
}
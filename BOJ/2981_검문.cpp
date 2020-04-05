#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());
#define cprcmp(x, y) sort(all(x), (y)), (x).erase(unique(all(x)), (x).end());

const int MAX = 1e9;
int N, gcd;
vector<int> v, vv;

int main() {
    FASTIO
    cin >> N;
    v.resize(N);
    for (auto& i : v) {
        cin >> i;
    }
    sort(all(v));
    gcd = v[1] - v[0];
    for (int i = 1; i < N - 1; i++) {
        gcd = __gcd(gcd, v[i + 1] - v[i]);
    }
    for (int i = 2; i * i <= gcd; i++) {
        if (!(gcd % i))
            vv.pb(i), vv.pb(gcd / i);
    }
    vv.pb(gcd);
    cpr(vv)
    for (auto& i : vv) {
        cout << i << ' ';
    }
}
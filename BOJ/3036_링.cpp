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

int N, a;

int main() {
    FASTIO
    cin >> N >> a;
    for (int i = 0; i < N - 1; i++) {
        int x, gcd;
        cin >> x;
        gcd = __gcd(a, x);
        cout << a / gcd << '/' << x / gcd << endl;
    }
}
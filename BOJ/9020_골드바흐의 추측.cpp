#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()
#define compress(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());
#define compress(x, y) sort(all(x), (y)), (x).erase(unique(all(x)), (x).end());

const int MAX = 1e4;
int T;
vector<bool> isPrime(MAX + 1, true);
vector<int> prime;

int main() {
    FASTIO
    cin >> T;
    for (int i = 2; i * i <= MAX; i++) {
        if (!isPrime[i])
            continue;
        for (int j = i + i; j <= MAX; j += i) {
            isPrime[j] = false;
        }
    }
    for (int i = 2; i <= MAX; i++) {
        if (isPrime[i])
            prime.pb(i);
    }
    while (T--) {
        int a, b, c;
        cin >> a;
        int t = lower_bound(all(prime), a >> 1) - prime.begin();
        for (int i = t; i >= 0; i--) {
            if (isPrime[a - prime[i]]) {
                b = prime[i], c = a - prime[i];
                break;
            }
        }
        cout << min(b, c) << ' ' << max(b, c) << endl;
    }
}
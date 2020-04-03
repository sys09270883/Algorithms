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

const int MAX = 1e7 + 1;
int N;
vector<bool> isPrime(MAX, true);
vector<int> prime;

int main() {
    FASTIO
    for (int i = 2; i * i < MAX; i++) {
        if (!isPrime[i])
            continue;
        for (int j = i + i; j < MAX; j += i) {
            isPrime[j] = true;
        }
    }
    for (int i = 2; i < MAX; i++) {
        if (isPrime[i])
            prime.pb(i);
    }
    cin >> N;
    for (auto& p : prime) {
        if (p > N)
            break;
        while (!(N % p)) {
            cout << p << endl;
            N /= p;
        }
    }
}
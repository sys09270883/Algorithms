#include <bits/stdc++.h>
using namespace std;
using ll = long long;
using ull = unsigned long long;
using pii = pair<int, int>;
using pll = pair<ll, ll>;
using pli = pair<ll, int>;
#pragma GCC optimize("Ofast")
#pragma GCC optimize("unroll-loops")
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define rall(x) (x).rbegin(), (x).rend()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

int M, sum, _xor;

int main() {
    FASTIO
    cin >> M;
    while (M--) {
        int q, a;
        cin >> q;
        if (q == 1) {
            cin >> a;
            sum += a;
            _xor ^= a;
        } else if (q == 2) {
            cin >> a;
            sum -= a;
            _xor ^= a;
        } else if (q == 3) {
            cout << sum << endl;
        } else {
            cout << _xor << endl;
        }
    }
}
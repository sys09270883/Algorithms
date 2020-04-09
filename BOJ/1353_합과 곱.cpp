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
#define Matrix vector<vector<int>>
#define Matrixll vector<vector<ll>>

int main() {
    FASTIO
    int S, P;
    cin >> S >> P;
    if (S == P)
        return !(cout << 1);
    double prev = 0;
    int x = 2;
    while (1) {
        double cur = pow((double)S / x, x);
        if (cur >= P)
            return !(cout << x);
        if (cur < prev)
            return !(cout << -1);
        prev = cur;
        x++;
    }
}
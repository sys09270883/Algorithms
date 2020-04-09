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

int N;
vector<pii> v(3);

int main() {
    FASTIO
    cin >> N;
    int a, b, c;
    pii na, nb, nc;
    cin >> a >> b >> c;
    v[0] = na = {a, a};
    v[1] = nb = {b, b};
    v[2] = nc = {c, c};
    for (int i = 1; i < N; i++) {
        cin >> a >> b >> c;
        na.first = max(v[0].first, v[1].first) + a;
        na.second = min(v[0].second, v[1].second) + a;
        nb.first = max(v[0].first, max(v[1].first, v[2].first)) + b;
        nb.second = min(v[0].second, min(v[1].second, v[2].second)) + b;
        nc.first = max(v[1].first, v[2].first) + c;
        nc.second = min(v[1].second, v[2].second) + c;
        v[0] = na, v[1] = nb, v[2] = nc;
    }
    int x = -987654321, y = 987654321;
    for (int i = 0; i < 3; i++) {
        x = max(x, v[i].first);
        y = min(y, v[i].second);
    }
    cout << x << ' ' << y;
}
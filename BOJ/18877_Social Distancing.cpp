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

const ll INF = LLONG_MAX >> 1;
int N, M;
vector<pll> v;

bool func(ll dist) {
    ll prev = -INF;
    int cnt = 0;
    for (auto& i : v) {
        while (max(prev + dist, i.first) <= i.second) {
            prev = max(prev + dist, i.first);
            cnt++;
            if (cnt >= N)
                return true;
        }
    }
    return false;
}

int main() {
    FASTIO
    cin >> N >> M;
    v.resize(M);
    for (auto& i : v) {
        int a, b;
        cin >> a >> b;
        i = {a, b};
    }
    sort(all(v));
    ll low = 0, high = INF, res = -1;
    while (low <= high) {
        ll m = low + high >> 1;
        if (func(m))
            res = m, low = m + 1;
        else
            high = m - 1;
    }
    cout << res;
}
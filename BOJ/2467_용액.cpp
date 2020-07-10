#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;
typedef pair<ll, int> pli;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

int N, val = 2e9;
pii res;
vector<int> v;

int main() {
    FASTIO
    cin >> N;
    v.resize(N);
    for (auto& i : v) {
        cin >> i;
    }
    for (int i = 0; i < N; i++) {
        auto tar = lower_bound(all(v), -v[i]) - v.begin();
        for (int j = tar - 1; j <= tar; j++) {
            if (j < 0 || j >= N || i == j)
                continue;
            if (val > abs(v[i] + v[j])) {
                val = abs(v[i] + v[j]);
                res = {v[i], v[j]};
            }
        }
    }
    cout << min(res.ft, res.sd) << ' ' << max(res.ft, res.sd);
}
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
#define rall(x) (x).rbegin(), (x).rend()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

multiset<int> ms;
int N, K, res, last;
vector<pii> v;

int main() {
    FASTIO
    cin >> N >> K;
    for (int i = 0; i < N; i++) {
        pii p;
        cin >> p.ft >> p.sd;
        v.pb(p);
    }
    sort(all(v), [](pii &p1, pii &p2) {
        return p1.sd < p2.sd;
    });
    for (int i = 0; i < K - 1; i++) {
        ms.insert(0);
    }
    for (auto& i : v) {
        int s = i.ft, e = i.sd;
        if (s <= last)
            continue;
        auto it = ms.lower_bound(s);
        if (it == ms.begin()) {
            res++;
            last = e;
        }
        else {
            it--;
            ms.erase(it);
            ms.insert(e);
        }
    }
    cout << res;
}
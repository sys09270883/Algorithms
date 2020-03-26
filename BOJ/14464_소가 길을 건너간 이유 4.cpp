#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
typedef long long ll;
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) x.begin(), x.end()

int C, N, res;
vector<int> cow;
vector<pii> chic;

int main() {
    FASTIO
    cin >> C >> N;
    for (int i = 0; i < C; i++) {
        int a;
        cin >> a;
        cow.pb(a);
    }
    for (int i = 0; i < N; i++) {
        int a, b;
        cin >> a >> b;
        chic.pb({b, a});
    }
    sort(all(cow));
    sort(all(chic));
    for (auto& i : chic) {
        auto it = lower_bound(cow.begin(), cow.end(), i.second);
        if (it != cow.end() && *it <= i.first)
            res++, cow.erase(it);
    }
    cout << res;
}
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

int T, N;

int main() {
    FASTIO
    cin >> T;
    while (T--) {
        cin >> N;
        ll res = 1;
        map<string, vector<string>> mp;
        for (int i = 0; i < N; i++) {
            string a, b;
            cin >> a >> b;
            mp[b].pb(a);
        }
        for (auto& i : mp) {
            res *= i.second.size() + 1;
        }
        cout << res - 1 << endl;
    }
}
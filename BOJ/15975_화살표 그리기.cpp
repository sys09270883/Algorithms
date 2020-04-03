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

const int MAX = 1e5 + 1;
int N;
ll res;
vector<vector<int>> v(MAX, vector<int>());
vector<pii> qry;

int main() {
    FASTIO
    cin >> N;
    for (int i = 0; i < N; i++) {
        int a, b;
        cin >> a >> b;
        qry.pb({a, b});
    }
    sort(all(qry));
    for (auto& q : qry) {
        v[q.second].pb(q.first);
    }
    for (int i = 1; i < MAX; i++) {
        int sz = v[i].size();
        if (sz < 2)
            continue;
        for (int j = 0; j < sz; j++) {
            if (j == 0)
                res += v[i][j + 1] - v[i][j];
            else if (j == sz - 1)
                res += v[i][j] - v[i][j - 1];
            else 
                res += min(v[i][j + 1] - v[i][j], v[i][j] - v[i][j - 1]);
        }
    }
    cout << res;
}
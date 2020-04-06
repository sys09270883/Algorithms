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

int N, idx;
vector<int> v, dp, p;

int main() {
    FASTIO
    cin >> N;
    v.resize(N + 1), p.resize(N + 1);
    for (int i = 1; i < N + 1; i++) {
        cin >> v[i];
    }
    dp.pb(v[1]);
    for (int i = 2; i < N + 1; i++) {
        if (dp.back() < v[i])
            dp.pb(v[i]), p[i] = ++idx;
        else {
            int pos = lower_bound(all(dp), v[i]) - dp.begin();
            dp[pos] = v[i];
            p[i] = pos;
        }
    }
    cout << idx + 1 << endl;
    stack<int> stack;
    for (int i = N; i >= 1 && idx >= 0; i--) {
        if (p[i] == idx)
            stack.push(v[i]), idx--;
    }
    while (!stack.empty()) {
        cout << stack.top() << ' ';
        stack.pop();
    }
}
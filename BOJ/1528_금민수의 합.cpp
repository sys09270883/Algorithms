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

const int INF = 987654321;
int N;
vector<int> dp, v, res;
string s;

void generate(string &s) {
    if (s.empty()) {
        s = "4";
        generate(s);
        s = "7";
        generate(s);
    }
    if (s.size() >= 7)
        return;
    v.pb(stoi(s));
    s += '4';
    generate(s);
    s.back() = '7';
    generate(s);
    s.pop_back();
}

int main() {
    FASTIO
    cin >> N;
    dp.assign(N + 1, INF);
    dp[0] = 0;
    generate(s);
    sort(all(v));
    for (int i = 0; i < N + 1; i++) {
        for (auto& j : v) {
            if (i - j < 0)
                continue;
            dp[i] = min(dp[i], dp[i - j] + 1);
        }
    }
    if (dp[N] == INF)
        return !(cout << -1);
    int a = N, b = -1, sz = v.size();
    while (true) {
        int mn = INF, mi = -1;
        for (int i = 0; i < sz; i++) {
            if (a - v[i] < 0)
                break;
            if (dp[a - v[i]] != INF) {
                if (mn > dp[a - v[i]]) {
                    mn = dp[a - v[i]];
                    mi = i;
                }
            }
        }
        if (mn == INF)
            break;
        a -= v[mi];
        b = mi;
        res.pb(v[b]);
    }
    sort(all(res));
    for (auto& i : res) {
        cout << i << ' ';
    }
}
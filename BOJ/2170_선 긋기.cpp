#include <bits/stdc++.h>
using namespace std;
using ll = long long;
using ull = unsigned long long;
using pii = pair<int, int>;
using pll = pair<ll, ll>;
using pli = pair<ll, int>;
#pragma GCC optimize("Ofast")
#pragma GCC optimize("unroll-loops")
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define rall(x) (x).rbegin(), (x).rend()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

int N, last = -2e9, sum, res;
vector<pii> v;

int main() {
    FASTIO
    cin >> N;
    v.resize(N);
    for (auto& i : v) {
        cin >> i.ft >> i.sd;
    }
    sort(all(v));
    for (auto& i : v) {
        if (last >= i.ft) {
            if (i.sd > last) {
                sum += abs(i.sd - last);
                last = i.sd;
            }
        } else {
            res += sum;
            last = i.sd;
            sum = abs(i.sd - i.ft);
        }
    }
    res += sum;
    cout << res;
}
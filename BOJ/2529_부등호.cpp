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

int k;
vector<char> op;
vector<int> minv, maxv;

ll get(vector<int> &v) {
    ll ret = 0, mul = 1;
    for (int i = k; i >= 0; i--) {
        ret += mul * v[i];
        mul *= 10;
    }
    return ret;
}

bool valid(vector<int> &v) {
    for (int i = 0; i < k; i++) {
        if (op[i] == '<') {
            if (v[i] > v[i + 1])
                return false;
        } else {
            if (v[i] < v[i + 1])
                return false;
        }
    }
    return true;
}

ll get_max() {
    ll ret = 0;
    do {
        if (valid(maxv))
            ret = max(ret, get(maxv));
    } while (prev_permutation(all(maxv)));
    return ret;
}

ll get_min() {
    ll ret = 1e13;
    do {
        if (valid(minv)) {
            ret = min(ret, get(minv));
        }
    } while (next_permutation(all(minv)));
    return ret;
}

int main() {
    FASTIO
    cin >> k;
    op.resize(k);
    for (auto& i : op) {
        cin >> i;
    }
    for (int i = 0; i < k + 1; i++) {
        maxv.pb(9 - i);
        minv.pb(i);
    }
    cout << setw(k + 1) << setfill('0') << get_max() << endl;
    cout << setw(k + 1) << setfill('0') << get_min();
}

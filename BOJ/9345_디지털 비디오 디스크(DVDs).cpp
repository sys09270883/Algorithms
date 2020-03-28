#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

const int INF = 987654321;
int T, N, K;
vector<int> v;
vector<int> mint, maxt;

void resize() {
    v.clear();
    mint.clear();
    maxt.clear();
    v.resize(N);
    mint.resize(1 << (int)ceil(log2(N)) + 1);
    maxt.resize(1 << (int)ceil(log2(N)) + 1);
}

void init(int n, int s, int e) {
    if (s == e) {
        mint[n] = maxt[n] = s;
        return;
    }
    int m = s + e >> 1;
    init(n << 1, s, m);
    init(n << 1 | 1, m + 1, e);
    mint[n] = min(mint[n << 1], mint[n << 1 | 1]);
    maxt[n] = max(maxt[n << 1], maxt[n << 1 | 1]);
}

void update(int i, int v, int n, int s, int e) {
    if (i > e || i < s)
        return;
    if (s == e) {
        mint[n] = maxt[n] = v;
        return;
    }
    int m = s + e >> 1;
    update(i, v, n << 1, s, m);
    update(i, v, n << 1 | 1, m + 1, e);
    mint[n] = min(mint[n << 1], mint[n << 1 | 1]);
    maxt[n] = max(maxt[n << 1], maxt[n << 1 | 1]);    
}

pii query(int l, int r, int n, int s, int e) {
    if (l > e || r < s)
        return {INF, -INF};
    if (l <= s && e <= r) 
        return {mint[n], maxt[n]};
    int m = s + e >> 1;
    auto left = query(l, r, n << 1, s, m);
    auto right = query(l, r, n << 1 | 1, m + 1, e);
    return {min(left.first, right.first), max(left.second, right.second)};
}

int main() {
    FASTIO
    cin >> T;
    while (T--) {
        cin >> N >> K;
        resize();
        init(1, 0, N - 1);
        for (int i = 0; i < N; i++) {
            v[i] = i;
        }
        for (int i = 0; i < K; i++) {
            int q, a, b;
            cin >> q >> a >> b;
            if (q) {
                auto ret = query(a, b, 1, 0, N - 1);
                cout << (ret.first == a && ret.second == b ? "YES" : "NO") << endl;
            }
            else {
                swap(v[a], v[b]);
                update(a, v[a], 1, 0, N - 1);
                update(b, v[b], 1, 0, N - 1);
            }
        }
    }
}
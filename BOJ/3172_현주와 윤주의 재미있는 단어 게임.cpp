#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

struct Query {
    string s;
    int idx;

    Query(string s, int idx) : s(s), idx(idx) {}

    bool operator<(const Query &q) {
        return this->s < q.s;
    }
};

int N;
vector<string> input;
vector<Query> v;
vector<int> tree;
ll res;

int update(int i, int n, int s, int e) {
    if (i > e || i < s)
        return tree[n];
    if (s == e)
        return ++tree[n];
    int m = s + e >> 1;
    return tree[n] = update(i, n << 1, s, m) + update(i, n << 1 | 1, m + 1, e);
}

int query(int l, int r, int n, int s, int e) {
    if (l > e || r < s)
        return 0;
    if (l <= s && e <= r)
        return tree[n];
    int m = s + e >> 1;
    return query(l, r, n << 1, s, m) + query(l, r, n << 1 | 1, m + 1, e);
}

int main() {
    FASTIO
    cin >> N;
    tree.resize(1 << (int)ceil(log2(N)) + 1);
    input.resize(N);
    for (auto& i : input) {
        cin >> i;
    }
    sort(all(input));
    for (int i = 1; i < N + 1; i++) {
        reverse(all(input[i - 1]));
        v.pb({input[i - 1], i});
    }
    sort(all(v));
    for (auto& i : v) {
        res += query(i.idx, N, 1, 1, N);
        update(i.idx, 1, 1, N);
    }
    cout << res;
}
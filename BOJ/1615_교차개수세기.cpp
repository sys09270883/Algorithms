#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
typedef long long ll;
#define endl '\n'
#define pb push_back
#define pii pair<int, int>

const int MAX = 2e3;
int N, M;
ll res;
vector<pii> p;
vector<int> tree;

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
    cin >> N >> M;
    p.resize(M);
    tree.resize((1 << (int)ceil(log2(MAX)) + 1));
    for (int i = 0; i < M; i++) {
        int a, b;
        cin >> a >> b;
        p[i] = {a, b};
    }
    sort(p.begin(), p.end());
    for (auto& i : p) {
        res += query(i.second + 1, MAX, 1, 1, MAX);
        update(i.second, 1, 1, 1, MAX);
    }
    cout << res;
}
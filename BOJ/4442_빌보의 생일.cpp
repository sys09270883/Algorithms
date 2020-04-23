#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());
#define cprcmp(x, y) sort(all(x), (y)), (x).erase(unique(all(x)), (x).end());
#define Matrix vector<vector<int>>
#define Matrixll vector<vector<ll>>

int N;
vector<ll> tree;
unordered_map<string, int> s2i;

void resize() {
    tree.assign(1 << (int)ceil(log2(N)) + 1, 0);
    s2i.clear();
}

ll update(int i, int n, int s, int e) {
    if (i > e || i < s)
        return tree[n];
    if (s == e)
        return ++tree[n];
    int m = s + e >> 1;
    return tree[n] = update(i, n << 1, s, m) + update(i, n << 1 | 1, m + 1, e);
}

ll query(int l, int r, int n, int s, int e) {
    if (l > e || r < s)
        return 0;
    if (l <= s && e <= r)
        return tree[n];
    int m = s + e >> 1;
    return query(l, r, n << 1, s, m) + query(l, r, n << 1 | 1, m + 1, e);
}

int main() {
    FASTIO
    while (cin >> N) {
        if (!N)
            break;
        resize();
        ll res = 0;
        for (int i = 1; i < N + 1; i++) {
            string s;
            cin >> s;
            s2i[s] = i;
        }
        for (int i = 0; i < N; i++) {
            string s;
            cin >> s;
            res += query(s2i[s] + 1, N, 1, 1, N);
            update(s2i[s], 1, 1, N);
        }
        cout << res << endl;
    }
}
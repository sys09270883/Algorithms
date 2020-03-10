#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
typedef long long ll;
#define endl '\n'
#define pb push_back
#define pii pair<int, int>

struct Query {
    char q;
    int a, b;
    Query(char q, int a, int b) : q(q), a(a), b(b) {}
};

ll update(vector<ll>& tree, int i, int d, int n, int s, int e) {
    if (i > e || i < s)
        return tree[n];
    if (s == e)
        return tree[n] += d;
    int m = s + e >> 1;
    return tree[n] = update(tree, i, d, n << 1, s, m) + update(tree, i, d, n << 1 | 1, m + 1, e);
}

ll sum(vector<ll>& tree, int l, int r, int n, int s, int e) {
    if (l > e || r < s)
        return 0;
    if (l <= s && e <= r)
        return tree[n];
    int m = s + e >> 1;
    return sum(tree, l, r, n << 1, s, m) + sum(tree, l, r, n << 1 | 1, m + 1, e);
}

int main() {
    FASTIO
    int T;
    cin >> T;
    while (T--) {
        int N, M;
        cin >> N >> M;
        vector<int> arr(N + 1, 0);
        vector<int> idx;
        vector<Query> query;
        for (int i = 0; i < M; i++) {
            char q;
            int a, b;
            cin >> q;
            if (q == 'R') {
                cin >> a >> b;
                query.pb({q, a, b});
            }
            else if (q == 'Q') {
                cin >> a;
                query.pb({q, a, -1});
            }
        }
        idx.pb(0);
        for (int i = 0; i < M; i++) {
            if (query[i].q == 'Q')
                continue;
            arr[query[i].a] += query[i].b;
            idx.pb(arr[query[i].a]);
        }
        sort(idx.begin(), idx.end());
        idx.erase(unique(idx.begin(), idx.end()), idx.end());
        fill(arr.begin(), arr.end(), 0);
        int MAX = idx.size();
        vector<ll> tree(1 << (int)ceil(log2(MAX)) + 1);
        for (int i = 0; i < M; i++) {
            if (query[i].q == 'R') {
                int tar = lower_bound(idx.begin(), idx.end(), arr[query[i].a]) - idx.begin() + 1;
                update(tree, tar, -1, 1, 1, MAX);
                arr[query[i].a] += query[i].b;
                tar = lower_bound(idx.begin(), idx.end(), arr[query[i].a]) - idx.begin() + 1;
                update(tree, tar, 1, 1, 1, MAX);
            }
            else if (query[i].q == 'Q') {
                int tar = upper_bound(idx.begin(), idx.end(), arr[query[i].a]) - idx.begin() + 1;
                cout << 1 + sum(tree, tar, MAX, 1, 1, MAX) << endl;
            }
        }
    }
}
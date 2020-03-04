#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
typedef long long ll;
#define endl '\n'
#define pb push_back
#define pii pair<int, int>

const int MAX = 5e5;
int N, M;
vector<int> tree;

int update(int i, int d, int n, int s, int e) {
    if (i > e || i < s)
        return tree[n];
    if (s == e)
        return tree[n] += d;
    int m = s + e >> 1;
    return tree[n] = update(i, d, n << 1, s, m) + update(i, d, n << 1 | 1, m + 1, e);
}

int query(int k, int n, int s, int e) {
    if (s == e)
        return s;
    int m = s + e >> 1;
    if (tree[n << 1] >= k)
        return query(k, n << 1, s, m);
    return query(k - tree[n << 1], n << 1 | 1, m + 1, e);
}

int main() {
    FASTIO
    cin >> N;
    tree.resize(1 << (int)ceil(log2(MAX)) + 1);
    for (int i = 1; i < N + 1; i++) {
        int x;
        cin >> x;
        update(i, x, 1, 1, N);
    }
    cin >> M;
    for (int i = 0; i < M; i++) {
        int q, a, b;
        cin >> q >> a;
        if (q == 1) {
            cin >> b;
            update(a, b, 1, 1, N);
        }
        else if (q == 2)
            cout << query(a, 1, 1, N) << endl;
    }
}
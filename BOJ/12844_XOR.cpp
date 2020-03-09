#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
typedef long long ll;
#define endl '\n'
#define pb push_back
#define pii pair<int, int>

int N, M, H;
vector<int> arr, tree, lazy;

int init(int n, int s, int e) {
    if (s == e)
        return tree[n] = arr[s];
    int m = s + e >> 1;
    return tree[n] = init(n << 1, s, m) ^ init(n << 1 | 1, m + 1, e);
}

void updateLazy(int n, int s, int e) {
    if (lazy[n]) {
        if (s ^ e) {
            lazy[n << 1] ^= lazy[n];
            lazy[n << 1 | 1] ^= lazy[n]; 
        }
        tree[n] ^= ((e - s + 1) & 1) * lazy[n];
        lazy[n] = 0;
    }
}

int update(int l, int r, int d, int n, int s, int e) {
    updateLazy(n, s, e);
    if (l > e || r < s)
        return tree[n];
    if (l <= s && e <= r) {
        if (s ^ e) {
            lazy[n << 1] ^= d;
            lazy[n << 1 | 1] ^= d;
        }
        return tree[n] ^= ((e - s + 1) & 1) * d;
    }
    int m = s + e >> 1;
    return tree[n] = update(l, r, d, n << 1, s, m) ^ update(l, r, d, n << 1 | 1, m + 1, e);
}

int query(int l, int r, int n, int s, int e) {
    updateLazy(n, s, e);
    if (l > e || r < s)
        return 0;
    if (l <= s && e <= r)
        return tree[n];
    int m = s + e >> 1;
    return query(l, r, n << 1, s, m) ^ query(l, r, n << 1 | 1, m + 1, e);
}

int main() {
    FASTIO
    cin >> N;
    arr.resize(N);
    for (int i = 0; i < N; i++) {
        cin >> arr[i];
    }
    H = 1 << (int)ceil(log2(N)) + 1;
    tree.resize(H);
    lazy.resize(H);
    init(1, 0, N - 1);
    cin >> M;
    for (int i = 0; i < M; i++) {
        int q, a, b, c;
        cin >> q >> a >> b;
        if (a > b)
            swap(a, b);
        if (q == 1) {
            cin >> c;
            update(a, b, c, 1, 0, N - 1);
        }
        else if (q == 2) {
            cout << query(a, b, 1, 0, N - 1) << endl;
        }
    }
}
#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
typedef long long ll;
#define endl '\n'
#define pb push_back
#define pii pair<int, int>

const int INF = 987654321;
int N, M;
vector<int> arr, tree;

int update(int i, int v, int n, int s, int e) {
    if (i > e || i < s)
        return tree[n];
    if (s == e)
        return tree[n] = v;
    int m = s + e >> 1;
    return tree[n] = max(update(i, v, n << 1, s, m), update(i, v, n << 1 | 1, m + 1, e));
}

int query(int l, int r, int n, int s, int e) {
    if (l > e || r < s)
        return -INF;
    if (l <= s && e <= r)
        return tree[n];
    int m = s + e >> 1;
    return max(query(l, r, n << 1, s, m), query(l, r, n << 1 | 1, m + 1, e));
}

int main() {
    FASTIO
    cin >> N >> M;
    arr.resize(N + 1);
    tree.resize(1 << (int)ceil(log2(N)) + 1);
    for (int i = 1; i < N + 1; i++) {
        cin >> arr[i];
    }
    for (int i = 1; i <= 2 * M - 1; i++) {
        update(i, arr[i], 1, 1, N);
    }
    for (int i = M; i <= N - M + 1; i++) {
        int l = i - M + 1, r = i + M - 1;
        cout << query(l, r, 1, 1, N) << ' ';
        update(l, 0, 1, 1, N);
        update(r + 1, arr[r + 1], 1, 1, N);
    }
}
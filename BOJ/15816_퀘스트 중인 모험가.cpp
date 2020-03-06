#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
typedef long long ll;
#define endl '\n'
#define pb push_back
#define pii pair<int, int>

const int MAX = 2e6;
int N, M;
vector<int> arr(MAX), idx, tree(1 << (int)ceil(log2(MAX)) + 1);
vector<vector<int>> p;

int update(int i, int v, int n, int s, int e) {
    if (i > e || i < s)
        return tree[n];
    if (s == e)
        return tree[n] = v;
    int m = s + e >> 1;
    return tree[n] = update(i, v, n << 1, s, m) + update(i, v, n << 1 | 1, m + 1, e);
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
    for (int i = 0; i < N; i++) {
        cin >> arr[i];
        idx.pb(arr[i]);
    }
    cin >> M;
    p.resize(M, vector<int>(3));
    for (int i = 0; i < M; i++) {
        cin >> p[i][0] >> p[i][1];
        if (p[i][0] == 2)
            cin >> p[i][2];
        else
            idx.pb(p[i][1]);
    }
    sort(idx.begin(), idx.end());
    idx.erase(unique(idx.begin(), idx.end()), idx.end());
    for (int i = 0, j = 0; i < N; i++) {
        while (idx[j] < arr[i]) {
            j++;
        }
        update(j, 1, 1, 0, MAX - 1);
    }
    for (int i = 0; i < M; i++) {
        if (p[i][0] == 1) {
            int k = lower_bound(idx.begin(), idx.end(), p[i][1]) - idx.begin();
            update(k, 1, 1, 0, MAX - 1);
        }
        else {
            int l = lower_bound(idx.begin(), idx.end(), p[i][1]) - idx.begin();
            int r = upper_bound(idx.begin(), idx.end(), p[i][2]) - idx.begin();
            cout << p[i][2] - p[i][1] + 1 - query(l, r - 1, 1, 0, MAX - 1) << endl;
        }
    }
}
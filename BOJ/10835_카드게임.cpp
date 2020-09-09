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

int N, l[2001], r[2001];
ll dp[2001][2001];

ll DFS(int i, int j) {
    if (i > N || j > N) {
        return 0;
    }
    auto &ret = dp[i][j];
    if (ret != -1) {
        return ret;
    }
    ret = max(ret, DFS(i + 1, j));
    ret = max(ret, DFS(i + 1, j + 1));
    if (l[i] > r[j]) {
        ret = max(ret, DFS(i, j + 1) + r[j]);
    }
    return ret;
}

int main() {
    FASTIO
    cin >> N;
    memset(dp, -1, sizeof(dp));
    for (int i = 1; i < N + 1; i++) {
        cin >> l[i];
    }
    for (int i = 1; i < N + 1; i++) {
        cin >> r[i];
    }
    cout << DFS(1, 1);
}
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

int N;
int arr[1001][10], res[1001];
bool vis[1001][10];

auto DFS(int cur, int prev) -> int {
    if (cur == N) {
        for (int i = 1; i < 10; i++) {
            if (i == prev) {
                continue;
            }
            if (arr[cur][i]) {
                res[cur] = i;
                return 1;
            }
        }
        return 0;
    }
    int ret = 0;
    for (int i = 1; i < 10; i++) {
        if (i == prev) {
            continue;
        }
        if (arr[cur][i] && !vis[cur + 1][i] && !ret) {
            vis[cur + 1][i] = true;
            res[cur] = i;
            ret |= DFS(cur + 1, i);
        }
    }
    return ret;
}

auto main() -> int {
    FASTIO
    cin >> N;
    for (int i = 1; i < N + 1; i++) {
        int a;
        cin >> a;
        while (a--) {
            int b;
            cin >> b;
            arr[i][b] = 1;
        }
    }
    if (DFS(1, 0)) {
        for (int i = 1; i < N + 1; i++) {
            cout << res[i] << endl;
        }
    } else {
        cout << -1;
    }
}
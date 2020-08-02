#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;
typedef pair<ll, int> pli;
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

vector<vector<int>> adj, level;
vector<bool> check;
int N, max_width, max_level, o;

void inorder(int cur, int dep) {
    if (cur == -1) {
        return;
    }
    inorder(adj[cur][0], dep + 1);
    level[dep].pb(++o);
    inorder(adj[cur][1], dep + 1);
}

int root() {
    int ret = -1;
    for (int i = 1; i < N + 1; i++) {
        if (!check[i]) {
            ret = i;
            break;
        }
    }
    return ret;
}

int main() {
    FASTIO
    cin >> N;
    adj.resize(N + 1, vector<int>());
    level.resize(N + 1, vector<int>());
    check.resize(N + 1);
    for (int i = 0; i < N; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        if (b > -1) {
            check[b] = true;
        }
        if (c > -1) {
            check[c] = true;
        }
        adj[a].pb(b);
        adj[a].pb(c);
    }
    inorder(root(), 1);
    for (int i = 1; i < N + 1; i++) {
        if (level[i].size()) {
            int width = level[i].back() - level[i].front() + 1;
            if (max_width < width) {
                max_width = width;
                max_level = i;
            }
        }
    }
    cout << max_level << ' ' << max_width;
}

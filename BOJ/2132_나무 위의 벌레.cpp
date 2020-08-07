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

int N, r, maxi;
ll maxv;
vector<int> v, candidate, dst;
vector<vector<int>> adj;

void DFS(int cur, int prev, int sum) {
    if (maxv < sum) {
        maxv = sum;
        candidate.clear();
        candidate.pb(cur);
    }
    else if (maxv == sum) {
        candidate.pb(cur);
    }
    for (auto& next : adj[cur]) {
        if (next != prev) {
            DFS(next, cur, sum + v[next]);
        }
    }
}

void DFS(int cur, int prev, int sum, int origin) {
    if (maxv < sum) {
        maxv = sum;
        dst.clear();
        dst.pb(min(origin, cur));
    }
    else if (maxv == sum) {
        dst.pb(min(origin, cur));
    }
    for (auto& next : adj[cur]) {
        if (next != prev) {
            DFS(next, cur, sum + v[next], origin);
        }
    }
}

void resize() {
    v.resize(N + 1);
    adj.resize(N + 1, vector<int>());
}

int main() {
    FASTIO
    cin >> N;
    resize();
    for (int i = 1; i < N + 1; i++) {
        int a;
        cin >> a;
        v[i] = a;
    }
    for (int i = 0; i < N - 1; i++) {
        int a, b;
        cin >> a >> b;
        adj[a].pb(b), adj[b].pb(a);
    }
    DFS(N, -1, v[N]);
    maxv = 0;
    for (auto& i : candidate) {
        DFS(i, -1, v[i], i);
    }
    sort(all(dst));
    cout << maxv << ' ' << dst[0];
}

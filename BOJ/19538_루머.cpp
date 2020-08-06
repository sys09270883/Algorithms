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

int N, M, t;
vector<vector<int>> adj;
vector<int> times;
vector<pii> bad;

void BFS() {
    queue<pii> q;
    for (auto& i : bad) {
        q.push({i.ft, i.sd});
    }
    while (q.size()) {
        auto tmp = q.front();
        q.pop();
        int cur = tmp.ft;
        int curt = tmp.sd;
        for (auto& next : adj[cur]) {
            if (times[next] == -1) {
                int cnt = 0;
                for (auto& i : adj[next]) {
                    if (times[i] > -1 && times[i] <= curt) {
                        cnt++;
                    }
                }
                if (adj[next].size() <= cnt << 1) {
                    times[next] = curt + 1;
                    q.push({next, curt + 1});
                }
            }
        }
    }
}

int main() {
    FASTIO
    cin >> N;
    adj.resize(N + 1, vector<int>());
    times.resize(N + 1, -1);
    for (int i = 1; i < N + 1; i++) {
        int a;
        while (1) {
            cin >> a;
            if (!a) {
                break;
            }
            adj[i].pb(a);
        }
    }
    cin >> M;
    for (int i = 0; i < M; i++) {
        int a;
        cin >> a;
        times[a] = 0;
        bad.pb({a, 0});
    }
    BFS();
    for (int i = 1; i < N + 1; i++) {
        cout << times[i] << ' ';
    }
}
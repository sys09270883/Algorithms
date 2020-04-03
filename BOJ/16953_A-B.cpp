#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()
#define compress(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());
#define compress(x, y) sort(all(x), (y)), (x).erase(unique(all(x)), (x).end());

const ll INF = 987654321987654321;
int A, B;
unordered_map<ll, bool> vis;

ll BFS() {
    queue<pll> q;
    q.push({A, 0});
    ll ret = INF;
    while (!q.empty()) {
        auto cur = q.front().first;
        auto cnt = q.front().second;
        q.pop();
        if (cur > B)
            continue;
        else if (cur == B)
            ret = min(ret, cnt);
        for (auto next : {cur << 1, cur * 10 + 1}) {
            if (vis.find(next) == vis.end()) {
                vis[next] = true;
                q.push({next, cnt + 1});
            }            
        }
    }
    return ret;
}

int main() {
    FASTIO
    cin >> A >> B;
    ll res = BFS();
    cout << (res == INF ? -1 : res + 1);
}
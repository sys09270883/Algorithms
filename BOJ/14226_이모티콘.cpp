#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;
typedef pair<ll, int> pli;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

struct Node {
    int val, cnt, cb;
};

const int MAX = 4e3;
int S;
vector<vector<bool>> vis;

int BFS() {
    queue<Node> q;
    q.push({1, 0, 0});
    vis.resize(S + MAX, vector<bool>(S + MAX));
    vis[1][0] = true;
    while (q.size()) {
        auto tmp = q.front();
        q.pop();
        int val = tmp.val, cnt = tmp.cnt, cb = tmp.cb;
        if (val == S)
            return cnt;

        int newcb = val;
        if (newcb <= S + MAX && !vis[val][newcb]) {
            vis[val][newcb] = true;
            q.push({val, cnt + 1, newcb});
        }

        int newval = val + cb;
        if (newval <= S + MAX && !vis[newval][cb]) {
            vis[newval][cb] = true;
            q.push({newval, cnt + 1, cb});
        }

        newval = val - 1;
        if (newval >= 0 && !vis[newval][cb]) {
            vis[newval][cb] = true;
            q.push({newval, cnt + 1, cb});
        }
    }
    return -1;
}

int main() {
    FASTIO
    cin >> S;
    cout << BFS();
}
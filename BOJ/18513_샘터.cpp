#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
typedef long long ll;
#define endl '\n'
#define pb push_back
#define pii pair<int, int>

struct Node {
    int idx, dir, diff;
    Node(int idx, int dir, int diff) : idx(idx), dir(dir), diff(diff) {}
};

const int MAX = (int)1e8;
int N, K;
vector<int> s;
unordered_map<int, int> vis;

ll BFS() {
    ll ret = 0, cnt = 0;
    queue<Node> q;
    for (auto& i : s) {
        vis[i] = 0;
        q.push({i, -1, 0});
        q.push({i, 1, 0});
    }
    while (!q.empty() && cnt < K) {
        int cur = q.front().idx;
        int dir = q.front().dir;
        int diff = q.front().diff;
        q.pop();
        int next = cur + dir;
        if (next < -MAX || next > MAX)
            continue;
        diff++;
        if (vis.find(next) == vis.end()) {
            vis[next] = diff;
            cnt++;
            q.push({next, dir, diff});
        }
        else {
            if (vis[next] <= diff)
                continue;
            vis[next] = diff;
            cnt++;
            q.push({next, dir, diff});
        }
        ret += diff;
    }
    return ret;
}

int main() {
    FASTIO
    cin >> N >> K;
    s.resize(N);
    for (int i = 0; i < N; i++) {
        cin >> s[i];
    }
    cout << BFS();
}
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

struct Node {
    int score, cnt, dcnt;

    Node(int score, int cnt, int dcnt) : score(score), cnt(cnt), dcnt(dcnt) {}
    
    bool operator<(const Node& n) const {
        if (cnt == n.cnt)
            return this->dcnt < n.dcnt;
        return this->cnt < n.cnt;
    }
};

const int INF = 987654321;
int N, A, B;
bool vis[1001][1001][10];

int BFS() {
    priority_queue<Node> pq;
    pq.push({A, 1, 0});
    pq.push({B, 1, 0});
    vis[A][1][0] = true;
    vis[B][1][0] = true;
    int ret = INF;
    while (!pq.empty()) {
        int cur = pq.top().score;
        int cnt = pq.top().cnt;
        int dcnt = pq.top().dcnt;
        pq.pop();
        if (ret < cnt || cur >= N + A)
            continue;
        else if (cur >= N) {
            ret = min(ret, cnt);
            continue;
        }
        for (auto next : {cur + A, cur + B}) {
            if (dcnt > (cnt + 1) / 10)
                continue;
            if (vis[next][cnt + 1][dcnt])
                continue;
            vis[next][cnt + 1][dcnt] = true;
            pq.push({next, cnt + 1, dcnt});
        }
        int next = cur << 1;
        if (dcnt + 1 > (cnt + 1) / 10)
            continue;
        if (vis[next][cnt + 1][dcnt + 1])
            continue;
        vis[next][cnt + 1][dcnt + 1] = true;
        pq.push({next, cnt + 1, dcnt + 1});
    }
    return ret;
}

int main() {
    FASTIO
    cin >> N >> A >> B;
    cout << BFS();
}
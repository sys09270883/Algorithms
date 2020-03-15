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
};

const int INF = 987654321;
int N, A, B;
bool vis[1001][501][10];

int BFS() {
    queue<Node> q;
    q.push({A, 1, 0});
    q.push({B, 1, 0});
    vis[A][1][0] = true;
    vis[B][1][0] = true;
    int ret = INF;
    while (!q.empty()) {
        int cur = q.front().score;
        int cnt = q.front().cnt;
        int dcnt = q.front().dcnt;
        q.pop();
        if (ret < cnt || cur >= N + A)
            continue;
        else if (cur >= N) {
            ret = min(ret, cnt);
            continue;
        }
        for (auto next : {cur + A, cur + B}) {
            int ncnt = cnt + 1;
            if (dcnt > ncnt / 10)
                continue;
            if (vis[next][ncnt][dcnt])
                continue;
            vis[next][ncnt][dcnt] = true;
            q.push({next, ncnt, dcnt});
        }
        int next = cur << 1;
        int ncnt = cnt + 1;
        int ndcnt = dcnt + 1;
        if (ndcnt > ncnt / 10)
            continue;
        if (vis[next][ncnt][ndcnt])
            continue;
        vis[next][ncnt][ndcnt] = true;
        q.push({next, ncnt, ndcnt});
    }
    return ret;
}

int main() {
    FASTIO
    cin >> N >> A >> B;
    cout << BFS();
}
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

const int MAX = 1e3;
pii b, d, j;
int x, y;
int dx[] = {-1, -1, -1, 0, 0, 1, 1, 1};
int dy[] = {-1, 0, 1, -1, 1, -1, 0, 1};
int arr[MAX + 1][MAX + 1];
bool vis[MAX + 1][MAX + 1];

struct Node {
    int x, y, cnt;

    Node(int x, int y, int cnt) : x(x), y(y), cnt(cnt) {}
};

int BFS() {
    queue<Node> q;
    q.push({b.ft, b.sd, 0});
    vis[b.ft][b.sd] = true;
    while (q.size()) {
        auto tmp = q.front();
        q.pop();
        int x = tmp.x;
        int y = tmp.y;
        int cnt = tmp.cnt;
        if (x == j.ft && y == j.sd) {
            return cnt;
        }
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx <= 0 || ny <= 0 || nx > MAX || ny > MAX || vis[nx][ny]) {
                continue;
            }
            vis[nx][ny] = true;
            q.push({nx, ny, cnt + 1});
        }
    }
    return -1;
}

int main() {
    FASTIO
    cin >> b.ft >> b.sd >> d.ft >> d.sd >> j.ft >> j.sd;
    x = BFS();
    y = abs(j.ft - d.ft) + abs(j.sd - d.sd);
    cout << (x == y ? "tie" : (x < y ? "bessie" : "daisy"));
}
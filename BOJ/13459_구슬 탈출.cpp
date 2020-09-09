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

struct Node {
    pii r, b;
    int cnt;

    Node(pii r, pii b, int cnt): r(r), b(b), cnt(cnt) {}
};

const int MAX = 10;
int N, M;
char arr[MAX][MAX];
bool vis[MAX][MAX][MAX][MAX];
int dx[] = {-1, 0, 1, 0};   // DOWN, LEFT, UP, RIGHT
int dy[] = {0, -1, 0, 1};
pii r, b;

int BFS() {
    queue<Node> q;
    q.push({r, b, 0});
    vis[r.ft][r.sd][b.ft][b.sd] = true;
    while (q.size()) {
        auto tmp = q.front();
        q.pop();
        pii r = tmp.r;
        pii b = tmp.b;
        int cnt = tmp.cnt;
        if (cnt > 10) {
            continue;
        }
        if (arr[r.ft][r.sd] == 'O') {
            return 1;
        }
        for (int i = 0; i < 4; i++) {
            pii nr = r;
            pii nb = b;
            bool flag = false;
            while (arr[nr.ft + dx[i]][nr.sd + dy[i]] != '#' && arr[nr.ft][nr.sd] != 'O') {
                nr = {nr.ft + dx[i], nr.sd + dy[i]};
            }
            while (arr[nb.ft + dx[i]][nb.sd + dy[i]] != '#' && arr[nb.ft][nb.sd] != 'O') {
                nb = {nb.ft + dx[i], nb.sd + dy[i]};
                if (arr[nb.ft][nb.sd] == 'O') {
                    flag = true;
                }
            }
            if (nr == nb) {
                if (i == 0) {
                    r.ft < b.ft ? nb.ft++ : nr.ft++;
                } else if (i == 1) {
                    r.sd < b.sd ? nb.sd++ : nr.sd++;
                } else if (i == 2) {
                    r.ft < b.ft ? nr.ft-- : nb.ft--;
                } else if (i == 3) {
                    r.sd < b.sd ? nr.sd-- : nb.sd--;
                }
            }
            if (flag || vis[nr.ft][nr.sd][nb.ft][nb.sd]) {
                continue;
            }
            vis[nr.ft][nr.sd][nb.ft][nb.sd] = true;
            q.push({nr, nb, cnt + 1});
        }
    }
    return 0;
}

int main() {
    FASTIO
    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> arr[i][j];
            if (arr[i][j] == 'R') {
                r = {i, j};
            } else if (arr[i][j] == 'B') {
                b = {i, j};
            }
        }
    }
    cout << BFS();
}
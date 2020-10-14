#include <bits/stdc++.h>
using namespace std;
using ll = long long;
using ull = unsigned long long;
using pii = pair<int, int>;
using pll = pair<ll, ll>;
using pli = pair<ll, int>;
using pic = pair<int, char>;
using pci = pair<char, int>;
#pragma GCC optimize("Ofast")
#pragma GCC optimize("unroll-loops")
#define FASTIO                        \
    ios_base::sync_with_stdio(false); \
    cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define rall(x) (x).rbegin(), (x).rend()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

struct Node
{
    int x, y, c;

    Node(int x, int y, int c) : x(x), y(y), c(c) {}
    Node(pii p) : x(p.ft), y(p.sd), c(0) {}
};

int M, N, P, HP;
vector<vector<char>> v;
map<char, pii> pos;
map<char, int> dps;
vector<pic> dist;
vector<vector<bool>> vis;
set<char> attacked;
pii boss;
int dx[] = {-1, 0, 1, 0};
int dy[] = {0, -1, 0, 1};

int BFS(char p)
{
    queue<Node> q;
    q.push(pos[p]);
    for (auto &i : vis)
    {
        fill(all(i), false);
    }
    vis[pos[p].ft][pos[p].sd] = true;
    while (q.size())
    {
        auto tmp = q.front();
        q.pop();
        int x = tmp.x;
        int y = tmp.y;
        int cnt = tmp.c;
        if (x == boss.ft && y == boss.sd)
            return cnt;
        for (int i = 0; i < 4; i++)
        {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= M || ny >= N || vis[nx][ny] || v[x][y] == 'X')
                continue;
            vis[nx][ny] = true;
            q.push({nx, ny, cnt + 1});
        }
    }
    return -1;
}

int main()
{
    FASTIO
    cin >> M >> N >> P;
    v.resize(M, vector<char>(N));
    vis.resize(M, vector<bool>(N));
    for (int i = 0; i < M; i++)
    {
        for (int j = 0; j < N; j++)
        {
            cin >> v[i][j];
            if ('a' <= v[i][j] && v[i][j] <= 'z')
                pos[v[i][j]] = {i, j};
            else if (v[i][j] == 'B')
                boss = {i, j};
        }
    }
    for (int i = 0; i < P; i++)
    {
        char a;
        int b;
        cin >> a >> b;
        dps[a] = b;
    }
    cin >> HP;
    for (int i = 0; i < P; i++)
    {
        dist.pb({BFS('a' + i), 'a' + i});
    }
    sort(all(dist));
    int turn = dist[0].ft;
    while (HP > 0)
    {
        for (auto &i : dist)
        {
            if (i.ft > turn)
                continue;
            HP -= dps[i.sd];
            i.ft++;
            attacked.insert(i.sd);
        }
        turn++;
    }
    cout << attacked.size();
}
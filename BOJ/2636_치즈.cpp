#include <bits/stdc++.h>
using namespace std;
using ll = long long;
using ull = unsigned long long;
using pii = pair<int, int>;
using pil = pair<int, ll>;
using pll = pair<ll, ll>;
using pli = pair<ll, int>;
using pic = pair<int, char>;
using pci = pair<char, int>;
using pss = pair<string, string>;
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

int N, M, last = 1e9, t;
vector<vector<int>> v;
vector<vector<bool>> vis, used;
set<pii> s;
int dx[] = {-1, 0, 1, 0};
int dy[] = {0, -1, 0, 1};

void melt()
{
    for (auto &i : s)
    {
        v[i.ft][i.sd] = 0;
    }
}

void BFS()
{
    queue<pii> q;
    q.push({0, 0});
    used[0][0] = true;
    while (q.size())
    {
        auto [x, y] = q.front();
        q.pop();
        for (int i = 0; i < 4; i++)
        {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx > N + 1 || ny > M + 1 || used[nx][ny])
                continue;
            used[nx][ny] = true;
            if (v[nx][ny] == 1)
            {
                s.insert({nx, ny});
                continue;
            }
            q.push({nx, ny});
        }
    }
}

int BFS(int x, int y)
{
    queue<pii> q;
    q.push({x, y});
    int ret = 0;
    vis[x][y] = true;
    while (q.size())
    {
        auto [x, y] = q.front();
        q.pop();
        ret++;
        for (int i = 0; i < 4; i++)
        {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 1 || ny < 1 || nx > N || ny > M || vis[nx][ny] || v[nx][ny] == 0)
                continue;
            vis[nx][ny] = true;
            q.push({nx, ny});
        }
    }
    return ret;
}

int main()
{
    FASTIO
    cin >> N >> M;
    v.resize(N + 2, vector<int>(M + 2));
    vis.resize(N + 2, vector<bool>(M + 2));
    used.resize(N + 2, vector<bool>(M + 2));
    for (int i = 1; i < N + 1; i++)
    {
        for (int j = 1; j < M + 1; j++)
        {
            cin >> v[i][j];
        }
    }
    while (1)
    {
        int sum = 0;
        for (auto &i : vis)
        {
            fill(all(i), false);
        }
        for (auto &i : used)
        {
            fill(all(i), false);
        }
        s.clear();
        BFS();
        for (int i = 1; i < N + 1; i++)
        {
            for (int j = 1; j < M + 1; j++)
            {
                if (v[i][j] == 1 && !vis[i][j])
                    sum += BFS(i, j);
            }
        }
        if (sum == 0)
            break;
        melt();
        last = min(last, sum);
        t++;
    }
    cout << t << endl
         << last;
}
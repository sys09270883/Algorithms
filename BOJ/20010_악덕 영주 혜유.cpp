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

struct Edge
{
    int a, b, c;
};

int N, K, minc, maxc;
vector<Edge> edges;
vector<int> par;
vector<vector<pii>> adj;

int find(int x)
{
    return par[x] = x == par[x] ? x : find(par[x]);
}

void merge(int x, int y)
{
    par[x] = y;
}

pii BFS(int s)
{
    queue<pii> q;
    q.push({s, 0});
    vector<bool> vis(N);
    vis[s] = true;
    pii ret = {s, 0};
    while (q.size())
    {
        auto tmp = q.front();
        q.pop();
        int cur = tmp.ft;
        int cd = tmp.sd;
        if (ret.sd < cd)
            ret = {cur, cd};
        for (auto &n : adj[cur])
        {
            int next = n.ft;
            int nd = n.sd;
            if (vis[next])
                continue;
            vis[next] = true;
            q.push({next, cd + nd});
        }
    }
    return ret;
}

int main()
{
    FASTIO
    cin >> N >> K;
    par.resize(N);
    adj.resize(N, vector<pii>());
    iota(all(par), 0);
    for (int i = 0; i < K; i++)
    {
        int a, b, c;
        cin >> a >> b >> c;
        edges.pb({a, b, c});
    }
    sort(all(edges), [](const Edge &e1, const Edge &e2) {
        return e1.c < e2.c;
    });
    for (auto &e : edges)
    {
        int fa = find(e.a);
        int fb = find(e.b);
        if (fa != fb)
        {
            adj[e.a].pb({e.b, e.c});
            adj[e.b].pb({e.a, e.c});
            merge(fa, fb);
            minc += e.c;
        }
    }
    pii a = BFS(0);
    pii b = BFS(a.ft);
    cout << minc << endl
         << b.sd;
}
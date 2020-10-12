#include <bits/stdc++.h>
using namespace std;
using pii = pair<int, int>;
#define endl '\n'
#define pb push_back
#define ft first
#define sd second

int N;
vector<vector<int>> adj;
vector<int> dst;

pii BFS(int start)
{
    queue<pii> q;
    q.push({start, 0});
    vector<bool> vis(N + 1, false);
    vis[start] = true;
    pii ret = {start, 0};
    while (q.size())
    {
        auto tmp = q.front();
        q.pop();
        int cur = tmp.ft;
        int cnt = tmp.sd;
        if (cnt > ret.sd)
        {
            ret = {cur, cnt};
            dst.clear();
            dst.pb(cur);
        }
        else if (cnt == ret.sd)
            dst.pb(cur);
        for (auto &next : adj[cur])
        {
            if (vis[next])
                continue;
            vis[next] = true;
            q.push({next, cnt + 1});
        }
    }
    return ret;
}

int solution(int n, vector<vector<int>> edges)
{
    N = n;
    adj.resize(N + 1, vector<int>());
    for (auto &e : edges)
    {
        int a = e[0], b = e[1];
        adj[a].pb(b);
        adj[b].pb(a);
    }
    pii x = BFS(1);
    pii y = BFS(x.ft);
    if (dst.size() >= 2)
        return y.sd;
    pii z = BFS(y.ft);
    if (dst.size() >= 2)
        return y.sd;
    else if (dst.size() == 1)
        return y.sd - 1;
    return -1;
}
#include <bits/stdc++.h>
using namespace std;
using ll = long long;
using ull = unsigned long long;
using pii = pair<int, int>;
using pll = pair<ll, ll>;
using pli = pair<ll, int>;
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

const int INF = 987654321;
int N, M, S, D;
vector<vector<pii>> adj;
vector<vector<int>> trace;
vector<int> dists;

void erase()
{
    queue<int> q;
    q.push(D);
    while (q.size())
    {
        int cur = q.front();
        q.pop();
        for (auto &prev : trace[cur])
        {
            for (auto &i : adj[prev])
            {
                if (i.ft == cur && i.sd != -1 && dists[cur] == dists[prev] + i.sd)
                {
                    q.push(prev);
                    i.sd = -1;
                }
            }
        }
    }
}

void dijkstra()
{
    fill(all(dists), INF);
    static auto cmp = [](const pii &p1, const pii &p2) {
        return p1.sd > p2.sd;
    };
    priority_queue<pii, vector<pii>, decltype(cmp)> pq(cmp);
    pq.push({S, 0});
    dists[S] = 0;
    while (pq.size())
    {
        auto tmp = pq.top();
        pq.pop();
        int cur = tmp.ft;
        int cdist = tmp.sd;
        if (cdist > dists[cur])
            continue;
        for (auto &n : adj[cur])
        {
            int next = n.ft;
            int ndist = n.sd;
            if (ndist == -1 || dists[next] < dists[cur] + ndist)
                continue;
            if (dists[next] > dists[cur] + ndist)
            {
                trace[next].clear();
                pq.push({next, dists[next] = dists[cur] + ndist});
            }
            trace[next].pb(cur);
        }
    }
}

int main()
{
    FASTIO
    while (cin >> N >> M)
    {
        if (N == 0 && M == 0)
            break;
        cin >> S >> D;
        adj.clear();
        adj.resize(N, vector<pii>());
        trace.clear();
        trace.resize(N, vector<int>());
        dists.resize(N);
        for (int i = 0; i < M; i++)
        {
            int a, b, c;
            cin >> a >> b >> c;
            adj[a].pb({b, c});
        }
        dijkstra();
        erase();
        dijkstra();
        cout << (dists[D] == INF ? -1 : dists[D]) << endl;
    }
}

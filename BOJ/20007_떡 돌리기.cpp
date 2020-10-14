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

const ull INF = 1e12;
int N, M, X, Y;
vector<vector<pil>> adj;
vector<ll> dists;

void dijkstra()
{
    fill(all(dists), INF);
    static auto cmp = [](const pii &p1, const pii &p2) {
        return p1.sd > p2.sd;
    };
    priority_queue<pii, vector<pii>, decltype(cmp)> pq(cmp);
    pq.push({Y, dists[Y] = 0});
    while (pq.size())
    {
        auto tmp = pq.top();
        pq.pop();
        int cur = tmp.ft;
        int cd = tmp.sd;
        if (cd > dists[cur])
            continue;
        for (auto &n : adj[cur])
        {
            int next = n.ft;
            int nd = n.sd;
            if (dists[next] > dists[cur] + nd)
            {
                dists[next] = dists[cur] + nd;
                pq.push({next, dists[next]});
            }
        }
    }
}

int main()
{
    FASTIO
    cin >> N >> M >> X >> Y;
    adj.resize(N, vector<pil>());
    dists.resize(N);
    for (int i = 0; i < M; i++)
    {
        int a, b, c;
        cin >> a >> b >> c;
        adj[a].pb({b, c});
        adj[b].pb({a, c});
    }
    dijkstra();
    for (int i = 0; i < N; i++)
    {
        if (dists[i] * 2 > X)
            return !(cout << -1);
    }
    sort(all(dists));
    int day = 0, idx = 0, tmp = 0;
    while (idx < N)
    {
        while (idx < N && tmp + dists[idx] * 2 <= X)
        {
            tmp += dists[idx++] * 2;
        }
        tmp = 0;
        day++;
    }
    cout << day;
}
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
#define FASTIO ios_base::sync_with_stdio(false), cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define rall(x) (x).rbegin(), (x).rend()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

const int INF = 1e9;
int N, M;
vector<vector<pii>> adj;

int dijkstra()
{
    auto cmp = [](const pii &p1, const pii &p2) {
        return p1.sd > p2.sd;
    };
    priority_queue<pii, vector<pii>, decltype(cmp)> pq(cmp);
    vector<int> dists(N + 1, INF);
    pq.push({1, dists[1] = 0});
    while (pq.size())
    {
        auto [cur, cd] = pq.top();
        pq.pop();
        if (dists[cur] > cd)
            continue;
        for (auto [next, nd] : adj[cur])
        {
            if (dists[next] > dists[cur] + nd)
                pq.push({next, dists[next] = dists[cur] + nd});
        }
    }
    return dists[N];
}

int main()
{
    FASTIO
    cin >> N >> M;
    adj.resize(N + 1, vector<pii>());
    for (int i = 0; i < M; i++)
    {
        int a, b, c;
        cin >> a >> b >> c;
        adj[a].pb({b, c});
        adj[b].pb({a, c});
    }
    cout << dijkstra() << endl;
}

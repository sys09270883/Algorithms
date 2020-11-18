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
vector<int> dists, trace, res;

void dijkstra()
{
    static auto cmp = [](const pii &n1, const pii &n2) {
        return n1.sd > n2.sd;
    };
    priority_queue<pii, vector<pii>, decltype(cmp)> pq(cmp);
    dists.assign(N + 1, -INF);
    trace.resize(N + 1);
    pq.push({1, dists[1] = 0});
    while (pq.size())
    {
        auto [cur, cd] = pq.top();
        pq.pop();
        if (dists[cur] > cd)
            continue;
        for (auto [next, nd] : adj[cur])
        {
            if (dists[next] < dists[cur] + nd)
            {
                dists[next] = dists[cur] + nd;
                trace[next] = cur;
                if (next > 1)
                    pq.push({next, dists[next]});
            }
        }
    }
}

int main()
{
    FASTIO
    cin >> N >> M;
    adj.resize(N + 1, vector<pii>(N + 1));
    for (int i = 0; i < M; i++)
    {
        int p, q, r;
        cin >> p >> q >> r;
        adj[p].pb({q, r});
    }
    dijkstra();
    cout << dists[1] << endl;
    res.pb(1);
    int tmp = trace[1];
    while (tmp != 1)
    {
        res.pb(tmp);
        tmp = trace[tmp];
    }
    res.pb(1);
    reverse(all(res));
    for (auto &i : res)
    {
        cout << i << ' ';
    }
}

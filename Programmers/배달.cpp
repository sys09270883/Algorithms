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

int n, k, res;
const int INF = 1e9;
vector<vector<pii>> adj;
vector<int> dists;

void dijkstra()
{
    static auto cmp = [](const pii &p1, const pii &p2) {
        return p1.sd < p2.sd;
    };
    priority_queue<pii, vector<pii>, decltype(cmp)> pq(cmp);
    pq.push({1, dists[1] = 0});
    while (pq.size())
    {
        auto [cur, cd] = pq.top();
        pq.pop();
        if (dists[cur] > cd)
            continue;
        for (auto n : adj[cur])
        {
            int next = n.ft;
            int nd = n.sd;
            if (dists[next] > dists[cur] + nd)
                pq.push({next, dists[next] = dists[cur] + nd});
        }
    }
}

int solution(int N, vector<vector<int>> road, int K)
{
    n = N;
    k = K;
    adj.resize(N + 1, vector<pii>());
    dists.resize(N + 1, INF);
    for (auto i : road)
    {
        int a = i[0], b = i[1], c = i[2];
        adj[a].pb({b, c});
        adj[b].pb({a, c});
    }
    dijkstra();
    for (int i = 1; i <= N; i++)
    {
        if (dists[i] <= K)
            res++;
    }
    return res;
}
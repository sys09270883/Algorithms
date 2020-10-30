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

int solution(int n, vector<int> stations, int w)
{
    int x = stations[0];
    int res = 0, prev = 1;
    pii y = {x - w, x + w};
    vector<pii> z = {y};
    for (int i = 1; i < stations.size(); i++)
    {
        if (z.back().sd >= stations[i] - w)
            z.back().sd = stations[i] + w;
        else
            z.pb({stations[i] - w, stations[i] + w});
    }
    z.pb({n + 1, -1});
    for (auto i : z)
    {
        res += ceil(1.0 * (i.ft - prev) / (w << 1 | 1));
        prev = i.sd + 1;
    }
    return res;
}
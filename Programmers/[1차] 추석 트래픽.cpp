#include <bits/stdc++.h>
using namespace std;
using ll = long long;
using ull = unsigned long long;
using pii = pair<int, int>;
using pll = pair<ll, ll>;
using pli = pair<ll, int>;
#pragma GCC optimize("Ofast")
#pragma GCC optimize("unroll-loops")
#define endl '\n'
#define pb push_back
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define rall(x) (x).rbegin(), (x).rend()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

int cnt, res;

int hms_to_ms(string s)
{
    static const ull H = 36e5;
    static const ull M = 6e4;
    static const ull S = 1e3;
    int ret = 0;
    ret += stoll(s.substr(0, 2)) * H;
    ret += stoll(s.substr(3, 5)) * M;
    ret += stoll(s.substr(6, 8)) * S;
    ret += stoll(s.substr(9, s.size()));
    return ret;
}

int s_to_ms(string s)
{
    auto val = stod(s.substr(0, s.size() - 1));
    return val * 1000;
}

int solution(vector<string> lines)
{
    vector<pii> events;
    for (auto line : lines)
    {
        stringstream ss(line);
        string tmp;
        vector<string> v;
        while (ss >> tmp)
        {
            v.pb(tmp);
        }
        auto cur = hms_to_ms(v[1]);
        auto offset = s_to_ms(v[2]);
        events.pb({cur - offset + 1, 1});
        events.pb({cur + 999, -1});
    }
    sort(all(events), [](const pii &p1, const pii &p2) {
        if (p1.ft == p2.ft)
            return p1.sd > p2.sd;
        return p1.ft < p2.ft;
    });
    for (auto e : events)
    {
        cnt += e.sd;
        res = max(res, cnt);
    }
    return res;
}
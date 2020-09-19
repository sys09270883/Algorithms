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

struct Building
{
    int l, h, r;
};

int N;
vector<Building> v;
map<pii, int> mp;

void merge(vector<pii> &s, vector<pii> &ls, vector<pii> &rs)
{
    int i = 0, j = 0, h1 = 0, h2 = 0, h = 0;
    while (i < ls.size() && j < rs.size())
    {
        if (ls[i].ft < rs[j].ft)
        {
            h1 = ls[i].sd;
            if (h != max(h1, h2))
            {
                h = max(h1, h2);
                s.pb({ls[i].ft, h});
            }
            i++;
        }
        else
        {
            h2 = rs[j].sd;
            if (h != max(h1, h2))
            {
                h = max(h1, h2);
                s.pb({rs[j].ft, h});
            }
            j++;
        }
    }
    while (i < ls.size())
    {
        if (ls[i].sd != h)
        {
            h = ls[i].sd;
            s.pb({ls[i].ft, h});
        }
        i++;
    }
    while (j < rs.size())
    {
        if (rs[j].sd != h)
        {
            h = rs[j].sd;
            s.pb({rs[j].ft, h});
        }
        j++;
    }
}

void getSkyline(int l, int r, vector<pii> &s)
{
    if (l > r)
        return;
    else if (l == r)
    {
        s.pb({v[l].l, v[l].h});
        s.pb({v[l].r, 0});
        return;
    }
    int m = l + r >> 1;
    vector<pii> ls, rs;
    getSkyline(l, m, ls);
    getSkyline(m + 1, r, rs);
    merge(s, ls, rs);
}

int main()
{
    FASTIO
    cin >> N;
    for (int i = 0; i < N; i++)
    {
        int a, b, c;
        cin >> a >> b >> c;
        mp[{a, c}] = mp.find({a, c}) == mp.end() ? b : max(mp[{a, c}], b);
    }
    for (auto &i : mp)
    {
        v.pb({i.ft.ft, i.sd, i.ft.sd});
    }
    vector<pii> s;
    getSkyline(0, N - 1, s);
    for (auto &i : s)
    {
        cout << i.ft << ' ' << i.sd << ' ';
    }
}

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

vector<vector<int>> v;
int K;

void clk(vector<int> &vv)
{
    int a = vv.back();
    vv.pop_back();
    vv.insert(vv.begin(), a);
}

void c_clk(vector<int> &vv)
{
    int a = vv.front();
    vv.erase(vv.begin());
    vv.pb(a);
}

void prop(int idx, int dir, int p_dir)
{
    if (idx < 0 || idx > 3)
    {
        return;
    }

    if (p_dir == -1)
    {
        if (idx > 0 && v[idx][6] != v[idx - 1][2])
        {
            prop(idx - 1, dir * -1, -1);
        }
    }
    else
    {
        if (idx < 3 && v[idx][2] != v[idx + 1][6])
        {
            prop(idx + 1, dir * -1, 1);
        }
    }

    dir == 1 ? clk(v[idx]) : c_clk(v[idx]);
}

void rotate(int idx, int dir)
{
    if (idx > 0 && v[idx][6] != v[idx - 1][2])
    {
        prop(idx - 1, dir * -1, -1);
    }
    if (idx < 3 && v[idx][2] != v[idx + 1][6])
    {
        prop(idx + 1, dir * -1, 1);
    }

    dir == 1 ? clk(v[idx]) : c_clk(v[idx]);
}

int main()
{
    FASTIO
    v.resize(4, vector<int>());
    for (int i = 0; i < 4; i++)
    {
        string s;
        cin >> s;
        for (auto &j : s)
        {
            v[i].pb(j - '0');
        }
    }
    cin >> K;
    for (int i = 0; i < K; i++)
    {
        int a, b;
        cin >> a >> b;
        rotate(a - 1, b);
    }

    int res = 0;
    for (int i = 0; i < 4; i++)
    {
        res += v[i][0] * pow(2, i);
    }
    cout << res << endl;
}

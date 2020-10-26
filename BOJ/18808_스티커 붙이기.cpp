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

int N, M, K, res, cur;
vector<vector<vector<int>>> s;
vector<vector<int>> mp;

bool attach(int x, int y)
{
    int w = s[cur].size();
    int h = s[cur][0].size();
    if (x + w - 1 >= N || y + h - 1 >= M)
        return false;
    for (int i = 0; i < w; i++)
    {
        for (int j = 0; j < h; j++)
        {
            if (s[cur][i][j] && mp[x + i][y + j])
                return false;
        }
    }
    for (int i = 0; i < w; i++)
    {
        for (int j = 0; j < h; j++)
        {
            mp[x + i][y + j] |= s[cur][i][j];
        }
    }
    return true;
}

void rotate()
{
    vector<vector<int>> tmp(s[cur][0].size(), vector<int>(s[cur].size()));
    for (int i = 0; i < tmp.size(); i++)
    {
        for (int j = 0; j < tmp[0].size(); j++)
        {
            tmp[i][j] = s[cur][tmp[0].size() - 1 - j][i];
        }
    }
    s[cur] = tmp;
}

int main()
{
    FASTIO
    cin >> N >> M >> K;
    mp.resize(N, vector<int>(M));
    for (int i = 0; i < K; i++)
    {
        int R, C;
        cin >> R >> C;
        vector<vector<int>> v(R, vector<int>(C));
        for (auto &i : v)
        {
            for (auto &j : i)
            {
                cin >> j;
            }
        }
        s.pb(v);
    }
LOOP:
    for (; cur < s.size(); cur++)
    {
        for (int k = 0; k < 4; k++)
        {
            for (int i = 0; i < N; i++)
            {
                for (int j = 0; j < M; j++)
                {
                    for (int k = 0; k < 4; k++)
                    {
                        if (attach(i, j))
                        {
                            cur++;
                            goto LOOP;
                        }
                    }
                }
            }
            rotate();
        }
    }
    for (auto &i : mp)
    {
        res += accumulate(all(i), 0);
    }
    cout << res << endl;
}

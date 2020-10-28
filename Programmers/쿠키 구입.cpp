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

int res, sz;

int solution(vector<int> cookie)
{
    sz = cookie.size();
    for (int i = 0; i < sz - 1; i++)
    {
        int l = i;
        int r = i + 1;
        int lsum = cookie[i];
        int rsum = cookie[r];
        if (lsum == rsum)
            res = max(res, lsum);
        bool f = true;
        while (f)
        {
            if (l && lsum <= rsum)
                lsum += cookie[--l];
            else if (r < sz - 1 && lsum >= rsum)
                rsum += cookie[++r];
            else
                f = false;
            if (lsum == rsum)
                res = max(res, lsum);
        }
    }
    return res;
}
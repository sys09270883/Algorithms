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

string get_max(string s)
{
    int cnt = 0;
    string result = "";
    for (int i = 0; i < s.size(); i++)
    {
        if (s[i] == 'M')
        {
            cnt++;
        }
        else
        {
            result += "5";
            for (int j = 0; j < cnt; j++)
            {
                result += "0";
            }
            cnt = 0;
        }
    }

    if (cnt > 0)
    {
        for (int i = 0; i < cnt; i++)
        {
            result += "1";
        }
    }

    return result;
}

string get_min(string s)
{
    string result = "";
    int cnt = 0;
    for (int i = 0; i < s.size(); i++)
    {
        if (s[i] == 'K')
        {
            if (cnt > 0)
            {
                result += "1";
                if (cnt - 1 > 0)
                {
                    for (int j = 0; j < cnt - 1; j++)
                    {
                        result += "0";
                    }
                }
                cnt = 0;
            }
            result += "5";
        }
        else
        {
            cnt++;
        }
    }

    if (cnt > 0)
    {
        result += "1";
        if (cnt - 1 > 0)
        {
            for (int j = 0; j < cnt - 1; j++)
            {
                result += "0";
            }
        }
    }

    return result;
}

int main()
{
    FASTIO
    string s;
    cin >> s;
    cout << get_max(s) << endl;
    cout << get_min(s) << endl;
}

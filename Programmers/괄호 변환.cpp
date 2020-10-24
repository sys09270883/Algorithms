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

bool is_balanced(string &s)
{
    int l = 0, r = 0;
    for (int i = 0; i < s.size(); i++)
    {
        s[i] == '(' ? l++ : r++;
    }
    return l == r;
}

bool is_right(string &s)
{
    stack<char> st;
    for (int i = 0; i < s.size(); i++)
    {
        if (s[i] == '(')
        {
            st.push(s[i]);
        }
        else
        {
            if (st.empty() || st.top() == ')')
                return false;
            st.pop();
        }
    }
    return true;
}

string func(string &s)
{
    if (s.empty())
        return "";
    string l, r;
    int a = 0, b = 2;
    while (1)
    {
        l = s.substr(a, b);
        if (is_balanced(l))
            break;
        b += 2;
    }
    r = s.substr(b, s.size());
    string ret = "";
    if (is_right(l))
        ret = l + func(r);
    else
    {
        ret += "(";
        ret += func(r);
        ret += ')';
        l = l.substr(1, s.size());
        l.pop_back();
        for (auto &i : l)
        {
            i = i == '(' ? ')' : '(';
        }
        ret += l;
    }
    return ret;
}

string solution(string p)
{
    return func(p);
}
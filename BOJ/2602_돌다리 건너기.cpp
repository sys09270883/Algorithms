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

string s, s1, s2;
ll dp[21][101][2];

int main()
{
    FASTIO
    cin >> s >> s1 >> s2;
    for (int i = 0; i < s1.size(); i++)
    {
        dp[0][i][0] = 1;
        dp[0][i][1] = 1;
    }
    for (int i = 1; i <= s.size(); i++)
    {
        for (int j = 1; j <= s1.size(); j++)
        {
            if (s[i - 1] == s1[j - 1])
                dp[i][j][0] += dp[i - 1][j - 1][1];
            if (s[i - 1] == s2[j - 1])
                dp[i][j][1] += dp[i - 1][j - 1][0];
            dp[i][j][0] += dp[i][j - 1][0];
            dp[i][j][1] += dp[i][j - 1][1];
        }
    }
    cout << dp[s.size()][s1.size()][0] + dp[s.size()][s1.size()][1];
}
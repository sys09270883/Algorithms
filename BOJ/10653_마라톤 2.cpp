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

const int INF = 1e9;
int N, K;
vector<pii> v;
int dp[501][501];

int get_dist(pii &p1, pii &p2)
{
    return abs(p1.ft - p2.ft) + abs(p1.sd - p2.sd);
}

int func(int cur, int cnt)
{
    if (cur == N - 1)
        return 0;
    int &ret = dp[cur][cnt];
    if (ret != -1)
        return ret;
    ret = INF;
    for (int i = 0; i <= cnt; i++)
    {
        int next = cur + 1 + i;
        if (next >= N)
            break;
        ret = min(ret, func(next, cnt - i) + get_dist(v[cur], v[next]));
    }
    return ret;
}

int main()
{
    FASTIO
    cin >> N >> K;
    memset(dp, -1, sizeof(dp));
    for (int i = 0; i < N; i++)
    {
        int a, b;
        cin >> a >> b;
        v.pb({a, b});
    }
    cout << func(0, K);
}

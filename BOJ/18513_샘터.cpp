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
using tiii = tuple<int, int, int>;
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

const int MAX = 1e8;
int N, K;
vector<int> s;
map<int, int> vis;

ll BFS()
{
    ll ret = 0, cnt = 0;
    queue<tiii> q;
    for (auto &i : s)
    {
        vis[i] = 0;
        q.push({i, -1, 0});
        q.push({i, 1, 0});
    }
    while (!q.empty() && cnt < K)
    {
        auto [cur, dir, diff] = q.front();
        q.pop();
        int next = cur + dir;
        diff++;
        if (vis.find(next) == vis.end())
        {
            vis[next] = diff;
            cnt++;
            q.push({next, dir, diff});
        }
        else
        {
            if (vis[next] <= diff)
                continue;
            vis[next] = diff;
            cnt++;
            q.push({next, dir, diff});
        }
        ret += diff;
    }
    return ret;
}

int main()
{
    FASTIO
    cin >> N >> K;
    s.resize(N);
    for (int i = 0; i < N; i++)
    {
        cin >> s[i];
    }
    cout << BFS();
}
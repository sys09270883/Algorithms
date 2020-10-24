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

int N, S, o;
vector<vector<int>> adj;
vector<int> l, r;

void DFS(int cur, int prev = -1)
{
    l[cur] = ++o;
    for (auto &next : adj[cur])
    {
        if (next != prev)
            DFS(next, cur);
    }
    r[cur] = ++o;
}

int main()
{
    FASTIO
    cin >> N;
    adj.resize(N + 1, vector<int>());
    l.resize(N << 1 | 1);
    r.resize(N << 1 | 1);
    for (int i = 0; i < N; i++)
    {
        int a, b;
        cin >> a;
        while (cin >> b)
        {
            if (b == -1)
                break;
            adj[a].pb(b);
        }
    }
    for (auto &i : adj)
    {
        sort(all(i));
    }
    cin >> S;
    DFS(S);
    for (int i = 1; i < N + 1; i++)
    {
        cout << i << ' ' << l[i] << ' ' << r[i] << endl;
    }
}

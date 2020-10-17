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

ll N, K, Q;

int main()
{
    FASTIO
    cin >> N >> K >> Q;
    for (int i = 0; i < Q; i++)
    {
        ll x, y, cnt = 0;
        cin >> x >> y;
        if (K == 1)
            cnt = abs(x - y);
        else
        {
            while (x != y)
            {
                auto &z = x > y ? x : y;
                z = (z - 2) / K + 1;
                cnt++;
            }
        }
        cout << cnt << endl;
    }
}
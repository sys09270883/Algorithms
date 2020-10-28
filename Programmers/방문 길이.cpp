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

const int SZ = 11;
bool vis[SZ][SZ];
set<int> edges;
int dx[] = {-1, 0, 1, 0};
int dy[] = {0, -1, 0, 1};
map<char, int> c2i = {{'U', 0}, {'L', 1}, {'D', 2}, {'R', 3}};

int solution(string dirs)
{
    int answer = 0;
    int x = 5, y = 5;
    for (auto c : dirs)
    {
        int nx = x + dx[c2i[c]];
        int ny = y + dy[c2i[c]];
        if (nx < 0 || ny < 0 || nx >= 11 || ny >= 11)
            continue;
        int minx = min(x, nx);
        int maxx = max(x, nx);
        int miny = min(y, ny);
        int maxy = max(y, ny);
        x = nx;
        y = ny;
        edges.insert(minx + maxx * 100 + miny * 10000 + maxy * 1000000);
    }
    return edges.size();
}
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;
typedef pair<ll, int> pli;
#pragma GCC optimize("Ofast")
#pragma GCC optimize("unroll-loops")
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define rall(x) (x).rbegin(), (x).rend()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

struct Edge {
    int a, b, c;

    Edge(int a, int b, int c): a(a), b(b), c(c) {}
};

int m, n, x, y, z, tot, sum;
vector<Edge> edges;
vector<int> par;

int find(int a) {
    return par[a] = a == par[a] ? a : find(par[a]);
}

void merge(int a, int b) {
    par[a] = b;
}

int main() {
    FASTIO
    while (1) {
        cin >> m >> n;
        if (m + n == 0) {
            break;
        }
        edges.clear();
        par.resize(m);
        for (int i = 0; i < m; i++) {
            par[i] = i;
        }
        tot = 0;
        sum = 0;
        for (int i = 0; i < n; i++) {
            cin >> x >> y >> z;
            edges.pb({x, y, z});
            tot += z;
        }
        sort(all(edges), [](const Edge &e1, const Edge &e2) {
            return e1.c < e2.c;
        });
        for (auto& e : edges) {
            int fa = find(e.a);
            int fb = find(e.b);
            if (fa != fb) {
                merge(fa, fb);
                sum += e.c;
            }
        }
        cout << tot - sum << endl;
    }
}
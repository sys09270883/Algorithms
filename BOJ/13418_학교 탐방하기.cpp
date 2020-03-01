#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
typedef long long ll;
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>

struct Edge {
    int v1, v2, c;
    Edge(int v1, int v2, int c) : v1(v1), v2(v2), c(c) {}
    bool operator<(const Edge& e) const{
        return this->c < e.c;
    }
};

int N, M, minc, maxc;
vector<int> par;
vector<Edge> edges;

void init() {
    for (int i = 0; i < N + 1; i++) {
        par[i] = i;
    }
}

int find(int x) {
    if (x == par[x])
        return x;
    return par[x] = find(par[x]);
}

void merge(int x, int y) {
    x = find(x);
    y = find(y);
    if (x != y)
        par[x] = y;
}

int main() {
    FASTIO
    cin >> N >> M;
    par.resize(N + 1);
    init();
    for (int i = 0; i < M + 1; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        edges.pb({a, b, c ^ 1});
    }
    sort(edges.begin(), edges.end());
    int sz = edges.size();
    for (int i = 0; i < sz; i++) {
        auto& e = edges[i];
        if (find(e.v1) != find(e.v2)) {
            merge(e.v1, e.v2);
            minc += e.c;
        }
    }
    init();
    for (int i = sz - 1; i >= 0; i--) {
        auto& e = edges[i];
        if (find(e.v1) != find(e.v2)) {
            merge(e.v1, e.v2);
            maxc += e.c;
        }
    }
    cout << maxc * maxc - minc * minc;
}
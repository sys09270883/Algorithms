#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());
#define cprcmp(x, y) sort(all(x), (y)), (x).erase(unique(all(x)), (x).end());
#define Matrix vector<vector<int>>
#define Matrixll vector<vector<ll>>

struct Edge {
    pii a, b;
    int dist;

    Edge(pii a, pii b, int dist): a(a), b(b), dist(dist) {}

    bool operator<(const Edge& e) const {
        return this->dist < e.dist;
    }
};

int N, C, res, cnt;
map<pii, int> p2i;
vector<int> par;
vector<pii> input;
vector<Edge> edges;

int find(int x) {
    if (x == par[x])
        return x;
    return par[x] = find(par[x]);
}

void merge(int x, int y) {
    if (x != y)
        par[x] = y;
}

int distance(pii a, pii b) {
    return (a.first - b.first) * (a.first - b.first) + (a.second - b.second) * (a.second - b.second);
}

int main() {
    FASTIO
    cin >> N >> C;
    par.resize(N + 1);
    for (int i = 1; i < N + 1; i++) {
        par[i] = i;
    }
    for (int i = 1; i < N + 1; i++) {
        int a, b;
        cin >> a >> b;
        input.pb({a, b});
        p2i[{a, b}] = i;
    }
    for (int i = 0; i < N; i++) {
        for (int j = i + 1; j < N; j++) {
            edges.pb({input[i], input[j], distance(input[i], input[j])});
        }
    }
    sort(all(edges));
    for (auto& e : edges) {
        if (e.dist < C)
            continue;
        int fa = find(p2i[e.a]), fb = find(p2i[e.b]);
        if (fa == fb)
            continue;
        merge(fa, fb);
        res += e.dist;
        cnt++;
    }
    if (cnt != N - 1)
        return !(cout << -1);
    cout << res;
}
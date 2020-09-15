#include <bits/stdc++.h>
using namespace std;
using ll = long long;
using ull = unsigned long long;
using pii = pair<int, int>;
using pll = pair<ll, ll>;
using pli = pair<ll, int>;
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

const int INF = 987654321;

struct Node {
    int ls, rs, sum, maxs;

    Node(int ls = -INF, int rs = -INF, int sum = 0, int maxs = -INF): ls(ls), rs(rs), sum(sum), maxs(maxs) {}
};

int N, M;
vector<Node> tree;

Node merge(const Node &n1, const Node &n2) {
    Node ret;
    ret.ls = max({n1.ls, n1.sum + n2.ls});
    ret.rs = max({n2.rs, n2.sum + n1.rs});
    ret.sum = n1.sum + n2.sum;
    ret.maxs = max({n1.maxs, n2.maxs, n1.rs + n2.ls});
    return ret;
}

Node update(int i, int v, int n, int s, int e) {
    if (i > e || i < s)
        return tree[n];
    if (s == e)
        return tree[n] = Node(v, v, v, v);
    int m = s + e >> 1;
    return tree[n] = merge(update(i, v, n << 1, s, m), update(i, v, n << 1 | 1, m + 1, e));
}

Node query(int l, int r, int n, int s, int e) {
    if (l > e || r < s)
        return Node();
    if (l <= s && e <= r)
        return tree[n];
    int m = s + e >> 1;
    return merge(query(l, r, n << 1, s, m), query(l, r, n << 1 | 1, m + 1, e));
}

int main() {
    FASTIO
    cin >> N;
    tree.resize(1 << (int)ceil(log2(N)) + 1);
    for (int i = 1; i < N + 1; i++) {
        int a;
        cin >> a;
        update(i, a, 1, 1, N);
    }
    cin >> M;
    for (int i = 0; i < M; i++) {
        int a, b;
        cin >> a >> b;
        cout << query(a, b, 1, 1, N).maxs << endl;
    }
}

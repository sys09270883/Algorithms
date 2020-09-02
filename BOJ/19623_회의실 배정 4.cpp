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

struct Room {
    int s, e, c;
    Room(): s(0), e(0), c(0) {}
    Room(int s, int e, int c): s(s), e(e), c(c) {}
};

int N, res;
vector<Room> room;
vector<int> idx, tree;

auto query(int i, int n, int s, int e) -> int {
    if (i > e || i < s) {
        return 0;
    }
    if (s == e) {
        return tree[n];
    }
    int m = s + e >> 1;
    return max(tree[n], max(query(i, n << 1, s, m), query(i, n << 1 | 1, m + 1, e)));
}

auto update(int i, int v, int n, int s, int e) {
    if (i <= s) {
        tree[n] = max(tree[n], v);
        return;
    }
    if (i > e) {
        return;
    }
    int m = s + e >> 1;
    update(i, v, n << 1, s, m);
    update(i, v, n << 1 | 1, m + 1, e);
}

auto main() -> int {
    FASTIO
    cin >> N;
    room.resize(N);
    for (auto& i : room) {
        cin >> i.s >> i.e >> i.c;
        idx.pb(i.s), idx.pb(i.e);
    }
    sort(all(room), [](const Room &r1, const Room &r2) {
        if (r1.e == r2.e) {
            return r1.s < r2.s;
        }
        return r1.e < r2.e;
    });
    cpr(idx)
    tree.resize(1 << (int)ceil(log2(idx.size())) + 1);
    for (int i = 0; i < N; i++) {
        int sum = query(lower_bound(all(idx), room[i].s) - idx.begin(), 1, 0, idx.size() - 1);
        res = max(res, sum + room[i].c);
        update(lower_bound(all(idx), room[i].e) - idx.begin(), sum + room[i].c, 1, 0, idx.size() - 1);
    }
    cout << res;
}
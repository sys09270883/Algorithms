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
vector<int> dp;

auto main() -> int {
    FASTIO
    cin >> N;
    room.resize(N);
    dp.resize(N);
    for (auto& i : room) {
        cin >> i.s >> i.e >> i.c;
    }
    dp[0] = room[0].c;
    res = dp[0];
    if (N > 1) {
        dp[1] = room[1].c;
        res = max(res, dp[1]);
    }
    if (N > 2) {
        dp[2] = room[2].c + dp[0];
        res = max(res, dp[2]);
    }
    for (int i = 3; i < N; i++) {
        dp[i] = room[i].c + max(dp[i - 2], dp[i - 3]);
        res = max(res, dp[i]);
    }
    cout << res;
}
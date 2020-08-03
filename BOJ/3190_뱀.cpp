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

vector<vector<int>> board;
int N, K, L, dir = 3, t, x = 1, y = 1, res, X;
int dx[] = {-1, 0, 1, 0};
int dy[] = {0, -1, 0, 1};
char C;
deque<pii> snake;

void rotate() {
    switch (C) {
    case 'L':
        dir++, dir %= 4; 
        break;
    case 'D':
        dir += 3, dir %= 4;
        break;
    }
}

bool contains(pii &p) {
    for (auto& i : snake) {
        if (i == p) {
            return true;
        }
    }
    return false;
}

int main() {
    FASTIO
    cin >> N >> K;
    board.resize(N + 1, vector<int>(N + 1));
    snake.pb({1, 1});
    for (int i = 0; i < K; i++) {
        int a, b;
        cin >> a >> b;
        board[a][b] = 1;
    }
    cin >> L;
    for (int i = 0; i < L; i++) {
        cin >> X >> C;
        while (t < X) {
            x += dx[dir];
            y += dy[dir];
            res++;
            pii p = {x, y};
            if (x < 1 || y < 1 || x > N || y > N || contains(p)) {
                return !(cout << res);
            }
            snake.pb({x, y});
            if (board[x][y] == 1) {
                board[x][y] = 0;
            }
            else {
                snake.pop_front();
            }
            t++;
        }
        rotate();
    }
    while (1) {
        x += dx[dir];
        y += dy[dir];
        res++;
        pii p = {x, y};
        if (x < 1 || y < 1 || x > N || y > N || contains(p)) {
            return !(cout << res);
        }
    }
}

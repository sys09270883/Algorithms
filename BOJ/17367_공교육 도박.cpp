#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

int N;
double dp[7][7][7][1001];

int calc(int a, int b, int c) {
    if (a == b && b == c)
        return 10000 + a * 1000;
    else if (a == b || b == c || c == a)
        return 1000 + (a == b ? a : c) * 100;
    return max(max(a, b), c) * 100;
}

double func(int a, int b, int c, int cnt) {
    double cur = calc(a, b, c);
    if (cnt == 0)
        return cur;
    double& ret = dp[a][b][c][cnt];
    if (ret > 0)
        return ret;
    double next = 0;
    for (int i = 1; i < 7; i++) {
        next += func(b, c, i, cnt - 1);
    }
    return ret = max(cur, next / 6);
}

int main() {
    FASTIO
    cin >> N;
    double sum = 0;
    memset(dp, -1, sizeof(dp));
    for (int i = 1; i < 7; i++) {
        for (int j = 1; j < 7; j++) {
            for (int k = 1; k < 7; k++) {
                sum += func(i, j, k, N - 3);
            }
        }
    }
    cout.precision(6);
    cout << fixed << sum / 216;
}
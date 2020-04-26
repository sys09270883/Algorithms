#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef unsigned long long ull;
typedef vector<vector<ull>> Matrix;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define pli pair<ll, int>
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

const int MOD = 1e9 + 7;
ull n, m;
Matrix DEFAULT = {{1, 1}, {1, 0}};

Matrix operator*(Matrix &a, Matrix &b) {
    Matrix ret = {{0, 0}, {0, 0}};
    for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 2; k++) {
                ret[i][j] += (a[i][k] * b[k][j]) % MOD;
                ret[i][j] %= MOD;
            }
        }
    }
    return ret;
}

Matrix matPow(Matrix a, ull b) {
    Matrix ret = {{1, 0}, {0, 1}};
    while (b) {
        if (b & 1) 
            ret = ret * a;
        b >>= 1, a = a * a;
    }
    return ret;
}

int main() {
    FASTIO
    cin >> n >> m;
    cout << matPow(DEFAULT, __gcd(n, m))[1][0] % MOD;
}
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

const int MOD = 1e3;
const Matrix DEFAULT = {{3, 5}, {1, 3}};
int T, N;

Matrix matMul(Matrix a, Matrix b) {
    Matrix ret = {{0, 0}, {0, 0}};
    for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 2; k++) {
                ret[i][j] += (a[i][k] * b[k][j]) % MOD;
            }
        }
    }
    return ret;
}

Matrix matPow(Matrix a, int b) {
    Matrix ret = {{1, 0}, {0, 1}};
    while (b) {
        if (b & 1)
            ret = matMul(ret, a);
        a = matMul(a, a), b >>= 1;
    }
    return ret;
}

int main() {
    FASTIO
    cout.fill('0');
    cin >> T;
    for (int i = 1; i < T + 1; i++) {
        cin >> N;
        cout << "Case #" << i << ": ";
        Matrix res = matPow(DEFAULT, N);
        cout << setw(3) << (res[0][0] * 2 + 999) % MOD << endl;
    }
}
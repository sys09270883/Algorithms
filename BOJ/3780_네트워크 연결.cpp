#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

const int MOD = 1e3;
int T, N;
vector<int> par, dist;

void resize() {
    par.clear();
    dist.clear();
    dist.resize(N + 1);
    par.resize(N + 1);
    for (int i = 1; i < N + 1; i++) {
        par[i] = i;
        dist[i] = 0;
    }
}

int find(int x) {
    if (x == par[x])
        return x;
    int p = find(par[x]);
    dist[x] += dist[par[x]];
    return par[x] = p;
}

void merge(int x, int y) {
    dist[x] = abs(x - y) % MOD;
    par[x] = y;
}

int main() {
    FASTIO
    cin >> T;
    while (T--) {
        cin >> N;
        resize();
        char q;
        int a, b;
        while (true) {
            cin >> q;
            if (q == 'E') {
                cin >> a;
                find(a);
                cout << dist[a] << endl;
            }
            else if (q == 'I') {
                cin >> a >> b;
                merge(a, b);
            }
            else if (q == 'O')
                break;
        }
    }
}
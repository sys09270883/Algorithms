#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
typedef long long ll;
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>

int main() {
    FASTIO
    int T;
    cin >> T;
    while (T--) {
        vector<int> v;
        int N, M, K;
        cin >> N >> M >> K;
        for (int i = 0; i < N; i++) {
            int a;
            cin >> a;
            v.pb(a);
        }
        for (int i = 0; i < M - 1; i++) {
            v.pb(v[i]);
        }
        ll sum = 0;
        int cnt = 0;
        for (int i = 0; i < M; i++) {
            sum += v[i];
        }
        if (N == M) {
            cout << (sum < K ? 1 : 0) << endl;
            continue;
        }
        for (int i = 0; i < N; i++) {
            if (sum < K)
                cnt++;
            sum -= v[i];
            sum += v[i + M];
        }
        cout << cnt << endl;
    }
}
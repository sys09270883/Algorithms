#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

int N, M;
string start;
vector<int> arr, res;
vector<vector<int>> v, swc;

void func(int cur, int cnt) {
    if (cur == M) {
        int idx = N;
        for (int i = 0; i < N; i++) {
            idx += arr[i];
        }
        if (v[idx].empty()) {
            v[idx].pb(cnt);
            for (int i = 0; i < cnt; i++) {
                v[idx].pb(res[i]);
            }
        }
        return;
    }
    func(cur + 1, cnt);
    res[cnt] = cur + 1;
    cnt++;
    for (int i = 0; i < N; i++) {
        arr[i] *= swc[cur][i];
    }
    func(cur + 1, cnt);
    cnt--;
    res[cnt] = 0;
    for (int i = 0; i < N; i++) {
        arr[i] *= swc[cur][i];
    }
}

int main() {
    FASTIO
    cin >> N >> M;
    cin >> start;
    arr.resize(N);
    res.resize(N);
    swc.resize(M, vector<int>(N));
    v.resize(N << 1 | 1, vector<int>());
    for (int i = 0; i < N; i++) {
        arr[i] = start[i] == '1' ? 1 : -1;
    }
    for (int i = 0; i < M; i++) {
        string s;
        cin >> s;
        for (int j = 0; j < N; j++) {
            swc[i][j] = s[j] == '1' ? -1 : 1;
        }
    }
    func(0, 0);
    for (int i = 0; i < 2 * N + 1; i++) {
        if (v[i].empty())
            cout << -1;
        for (auto& j : v[i]) {
            cout << j << ' ';
        }
        cout << endl;
    }
}
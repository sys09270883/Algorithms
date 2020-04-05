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

int N, M;
vector<int> v;
vector<vector<int>> dp;

int palindrome(int s, int e) {
    if (s >= e)
        return 1;
    int &ret = dp[s][e];
    if (ret != -1)
        return ret;
    if (v[s] != v[e])
        return ret = 0;
    return ret = palindrome(s + 1, e - 1);
}

int main() {
    FASTIO
    cin >> N;
    v.resize(N + 1);
    dp.resize(N + 1, vector<int>(N + 1, -1));
    for (int i = 1; i < N + 1; i++) {
        cin >> v[i];
    }
    cin >> M;
    for (int i = 0; i < M; i++) {
        int s, e;
        cin >> s >> e;
        cout << palindrome(s, e) << endl;
    }
}
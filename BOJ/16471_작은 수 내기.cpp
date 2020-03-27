#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

int N, cnt;
vector<int> a, b;

int main() {
    FASTIO
    cin >> N;
    a.resize(N);
    b.resize(N);
    for (auto& i : a) {
        cin >> i;
    }
    for (auto& i : b) {
        cin >> i;
    }
    sort(all(a)), sort(all(b));
    for (int i = 0; i < N; i++) {
        auto it = upper_bound(all(b), a[i]);
        if (it == b.end())
            continue;
        b.erase(it);
        cnt++;
    }
    cout << (cnt >= (N + 1) / 2 ? "YES" : "NO");
}
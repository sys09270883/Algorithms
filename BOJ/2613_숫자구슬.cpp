#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
typedef long long ll;
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>

const int MAX = 987654321;
int N, M, l = MAX, r, minsum = MAX;
vector<int> v;

int main() {
    FASTIO
    cin >> N >> M;
    v.resize(N);
    for (auto& i : v) {
        cin >> i;
        r += i;
        l = min(l, i);
    }
    while (l <= r) {
        int m = l + r >> 1, sum = 0, cnt = 0;
        bool flag = true;
        for (int i = 0; i < N; i++) {
            if (sum + v[i] > m) {
                sum = v[i];
                if (sum > m) {
                    flag = false;
                    break;
                }
                cnt++;
                continue;
            }
            sum += v[i];
        }
        if (sum)
            cnt++;
        if (cnt <= M && flag) {
            r = m - 1;
            minsum = min(minsum, m);
        }
        else {
            l = m + 1;
        }
    }
    int sz = 0, sum = 0;
    vector<int> szv;
    for (int i = 0; i < N; i++) {
        if (sum + v[i] > minsum) {
            sum = v[i];
            szv.pb(sz);
            sz = 1;
            continue;
        }
        sum += v[i];
        sz++;
    }
    if (sum)
        szv.pb(sz);
    cout << minsum << endl;
    sz = szv.size();
    int diff = M - sz;
    for (int i = 0; i < sz; i++) {
        if (szv[i] == 1)
            cout << 1 << ' ';
        else {
            while (szv[i] >= 2 && diff >= 1){
                cout << 1 << ' ';
                szv[i]--, diff--;
            }
            cout << szv[i] << ' ';
        }
    }
}
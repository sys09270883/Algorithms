#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

int N, res, cnt, l, r, sz;
string s;
vector<int> alpha(26);

int main() {
    FASTIO
    cin >> N;
    cin >> s;
    sz = s.size();
    while (l < sz) {
        while (r < sz) {
            if (cnt > N)
                break;
            if (alpha[s[r++] - 'a']++ == 0 && ++cnt > N)
                break;
            res = max(res, r - l);
        }
        while (true) {
            if (--alpha[s[l++] - 'a'] <= 0 && --cnt <= N)
                break;
        }
    }
    cout << res;
}
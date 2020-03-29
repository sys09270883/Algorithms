#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

int odd, oidx = -1;
string s, a, ret;
vector<int> alpha(26);

int main() {
    FASTIO
    cin >> s;
    for (auto& i : s) {
        alpha[i - 'A']++;
    }
    for (int i = 0; i < 26; i++) {
        if (alpha[i] & 1)
            odd++, oidx = i;
    }
    if (odd >= 2)
        return !(cout << "I'm Sorry Hansoo");
    for (int i = 0; i < 26; i++) {
        if (alpha[i]) {
            int sz = alpha[i] >> 1;
            while (sz--) {
                a += (char)(i + 'A');
            }
        }
    }
    ret += a;
    if (odd)
        ret += (char)(oidx + 'A');
    reverse(all(a));
    ret += a;
    cout << ret;
}
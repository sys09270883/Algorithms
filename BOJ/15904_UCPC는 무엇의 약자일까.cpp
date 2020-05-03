#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;
typedef pair<ll, int> pli;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

const string s1 = "I love UCPC", s2 = "I hate UCPC", s3 = "UCPC";

int main() {
    FASTIO
    string s;
    getline(cin, s);
    vector<bool> flag(4, false);
    int pos = 0;
    for (int i = 0; i < 4; i++) {
        for (int j = pos; j < s.size(); j++) {
            if (s[j] == s3[i]) {
                flag[i] = true;
                s[j] = ' ';
                pos = j;
                break;
            }
        }
    }
    if (flag[0] && flag[1] && flag[2] && flag[3])
        cout << s1;
    else
        cout << s2;
}
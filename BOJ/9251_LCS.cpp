#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
typedef long long ll;
#define endl '\n'
#define pb push_back
#define pii pair<int, int>

int main() {
    FASTIO
    string s1, s2;
    cin >> s1 >> s2;
    int sz1 = s1.size(), sz2 = s2.size();
    vector<vector<int>> dp(sz1 + 2, vector<int>(sz2 + 2));
    for (int i = 1; i < sz1 + 1; i++) {
        for (int j = 1; j < sz2 + 1; j++) {
            if (s1[i - 1] == s2[j - 1])
                dp[i][j] = dp[i - 1][j - 1] + 1;
            else
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
        }
    }
    cout << dp[sz1][sz2];
}
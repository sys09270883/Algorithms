#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

int T, K;

int main() {
    FASTIO
	cin >> T;
    while (T--) {
        cin >> K;
        if (K == (K & -K))
            cout << "GoHanGang" << endl;
        else
            cout << "Gazua" << endl;
    }
}
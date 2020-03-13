#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

const int INF = 987654321;
int N, S, res = INF;
vector<int> arr;

int main() {
    FASTIO
    cin >> N >> S;
    arr.resize(N + 1);
    for (int i = 1; i < N + 1; i++) {
        cin >> arr[i];
    }
    if (arr[1] >= S) {
        cout << 1;
        return 0;
    }
    int l = 0, r = 1, sum = arr[l] + arr[r];
    while (true) {
        if (sum >= S) {
            res = min(res, r - l + 1);
            sum -= arr[l++];
            if (l > N)
                break;
            continue;
        }
        r++;
        if (r > N)
            break;
        sum += arr[r];
    }
    cout << (res == INF ? 0 : res);
}
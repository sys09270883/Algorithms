#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

int N;
int arr[] = {-1, 1, 2, 4, 7, 8, 11, 13, 14};

int main() {
    FASTIO
    cin >> N;
    int a = N / 8;
    int b = N % 8;
    if (b == 0) {
        a--;
        b = 8;
    }
    if (!(N / 8))
        cout << arr[b];
    else
        cout << a * 15 + arr[b];
}

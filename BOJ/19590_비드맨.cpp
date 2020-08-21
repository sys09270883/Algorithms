#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;
typedef pair<ll, int> pli;
#pragma GCC optimize("Ofast")
#pragma GCC optimize("unroll-loops")
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define rall(x) (x).rbegin(), (x).rend()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

int N, maxv;
ll tot;

int main() {
    FASTIO
    cin >> N;
    for (int i = 0; i < N; i++) {
        int a;
        cin >> a;
        tot += a;
        maxv = max(maxv, a);
    }
    if (tot - maxv > maxv) {
        cout << (tot & 1);
    } else {
        cout << maxv - (tot - maxv);
    }
}
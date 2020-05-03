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

int N, M;
priority_queue<ll, vector<ll>, greater<ll>> pq;
ll res;

int main() {
    FASTIO
    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        int a;
        cin >> a;
        pq.push(a);
    }
    while (M--) {
        auto a = pq.top();
        pq.pop();
        auto b = pq.top();
        pq.pop();
        pq.push(a + b), pq.push(a + b);
    }
    while (pq.size()) {
        res += pq.top(), pq.pop();
    }
    cout << res;
}
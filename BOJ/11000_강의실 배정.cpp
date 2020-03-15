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
vector<pii> p;
priority_queue<int, vector<int>, greater<int>> pq;

int main() {
    FASTIO
    cin >> N;
    for (int i = 0; i < N; i++) {
        int a, b;
        cin >> a >> b;
        p.pb({a, b});
    }
    sort(p.begin(), p.end());
    pq.push(p[0].second);
    for (int i = 1; i < N; i++) {
        if (pq.top() <= p[i].first)
            pq.pop();
        pq.push(p[i].second);
    }
    cout << pq.size();
}
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

unordered_map<int, int> hm, order;

struct Node {
    int val, freq;

    Node(int val, int freq) : val(val), freq(freq) {}

    bool operator<(const Node &n) const {
        if (this->freq == n.freq)
            return order[this->val] < order[n.val];
        return this->freq > n.freq;
    }
};

int N, C;
vector<Node> v;

int main() {
    FASTIO
    cin >> N >> C;
    for (int i = 0; i < N; i++) {
        int x;
        cin >> x;
        if (hm.find(x) == hm.end())
            hm[x] = 0;
        hm[x]++;
        if (order.find(x) == hm.end())
            order[x] = 0;
        if (!order[x])
            order[x] = order.size() + 1;
    }
    for (auto& i : hm) {
        v.pb({i.first, i.second});
    }
    sort(all(v));
    for (auto& i : v) {
        int sz = i.freq;
        while (sz--) {
            cout << i.val << ' ';
        }
    }
}
#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
#define pb push_back
typedef long long ll;

struct Node {
    int idx, val;
    Node(int idx, int val) {
        this->idx = idx;
        this->val = val;
    }
    bool operator<(const Node& n) const {
        return this->val > n.val;
    }
};

int N, L, x;
priority_queue<Node> pq;

int main() {
    FASTIO
    cin >> N >> L;
    for (int i = 1; i < N + 1; i++)
    {
        cin >> x;
        pq.push({i, x});
        while (pq.top().idx < i - L + 1) {
            pq.pop();
        }
        cout << pq.top().val << ' ';
    }
}
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
vector<int> in, post, idx;

void preOrder(int inl, int inr, int postl, int postr) {
    if (inl > inr || postl > postr)
        return;
    int root = post[postr];
    cout << root << ' ';
    preOrder(inl, idx[root], postl, postl + idx[root] - inl - 1);
    preOrder(idx[root] + 1, inr, postl + idx[root] - inl, postr - 1);
}

int main() {
    FASTIO
    cin >> N;
    in.resize(N + 1);
    post.resize(N + 1);
    idx.resize(N + 1);
    for (int i = 1; i < N + 1; i++) {
        cin >> in[i];
        idx[in[i]] = i;
    }
    for (int i = 1; i < N + 1; i++) {
        cin >> post[i];
    }
    preOrder(1, N, 1, N);
}
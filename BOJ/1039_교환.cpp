#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define all(x) (x).begin(), (x).end()
#define next _next

struct Node {
    string s;
    int cnt;
    Node(string s, int cnt) : s(s), cnt(cnt) {}
};

string N;
int K;
bool vis[1000001][11];

int BFS() {
    int ret = -1;
    int sz = N.size();
    if (sz < 2)
        return ret;
    queue<Node> q;
    q.push({N, 0});
    int n = stoi(N);
    vis[n][0] = true;
    while (!q.empty()) {
        auto cur = q.front().s;
        auto cnt = q.front().cnt;
        q.pop();
        if (cnt > K)
            continue;
        else if (cnt == K)
            ret = max(ret, stoi(cur));
        for (int i = 0; i < sz; i++) {
            for (int j = i + 1; j < sz; j++) {
                string tmp = cur;
                swap(tmp[i], tmp[j]);
                if (i == 0 && tmp[i] == '0')
                    continue;
                n = stoi(tmp);
                if (vis[n][cnt + 1])
                    continue;
                vis[n][cnt + 1] = true;
                q.push({tmp, cnt + 1});
            }
        }
    }
    return ret;
}

int main() {
    FASTIO
    cin >> N;
    cin >> K;
    cout << BFS();
}
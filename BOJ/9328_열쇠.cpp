#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
typedef long long ll;
#define endl '\n'
#define pb push_back
#define pii pair<int, int>

struct Node {
    int x, y;
    Node(int x, int y) : x(x), y(y) {}
    bool operator==(const Node& n) const {
        if (this->x == n.x && this->y == n.y)
            return true;
        return false;
    }
    friend struct hash<Node>;
};

namespace std {
    template<>
    struct hash<Node> {
        size_t operator()(const Node& n) const {
            hash<int> hash_func;
            return n.x ^ (hash_func(n.y)) * 37;
        }
    };
}

int T, H, W;
vector<vector<char>> arr;
vector<vector<bool>> vis;
unordered_set<char> key;
unordered_set<Node> door;
int dx[] = {-1, 0, 1, 0}, dy[] = {0, -1, 0, 1};

int BFS() {
    queue<Node> q;
    q.push({0, 0});
    vis[0][0] = true;
    int ret = 0;
    while (!q.empty()) {
        int x = q.front().x;
        int y = q.front().y;
        q.pop();
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= H + 2 || ny >= W + 2 || vis[nx][ny] || arr[nx][ny] == '*')
                continue;
            char& c = arr[nx][ny];
            if ('a' <= c && c <= 'z') {
                key.insert(c);
                for (auto& t : door) {
                    int tx = t.x;
                    int ty = t.y;
                    if (arr[tx][ty] == c - 32) {
                        if (vis[tx][ty])
                            continue;
                        vis[tx][ty] = true;
                        q.push({tx, ty});
                    }
                }
            }
            else if ('A' <= c && c <= 'Z') {
                if (key.find(c + 32) == key.end()) {
                    door.insert({nx, ny});
                    continue;
                }
            }
            else if (c == '$')
                ret++;
            vis[nx][ny] = true;
            q.push({nx, ny});
        }
    }
    return ret;
}

int main() {
    FASTIO
    cin >> T;
    string s;
    while (T--) {
        cin >> H >> W;
        key.clear();
        arr.clear();
        door.clear();
        arr.resize(H + 2, vector<char>(W + 2, '.'));
        vis.clear();
        vis.resize(H + 2, vector<bool>(W + 2));
        for (int i = 1; i < H + 1; i++) {
            cin >> s;
            for (int j = 1; j < W + 1; j++) {
                arr[i][j] = s[j - 1];
            }
        }
        cin >> s;
        if (s != "0") {
            for (auto& i : s) {
                key.insert(i);
            }
        }
        cout << BFS() << endl;
    }
}
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());
#define cprcmp(x, y) sort(all(x), (y)), (x).erase(unique(all(x)), (x).end());
#define Matrix vector<vector<int>>
#define Matrixll vector<vector<ll>>

char v[4][4];
bool vis[4][4];
int dx[] = {-1, -1, -1, 0, 0, 1, 1, 1};
int dy[] = {-1, 0, 1, -1, 1, -1, 0, 1};
int score[] = {0, 0, 0, 1, 1, 2, 3, 5, 11};
set<string> wset;
int w, b;

struct Trie {
    Trie *next[26];
    bool finish;

    Trie(): finish(false) {
        memset(next, 0, sizeof(next));
    }

    ~Trie() {
        for (int i = 0; i < 26; i++) {
            if (next[i])
                delete next[i];
        }
    }

    void insert(string &s, int idx) {
        if (idx >= s.size()) {
            finish = true;
            return;
        }
        int cur = s[idx] - 'A';
        if (!next[cur])
            next[cur] = new Trie();
        next[cur]->insert(s, idx + 1);
    }

    void find(string s, int idx, int x, int y) {
        if (x < 0 || y < 0 || x > 3 || y > 3)
            return;
        if (vis[x][y] || s.size() >= 8)
            return;
        vis[x][y] = true;
        s += v[x][y];
        int cur = s[idx] - 'A';
        if (!next[cur]) {
            vis[x][y] = false;
            return;
        }
        if (next[cur]->finish)
            wset.insert(s);
        for (int i = 0; i < 8; i++) {
            next[cur]->find(s, idx + 1, x + dx[i], y + dy[i]);
        }
        vis[x][y] = false;
    }
};


int main() {
    FASTIO
    Trie *root = new Trie();
    cin >> w;
    for (int i = 0; i < w; i++) {
        string s;
        cin >> s;
        root->insert(s, 0);
    }
    cin >> b;
    while (b--) {
        wset.clear();
        for (int i = 0; i < 4; i++) {
            string s;
            cin >> s;
            for (int j = 0; j < 4; j++) {
                v[i][j] = s[j];
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                root->find("", 0, i, j);
            }
        }
        int tot = 0;
        string lstr = "";
        for (auto& i : wset) {
            if (lstr.size() < i.size())
                lstr = i;
            tot += score[i.size()];
        }
        cout << tot << ' ' << lstr << ' ' << wset.size() << endl;
    }
    delete root;
}
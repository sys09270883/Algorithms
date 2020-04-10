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

int T, N1, N2, res;

int normalize(char c) {
    int ret = 0;
    if ('0' <= c && c <= '9')
        ret = c - '0';
    else if ('a' <= c && c <= 'z')
        ret = c - 'a' + 10;
    else if ('A' <= c && c <= 'Z')
        ret = c - 'A' + 36;
    else if (c == '.')
        ret = 62;
    return ret;
}

struct Trie {
    Trie *next[63];
    bool finish, erase;

    Trie(): finish(false), erase(false) {
        fill(next, next + 63, nullptr);
    }

    ~Trie() {
        for (int i = 0; i < 63; i++) {
            if (next[i])
                delete next[i];
        }
    }

    void insert(string &s, int idx, bool flag) {
        if (idx >= s.size()) {
            erase = flag;
            finish = flag;
            return;
        }
        int sidx = normalize(s[idx]);
        if (!next[sidx])
            next[sidx] = new Trie();
        erase = flag;
        next[sidx]->insert(s, idx + 1, flag);
    }

    int find() {
        int ret = 0;
        if (erase)
            return 1;
        else if (finish)
            ret++;
        for (int i = 0; i < 63; i++) {
            if (next[i])
                ret += next[i]->find();
        }
        return ret;
    }
};

int main() {
    FASTIO
    cin >> T;
    while (T--) {
        Trie *root = new Trie();
        cin >> N1;
        for (int i = 0; i < N1; i++) {
            string s;
            cin >> s;
            root->insert(s, 0, true);
        }
        cin >> N2;
        for (int i = 0; i < N2; i++) {
            string s;
            cin >> s;
            root->insert(s, 0, false);
        }
        res = root->find();
        delete root;
        cout << res << endl;
    }
}
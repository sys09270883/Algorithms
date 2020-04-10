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

struct Trie {
    Trie *next[26];
    int branch;
    bool finish;

    Trie(): branch(0), finish(false) {
        fill(next, next + 26, nullptr);
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
        int sidx = s[idx] - 'a';
        if (next[sidx] == nullptr) {
            branch++;
            next[sidx] = new Trie();
        }
        next[sidx]->insert(s, idx + 1);
    }

    int find(string &s, int idx, int cnt) {
        if (idx >= s.size())
            return cnt;
        int ret = 0, sidx = s[idx] - 'a';
        if (idx && (branch > 1 || (branch == 1 && finish) && next[sidx] != nullptr))
            ret = next[sidx]->find(s, idx + 1, cnt + 1);
        else
            ret = next[sidx]->find(s, idx + 1, cnt);
        return ret;
    }
};

int main() {
    FASTIO
    int N;
    cout << fixed << setprecision(2);
    while (cin >> N) {
        vector<string> v(N);
        Trie* root = new Trie();
        for (auto& i : v) {
            cin >> i;
            root->insert(i, 0);
        }
        int res = 0;
        for (auto& i : v) {
            res += root->find(i, 0, 0) + 1;
        }
        delete root;
        cout << 1.0 * res / N << endl;
    }
}
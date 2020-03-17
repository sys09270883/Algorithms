#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

struct Trie {
    Trie* child[26];
    bool finish;

    Trie() : finish(false) {
        memset(child, 0, sizeof(child));
    }

    ~Trie() {
        for (int i = 0; i < 26; i++) {
            if (child[i])
                delete child[i];
        }
    }

    void insert(const char* key) {
        if (!*key) {
            this->finish = true;
            return;
        }
        int cur = *key - 'a';
        if (!child[cur])
            child[cur] = new Trie;
        child[cur]->insert(key + 1);
    }

    bool query(const char* key) {
        Trie* ret = find(key);
        if (ret == NULL || !ret->finish)
            return false;
        return true;
    }

    Trie* find(const char* key) {
        if (!*key)
            return this;
        int cur = *key - 'a';
        if (!child[cur])
            return NULL;
        return child[cur]->find(key + 1);
    }
};

int N, M, res;
string s;

int main() {
    FASTIO
    cin >> N >> M;
    Trie root;
    for (int i = 0; i < N; i++) {
        cin >> s;
        root.insert(s.c_str());
    }
    for (int i = 0; i < M; i++) {
        cin >> s;
        if (root.query(s.c_str()))
            res++;
    }
    cout << res;
}
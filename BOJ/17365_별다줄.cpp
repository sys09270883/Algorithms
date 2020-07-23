#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;
typedef pair<ll, int> pli;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define rall(x) (x).rbegin(), (x).rend()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

struct Trie {
    Trie *next[26];
    int weight;

    Trie(): weight(0) {
        fill(next, next + 26, nullptr);
    }

    ~Trie() {
        for (int i = 0; i < 26; i++) {
            if (next[i])
                delete next[i];
        }
    }

    void insert(string &s, int idx = 0) {
        if (idx >= s.size())
            return;
        int sidx = s[idx] - 'a';
        if (!next[sidx])
            next[sidx] = new Trie();
        next[sidx]->weight++;
        next[sidx]->insert(s, idx + 1);
    }
};

const int MOD = 1e9 + 7;
int N;
string tar;
vector<ll> dp;

int main() {
    FASTIO
    cin >> N;
    Trie trie;
    for (int i = 0; i < N; i++) {
        string s;
        cin >> s;
        trie.insert(s);
    }
    cin >> tar;
    dp.resize(tar.size() + 1);
    dp[0] = 1;
    for (int i = 0; i < tar.size(); i++) {
        Trie *cur = &trie;
        for (int j = 0; i + j < tar.size(); j++) {
            if (!cur->next[tar[i + j] - 'a'])
                break;
            cur = cur->next[tar[i + j] - 'a'];
            dp[i + j + 1] += dp[i] * cur->weight;
            dp[i + j + 1] %= MOD;
        }
    }
    cout << dp[tar.size()];
}
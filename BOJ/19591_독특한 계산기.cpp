#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef unsigned long long ull;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;
typedef pair<ll, int> pli;
#pragma GCC optimize("Ofast")
#pragma GCC optimize("unroll-loops")
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define rall(x) (x).rbegin(), (x).rend()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

bool is_operator(char c) {
    if (c == '+' || c == '*' || c == '-' || c == '/') {
        return true;
    }
    return false;
}

ll operate(ll a, char c, ll b) {
    if (c == '+') {
        return a + b;
    } else if (c == '-') {
        return a - b;
    } else if (c == '*') {
        return a * b;
    }
    return a / b;
}

deque<ll> num;
deque<char> op;
map<char, int> prio;

int main() {
    FASTIO
    string s;
    cin >> s;
    prio['+'] = 0;
    prio['-'] = 0;
    prio['*'] = 1;
    prio['/'] = 1;
    string ss;
    if (s[0] == '-') {
        for (int i = 1; i < s.size(); i++) {
            if (is_operator(s[i])) {
                op.pb(s[i]);
                num.pb(stoll(ss));
                ss = "";
            } else {
                ss += s[i];
            }
        }
    } else {
        for (int i = 0; i < s.size(); i++) {
            if (is_operator(s[i])) {
                op.pb(s[i]);
                num.pb(stoll(ss));
                ss = "";
            } else {
                ss += s[i];
            }
        }
    }
    num.pb(stoll(ss));
    if (s[0] == '-') {
        ll front = num.front();
        num.pop_front();
        num.push_front(-front);
    }
    while (1) {
        ll opsz = op.size();
        if (opsz == 0) {
            break;
        } else if (opsz == 1) {
            ll a = num.front();
            ll b = num.back();
            ll val = operate(a, op.front(), b);
            num.pop_front();
            num.pop_back();
            op.pop_back();
            num.pb(val);
        } else {
            char opfront = op.front();
            char opback = op.back();
            if (prio[opfront] == prio[opback]) {
                ll a = num.front();
                num.pop_front();
                ll b = num.front();
                ll front_val = operate(a, opfront, b);
                ll c = num.back();
                num.pop_back();
                ll d = num.back();
                ll back_val = operate(d, opback, c);
                if (front_val >= back_val) {
                    num.pb(c);
                    op.pop_front();
                    num.pop_front();
                    num.push_front(front_val);
                } else {
                    num.push_front(a);
                    op.pop_back();
                    num.pop_back();
                    num.pb(back_val);
                }
            } else if (prio[opfront] > prio[opback]) {
                op.pop_front();
                ll a = num.front();
                num.pop_front();
                ll b = num.front();
                num.pop_front();
                num.push_front(operate(a, opfront, b));
            } else {
                op.pop_back();
                ll a = num.back();
                num.pop_back();
                ll b = num.back();
                num.pop_back();
                num.pb(operate(b, opback, a));
            }
        }
    }
    cout << num.front();
}
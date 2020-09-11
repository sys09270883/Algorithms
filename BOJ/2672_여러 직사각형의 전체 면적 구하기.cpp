#include <bits/stdc++.h>
using namespace std;
using ll = long long;
using ull = unsigned long long;
using pii = pair<int, int>;
using pll = pair<ll, ll>;
using pli = pair<ll, int>;
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

struct Rect {
    double x1, y1, x2, y2;

    Rect(double x1, double y1, double x2, double y2): x1(x1), y1(y1), x2(x2), y2(y2) {}
};

int N;
vector<Rect> v;
vector<double> xv, yv;
double res;

bool check(const Rect &r1, const Rect &r2) {
    return r1.x1 < r2.x2 && r1.x2 > r2.x1 && r1.y1 < r2.y2 && r1.y2 > r2.y1;
}

int main() {
    FASTIO
    cin >> N;
    for (int i = 0; i < N; i++) {
        double x, y, w, h;
        cin >> x >> y >> w >> h;
        v.pb({x, y, x + w, y + h});
        xv.pb(x), xv.pb(x + w);
        yv.pb(y), yv.pb(y + h);
    }
    sort(all(xv));
    sort(all(yv));
    for (int i = 0; i < xv.size(); i++) {
        for (int j = 0; j < yv.size(); j++) {
            for (int k = 0; k < v.size(); k++) {
                if (check(Rect{xv[i], yv[j], xv[i + 1], yv[j + 1]}, v[k])) {
                    res += (xv[i + 1] - xv[i]) * (yv[j + 1] - yv[j]);
                    break;
                }
            }
        }
    }
    if (res - (int)res < 1e-3) {
        return !(cout << (int)res);
    }
    cout << fixed << setprecision(2) << res;
}
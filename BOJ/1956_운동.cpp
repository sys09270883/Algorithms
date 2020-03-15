#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

const int INF = 987654321;
int V, E;
vector<vector<int>> arr;

void floyd() {
    for (int k = 1; k < V + 1; k++) {
        for (int i = 1; i < V + 1; i++) {
            for (int j = 1; j < V + 1; j++) {
                arr[i][j] = min(arr[i][j], arr[i][k] + arr[k][j]);
            }
        }
    }
}

int main() {
    FASTIO
    cin >> V >> E;
    arr.resize(V + 1, vector<int>(V + 1, INF));
    for (int i = 0; i < E; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        arr[a][b] = c;
    }
    floyd();
    int min = INF;
    for (int i = 1; i < V + 1; i++) {
        if (min > arr[i][i])
            min = arr[i][i];
    }
    cout << (min == INF ? -1 : min);
}
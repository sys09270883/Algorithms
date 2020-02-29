#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
#define pb push_back
typedef long long ll;

const int INF = 987654321;
int N, M;
vector<int> arr, maxTree, minTree;

int minUpdate(int i, int v, int n, int s, int e) {
    if (i > e || i < s)
        return minTree[n];
    if (s == e)
        return minTree[n] = v;
    int m = s + e >> 1;
    return minTree[n] = min(minUpdate(i, v, 2 * n, s, m), minUpdate(i, v, 2 * n + 1, m + 1, e));
}

int maxUpdate(int i, int v, int n, int s, int e) {
    if (i > e || i < s)
        return maxTree[n];
    if (s == e)
        return maxTree[n] = v;
    int m = s + e >> 1;
    return maxTree[n] = max(maxUpdate(i, v, 2 * n, s, m), maxUpdate(i, v, 2 * n + 1, m + 1, e));
}

int minQuery(int l, int r, int n, int s, int e) {
    if (l > e || r < s)
        return INF;
    if (l <= s && e <= r)
        return minTree[n];
    int m = s + e >> 1;
    return min(minQuery(l, r, 2 * n, s, m), minQuery(l, r, 2 * n + 1, m + 1, e));
}

int maxQuery(int l, int r, int n, int s, int e) {
    if (l > e || r < s)
        return -1;
    if (l <= s && e <= r)
        return maxTree[n];
    int m = s + e >> 1;
    return max(maxQuery(l, r, 2 * n, s, m), maxQuery(l, r, 2 * n + 1, m + 1, e));
}

int main() {
    FASTIO
    cin >> N >> M;
    arr.resize(N + 1);
    minTree.resize(1 << (int)ceil(log2(N)) + 1, INF);
    maxTree.resize(1 << (int)ceil(log2(N)) + 1, -1);
    for (int i = 1; i < N + 1; i++)
    {
        cin >> arr[i];
        minUpdate(arr[i], i, 1, 1, N);
        maxUpdate(arr[i], i, 1, 1, N);
    }
    for (int i = 0; i < M; i++)
    {
        int q, a, b;
        cin >> q >> a >> b;
        if (q == 1)
        {
            swap(arr[a], arr[b]);
            minUpdate(arr[a], a, 1, 1, N);
            minUpdate(arr[b], b, 1, 1, N);
            maxUpdate(arr[a], a, 1, 1, N);
            maxUpdate(arr[b], b, 1, 1, N);
        }
        else
        {
            if (maxQuery(a, b, 1, 1, N) - minQuery(a, b, 1, 1, N) == b - a)
                cout << "YES\n";
            else
                cout << "NO\n";
        }
    }
}
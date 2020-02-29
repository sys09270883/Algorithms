#include <bits/stdc++.h>
using namespace std;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
#define pb push_back
#define pf push_front
typedef long long ll;

int N;
vector<int> arr;

int main() {
    FASTIO
    cin >> N;
    arr.resize(N);
    for (int i = 0; i < N; i++)
    {
        cin >> arr[i];
    }
    sort(arr.begin(), arr.end(), greater<int>());
    for (int i = 0; i < N; i++)
    {
        arr[i] += i + 1;
    }
    cout << *max_element(arr.begin(), arr.end()) + 1;
}
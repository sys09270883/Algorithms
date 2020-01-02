#include <bits/stdc++.h>
using namespace std;

int N, M;
vector<int> v;

void func(int depth) {
    if (depth == M) {
        for (size_t i = 0; i < M; i++)
        {
            cout << v[i] << ' ';
        }
        cout << '\n';
        return;
    }

    for (size_t i = 1; i < N + 1; i++)
    {
        v.push_back(i);
        func(depth + 1);
        v.pop_back();
    }
}

int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cin >> N >> M;
    func(0);
    return 0;
}
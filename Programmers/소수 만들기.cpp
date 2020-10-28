#include <bits/stdc++.h>
using namespace std;
using ll = long long;
using ull = unsigned long long;
using pii = pair<int, int>;
using pil = pair<int, ll>;
using pll = pair<ll, ll>;
using pli = pair<ll, int>;
using pic = pair<int, char>;
using pci = pair<char, int>;
using pss = pair<string, string>;
#pragma GCC optimize("Ofast")
#pragma GCC optimize("unroll-loops")
#define FASTIO ios_base::sync_with_stdio(false), cin.tie(NULL);
#define endl '\n'
#define pb push_back
#define ft first
#define sd second
#define all(x) (x).begin(), (x).end()
#define rall(x) (x).rbegin(), (x).rend()
#define cpr(x) sort(all(x)), (x).erase(unique(all(x)), (x).end());

const int SZ = 5e4;
int res;
vector<bool> is_prime(SZ, true);

int solution(vector<int> nums)
{
    is_prime[1] = false;
    for (int i = 2; i * i < SZ; i++)
    {
        if (!is_prime[i])
            continue;
        for (int j = i + i; j <= SZ; j += i)
        {
            is_prime[j] = false;
        }
    }
    int sz = nums.size();
    for (int i = 0; i < sz; i++)
    {
        for (int j = i + 1; j < sz; j++)
        {
            for (int k = j + 1; k < sz; k++)
            {
                if (is_prime[nums[i] + nums[j] + nums[k]])
                    res++;
            }
        }
    }
    return res;
}
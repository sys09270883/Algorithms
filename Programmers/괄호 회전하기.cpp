#include <bits/stdc++.h>
using namespace std;

void rotate(string &s)
{
    s += s[0];
    s.erase(s.begin());
}

bool match(char c1, char c2)
{
    if (c1 == '(' && c2 == ')')
    {
        return true;
    }
    if (c1 == '{' && c2 == '}')
    {
        return true;
    }
    if (c1 == '[' && c2 == ']')
    {
        return true;
    }
    return false;
}

int count(string s)
{
    stack<char> st;

    for (auto c : s)
    {
        if (st.empty())
        {
            st.push(c);
            continue;
        }
        if (!match(st.top(), c))
        {
            st.push(c);
        }
        else
        {
            st.pop();
        }
    }

    return st.empty();
}

int solution(string s)
{
    int answer = 0, sz = s.size();

    for (int i = 0; i < sz; i++)
    {
        answer += count(s);
        rotate(s);
    }

    return answer;
}
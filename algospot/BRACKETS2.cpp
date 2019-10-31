/*
https://algospot.com/judge/problem/read/BRACKETS2
[문제]
Best White is a mathematics graduate student at T1 University. 
Recently, he finished writing a paper and he decided to polish it. 
As he started to read it from the beginning, he realized that some of the formulas have problems: 
some of the brackets are mismatched! 
Since there are so many formulas in his paper, he decided to check their validity with a computer program.

The following kinds of brackets are included in Best White’s paper.

Round brackets are opened by a ( and closed by a ).
Curly brackets are opened by a { and closed by a }.
Square brackets are opened by a [ and closed by a ].
A formula is said well-matched when the following conditions are met:

Every bracket has a corresponding pair. ( corresponds to ), [ corresponds to ], et cetera.
Every bracket pair is opened first, and closed later.
No two pairs "*cross*" each other. For example, [(]) is not well-matched.
Write a program to help Best White by checking if each of his formulas is well-matched. 
To make the problem easier, everything other than brackets are removed from the formulas.

[입력]
The first line of the input will contain the number of test cases C (1≤C≤100). 
Each test is given in a single line as a character string. 
The strings will only include characters in "[](){}" (quotes for clarity). The length of the string will not exceed 10,000.

[출력]
For each test case, print a single line "YES" when the formula is well-matched; print "NO" otherwise. (quotes for clarity)

[풀이]
스택에 왼쪽 괄호들을 넣고, 괄호의 짝이 안맞거나, 최종적으로 스택이 비어있지 않으면 false 아니면 true를 리턴한다.

*/

#include <iostream>
#include <string>
#include <stack>

using namespace std;

bool func(string & str, stack<char> & stack) {
	int len = str.length();

	for (int i = 0; i < len; i++)
	{
		char cur = str.at(i);

		if (cur == '(' || cur == '{' || cur == '[')
			stack.push(cur);

		else if (cur == ')') {
			if (stack.empty())
				return false;

			else if (stack.top() != '(')
				return false;

			stack.pop();
		}

		else if (cur == '}') {
			if (stack.empty())
				return false;

			else if (stack.top() != '{')
				return false;

			stack.pop();
		}

		else if (cur == ']') {
			if (stack.empty())
				return false;

			else if (stack.top() != '[')
				return false;

			stack.pop();
		}
	}

	if (!stack.empty())
		return false;

	return true;
}

void clear(stack<char> & stack) {
	while (!stack.empty()) {
		stack.pop();
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int N;
	string str;
	stack<char> stack;
	cin >> N;
	cin.ignore();

	for (int i = 0; i < N; i++)
	{
		clear(stack);
		getline(cin, str);

		if (func(str, stack))
			cout << "YES\n";

		else
			cout << "NO\n";
	}

	return 0;
}
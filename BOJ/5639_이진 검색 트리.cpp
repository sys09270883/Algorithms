#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
#define endl '\n'
#define pb push_back
#define pii pair<int, int>
#define pll pair<ll, ll>
#define all(x) (x).begin(), (x).end()

struct Node {
    Node* l;
    Node* r;
    int val;

    Node(int val) : val(val), l(NULL), r(NULL) {}
};

Node* insert(Node* root, Node* node) {
    if (!root)
        return node;
    if (node->val < root->val)
        root->l = insert(root->l, node);
    else if (node->val > root->val)
        root->r = insert(root->r, node);
    return root;
}

void postOrder(Node* n) {
    if (n) {
        postOrder(n->l);
        postOrder(n->r);
        cout << n->val << endl;
    }
}

int main() {
    FASTIO
    int node;
    Node* root = NULL;
    while (cin >> node) {
        root = insert(root, new Node(node));
    }
    postOrder(root);
}
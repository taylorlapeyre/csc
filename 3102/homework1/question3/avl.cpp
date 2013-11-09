#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <fstream>
#include <string>
using namespace std;

struct node
{
    int key;
    int size;
    struct node *parent;
    struct node *left;
    struct node *right;
    int height;
};

struct node* createNode(int key)
{
    struct node* node = (struct node*) malloc(sizeof(struct node));
    node->key = key;
    node->parent = NULL;
    node->left = NULL;
    node->right = NULL;
    node->height = 1;
    node->size = 1;
    return node;
}

int height(struct node *N)
{
    return (N != NULL) ? N->height : 0;
}

int size(struct node* node)
{
    return (node != NULL) ? size(node->left) + 1 + size(node->right) : 0;
}

int max(int a, int b)
{
    return (a > b) ? a : b;
}

int getBalanceFactor(struct node *node)
{
    return (node != NULL) ? height(node->left) - height(node->right) : 0;
}

struct node *minNode(struct node* node)
{
    struct node* current = node;

    while (current->left)
        current = current->left;

    return current;
}

struct node *maxNode(struct node* node)
{
    struct node* current = node;

    while (current->right)
        current = current->right;

    return current;
}

struct node *rightRotate(struct node *y)
{
    struct node *x = y->left;
    struct node *t2 = x->right;

    // Do rotation
    x->right = y;
    y->left = t2;

    y->height = max(height(y->left),height(y->right))+1;
    x->height = max(height(x->left), height(x->right))+1;

    y->size = size(y);
    x->size =size(x);

    return x;
}

struct node *leftRotate(struct node *x)
{
    struct node *y = x->right;
    struct node *T2 = y->left;

    // Do rotation
    y->left = x;
    x->right = T2;

    x->height = max(height(x->left), height(x->right))+1;
    y->height = max(height(y->left), height(y->right))+1;

    x->size = size(x);
    y->size = size(y);

    return y;
}

struct node* insert(struct node* node, int key)
{

    // Perform BST insertion
    if (node == NULL) return(createNode(key));

    if (key < node->key)
        node->left = insert(node->left, key);
    else
        node->right = insert(node->right, key);

    int balance = getBalanceFactor(node);
    node->height = max(height(node->left), height(node->right))+1;
    node->size = size(node);

    if (balance > 1 && key < node->left->key)
        return rightRotate(node);

    if (balance < -1 && key > node->right->key)
        return leftRotate(node);

    if (balance > 1 && key > node->left->key)
    {
        node->left = leftRotate(node->left);
        return rightRotate(node);
    }

    if (balance < -1 && key < node->right->key)
    {
        node->right = rightRotate(node->right);
        return leftRotate(node);
    }

    return node;
}

struct node* deleteNode(struct node* root, int key)
{
    if (root == NULL) return root;

    if (key < root->key) {
        root->left = deleteNode(root->left, key);
    } else if (key > root->key) {
        root->right = deleteNode(root->right, key);
    } else {
        if ((root->left == NULL) || (root->right == NULL)) {
            struct node *temp = root->left ? root->left : root->right;

            if (temp == NULL) { temp = root; root = NULL; }
            else *root = *temp;

            free(temp);
        } else {
            struct node *temp = minNode(root->right);
            root->key = temp->key;
            root->right = deleteNode(root->right, temp->key);
        }
    }

    if (root == NULL) return root;

    root->height = max(height(root->left), height(root->right))+1;
    root->size = size(root);

    int balance = getBalanceFactor(root);

    if (balance > 1 && getBalanceFactor(root->left) >= 0)
        return rightRotate(root);

    if (balance < -1 && getBalanceFactor(root->right) <= 0)
        return leftRotate(root);

    if (balance > 1 && getBalanceFactor(root->left) < 0)
    {
        root->left = leftRotate(root->left);
        return rightRotate(root);
    }

    if (balance < -1 && getBalanceFactor(root->right) > 0)
    {
        root->right = rightRotate(root->right);
        return leftRotate(root);
    }

    return root;
}

bool search(struct node* root, int key)
{
    if (root == NULL) return false;

    if (root->key == key)
        return true;
    else if (key < root->key)
        search(root->left, key);
    else if (key > root->key)
        search(root->right, key);
}

struct node* successor(struct node* x)
{
    struct node *y = x->parent;

    if (x->right != NULL)
        return(minNode(x->right));

    while (y != NULL && x == y->right) {
        x = y;
        y = y->parent;
    }
    return y;
}

struct node* predecessor(struct node* x)
{
    if (x->left != NULL)
        return(maxNode(x->left));
    struct node* y = x->parent;

    while (y != NULL && x == y->left) {
        x = y;
        y = y->parent;
    }
    return y;
}

void preOrderTraversal(struct node *root)
{
    if (root) {
        printf("%d ", root->key);
        preOrderTraversal(root->left);
        preOrderTraversal(root->right);
    }
}

void inOrderTraversal(struct node *root)
{
    if (root != NULL) {
        inOrderTraversal(root->left);
        printf("%d ", root->key);
        inOrderTraversal(root->right);
    }
}

int rankOfNode(struct node *x, int i)
{
    if (x == NULL) return 0;
    if (i < x->key) return rankOfNode(x->left, i);
    if (i == x->key) return (x->left->size+1);
    return ((x->left->size+1)+(rankOfNode(x->right, i)));
}


int select(struct node *x, int i)
{
    if (x == NULL) return 0;

    if (x->left->size >= i)
        return select(x->left, i);
    if (x->left->size+1 == i)
        return x->key;
    return select(x->right, i-1-(x->left->size));
}

int main()
{
    ifstream inputFile("input.txt");
    inputFile.open("input.txt", ifstream::in);

    string line;
    while (getline(inputFile, line))
        cout << line << endl;
    inputFile.close();

    // Make AVL
    struct node *root = NULL;
    root = insert(root, 9);
    root = insert(root, 5);
    root = insert(root, 10);
    root = insert(root, 0);
    root = insert(root, 6);
    root = insert(root, 11);
    root = insert(root, -1);
    root = insert(root, 1);
    root = insert(root, 2);

    preOrderTraversal(root);
    inOrderTraversal(root);

    root = deleteNode(root, 10);
    preOrderTraversal(root);

    printf("\n");

    bool found = search(root, 6);
    printf("Searching for  for 6...");
    printf(found ? "found" : "not found");

    printf("\n");

    printf("%d", rankOfNode(root, 0));
    printf("%d", rankOfNode(root, 1));
    // NOT WORKING..... printf("%d", rankOfNode(root, 2));

    printf("\n%d\n", select(root, 2));
    printf("\n%d\n", select(root, 3));

    return 0;
}

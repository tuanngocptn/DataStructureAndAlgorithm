package util;

public class AVLTree<E> {
    Node root;

    class Node {
        int key, height;
        Node left, right;

        public Node(int key) {
            this.key = key;
            this.height = 1;
        }
    }

    int heigh(Node n) {
        if (n == null) {
            return 0;
        }
        return n.height;
    }

    int max(int a, int b) {
        return a > b ? a : b;
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node t2 = x.right;
        //perform rotate
        x.right = y;
        y.left = t2;
        //update height
        y.height = max(heigh(y.left), heigh(y.right)) + 1;
        x.height = max(heigh(x.left), heigh(x.right)) + 1;
        //return new root
        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node t2 = y.left;
        //perform rotate
        y.left = x;
        x.right = t2;
        //update height
        x.height = max(heigh(x.left), heigh(x.right)) + 1;
        y.height = max(heigh(y.left), heigh(y.right)) + 1;
        //return new root
        return y;
    }

    int getBalance(Node n) {
        if (n == null) return 0;
        return heigh(n.left) - heigh(n.right);
    }

    Node insert(Node node, int key) {
        //insert theo bsttree
        if (node == null) return new Node(key);
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            return node;
        }
        //update height of ancestor node
        node.height = 1 + max(heigh(node.left), heigh(node.right));
        //get balance factor of this ancestor
        //left left case
        int balance = getBalance(node);
        //if this node becomes unbalanced there 4 cases
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }
        //right right case
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }
        //left right case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //right left case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }


    public static void main(String[] args) {
        AVLTree t = new AVLTree();
        t.root = t.insert(t.root, 10);
        t.root = t.insert(t.root, 20);
        t.root = t.insert(t.root, 30);
        t.root = t.insert(t.root, 40);
        t.root = t.insert(t.root, 50);
        t.root = t.insert(t.root, 25);
        t.root = t.insert(t.root, 52);
        t.preOrder(t.root);
    }
}


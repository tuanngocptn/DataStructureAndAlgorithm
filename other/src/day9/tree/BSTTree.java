package day9.tree;

public class BSTTree {


    Node root;

    public BSTTree() {
        root = null;
    }


    void insert(int x) {
        if (root == null) {
            root = new Node(x);
            return;
        }
        Node f, p;
        p = root;
        f = null;
        while (p != null) {
            if (p.info == x) {
                System.out.println(" The key " + x + " already exists, no insertion");
                return;
            }
            f = p;
            if (x < p.info) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (x < f.info) {
            f.left = new Node(x);
        } else {
            f.right = new Node(x);
        }
    }

    void preOrder(Node p) {
        if (p == null) {
            return;
        }
        visit(p);
        preOrder(p.left);
        preOrder(p.right);
    }

    void inOrder(Node p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }

    void postOrder(Node p) {
        if (p == null) {
            return;
        }
        postOrder(p.left);
        postOrder(p.right);
        visit(p);
    }

    void visit(Node p) {
        System.out.print(" " + p.info);
    }

    int countNode(Node p) {
        if (p == null) {
            return 0;
        }
        return 1 + countNode(p.left) + countNode(p.right);
    }

    int countLeaf(Node p) {
        if (p.left == null && p.right == null) {
            return 1;
        }
        if (p.left == null) {
            return countLeaf(p.right);
        }
        if (p.right == null) {
            return countLeaf(p.left);
        }
        return countLeaf(p.left) + countLeaf(p.right);
    }

    int heightTree(Node p) {
        if (p == null) return 0;
        int hleft = heightTree(p.left);
        int hright = heightTree(p.right);
        return 1 + (hleft > hright ? hleft : hright);
    }

    int search(Node p, int x) {
        if (p != null) {
            if (p.info == x) return p.info;
            if (p.info > x) return search(p.left, x);
            else return search(p.right, x);
        }
        return 0;
    }

    int searchPrint(Node p, int x) {
        if (p != null) {
            System.out.print(" " + p.info);
            if (p.info == x) return p.info;
            if (p.info > x) {
                return searchPrint(p.left, x);
            } else return searchPrint(p.right, x);
        }
        return 0;
    }

    int searchInfoHigherThanx(Node p, int x) {
        if (p != null) {
            int i = 0;
            if (p.info > x) {
                System.out.print(" " + p.info);
                i = 1;
            }
            return i + searchInfoHigherThanx(p.left, x) + searchInfoHigherThanx(p.right, x);
        }
        return 0;
    }

    int searchAndEvenNode(Node p) {
        if (p != null) {
            int i = 0;
            if (p.info % 2 == 0) {
                System.out.print(" " + p.info);
                i = 1;
            }
            return i + searchAndEvenNode(p.left) + searchAndEvenNode(p.right);
        }
        return 0;
    }

    int internalNode(Node p) {
        System.out.print(" " + p.info);
        if (p.left == null && p.right == null) {
            return 0;
        }
        if (p.left != null) {
            return internalNode(p.left);
        }
        if (p.right != null) {
            return internalNode(p.right);
        }
        return internalNode(p.left) + internalNode(p.right);
    }

}

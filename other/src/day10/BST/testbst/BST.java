package day10.BST.testbst;

public class BST<E extends Comparable<? super E>> {
    BSTNode<E> root = null;

    public void insert(E info) {
        BSTNode<E> p, t;
        for (p = null, t = root; t != null; ) {
            p = t;
            t = (info.compareTo(t.info) < 0 ? t.left : t.right);
        }
        if (p == null) root = new BSTNode<>(info);
        else if (info.compareTo(p.info) < 0) p.left = new BSTNode<>(info);
        else p.right = new BSTNode<>(info);
    }

    //phep duyet cay LNR duyt trung tu
    public void LNR(BSTNode<E> p) {
        if (p != null) {
            LNR(p.left);
            System.out.print(" " + p.info);
            LNR(p.right);
        }
    }

    //duyet tien tu
    public void NLR(BSTNode<E> p) {
        if (p != null) {
            System.out.print(" " + p.info);
            NLR(p.left);
            NLR(p.right);
        }
    }

    //duyet hau tu
    public void LRN(BSTNode<E> p) {
        if (p != null) {
            LRN(p.left);
            LRN(p.right);
            System.out.print(" " + p.info);
        }
    }

    //dem so nut cua cay
    public int countNode(BSTNode<E> p) {
        if (p == null) return 0;
        return 1 + countNode(p.left) + countNode(p.right);
    }

    //dem so la
    public int countLeaf(BSTNode<E> p) {
        if (p == null) return 0;
        if (p.left == null && p.right == null) {
            return 1;
        }
        return countLeaf(p.left) + countLeaf(p.right);
    }

    //dem so nut trong internal node
    public int countNonTerminal(BSTNode<E> p) {
        if (p == null) return 0;
        if (p.left != null || p.right != null) {
            return 1 + countNonTerminal(p.left) + countNonTerminal(p.right);
        }
        return countNonTerminal(p.left) + countNonTerminal(p.right);
    }

    //viet ham kiem tra x co trong cay
    public boolean isMember(BSTNode<E> p, E x) {
        if (p == null) return false;
        if (x.equals(p.info)) return true;
        return x.compareTo(p.info) < 0 ? isMember(p.left, x) : isMember(p.right, x);
    }

    //ham in cac nut la cua cay
    public int printLeaf(BSTNode<E> p) {
        if (p == null) return 0;
        if (p.left == null && p.right == null) {
            System.out.print(" " + p.info);
            return 1;
        }
        return printLeaf(p.left) + printLeaf(p.right);
    }

    // ham in cac node cua tree 2 nam trong khoang [5,10]
    public void fiveToTen(BSTNode<E> p, boolean boo, E first, E last) {
        if (p != null) {
            if (p.info == first) {
                boo = true;
            }
            if (p.info == last) {
                System.out.print(" " + p.info);
                return;
            }
            if (boo) {
                System.out.print(" " + p.info);
            }
            fiveToTen(p.left, boo, first, last);
            fiveToTen(p.right, boo, first, last);
        }
    }
}

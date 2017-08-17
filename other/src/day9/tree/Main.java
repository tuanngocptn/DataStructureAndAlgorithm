package day9.tree;

public class Main {
    public static void main(String[] args) {
        BSTTree tree = new BSTTree();
        System.out.print("\nCreate BST tree");
        tree.insert(25);
        tree.insert(22);
        tree.insert(2);
        tree.insert(35);
        tree.insert(15);
        tree.insert(17);
        tree.insert(9);
        tree.insert(12);
        tree.insert(30);
        tree.insert(37);
        tree.insert(32);
        tree.insert(42);
        System.out.print("\nInoder Traversal:");
        tree.inOrder(tree.root);
        System.out.print("\nInoder Traversal:");
        tree.preOrder(tree.root);
        System.out.print("\nSum of node: ");
        System.out.print(tree.countNode(tree.root));
        System.out.print("\nSum of leaf: ");
        System.out.print(tree.countLeaf(tree.root));
        System.out.print("\nHeight tree: ");
        System.out.print(tree.heightTree(tree.root));
        System.out.print("\nSearch: ");
        System.out.print(tree.search(tree.root,32));
        System.out.print("\nSearch And Print:");
        System.out.print(" --> "+tree.searchPrint(tree.root,32));
        System.out.print("\nSearch info higher than x:");
        System.out.print(" --> "+tree.searchInfoHigherThanx(tree.root,16));
        System.out.print("\nSearch prin so chan:");
        System.out.print(" --> "+tree.searchAndEvenNode(tree.root));
        System.out.print("\nSearch prin internal:");
        System.out.print(" --> "+tree.internalNode(tree.root));

    }
}

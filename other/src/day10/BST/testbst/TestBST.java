package day10.BST.testbst;

public class TestBST {
    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        /*tree.insert(25);
        tree.insert(37);
        tree.insert(10);
        tree.insert(18);
        tree.insert(29);
        tree.insert(50);
        tree.insert(3);
        tree.insert(1);
        tree.insert(6);
        tree.insert(5);
        tree.insert(12);
        tree.insert(20);
        tree.insert(35);
        tree.insert(13);
        tree.insert(32);
        tree.insert(41);
        System.out.println("\nInoder traversal: ");
        tree.LNR(tree.root);
        System.out.println("");
        System.out.println("\nPreOder: ");
        tree.NLR(tree.root);
        System.out.println("");
        System.out.println("\nPostOder: ");
        tree.LRN(tree.root);
        System.out.println("");
        System.out.println("\nTotal Node: ");
        System.out.println(tree.countNode(tree.root));
        System.out.println("\nTotal Leaf: ");
        System.out.println(tree.countLeaf(tree.root));
        System.out.println("\nTotal internal Node: ");
        System.out.println(tree.countNonTerminal(tree.root));
        System.out.println("\nIs member: ");
        System.out.println(tree.isMember(tree.root,133));
        System.out.println(tree.isMember(tree.root,13));
        System.out.println("\nPrint Leaf: ");
        tree.printLeaf(tree.root);*/
        IntBST tree1 = new IntBST().intSert("9,7,11,6,8,10,12");
        System.out.println("\nTree 2");
        tree1.LNR(tree1.root);
        IntBST tree2 = new IntBST().intSert("7,5,10,4,6,9,15");
        System.out.println("\nTree 2");
        tree2.LNR(tree2.root);
        System.out.println("");
        tree1.load(tree1.root,tree2);
        System.out.println("");
        tree2.fiveToTen(tree2.root,false,5,10);
    }
}

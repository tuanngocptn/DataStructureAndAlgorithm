package day10.BST.testbst;

public class IntBST extends BST<Integer> {
    public IntBST intSert(String input) {
        String[] s = input.replaceAll("^[,\\s]+", "").split("[,\\s+]");
        for (String l : s) this.insert(Integer.parseInt(l));
        return this;
    }
    public void printGiao(IntBST tree1, IntBST tree2){

    }

    public void load(BSTNode<Integer> p, IntBST tree2) {
        if (p != null) {
            if(tree2.isMember(tree2.root,p.info)){
                System.out.println(p.info);
            }
            load(p.left,tree2);
            load(p.right,tree2);
            System.out.print("-" + p.info);
        }
    }

}

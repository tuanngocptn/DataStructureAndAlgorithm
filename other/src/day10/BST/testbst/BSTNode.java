package day10.BST.testbst;

public class BSTNode<E> {
    E info;
    BSTNode<E> left = null, right = null;

    public BSTNode(){

    }

    public BSTNode(E info) {
        this.info = info;
    }


}

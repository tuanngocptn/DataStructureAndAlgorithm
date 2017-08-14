package util.search.impl;

import org.apache.log4j.Logger;

import util.collection.DoubleLinkedLstQueue;
import util.search.Search;

/**
 *
 * @author : Pham Tuan Ngoc
 *
 * this class will read file
 */
public abstract class TreeSearch<E> implements Search<E>{
	
	final static Logger logger = Logger.getLogger(TreeSearch.class);
	public Tree root;
	public TreeSearch(){
		root = null;
	}
	
	class Tree{
		E e = null;
		Tree left = null;
		Tree right = null;
		Tree(){
			
		}
		Tree(E e, Tree left, Tree right) {
			this.e = e;
			this.left = left;
			this.right = right;
		}		
	}

	/**
	 * insert element to tree search with cl type
	 * @param cl class input
	 * @param e Object of cl
	 */
	@SuppressWarnings("null")
	public	void insert(Class cl, E e) {
	    if (root == null) {
	        root = new Tree();
	        root.e = e;
	        return;
	    }
	    Tree treeRoot = root;
	    Tree treeInsert = null;
	    
	    while (treeRoot != null) {
	        if (compare(treeRoot.e, e) == 0) {
	        	logger.info("Element Already Exists: "+e);
	            return;
	        }
	        treeInsert = treeRoot;
	        if (compare(e,treeRoot.e) < 0) {
	        	treeRoot = treeRoot.left;
	        } else {
	        	treeRoot = treeRoot.right;
	        }
	    }
	    if (compare(e,treeInsert.e) < 0) {
	        treeInsert.left = new Tree();
	        treeInsert.left.e = e;
	    } else {
	    	treeInsert.right = new Tree();
	        treeInsert.right.e = e;
	    }
	}

	/**
	 * find element in tree
	 * @param findTree tree find
	 * @param e object find with 1 information in that
	 * @return if found return the object with full information else return null
	 */
	Tree find(Tree findTree, E e){
		if(findTree == null){
			return null;
		}
		if(compare(findTree.e,e)==0){
			return findTree;
		}else if (compare(findTree.e,e) > 0) {
			return find(findTree.left,e);
		}else {
			return find(findTree.right,e);
		}
	}

	/**
	 * get element with full information of element input
	 * @param e object input
	 * @return return element with full information
	 */
	public E get(E e) {
		Tree tree = find(root, e);
		if(tree == null){
			return null;
		}
		return tree.e;
	}

	/**
	 * preOrder and add all element equal Element input
	 * @param resultLst list add
	 * @param tree tree search
	 * @param e object input
	 */
	void preOrderSearch(DoubleLinkedLstQueue<E> resultLst, Tree tree, E e) {
	    if (tree == null) {	    	
	        return;
	    }	    
	    if(constains(tree.e, e)){
	    	resultLst.insertFirst(tree.e);
	    }
	    preOrderSearch(resultLst,tree.left,e);
	    preOrderSearch(resultLst,tree.right,e);
	}

	/**
	 * search all element with input
	 * @param e param with information would like to find
	 * @return list object equal
	 */
	public DoubleLinkedLstQueue<E> searchAll(E e) {
		DoubleLinkedLstQueue<E> resultLst = new DoubleLinkedLstQueue<E>();
		preOrderSearch(resultLst,root, e);		
		return resultLst;
	}
	
}

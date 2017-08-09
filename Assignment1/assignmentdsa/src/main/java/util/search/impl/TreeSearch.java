package util.search.impl;

import model.entities.Customer;
import model.entities.Product;
import model.iofile.ReadFile;

import org.apache.commons.lang.ObjectUtils.Null;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import etc.Constants;
import util.search.Search;

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
	
	public void preOrder(Tree tree) {
	    if (tree == null) {
	        return;
	    }
	    System.out.println(tree.e);
	    preOrder(tree.left);
	    preOrder(tree.right);
	}
	
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

	public E get(E e) {
		return find(root, e).e;
	}
}

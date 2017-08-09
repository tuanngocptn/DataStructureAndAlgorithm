package util.search.impl;

import model.entities.Customer;
import model.entities.Product;

import org.apache.log4j.Logger;

import util.search.Search;

public class TreeSearch<E> implements Search<E>{
	
	final static Logger logger = Logger.getLogger(TreeSearch.class);
	Tree root;
	public TreeSearch(){
		root = null;
	}
	
	public E get(Class cl, String code) {
		return null;
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
	void insert(Class cl, E e) {
	    if (root == null) {
	        root = new Tree();
	        return;
	    }
	    Tree treeRoot = root;
	    Tree treeInsert = null;
	    
	    while (treeRoot != null) {
	        if (compare(cl, treeRoot.e, e) == 0) {
	        	logger.info("Element Already Exists: "+e);
	            return;
	        }
	        treeInsert = treeRoot;
	        if (compare(cl,e,treeRoot.e) < 0) {
	            root = root.left;
	        } else {
	            root = root.right;
	        }
	    }
	    if (compare(cl,e,treeRoot.e) < 0) {
	        treeInsert.left = new Tree();
	        treeInsert.left.e = e;
	    } else {
	    	treeInsert.right = new Tree();
	        treeInsert.right.e = e;
	    }
	}
	
	void preOrder(Tree tree) {
	    if (tree == null) {
	        return;
	    }
	    System.out.println(tree.e);
	    preOrder(tree.left);
	    preOrder(tree.right);
	}
	
	int compare(Class cl, Object o1, Object o2){
		if(cl.equals(Customer.class)){
			Customer customer1 = (Customer) o1;
			Customer customer2 = (Customer) o2;
			return customer1.getCcode().compareTo(customer2.getCcode());
		}
		if(cl.equals(Product.class)){
			Product product1 = (Product) o1;
			Product product2 = (Product) o2;
			return product1.getPcode().compareTo(product2.getPcode());
		}
		return 0;
	}
}

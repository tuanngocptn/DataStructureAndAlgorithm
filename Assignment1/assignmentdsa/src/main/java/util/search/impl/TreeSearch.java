package util.search.impl;

import model.entities.Customer;

import org.apache.log4j.Logger;

import util.collection.LinkedLstDequeue;
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
	    preOrder(tree.left);
	    preOrder(tree.right);
	}
	
//	int compare(Class cl, Object o1, Object o2){
//		if(cl.equals(Customer.class)){
//			Customer customer1 = (Customer) o1;
//			Customer customer2 = (Customer) o2;
//			return customer1.getCcode().compareTo(customer2.getCcode());
//		}
//		if(cl.equals(Product.class)){
//			Product product1 = (Product) o1;
//			Product product2 = (Product) o2;
//			return product1.getPcode().compareTo(product2.getPcode());
//		}
//		return 0;
//	}
	
//	public static TreeSearch<Object> read(Class cl){
//		JSONArray jsonArray;
//		if(cl.equals(Customer.class)){
//			jsonArray = new JSONArray(ReadFile.read(Constants.CUSTOMER_DATA_URL));
//			TreeSearch<Object> treeSearch = new TreeSearch<Object>();
//			for(int i =0; i<jsonArray.length();i++){
//				Customer customer = new Customer();
//				JSONObject jsonObject = jsonArray.getJSONObject(i);
//				customer.setCcode(jsonObject.getString(Constants.CUSTOMER_CCODE));
//				customer.setCusName(jsonObject.getString(Constants.CUSTOMER_CUSNAME));
//				customer.setPhone(jsonObject.getString(Constants.CUSTOMER_PHONE));
//				treeSearch.insert(Customer.class, customer);				
//			}
//			return treeSearch;
//		}		
//		if(cl.equals(Product.class)){
//			jsonArray = new JSONArray(ReadFile.read(Constants.PRODUCT_DATA_URL));
//			TreeSearch<Object> treeSearch = new TreeSearch<Object>();
//			for(int i =0; i<jsonArray.length();i++){
//				Product product = new Product();
//				JSONObject jsonObject = jsonArray.getJSONObject(i);
//				product.setPcode(jsonObject.getString(Constants.PRODUCT_CODE));
//				product.setProName(jsonObject.getString(Constants.PRODUCT_NAME));
//				product.setQuantity(jsonObject.getInt(Constants.PRODUCT_QUANTITY));
//				product.setSaled(jsonObject.getInt(Constants.PRODUCT_SALE));
//				product.setPrice(jsonObject.getDouble(Constants.PRODUCT_PRICE));
//				treeSearch.insert(Product.class, product);
//			}
//			return treeSearch;
//		}
//		/*if(cl.equals(Order.class)){
//			jsonArray = new JSONArray(ReadFile.read(Constants.PRODUCT_DATA_URL));
//			Order[] orders = new Order[jsonArray.length()];
//			for(int i =0; i<jsonArray.length();i++){
//				Order order = new Order();
//				JSONObject jsonObject = jsonArray.getJSONObject(i);
//				order.setCcode(jsonObject.getString(Constants.ORDER_CUSTOMER_CODE));
//				order.setPcode(jsonObject.getString(Constants.ORDER_PRODUCT_CODE));
//				order.setQuantity(jsonObject.getInt(Constants.ORDER_QUANTITY));
//			}
//			return orders;
//		}*/	
//		return null;
//	}
	
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
		Tree tree = find(root, e);
		if(tree == null){
			return null;
		}
		return tree.e;
	}
	
	void preOrderSearch(LinkedLstDequeue<E> resultLst, Tree tree,E e) {
	    if (tree == null) {	    	
	        return;
	    }	    
	    if(constains(tree.e, e)){
	    	resultLst.insertFirst(tree.e);
	    }
	    preOrderSearch(resultLst,tree.left,e);
	    preOrderSearch(resultLst,tree.right,e);
	}

	/* (non-Javadoc)
	 * @see util.search.Search#getlst(java.lang.Object)
	 */
	public LinkedLstDequeue<E> searchAll(E e) {
		LinkedLstDequeue<E> resultLst = new LinkedLstDequeue<E>();
		preOrderSearch(resultLst,root, e);		
		return resultLst;
	}
	
}

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

public class TreeSearch<E> implements Search<E>{
	
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
	        if (compare(cl, treeRoot.e, e) == 0) {
	        	logger.info("Element Already Exists: "+e);
	            return;
	        }
	        treeInsert = treeRoot;
	        if (compare(cl,e,treeRoot.e) < 0) {
	        	treeRoot = treeRoot.left;
	        } else {
	        	treeRoot = treeRoot.right;
	        }
	    }
	    if (compare(cl,e,treeInsert.e) < 0) {
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
	
	public static TreeSearch<Object> read(Class cl){
		JSONArray jsonArray;
		if(cl.equals(Customer.class)){
			jsonArray = new JSONArray(ReadFile.read(Constants.CUSTOMER_DATA_URL));
			TreeSearch<Object> treeSearch = new TreeSearch<Object>();
			for(int i =0; i<jsonArray.length();i++){
				Customer customer = new Customer();
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				customer.setCcode(jsonObject.getString(Constants.CUSTOMER_CCODE));
				customer.setCusName(jsonObject.getString(Constants.CUSTOMER_CUSNAME));
				customer.setPhone(jsonObject.getString(Constants.CUSTOMER_PHONE));
				treeSearch.insert(Customer.class, customer);				
			}
			return treeSearch;
		}		
		if(cl.equals(Product.class)){
			jsonArray = new JSONArray(ReadFile.read(Constants.PRODUCT_DATA_URL));
			TreeSearch<Object> treeSearch = new TreeSearch<Object>();
			for(int i =0; i<jsonArray.length();i++){
				Product product = new Product();
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				product.setPcode(jsonObject.getString(Constants.PRODUCT_CODE));
				product.setProName(jsonObject.getString(Constants.PRODUCT_NAME));
				product.setQuantity(jsonObject.getInt(Constants.PRODUCT_QUANTITY));
				product.setSaled(jsonObject.getInt(Constants.PRODUCT_SALE));
				product.setPrice(jsonObject.getDouble(Constants.PRODUCT_PRICE));
				treeSearch.insert(Product.class, product);
			}
			return treeSearch;
		}
		/*if(cl.equals(Order.class)){
			jsonArray = new JSONArray(ReadFile.read(Constants.PRODUCT_DATA_URL));
			Order[] orders = new Order[jsonArray.length()];
			for(int i =0; i<jsonArray.length();i++){
				Order order = new Order();
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				order.setCcode(jsonObject.getString(Constants.ORDER_CUSTOMER_CODE));
				order.setPcode(jsonObject.getString(Constants.ORDER_PRODUCT_CODE));
				order.setQuantity(jsonObject.getInt(Constants.ORDER_QUANTITY));
			}
			return orders;
		}*/	
		return null;
	}
	
	Tree find(Class cl,Tree findTree, String code){
		if(findTree == null){
			return null;
		}
		if(cl.equals(Customer.class)){
			Customer customer = (Customer) findTree.e;
			if(customer.getCcode().compareTo(code)==0){
				return findTree;
			}else if (customer.getCcode().compareTo(code) > 0) {
				return find(cl,findTree.left,code);
			}else {
				return find(cl,findTree.right,code);
			}
		}
		if(cl.equals(Product.class)){
			Product product = (Product) findTree.e;
			if(product.getPcode().compareTo(code)==0){
				return findTree;
			}else if (product.getPcode().compareTo(code) > 0) {
				return find(cl,findTree.left,code);
			}else {
				return find(cl,findTree.right,code);
			}
		}
		return null;
	}

	public Object get(Class cl, String code) {
		return find(cl, root, code).e;
	}
}

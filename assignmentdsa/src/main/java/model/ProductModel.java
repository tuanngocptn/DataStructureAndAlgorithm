package model;

import etc.Constants;
import model.entities.Product;
import model.iofile.ReadFile;
import model.iofile.WriteFile;

import org.json.JSONArray;
import org.json.JSONObject;

import util.collection.DoubleLinkedLstQueue;
import util.collection.LinkedLstStack;
import util.sort.Sort;
import util.sort.impl.SelectSort;
import util.search.impl.TreeSearch;

public class ProductModel {
    /**
     * get all products from file
     * @return the linkedStack of product
     */
    public static LinkedLstStack<Product> getAll(){
        LinkedLstStack<Product> productLinkedLstStack = new LinkedLstStack<Product>();
        String strCustomerJson = ReadFile.read(Constants.PRODUCT_DATA_URL);
        JSONArray jsonArray = new JSONArray(strCustomerJson);
        
        for(int i = jsonArray.length() - 1; i >-1; i--){
        	JSONObject jsonObject =(JSONObject) jsonArray.get(i);
            Product product = new Product();
            product.setPcode(jsonObject.getString(Constants.PRODUCT_CODE));
            product.setProName(jsonObject.getString(Constants.PRODUCT_NAME));
            product.setQuantity(jsonObject.getInt(Constants.PRODUCT_QUANTITY));
            product.setSaled(jsonObject.getInt(Constants.PRODUCT_SALE));
            product.setPrice(jsonObject.getDouble(Constants.PRODUCT_PRICE));
            productLinkedLstStack.push(product);
        }        
        return productLinkedLstStack;
    }

    /**
     * method to save all product to file
     * @param productLinkedLstStack linkedList of product need to save
     * @return true if save successful
     */
    public static boolean saveAll( LinkedLstStack<Product> productLinkedLstStack){
        return WriteFile.write(Constants.PRODUCT_DATA_URL,productLinkedLstStack.display());
    }

    /**
     * add new product
     * @param product input need to add
     * @return true if add successful
     */
    public static boolean add(Product product){
        LinkedLstStack<Product> productLinkedLstStack = getAll();
        productLinkedLstStack.peek();
        Product productModel = productLinkedLstStack.get();
        while (productModel != null){
            if (productModel.getPcode().equals(product.getPcode())){
                return false;
            }
            productModel = productLinkedLstStack.get();
        }
        productLinkedLstStack.peek();
        productLinkedLstStack.push(product);
        saveAll(productLinkedLstStack);
        return true;
    }

    /**
     * delete product by code
     * @param code input to delete
     * @return true if delete successful
     */
    public static boolean deleteByCode(String code){
    	LinkedLstStack<Product> productLinkedLstStack = ProductModel.getAll();
    	productLinkedLstStack.peek();
    	Product product = productLinkedLstStack.get();
    	int serial = 0;
    	boolean flag = false;
    	while (product != null) {
    		if(product.getPcode().equals(code)){
    			flag = true;
    			break;
    		}
    		serial++;
    		product = productLinkedLstStack.get();
		}
    	if(flag){
    		productLinkedLstStack.peek();
	    	productLinkedLstStack.delete(serial);
	    	saveAll(productLinkedLstStack);
    	}
    	return flag;
    }

    /**
     * get treeSearch of Product from file
     * @return the treeSearch of product
     */
    public static TreeSearch<Product> getTreeSearch(){
		JSONArray jsonArray = new JSONArray(ReadFile.read(Constants.PRODUCT_DATA_URL));
		TreeSearch<Product> treeSearch = new TreeSearch<Product>(){
			public int compare(Product c1, Product c2) {
				return c1.getPcode().compareTo(c2.getPcode());
			}

			public boolean constains(Product e1, Product e2) {
				return e1.getPcode().toLowerCase().contains(e2.getPcode().toLowerCase());
			}
		};
		for(int i =0; i<jsonArray.length();i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
            Product product = new Product();
            product.setPcode(jsonObject.getString(Constants.PRODUCT_CODE));
            product.setProName(jsonObject.getString(Constants.PRODUCT_NAME));
            product.setQuantity(jsonObject.getInt(Constants.PRODUCT_QUANTITY));
            product.setSaled(jsonObject.getInt(Constants.PRODUCT_SALE));
            product.setPrice(jsonObject.getDouble(Constants.PRODUCT_PRICE));
			treeSearch.insert(Product.class, product);				
		}
		return treeSearch;
    }

    /**
     * Using treeSearch to get product have the code equal code product in tree
     * @param product input
     * @return product with full information or null if not
     */
    public static Product get(Product product) {
		TreeSearch<Product> treeSearch = getTreeSearch();
		return treeSearch.get(product);
	}

    /**
     * edit product.
     * @param product need edit
     * @return true if success
     */
	public static boolean editProduct(Product product){
        DoubleLinkedLstQueue<Product> productDoubleLinkedLstQueue = getAllDoubleLinkedLstQueue();
        for (int i = 0; i < productDoubleLinkedLstQueue.length(); i++) {
            if (productDoubleLinkedLstQueue.getAt(i).getPcode().equals(product.getPcode())){
                productDoubleLinkedLstQueue.delete(i);
                productDoubleLinkedLstQueue.insertAt(i,product);
                saveAllDoubleLinkedLstQueue(productDoubleLinkedLstQueue);
                return true;
            }
        }
        return false;
    }

    /**
     * sort product and save all to file
     * @param isLowToHigh type of sort
     * @return true if sort successful
     */
    @SuppressWarnings("unchecked")
    public static boolean sort(final boolean isLowToHigh){
        DoubleLinkedLstQueue<Product> productDoubleLinkedLstQueue = getAllDoubleLinkedLstQueue();
        Sort<Product> productSort = null;
        if(Constants.TYPE_SORT.equals(Constants.SELECT_SORT)){
        	productSort = new SelectSort<Product>() {
                public boolean compare(Object o, Object o1) {
                	Product product = (Product) o;
                	Product customer1 = (Product) o1;
                    if (isLowToHigh)
                        return product.getPcode().compareTo(customer1.getPcode()) > 0;
                    else
                        return product.getPcode().compareTo(customer1.getPcode()) < 0;
                }
            };
        }
        if (productSort!=null) {
            productDoubleLinkedLstQueue = productSort.sort(productDoubleLinkedLstQueue);
            saveAllDoubleLinkedLstQueue(productDoubleLinkedLstQueue);
            return true;
        }
        return false;
    }

    /**
     * get DoubleLinkedLstQueue of product
     * @return the Double Linked List Queue of product
     */
    private static DoubleLinkedLstQueue<Product> getAllDoubleLinkedLstQueue(){
        DoubleLinkedLstQueue<Product> productDoubleLinkedLstQueue = new DoubleLinkedLstQueue<Product>();
        String strCustomerJson = ReadFile.read(Constants.PRODUCT_DATA_URL);
        JSONArray jsonArray = new JSONArray(strCustomerJson);
        for (Object object: jsonArray) {
            JSONObject jsonObject =(JSONObject) object;
            Product product = new Product();
            product.setPcode(jsonObject.getString(Constants.PRODUCT_CODE));
            product.setProName(jsonObject.getString(Constants.PRODUCT_NAME));
            product.setQuantity(jsonObject.getInt(Constants.PRODUCT_QUANTITY));
            product.setSaled(jsonObject.getInt(Constants.PRODUCT_SALE));
            product.setPrice(jsonObject.getDouble(Constants.PRODUCT_PRICE));
            productDoubleLinkedLstQueue.insertLast(product);
        }
        return productDoubleLinkedLstQueue;
    }

    /**
     * Using tree search to search by code
     * @param code input
     * @return the double linked list queue of product result
     */
    public static DoubleLinkedLstQueue<Product> searchAll(String code){
    	Product product = new Product();
    	product.setPcode(code);
    	TreeSearch<Product> treeSearch = getTreeSearch();
    	return treeSearch.searchAll(product);
    }

    /**
     * save all Product to file
     * @param productDoubleLinkedLstQueue List need to save
     * @return true if save successful
     */
    private static boolean saveAllDoubleLinkedLstQueue( DoubleLinkedLstQueue<Product> productDoubleLinkedLstQueue){
        return WriteFile.write(Constants.PRODUCT_DATA_URL, productDoubleLinkedLstQueue.displayForward());
    }
}

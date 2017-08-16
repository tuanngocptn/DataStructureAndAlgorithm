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

    public static boolean saveAll( LinkedLstStack<Product> productLinkedLstStack){
        return WriteFile.write(Constants.PRODUCT_DATA_URL,productLinkedLstStack.display());
    }

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
    
    public static Product get(Product product) {
		TreeSearch<Product> treeSearch = getTreeSearch();
		return treeSearch.get(product);
	}
    
    @SuppressWarnings("unchecked")
    public static boolean sort(final boolean isLowToHigh){
        DoubleLinkedLstQueue<Product> customerDoubleLinkedLstQueue = getAllDoubleLinkedLstQueue();
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
            customerDoubleLinkedLstQueue = productSort.sort(customerDoubleLinkedLstQueue);
            saveAllDoubleLinkedLstQueue(customerDoubleLinkedLstQueue);
            return true;
        }
        return false;
    }
    
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
    
    public static DoubleLinkedLstQueue<Product> searchAll(String code){
    	Product product = new Product();
    	product.setPcode(code);
    	TreeSearch<Product> treeSearch = getTreeSearch();
    	return treeSearch.searchAll(product);
    }
    
    private static boolean saveAllDoubleLinkedLstQueue( DoubleLinkedLstQueue<Product> productDoubleLinkedLstQueue){
        return WriteFile.write(Constants.PRODUCT_DATA_URL, productDoubleLinkedLstQueue.displayForward());
    }
}

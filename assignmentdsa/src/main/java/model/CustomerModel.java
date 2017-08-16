package model;

import util.collection.DoubleLinkedLstQueue;
import model.entities.Customer;
import etc.Constants;
import model.iofile.ReadFile;
import model.iofile.WriteFile;

import org.json.JSONArray;
import org.json.JSONObject;

import util.sort.impl.SelectSort;
import util.sort.Sort;
import util.search.impl.TreeSearch;

public class CustomerModel {

    private CustomerModel(){

    }

    public static DoubleLinkedLstQueue<Customer> getAll(){
        DoubleLinkedLstQueue<Customer> customerDoubleLinkedLstQueue = new DoubleLinkedLstQueue<Customer>();
        String strCustomerJson = ReadFile.read(Constants.CUSTOMER_DATA_URL);
        JSONArray jsonArray = new JSONArray(strCustomerJson);
        for (Object object: jsonArray) {
            JSONObject jsonObject =(JSONObject) object;
            Customer customer = new Customer();
            customer.setCcode(jsonObject.getString(Constants.CUSTOMER_CCODE));
            customer.setCusName(jsonObject.getString(Constants.CUSTOMER_CUSNAME));
            customer.setPhone(jsonObject.getString(Constants.CUSTOMER_PHONE));
            customerDoubleLinkedLstQueue.insertLast(customer);
        }
        return customerDoubleLinkedLstQueue;
    }

    public static boolean saveAll( DoubleLinkedLstQueue<Customer> customerDoubleLinkedLstQueue){
        return WriteFile.write(Constants.CUSTOMER_DATA_URL, customerDoubleLinkedLstQueue.displayForward());
    }

    public static boolean add(Customer customer){
        DoubleLinkedLstQueue<Customer> doubleLinkedLstQueue = getAll();
        for(int i = 0; i< doubleLinkedLstQueue.length(); i++){
        	if(doubleLinkedLstQueue.getAt(i).getCcode().equals(customer.getCcode())){
        		return false;
        	}
        }
        doubleLinkedLstQueue.insertLast(customer);
        return saveAll(doubleLinkedLstQueue);
    }

    public static Customer findByCode(String code){
        DoubleLinkedLstQueue<Customer> doubleLinkedLstQueue = getAll();
        for (int i = 0; i < doubleLinkedLstQueue.length(); i++) {
            if (doubleLinkedLstQueue.getAt(i).getCcode().equals(code)){
                return doubleLinkedLstQueue.getAt(i);
            }
        }
        return null;
    }

    public static boolean deleteByCode(String code){
        DoubleLinkedLstQueue<Customer> doubleLinkedLstQueue = getAll();
        for (int i = 0; i < doubleLinkedLstQueue.length(); i++) {
            if (doubleLinkedLstQueue.getAt(i).getCcode().equals(code)){
                doubleLinkedLstQueue.delete(i);
                saveAll(doubleLinkedLstQueue);
                return true;
            }
        }
        return false;
    }


    @SuppressWarnings("unchecked")
    public static boolean sort(final boolean isLowToHigh){
        DoubleLinkedLstQueue<Customer> customerDoubleLinkedLstQueue = getAll();
        Sort<Customer> customerSort = null;
        if(Constants.TYPE_SORT.equals(Constants.SELECT_SORT)){
            customerSort = new SelectSort<Customer>() {
                public boolean compare(Object o, Object o1) {
                    Customer customer = (Customer) o;
                    Customer customer1 = (Customer) o1;
                    if (isLowToHigh)
                        return customer.getCcode().compareTo(customer1.getCcode()) >= 0;
                    else
                        return customer.getCcode().compareTo(customer1.getCcode()) <= 0;
                }
            };
        }
        if (customerSort!=null) {
            customerDoubleLinkedLstQueue = customerSort.sort(customerDoubleLinkedLstQueue);
            saveAll(customerDoubleLinkedLstQueue);
            return true;
        }
        return false;
    }
    
    public static DoubleLinkedLstQueue<Customer> searchAll(String code){
    	Customer customer = new Customer();
    	customer.setCcode(code);
    	TreeSearch<Customer> treeSearch = getTreeSearch();
    	return treeSearch.searchAll(customer);
    }
    
    public static TreeSearch<Customer> getTreeSearch(){
		JSONArray jsonArray = new JSONArray(ReadFile.read(Constants.CUSTOMER_DATA_URL));
		TreeSearch<Customer> treeSearch = new TreeSearch<Customer>(){
			public int compare(Customer c1, Customer c2) {
				return c1.getCcode().compareTo(c2.getCcode());
			}

			public boolean constains(Customer e1, Customer e2) {
				return e1.getCcode().toLowerCase().contains(e2.getCcode().toLowerCase());
			}
		};
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
    
    public static Customer get(Customer customer) {
		TreeSearch<Customer> treeSearch = getTreeSearch();
		return treeSearch.get(customer);
	}

}

package model;

import util.collection.LinkedLstDequeue;
import model.entities.Customer;
import etc.Constants;
import model.iofile.ReadFile;
import model.iofile.WriteFile;
import org.json.JSONArray;
import org.json.JSONObject;
import util.collection.sort.SelectSort;
import util.collection.sort.Sort;

public class CustomerModel {

    private CustomerModel(){

    }

    public static LinkedLstDequeue<Customer> getAll(){
        LinkedLstDequeue<Customer> customerLinkedLstDequeue = new LinkedLstDequeue<Customer>();
        String strCustomerJson = ReadFile.read(Constants.CUSTOMER_DATA_URL);
        JSONArray jsonArray = new JSONArray(strCustomerJson);
        for (Object object: jsonArray) {
            JSONObject jsonObject =(JSONObject) object;
            Customer customer = new Customer();
            customer.setCcode(jsonObject.getString(Constants.CUSTOMER_CCODE));
            customer.setCusName(jsonObject.getString(Constants.CUSTOMER_CUSNAME));
            customer.setPhone(jsonObject.getString(Constants.CUSTOMER_PHONE));
            customerLinkedLstDequeue.insertLast(customer);
        }
        return customerLinkedLstDequeue;
    }

    public static boolean saveAll( LinkedLstDequeue<Customer> customerLinkedLstDequeue){
        return WriteFile.write(Constants.CUSTOMER_DATA_URL,customerLinkedLstDequeue.displayForward());
    }

    public static boolean add(Customer customer){
        LinkedLstDequeue<Customer> linkedLstDequeue = getAll();
        for(int i = 0; i<linkedLstDequeue.length();i++){
        	if(linkedLstDequeue.getAt(i).getCcode().equals(customer.getCcode())){
        		return false;
        	}
        }
        linkedLstDequeue.insertLast(customer);
        return saveAll(linkedLstDequeue);
    }

    public static Customer findByCode(String code){
        LinkedLstDequeue<Customer> linkedLstDequeue = getAll();
        for (int i = 0; i < linkedLstDequeue.length(); i++) {
            if (linkedLstDequeue.getAt(i).getCcode().equals(code)){
                return linkedLstDequeue.getAt(i);
            }
        }
        return null;
    }

    public static boolean deleteByCode(String code){
        LinkedLstDequeue<Customer> linkedLstDequeue = getAll();
        for (int i = 0; i < linkedLstDequeue.length(); i++) {
            if (linkedLstDequeue.getAt(i).getCcode().equals(code)){
                linkedLstDequeue.delete(i);
                saveAll(linkedLstDequeue);
                return true;
            }
        }
        return false;
    }


    @SuppressWarnings("unchecked")
    public static boolean sort(final boolean isLowToHigh){
        LinkedLstDequeue<Customer> customerLinkedLstDequeue = getAll();
        Sort<Customer> customerSort = null;
        if(Constants.TYPE_SORT.equals(Constants.SELECT_SORT)){
            customerSort = new SelectSort<Customer>() {
                public boolean compare(Object o, Object o1) {
                    Customer customer = (Customer) o;
                    Customer customer1 = (Customer) o1;
                    if (isLowToHigh)
                        return customer.getCcode().compareTo(customer1.getCcode()) > 0;
                    else
                        return customer.getCcode().compareTo(customer1.getCcode()) < 0;
                }
            };
        }
        if (customerSort!=null) {
            customerLinkedLstDequeue = customerSort.sort(customerLinkedLstDequeue);
            saveAll(customerLinkedLstDequeue);
            return true;
        }
        return false;
    }

}

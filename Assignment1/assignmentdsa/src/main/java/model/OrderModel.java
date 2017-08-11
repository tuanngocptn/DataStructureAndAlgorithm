package model;

import model.entities.Customer;
import model.entities.Order;
import model.entities.Product;
import model.iofile.ReadFile;
import model.iofile.WriteFile;

import org.json.JSONArray;
import org.json.JSONObject;

import util.collection.LinkedLstDequeue;
import etc.Constants;

public class OrderModel {
	public OrderModel(){
		
	}
	
	public static LinkedLstDequeue<Order> getAll(){
        LinkedLstDequeue<Order> orderLinkedLstDequeue = new LinkedLstDequeue<Order>();
        String strCustomerJson = ReadFile.read(Constants.ORDER_DATA_URL);
        JSONArray jsonArray = new JSONArray(strCustomerJson);
        for (Object object: jsonArray) {
            JSONObject jsonObject =(JSONObject) object;
            Order order = new Order();
            order.setCcode(jsonObject.getString(Constants.ORDER_CUSTOMER_CODE));
            order.setPcode(jsonObject.getString(Constants.ORDER_PRODUCT_CODE));
            order.setQuantity(jsonObject.getInt(Constants.ORDER_QUANTITY));
            orderLinkedLstDequeue.insertLast(order);
        }
        return orderLinkedLstDequeue;
    }
	
	public static boolean saveAll( LinkedLstDequeue<Order> orderLinkedLstDequeue){
        return WriteFile.write(Constants.ORDER_DATA_URL,orderLinkedLstDequeue.displayForward());
    }
	
	public static boolean add(Order order){
		LinkedLstDequeue<Order> linkedLstDequeue = getAll();
		Product product = new Product();
		product.setPcode(order.getPcode());
		Customer customer = new Customer();
		customer.setCcode(order.getCcode());
		product = ProductModel.get(product);
		customer = CustomerModel.get(customer);
		if(customer != null && product != null){			
		    linkedLstDequeue.insertLast(order);
		    return saveAll(linkedLstDequeue);
		}
		return false;
    }
	
}

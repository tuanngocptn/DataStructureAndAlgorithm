package util.search;

import model.entities.Customer;
import model.entities.Order;
import model.entities.Product;
import model.iofile.ReadFile;

import org.json.JSONArray;
import org.json.JSONObject;

import etc.Constants;

public class SearchObject {
	Object[] read(Class cl){
		JSONArray jsonArray;
		if(cl.equals(Customer.class)){
			jsonArray = new JSONArray(ReadFile.read(Constants.CUSTOMER_DATA_URL));
			Customer[] customers = new Customer[jsonArray.length()];
			for(int i =0; i<jsonArray.length();i++){
				Customer customer = new Customer();
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				customer.setCcode(jsonObject.getString(Constants.CUSTOMER_CCODE));
				customer.setCusName(jsonObject.getString(Constants.CUSTOMER_CUSNAME));
				customer.setPhone(jsonObject.getString(Constants.CUSTOMER_PHONE));
				customers[i] = customer;				
			}
			return customers;
		}		
		if(cl.equals(Product.class)){
			jsonArray = new JSONArray(ReadFile.read(Constants.PRODUCT_DATA_URL));
			Product[] products = new Product[jsonArray.length()];
			for(int i =0; i<jsonArray.length();i++){
				Product product = new Product();
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				product.setPcode(jsonObject.getString(Constants.PRODUCT_CODE));
				product.setProName(jsonObject.getString(Constants.PRODUCT_NAME));
				product.setQuantity(jsonObject.getInt(Constants.PRODUCT_QUANTITY));
				product.setSaled(jsonObject.getInt(Constants.PRODUCT_SALE));
				product.setPrice(jsonObject.getDouble(Constants.PRODUCT_PRICE));
				products[i] = product;
			}
			return products;
		}
		if(cl.equals(Order.class)){
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
		}
		return null;
	}
}

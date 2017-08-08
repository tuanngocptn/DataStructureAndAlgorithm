package model;

import etc.Constants;
import model.entities.Customer;
import model.entities.Product;
import model.iofile.ReadFile;
import model.iofile.WriteFile;
import org.json.JSONArray;
import org.json.JSONObject;
import util.collection.LinkedLstStack;

public class ProductModel {
    public static LinkedLstStack<Product> getAll(){
        LinkedLstStack<Product> productLinkedLstStack = new LinkedLstStack<Product>();
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
            productLinkedLstStack.push(product);
        }
        return productLinkedLstStack;
    }

    public static boolean saveAll( LinkedLstStack<Product> productLinkedLstStack){
        return WriteFile.write(Constants.PRODUCT_DATA_URL,productLinkedLstStack.display());
    }

    public static boolean add(Product product){
        LinkedLstStack<Product> productLinkedLstStack = getAll();
        productLinkedLstStack.push(product);
        productLinkedLstStack.peek();
        while (!productLinkedLstStack.isEmpty()){
            if (productLinkedLstStack.get().equals(product.getPcode())){
                return false;
            }
        }
        productLinkedLstStack.peek();
        productLinkedLstStack.push(product);
        return true;
    }
}

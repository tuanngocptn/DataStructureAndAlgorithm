package model;

import com.google.gson.Gson;
import model.entities.Customer;
import model.entities.Order;
import model.entities.Product;
import model.iofile.ReadFile;
import model.iofile.WriteFile;

import org.json.JSONArray;
import org.json.JSONObject;

import util.collection.DoubleLinkedLstQueue;
import util.collection.LinkedLstStack;
import util.sort.Sort;
import util.sort.impl.SelectSort;
import etc.Constants;

/**
 * @author : Pham Tuan Ngoc - id : gc01007 - class : bt007
 */

public class OrderModel {
    private static final String FORMAT_SEARCH_TEXT = "{\"customer\":%s,\"product\":%s}";

    /**
     * private constructor
     */
    private OrderModel() {
        //never use
    }

    /**
     * get Order list
     * @return Double Linked list queue of Order
     */
    public static DoubleLinkedLstQueue<Order> getAll() {
        DoubleLinkedLstQueue<Order> orderDoubleLinkedLstQueue = new DoubleLinkedLstQueue<Order>();
        String strCustomerJson = ReadFile.read(Constants.ORDER_DATA_URL);
        JSONArray jsonArray = new JSONArray(strCustomerJson);
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            Order order = new Order();
            order.setCcode(jsonObject.getString(Constants.ORDER_CUSTOMER_CODE));
            order.setPcode(jsonObject.getString(Constants.ORDER_PRODUCT_CODE));
            order.setQuantity(0);
            orderDoubleLinkedLstQueue.insertLast(order);
        }
        return orderDoubleLinkedLstQueue;
    }

    /**
     * Save all to file
     * @param orderDoubleLinkedLstQueue list need to save
     * @return true if save successful
     */
    public static boolean saveAll(DoubleLinkedLstQueue<Order> orderDoubleLinkedLstQueue) {
        return WriteFile.write(Constants.ORDER_DATA_URL, orderDoubleLinkedLstQueue.displayForward());
    }

    public static boolean add(Order order) {
        DoubleLinkedLstQueue<Order> doubleLinkedLstQueue = getAll();
        String productStr = order.getPcode();
        String[] arrProduct = productStr.split(",");
        for (String str: arrProduct) {
            String[] productArr = str.split(":");
            Product product = new Product();
            product.setPcode(productArr[0]);
            product = ProductModel.get(product);
            if (product.getQuantity() < Integer.parseInt(productArr[1])){
                return false;
            }
            product.setQuantity(product.getQuantity() - Integer.parseInt(productArr[1]));
            product.setSaled(product.getSaled() + Integer.parseInt(productArr[1]));
            ProductModel.editProduct(product);
        }
        doubleLinkedLstQueue.insertLast(order);
        return saveAll(doubleLinkedLstQueue);
    }

    /**
     * sort Order
     * @param isPCode true when sort by product code and false when sort by customer code
     * @param isLowToHigh type sort
     * @return true if sort successful
     */
    @SuppressWarnings("unchecked")
    public static boolean sort(boolean isPCode, final boolean isLowToHigh) {
        DoubleLinkedLstQueue<Order> orderDoubleLinkedLstQueue = getAll();
        Sort<Order> orderSort = null;
        if (isPCode) {
            if (Constants.TYPE_SORT.equals(Constants.SELECT_SORT)) {
                orderSort = new SelectSort<Order>() {
                    public boolean compare(Object o, Object o1) {
                        Order order = (Order) o;
                        Order order1 = (Order) o1;
                        if (isLowToHigh)
                            return order.getPcode().compareTo(order1.getPcode()) > 0;
                        else
                            return order.getPcode().compareTo(order1.getPcode()) < 0;
                    }
                };
            }
        } else {
            if (Constants.TYPE_SORT.equals(Constants.SELECT_SORT)) {
                orderSort = new SelectSort<Order>() {
                    public boolean compare(Object o, Object o1) {
                        Order order = (Order) o;
                        Order order1 = (Order) o1;
                        if (isLowToHigh)
                            return order.getCcode().compareTo(order1.getCcode()) > 0;
                        else
                            return order.getCcode().compareTo(order1.getCcode()) < 0;
                    }
                };
            }
        }
        if (orderSort != null) {
            orderDoubleLinkedLstQueue = orderSort.sort(orderDoubleLinkedLstQueue);
            saveAll(orderDoubleLinkedLstQueue);
            return true;
        }
        return false;
    }

    /**
     * get order by customer code and product code
     * @param ccode customer code input
     * @param pcode product code input
     * @return the String of Order with json format
     */
    public static String get(String ccode, String pcode) {
        Gson gson = new Gson();
        String result = Constants.DEFAULT_RESULT;
        Customer customer = new Customer();
        customer.setCcode(ccode);
        customer = CustomerModel.get(customer);

        String[] strArr = pcode.split(",");
        LinkedLstStack<Product> productLinkedLstStack = new LinkedLstStack<Product>();
        for (String str: strArr) {
            String[] arr = str.split(":");
            Product product = new Product();
            product.setPcode(arr[0]);
            product = ProductModel.get(product);
            product.setSaled(0);
            product.setQuantity(Integer.parseInt(arr[1]));
            productLinkedLstStack.push(product);
        }
        if(customer != null){
            result = String.format(FORMAT_SEARCH_TEXT,gson.toJson(customer),productLinkedLstStack.display());
        }
        return result;
    }
}

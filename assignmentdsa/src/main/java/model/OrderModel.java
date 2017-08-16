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
import util.sort.Sort;
import util.sort.impl.SelectSort;
import etc.Constants;

public class OrderModel {
    private static final String FORMAT_SEARCH_TEXT = "{\"customer\":%s,\"product\":%s}";

    public OrderModel() {

    }

    public static DoubleLinkedLstQueue<Order> getAll() {
        DoubleLinkedLstQueue<Order> orderDoubleLinkedLstQueue = new DoubleLinkedLstQueue<Order>();
        String strCustomerJson = ReadFile.read(Constants.ORDER_DATA_URL);
        JSONArray jsonArray = new JSONArray(strCustomerJson);
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            Order order = new Order();
            order.setCcode(jsonObject.getString(Constants.ORDER_CUSTOMER_CODE));
            order.setPcode(jsonObject.getString(Constants.ORDER_PRODUCT_CODE));
            order.setQuantity(jsonObject.getInt(Constants.ORDER_QUANTITY));
            orderDoubleLinkedLstQueue.insertLast(order);
        }
        return orderDoubleLinkedLstQueue;
    }

    public static boolean saveAll(DoubleLinkedLstQueue<Order> orderDoubleLinkedLstQueue) {
        return WriteFile.write(Constants.ORDER_DATA_URL, orderDoubleLinkedLstQueue.displayForward());
    }

    public static boolean add(Order order) {
        DoubleLinkedLstQueue<Order> doubleLinkedLstQueue = getAll();
        Product product = new Product();
        product.setPcode(order.getPcode());
        Customer customer = new Customer();
        customer.setCcode(order.getCcode());
        product = ProductModel.get(product);
        customer = CustomerModel.get(customer);
        if (customer != null && product != null) {
            doubleLinkedLstQueue.insertLast(order);
            return saveAll(doubleLinkedLstQueue);
        }
        return false;
    }

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

    public static String get(String ccode, String pcode) {
        Gson gson = new Gson();
        String result = Constants.DEFAULT_RESULT;
        Product product = new Product();
        Customer customer = new Customer();
        product.setPcode(pcode);
        customer.setCcode(ccode);
        product = ProductModel.get(product);
        customer = CustomerModel.get(customer);
        if (product != null && customer != null) {
            result = String.format(FORMAT_SEARCH_TEXT, gson.toJson(customer), gson.toJson(product));
        }
        return result;
    }
}

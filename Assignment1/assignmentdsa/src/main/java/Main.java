import model.ProductModel;
import model.entities.Product;
import util.collection.LinkedLstDequeue;
import model.entities.Customer;
import model.CustomerModel;
import util.collection.LinkedLstStack;
import util.search.SearchObject;
import util.search.impl.TreeSearch;

public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        /*LinkedLstStack<Product> linkedLstStack = new LinkedLstStack<Product>();
        for (int i = 3; i >= 0; i--) {
            Product product = new Product();
            product.setPrice(i);
            product.setSaled(i);
            product.setQuantity(i);
            product.setProName(""+i);
            product.setPcode(""+i);
            linkedLstStack.push(product);
        }
        ProductModel.saveAll(linkedLstStack);
        System.out.println(ProductModel.getAll().display());*/
//    	SearchObject<Customer> searchObject = new SearchObject<Customer>();
//    	TreeSearch<Object> treeSearch = TreeSearch.read(Customer.class);
//    	Customer customer = (Customer)treeSearch.get(Customer.class, "c1");
//    	System.out.println(customer.getCcode());
//    	System.out.print(Customer.class.equals(Customer.class));
    	Customer customer = new Customer();
    	customer.setCcode("c2");
    	System.out.println(CustomerModel.get(customer).getPhone());
    }

}

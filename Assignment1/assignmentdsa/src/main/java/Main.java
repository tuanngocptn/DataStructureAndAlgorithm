import model.ProductModel;
import model.entities.Product;
import util.collection.LinkedLstDequeue;
import model.entities.Customer;
import model.CustomerModel;
import util.collection.LinkedLstStack;
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
    	TreeSearch<Customer> treeSearch = new TreeSearch<Customer>();
    	treeSearch.get(Product.class, "");
//    	System.out.print(Customer.class.equals(Customer.class));
    }

}

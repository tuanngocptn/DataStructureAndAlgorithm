import model.ProductModel;
import model.entities.Product;
import util.collection.LinkedLstStack;


public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
       LinkedLstStack<Product> linkedLstStack = new LinkedLstStack<Product>();
        for (int i = 0; i < 9; i++) {
            Product product = new Product();
            product.setPrice(i);
            product.setSaled(i);
            product.setQuantity(i);
            product.setProName(""+i);
            product.setPcode(""+i);
            linkedLstStack.push(product);
        }
        ProductModel.saveAll(linkedLstStack);
        System.out.println(ProductModel.getAll().display());
//        linkedLstStack.peek();
//        while (linkedLstStack.hasNext()) {
//            System.out.println(linkedLstStack.get().getPcode());
//		}
//        System.out.println(ProductModel.getAll().display());
//        System.out.println(linkedLstStack.display());
//    	SearchObject<Customer> searchObject = new SearchObject<Customer>();
//    	TreeSearch<Object> treeSearch = TreeSearch.read(Customer.class);
//    	Customer customer = (Customer)treeSearch.get(Customer.class, "c1");
//    	System.out.println(customer.getCcode());
//    	System.out.print(Customer.class.equals(Customer.class));
//    	Customer customer = new Customer();
//    	customer.setCcode("c2");
//    	System.out.println(CustomerModel.get(customer).getPhone());
        ProductModel.sort(true);
        System.out.println(ProductModel.getAll().display());
    	
    }

}

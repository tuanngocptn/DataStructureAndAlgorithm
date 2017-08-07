import util.collection.LinkedLstDequeue;
import model.entities.Customer;
import model.CustomerModel;

public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        LinkedLstDequeue<Customer> customerLinkedLstDequeue = new LinkedLstDequeue<Customer>();
        for (int i = 3; i >= 0; i--) {
            Customer customer = new Customer();
            customer.setCcode("c" + i);
            customer.setCusName("name");
            customer.setPhone(Integer.toString(i));
            customerLinkedLstDequeue.insertLast(customer);
        }
        CustomerModel.saveAll(customerLinkedLstDequeue);

        System.out.println(CustomerModel.getAll().displayForward());

        CustomerModel.sort(true);
        System.out.println(CustomerModel.getAll().displayForward());
    }

}

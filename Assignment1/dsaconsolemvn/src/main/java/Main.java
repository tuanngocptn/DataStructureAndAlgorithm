import collection.LinkedLstDequeue;
import entities.Customer;
import model.CustomerModel;
import sort.SelectSort;
import sort.Sort;

public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        LinkedLstDequeue<Customer> customerLinkedLstDequeue = new LinkedLstDequeue<Customer>();
        for (int i = 3; i >= 0; i--) {
            Customer customer = new Customer();
            customer.setCcode("c"+i);
            customer.setCusName("name");
            customer.setPhone(Integer.toString(i));
            customerLinkedLstDequeue.insertLast(customer);
        }
        CustomerModel.saveAll(customerLinkedLstDequeue);

//        System.out.println(CustomerModel.getAll().displayForward());

       customerLinkedLstDequeue = CustomerModel.getAll();
        Sort<Customer> sort = new SelectSort<Customer>() {
            @Override
            public boolean compare(Object o, Object o1) {
                Customer customer = (Customer) o;
                Customer customer1 = (Customer) o1;
                return customer.getCcode().compareTo(customer1.getCcode()) > 0;
            }
        };
        customerLinkedLstDequeue = sort.sort(customerLinkedLstDequeue);
        System.out.println(customerLinkedLstDequeue.displayForward());


    }

}

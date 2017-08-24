import model.CustomerModel;
import model.OrderModel;
import model.ProductModel;
import model.entities.Customer;
import model.entities.Order;
import model.entities.Product;
import util.collection.DoubleLinkedLstQueue;
import util.collection.LinkedLstStack;


public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        System.out.println(CustomerModel.getAll().displayForward());
        Customer customer = new Customer();
        customer.setCcode("C10");
        customer.setCusName("PT");
        customer.setPhone("123");
        CustomerModel.editCustomer(customer);
        System.out.println(CustomerModel.getAll().displayForward());

        /*resetProduct();
        resetCustomer();
        resetOrder();*/
    }

    private static void resetOrder() {
        DoubleLinkedLstQueue<Order> orderDoubleLinkedLstQueue = new DoubleLinkedLstQueue<Order>();
        orderDoubleLinkedLstQueue.insertLast(newOrder("C01","MT01",10));
        orderDoubleLinkedLstQueue.insertLast(newOrder("C02","CD01",5));
        OrderModel.saveAll(orderDoubleLinkedLstQueue);
    }

    private static void resetProduct(){
        LinkedLstStack<Product> productLinkedLstStack = new LinkedLstStack<Product>();
        productLinkedLstStack.push(newProduct("MT01","Mì Ăn Liền Hảo Hảo",100,20,5500));
        productLinkedLstStack.push(newProduct("CD01","Con Đom Đóm",150,10,40000));
        productLinkedLstStack.push(newProduct("DA01","Dầu Ăn Neptune",50,5,100000));
        productLinkedLstStack.push(newProduct("MD02","Mì Ăn Liền Miliket",200,10,6000));
        productLinkedLstStack.push(newProduct("NG01","Cocacola",300,10,12000));
        productLinkedLstStack.push(newProduct("KE01","Kẹo Mút Chuppachup",150,10,40000));
        productLinkedLstStack.push(newProduct("NG02","Pepsi",250,10,10000));
        productLinkedLstStack.push(newProduct("KD01","Colgate",40,0,45000));
        productLinkedLstStack.push(newProduct("KE02","Kẹo dynamite",250,10,20000));
        productLinkedLstStack.push(newProduct("KD02","CloseUp",500,3,55000));
        ProductModel.saveAll(productLinkedLstStack);
    }

    private static void resetCustomer(){
        DoubleLinkedLstQueue<Customer> customerDoubleLinkedLstQueue = new DoubleLinkedLstQueue<Customer>();
        customerDoubleLinkedLstQueue.insertLast(newCustomer("C01","Phi Nguyen","84685646853"));
        customerDoubleLinkedLstQueue.insertLast(newCustomer("C02","Thanh Hang Duong","84953573985"));
        customerDoubleLinkedLstQueue.insertLast(newCustomer("C03","Dương Đình Vũ","84268954352"));
        customerDoubleLinkedLstQueue.insertLast(newCustomer("C04","Tony Tín Quang Nguyễn","84975321685"));
        customerDoubleLinkedLstQueue.insertLast(newCustomer("C05","Nguyễn Tấn Phát","84965386795"));
        customerDoubleLinkedLstQueue.insertLast(newCustomer("C06","Alexander Huỳnh","84965268751"));
        customerDoubleLinkedLstQueue.insertLast(newCustomer("C07","Ngọc Thịnh","84986216795"));
        customerDoubleLinkedLstQueue.insertLast(newCustomer("C08","Ph Hoàng","84521687953"));
        customerDoubleLinkedLstQueue.insertLast(newCustomer("C09","Dat Nguyen","84213697568"));
        customerDoubleLinkedLstQueue.insertLast(newCustomer("C10","Ngọc Lọc Cọc","84975216852"));
        CustomerModel.saveAll(customerDoubleLinkedLstQueue);
    }

    private static Product newProduct(String code, String name, int quantity, int sale, float price){
        Product product = new Product();
        product.setPcode(code);
        product.setProName(name);
        product.setQuantity(quantity);
        product.setSaled(sale);
        product.setPrice(price);
        return product;
    }

    private static Customer newCustomer(String code, String name, String phone){
        Customer customer = new Customer();
        customer.setCcode(code);
        customer.setCusName(name);
        customer.setPhone(phone);
        return customer;
    }

    private static Order newOrder(String ccode, String pcode,int quantity){
        Order order = new Order();
        order.setCcode(ccode);
        order.setPcode(pcode);
        order.setQuantity(quantity);
        return order;
    }
}

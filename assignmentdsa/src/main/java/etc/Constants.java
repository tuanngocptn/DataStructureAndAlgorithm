package etc;

/**
 *
 * @author : Pham Tuan Ngoc
 *
 * this is class to define all constants in project
 */
public class Constants {
	
	//URL host to file
//	public static String ROOT_PATH = "/home/local/SUTRIXMEDIA1/ngoc.pt/Documents/green/code/DataStructureAndAlgorithm/assignmentdsa/";
	public static final String ROOT_PATH = "/home/panda/Documents/hoctap/dsa/code/DataStructureAndAlgorithm/assignmentdsa/";

	//URI to file
    public static final String CUSTOMER_DATA_URL = "src/main/java/etc/data/customer_list.txt";

    public static final String ORDER_DATA_URL = "src/main/java/etc/data/order_list.txt";

    public static final String PRODUCT_DATA_URL = "src/main/java/etc/data/product_list.txt";
    
    //Param of customer
    public static final String CUSTOMER_CCODE = "ccode";

    public static final String CUSTOMER_CUSNAME = "cusName";

    public static final String CUSTOMER_PHONE = "phone";
    
    //Pram of product
    public static final String PRODUCT_CODE = "pcode";

    public static final String PRODUCT_NAME = "proName";

    public static final String PRODUCT_QUANTITY = "quantity";

    public static final String PRODUCT_SALE = "saled";

    public static final String PRODUCT_PRICE = "price";
    
    //Param of order
    public static final String ORDER_PRODUCT_CODE = "pcode";

    public static final String ORDER_CUSTOMER_CODE = "ccode";

    public static final String ORDER_QUANTITY= "quantity";
    
    //Action request from client
    public static final String IS_LOW_TO_HIGH = "isLowToHigh";
    
    public static final String ACTION = "action";
    
    public static final String GET_ALL_ACTION = "getAll";
    
    public static final String ADD_ACTION = "add";
    
    public static final String DELETE_ACTION = "delete";
    
    public static final String SORT_ACTION = "sort";

    public static final String SEARCH_ACTION = "search";  

    public static final String SEARCH_TYPE = "type";

    public static final String FIND_ORDER_ACTION = "search";
    
    //Defauld result of servlet
    public static final String DEFAULT_RESULT = "[]";
    
    //type of sort
    public static final String TYPE_SORT = "select";

    public static final String SELECT_SORT = "select";  
}

package control.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CustomerModel;
import model.OrderModel;
import model.iofile.ReadFile;

import org.apache.commons.lang.StringUtils;
import org.apache.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import control.Init;
import etc.Constants;

/**
 * Servlet implementation class Order
 */
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(Order.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Order() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Init.setHeader(request, response);
        PrintWriter print = response.getWriter();
        String action = request.getParameter(Constants.ACTION);
        if (StringUtils.isBlank(action)) {
            Init.badRequest(response);
            return;
        }
        
        if (action.equals(Constants.GET_ALL_ACTION)) {
            print.write(ReadFile.read(Constants.ORDER_DATA_URL));
            return;
        }
        
        if (action.equals(Constants.ADD_ACTION)) {
        	String ccode = request.getParameter(Constants.ORDER_CUSTOMER_CODE);
            String pcode = request.getParameter(Constants.ORDER_PRODUCT_CODE);
            String strQuantity = request.getParameter(Constants.ORDER_QUANTITY);
            if (StringUtils.isBlank(ccode) || StringUtils.isBlank(pcode) || StringUtils.isBlank(strQuantity)) {
                Init.badRequest(response);
                return;
            }
            int quantity = 0;
            try{
            	quantity = Integer.parseInt(strQuantity);
            }catch(ParseException e){
            	logger.error("ParseException: ",e);
            }
            
            model.entities.Order order = new model.entities.Order();
            order.setCcode(ccode);
            order.setPcode(pcode);
            order.setQuantity(quantity);
            if (OrderModel.add(order)) {
                print.write(ReadFile.read(Constants.ORDER_DATA_URL));
                return;
            }
            Init.forbidden(response);
            return;
        }
	}
}

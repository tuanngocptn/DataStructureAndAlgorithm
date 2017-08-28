package control.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrderModel;
import model.ProductModel;
import model.entities.Product;
import model.iofile.ReadFile;

import org.apache.commons.lang.StringUtils;
import org.apache.http.ParseException;
import org.apache.log4j.Logger;

import control.Init;
import etc.Constants;

/**
 * @author : Pham Tuan Ngoc - id : gc01007 - class : bt007
 * <p>
 * this is servlet class provided Order API
 */
public class Order extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(Order.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Init.setHeader(request, response);
        PrintWriter print;
        try {
            print = response.getWriter();
        } catch (Exception ex) {
            logger.error("getWriter Exception", ex);
            return;
        }
        String action = request.getParameter(Constants.ACTION);
        if (StringUtils.isBlank(action)) {
            Init.badRequest(response, "Action null or empty");
            return;
        }

        if (action.equals(Constants.GET_ALL_ACTION)) {
            print.write(ReadFile.read(Constants.ORDER_DATA_URL));
            return;
        }

        if (action.equals(Constants.ADD_ACTION)) {
            String ccode = request.getParameter(Constants.ORDER_CUSTOMER_CODE);
            String pcode = request.getParameter(Constants.ORDER_PRODUCT_CODE);
            if (StringUtils.isBlank(ccode) || StringUtils.isBlank(pcode)) {
                Init.badRequest(response, "Any field blank or wrong type input");
                return;
            }

            model.entities.Order order = new model.entities.Order();
            order.setCcode(ccode);
            order.setPcode(pcode);
            order.setQuantity(0);
            if (OrderModel.add(order)) {
                print.write(ReadFile.read(Constants.ORDER_DATA_URL));
                return;
            }
            Init.forbidden(response, "Add Order false");
            return;
        }

        if (action.equals(Constants.SORT_ACTION)) {
            String type = request.getParameter(Constants.SEARCH_TYPE);
            String strLowToHigh = request.getParameter(Constants.IS_LOW_TO_HIGH);
            if (StringUtils.isBlank(strLowToHigh) || !(strLowToHigh.equals("1") || strLowToHigh.equals("0"))
                    || StringUtils.isBlank(type) || !(type.equals(Constants.ORDER_PRODUCT_CODE) || type.equals(Constants.ORDER_CUSTOMER_CODE))) {
                Init.badRequest(response, "please fill all field");
                return;
            }
            if (OrderModel.sort(type.equals(Constants.ORDER_PRODUCT_CODE), strLowToHigh.equals("1"))) {
                print.write(ReadFile.read(Constants.ORDER_DATA_URL));
                return;
            }
            Init.forbidden(response, "Sort false");
            return;
        }
        if (action.equals(Constants.SEARCH_ACTION)) {
            String ccode = request.getParameter(Constants.ORDER_CUSTOMER_CODE);
            String pcode = request.getParameter(Constants.ORDER_PRODUCT_CODE);
            if (StringUtils.isBlank(ccode) || StringUtils.isBlank(pcode)) {
                Init.badRequest(response, "ccode or pcode is null or empty");
                return;
            }
            print.write(OrderModel.get(ccode, pcode));
            return;
        }

        print.write(Constants.DEFAULT_RESULT);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            doPost(request, response);
        } catch (Exception ex) {
            logger.error("Exception", ex);
        }

    }
}

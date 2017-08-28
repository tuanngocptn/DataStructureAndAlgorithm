package control.servlet;

import control.Init;
import etc.Constants;
import jdk.nashorn.internal.runtime.ParserException;
import model.CustomerModel;
import model.iofile.ReadFile;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author : Pham Tuan Ngoc - id : gc01007 - class : bt007
 * <p>
 * this is servlet class provided Customer API
 */
public class Customer extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Customer.class);
    private static final String FORMAT_SEARCH_TEXT = "{\"ccode\":\"%s\",\"cusName\":\"%s\",\"phone\":\"%s\"}";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Init.setHeader(request, response);
        PrintWriter print;
        try {
            print = response.getWriter();
        } catch (Exception ex) {
            logger.error("Exception", ex);
            return;
        }
        String action = request.getParameter(Constants.ACTION);
        if (StringUtils.isBlank(action)) {
            Init.badRequest(response, "action null or empty");
            return;
        }

        if (action.equals(Constants.GET_ALL_ACTION)) {
            print.write(ReadFile.read(Constants.CUSTOMER_DATA_URL));
            return;
        }

        if (action.equals(Constants.ADD_ACTION)) {
            String ccode = request.getParameter(Constants.CUSTOMER_CCODE);
            String cusName = request.getParameter(Constants.CUSTOMER_CUSNAME);
            String phone = request.getParameter(Constants.CUSTOMER_PHONE);
            if (StringUtils.isBlank(ccode) || StringUtils.isBlank(cusName) || StringUtils.isBlank(phone)) {
                Init.badRequest(response, "Any field blank or wrong type input");
                return;
            }
            model.entities.Customer customer = new model.entities.Customer();
            customer.setCcode(ccode);
            customer.setCusName(cusName);
            customer.setPhone(phone);
            try {
                long longPhone = Long.parseLong(phone);
                if(longPhone < 100000000 || longPhone > 10000000*10000000){
                    Init.forbidden(response, "Wrong phone number");
                    return;
                }
            }catch (ParserException ex){
                Init.forbidden(response, "Wrong phone number");
                return;
            }
            if (CustomerModel.add(customer)) {
                print.write(ReadFile.read(Constants.CUSTOMER_DATA_URL));
                return;
            }
            Init.forbidden(response, "Add false");
            return;
        }

        if (action.equals(Constants.DELETE_ACTION)) {
            String ccode = request.getParameter(Constants.CUSTOMER_CCODE);
            if (StringUtils.isBlank(ccode)) {
                Init.badRequest(response, "please fill all field");
                return;
            }
            if (CustomerModel.deleteByCode(ccode)) {
                print.write(ReadFile.read(Constants.CUSTOMER_DATA_URL));
                return;
            }
            Init.forbidden(response, "delete false");
            return;
        }

        if (action.equals(Constants.SORT_ACTION)) {
            String strLowToHigh = request.getParameter(Constants.IS_LOW_TO_HIGH);
            if (StringUtils.isBlank(strLowToHigh) || !(strLowToHigh.equals("1") || strLowToHigh.equals("0"))) {
                Init.badRequest(response, "Any field blank or wrong type input");
                return;
            }
            if (CustomerModel.sort(strLowToHigh.equals("1"))) {
                print.write(ReadFile.read(Constants.CUSTOMER_DATA_URL));
                return;
            }
            Init.forbidden(response, "sort false");
            return;
        }

        if (action.equals(Constants.EDIT_ACTION)) {
            String ccode = request.getParameter(Constants.CUSTOMER_CCODE);
            String cusName = request.getParameter(Constants.CUSTOMER_CUSNAME);
            String phone = request.getParameter(Constants.CUSTOMER_PHONE);
            if (StringUtils.isBlank(ccode) || StringUtils.isBlank(cusName) || StringUtils.isBlank(phone)) {
                Init.badRequest(response, "please fill all field");
                return;
            }
            try {
                long longPhone = Long.parseLong(phone);
                if(longPhone < 100000000 || longPhone > 10000000*10000000){
                    Init.forbidden(response, "Wrong phone number");
                    return;
                }
            }catch (ParserException ex){
                Init.forbidden(response, "Wrong phone number");
                return;
            }
            model.entities.Customer customer = new model.entities.Customer();
            customer.setCcode(ccode);
            customer.setCusName(cusName);
            customer.setPhone(phone);
            if (CustomerModel.editCustomer(customer)) {
                print.write(ReadFile.read(Constants.CUSTOMER_DATA_URL));
                return;
            }
            Init.forbidden(response, "edit false");
            return;
        }


        if (action.equals(Constants.SEARCH_ACTION)) {
            String code = request.getParameter(Constants.CUSTOMER_CCODE);
            if (StringUtils.isBlank(code)) {
                Init.badRequest(response, "please fill all field");
                return;
            }
            print.write(CustomerModel.searchAll(code).displayForward());
            return;
        }

        if (action.equals(Constants.SEARCH_BY_CODE_ACTION)) {
            String code = request.getParameter(Constants.CUSTOMER_CCODE);
            if (StringUtils.isBlank(code)) {
                Init.badRequest(response, "please fill all field");
                return;
            }
            model.entities.Customer customer = new model.entities.Customer();
            customer.setCcode(code);
            customer = CustomerModel.get(customer);
            print.write(String.format(FORMAT_SEARCH_TEXT, customer.getCcode(), customer.getCusName(), customer.getPhone()));
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

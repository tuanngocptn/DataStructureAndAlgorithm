package control.servlet;

import control.Init;
import etc.Constants;
import model.CustomerModel;
import model.ProductModel;
import model.iofile.ReadFile;

import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class Customer extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Init.setHeader(request, response);
        PrintWriter print = response.getWriter();
        String action = request.getParameter(Constants.ACTION);
        if (StringUtils.isBlank(action)) {
            Init.badRequest(response);
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
                Init.badRequest(response);
                return;
            }
            model.entities.Customer customer = new model.entities.Customer();
            customer.setCcode(ccode);
            customer.setCusName(cusName);
            customer.setPhone(phone);
            if (CustomerModel.add(customer)) {
                print.write(ReadFile.read(Constants.CUSTOMER_DATA_URL));
                return;
            }
            Init.forbidden(response);
            return;
        }

        if (action.equals(Constants.DELETE_ACTION)) {
            String ccode = request.getParameter(Constants.CUSTOMER_CCODE);
            if (StringUtils.isBlank(ccode)) {
                Init.badRequest(response);
                return;
            }
            if (CustomerModel.deleteByCode(ccode)) {
                print.write(ReadFile.read(Constants.CUSTOMER_DATA_URL));
                return;
            }
            Init.forbidden(response);
            return;
        }
        
        if (action.equals(Constants.SORT_ACTION)) {
            String strLowToHigh = request.getParameter(Constants.IS_LOW_TO_HIGH);
            if (StringUtils.isBlank(strLowToHigh) || !(strLowToHigh.equals("1") || strLowToHigh.equals("0"))) {
                Init.badRequest(response);
                return;
            }
            if (CustomerModel.sort(strLowToHigh.equals("1"))) {
                print.write(ReadFile.read(Constants.CUSTOMER_DATA_URL));
                return;
            }
            Init.forbidden(response);
            return;
        }
        
        if (action.equals(Constants.SEARCH_ACTION)) {
        	String code = request.getParameter(Constants.CUSTOMER_CCODE);
            if (StringUtils.isBlank(code)) {
                Init.badRequest(response);
                return;
            }
            print.write(CustomerModel.searchAll(code).displayForward());
            return;
        }
        print.write(Constants.DEFAULT_RESULT);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

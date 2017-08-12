package control.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductModel;
import model.iofile.ReadFile;

import org.apache.commons.lang.StringUtils;
import org.apache.http.ParseException;
import org.apache.log4j.Logger;

import control.Init;
import etc.Constants;

/**
 *
 * @author : Pham Tuan Ngoc
 *
 * this is servlet class provided Order API
 */
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(Product.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Init.setHeader(request, response);
        PrintWriter print = response.getWriter();
        String action = request.getParameter(Constants.ACTION);
        if (StringUtils.isBlank(action)) {
            Init.badRequest(response);
            return;
        }

        if (action.equals(Constants.GET_ALL_ACTION)) {
            print.write(ReadFile.read(Constants.PRODUCT_DATA_URL));
            return;
        }
        
        if (action.equals(Constants.ADD_ACTION)) {
            String pcode = request.getParameter(Constants.PRODUCT_CODE);
            String proName = request.getParameter(Constants.PRODUCT_NAME);
            String quantity = request.getParameter(Constants.PRODUCT_QUANTITY);
            String saled = request.getParameter(Constants.PRODUCT_SALE);
            String price = request.getParameter(Constants.PRODUCT_PRICE);
            if (StringUtils.isBlank(pcode) || StringUtils.isBlank(proName) || StringUtils.isBlank(quantity)
            		|| StringUtils.isBlank(saled) || StringUtils.isBlank(price)) {
                Init.badRequest(response);
                return;
            }
            int quantityInte = 0;
            int saledInte = 0;
            Double priceDoub = 0.0;
            try{
            	quantityInte = Integer.parseInt(quantity);
            	saledInte = Integer.parseInt(saled);
            	priceDoub = Double.parseDouble(price);
            }catch(ParseException ex){
            	logger.error("ParseException: ",ex);
            }
            model.entities.Product product = new model.entities.Product();
            product.setPcode(pcode);
            product.setProName(proName);
            product.setQuantity(quantityInte);
            product.setSaled(saledInte);
            product.setPrice(priceDoub);
            if (ProductModel.add(product)) {
                print.write(ReadFile.read(Constants.PRODUCT_DATA_URL));
                return;
            }
            Init.forbidden(response);
            return;
        }
        
        if (action.equals(Constants.DELETE_ACTION)) {
            String pcode = request.getParameter(Constants.PRODUCT_CODE);
            if (StringUtils.isBlank(pcode)) {
                Init.badRequest(response);
                return;
            }
            if (ProductModel.deleteByCode(pcode)) {
                print.write(ReadFile.read(Constants.PRODUCT_DATA_URL));
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
            if (ProductModel.sort(strLowToHigh.equals("1"))) {
                print.write(ReadFile.read(Constants.PRODUCT_DATA_URL));
                return;
            }
            Init.forbidden(response);
            return;
        }
        if (action.equals(Constants.SEARCH_ACTION)) {
            String code = request.getParameter(Constants.PRODUCT_CODE);
            if (StringUtils.isBlank(code)) {
                Init.badRequest(response);
                return;
            }
            print.write(ProductModel.searchAll(code).displayForward());
            return;
        }
        print.write(Constants.DEFAULT_RESULT);
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}

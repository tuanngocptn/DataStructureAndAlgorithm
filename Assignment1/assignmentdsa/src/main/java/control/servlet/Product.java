package control.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import control.Init;
import etc.Constants;
import model.CustomerModel;
import model.entities.Customer;
import model.iofile.ReadFile;

/**
 * Servlet implementation class Product
 */
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ACTION = "action";
	private static final String GET_ALL_ACTION = "getAll";
	private static final String ADD_ACTION = "add";
	private static final String DELETE_ACTION = "delete";
	private static final String CODE_PARAM = "ccode";
	private static final String NAME_PARAM = "cusName";
	private static final String PHONE_PARAM = "phone";
	private static final String DEFAULT_RESULT = "[]";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Init.setHeader(request, response);
		PrintWriter print = response.getWriter();
		String action = request.getParameter(ACTION);
		if (StringUtils.isBlank(action)) {
			Init.badRequest(response);
			return;
		}
		
		if (action.equals(GET_ALL_ACTION)) {
			print.write(ReadFile.read(Constants.CUSTOMER_DATA_URL));
			return;
		}
		
		if(action.equals(ADD_ACTION)){
			String ccode = request.getParameter(CODE_PARAM);
			String cusName = request.getParameter(NAME_PARAM);
			String phone = request.getParameter(PHONE_PARAM);
			if(StringUtils.isBlank(ccode) || StringUtils.isBlank(cusName) || StringUtils.isBlank(phone)){
				Init.badRequest(response);
				return;
			}
			Customer customer = new Customer();
			customer.setCcode(ccode);
			customer.setCusName(cusName);
			customer.setPhone(phone);
			if(CustomerModel.add(customer)){
				print.write(ReadFile.read(Constants.CUSTOMER_DATA_URL));
				return;
			}
			Init.forbidden(response);
			return;
		}		
		
		if(action.equals(DELETE_ACTION)){
			String ccode = request.getParameter(CODE_PARAM);
			if(StringUtils.isBlank(ccode)){
				Init.badRequest(response);
				return;
			}
			if(CustomerModel.deleteByCode(ccode)){
				print.write(ReadFile.read(Constants.CUSTOMER_DATA_URL));
				return;
			}
			Init.forbidden(response);
			return;
		}
		print.write(DEFAULT_RESULT);
	}

}

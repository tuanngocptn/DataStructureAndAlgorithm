package control;


import org.apache.log4j.Logger;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author : Pham Tuan Ngoc
 *
 * this is class use to set header when servlet return value
 */
public class Init {
    final static Logger logger = Logger.getLogger(Init.class);

    /**
     * Allow get api from ajax
     *
     * @param request servlet request
     * @param response servlet response
     */
    public static void setHeader(HttpServletRequest request, HttpServletResponse response) {
        String clientOrigin = request.getHeader("origin");
        response.setHeader("Access-Control-Allow-Origin", clientOrigin);
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }

    /**
     * return 400 status code to client
     *
     * @param response servlet response
     */
    public static void badRequest(HttpServletResponse response) {
        response.setContentType("text/html");
        response.setStatus(400);
        try {
            response.getWriter().write("<!DOCTYPE html><html><head><title>400: Bad Request</title></head><body>");
            response.getWriter().write("<h1>400: Bad Request</h1>The server cannot or will not process the request due to an apparent client error");
            response.getWriter().write("</body></html>");
        } catch (IOException e) {
            logger.error("IOException: ", e);
        }
    }

    /**
     * return 403 status code to client
     *
     * @param response servlet response
     */
    public static void forbidden(HttpServletResponse response){
        response.setContentType("text/html");
        response.setStatus(403);
        try {
            response.getWriter().write("<!DOCTYPE html><html><head><title>403: Forbidden</title></head><body>");
            response.getWriter().write("<h1>403: Forbidden</h1>The request was valid, but the server is refusing action. The user might not have the necessary permissions for a resource, or may need an account of some sort.");
            response.getWriter().write("</body></html>");
        } catch (IOException e) {
            logger.error("IOException: ", e);
        }
    }
}

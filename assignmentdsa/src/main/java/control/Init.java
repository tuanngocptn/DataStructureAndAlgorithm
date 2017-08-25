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

    /**
     * private constructor
     */
    private Init() {
        // not call
    }

    private static final Logger logger = Logger.getLogger(Init.class);

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
    public static void badRequest(HttpServletResponse response, String message) {
        response.setStatus(400);
        try {
            response.getWriter().write("{\"message\":\"" + message + "\"}");
        } catch (IOException e) {
            logger.error("IOException: ", e);
        }
    }

    /**
     * return 403 status code to client
     *
     * @param response servlet response
     */
    public static void forbidden(HttpServletResponse response, String message){
        response.setContentType("text/html");
        response.setStatus(403);
        try {
            response.getWriter().write("{\"message\":\"" + message + "\"}");
        } catch (IOException e) {
            logger.error("IOException: ", e);
        }
    }
}

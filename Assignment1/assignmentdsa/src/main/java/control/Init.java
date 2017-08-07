package control;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Init {
	public static void setHeader(HttpServletRequest request, HttpServletResponse response){
		String clientOrigin = request.getHeader("origin");
		response.setHeader("Access-Control-Allow-Origin", clientOrigin);
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
		response.setContentType("application/json");
	}
	
	public static void badRequest(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setStatus(400);
		response.getWriter().write("<!DOCTYPE html><html><head><title>400: Bad Request</title></head><body>");
		response.getWriter().write("<h1>400: Bad Request</h1>The server cannot or will not process the request due to an apparent client error");
		response.getWriter().write("</body></html>");
	}
	
	public static void forbidden(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setStatus(403);
		response.getWriter().write("<!DOCTYPE html><html><head><title>403: Forbidden</title></head><body>");
		response.getWriter().write("<h1>403: Forbidden</h1>The request was valid, but the server is refusing action. The user might not have the necessary permissions for a resource, or may need an account of some sort.");
		response.getWriter().write("</body></html>");
	}
}

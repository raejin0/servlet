package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 1. parameter transmit function.
 * http://localhost:8080/request-param?username=hello&age=20
 */

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		System.out.println("[get all parameters]");
		request.getParameterNames().asIterator()
				.forEachRemaining(paramName -> System.out.println(paramName + " = " + request.getParameter(paramName)));
		System.out.println();

		System.out.println("[get single parameter]");
		String username = request.getParameter("username");
		String age = request.getParameter("age");
		System.out.println("username = " + username);
		System.out.println("age = " + age);
		System.out.println();

		System.out.println("[get duplicate paramter names]");
		// http://localhost:8080/request-param?username=hello&age=20&username=hello2
		String[] usernames = request.getParameterValues("username");
		for (String name : usernames) {
			System.out.println("s = " + name);
		}

		response.getWriter().write("ok");
	}
}

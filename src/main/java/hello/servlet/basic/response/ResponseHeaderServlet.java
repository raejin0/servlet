package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// [status-line]
		response.setStatus(HttpServletResponse.SC_OK);
		//response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

		// [response-header]
		response.setHeader("Content-type", "text/plain;charset=utf-8");

		// invalidate cache
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate" );
		response.setHeader("Pragma", "no-cache");   // 과거버전 캐시 무효화

		response.setHeader("my-header", "hello"); // creating own header

		// [header - convenience methods]
		content(response);
		cookie(response);
		redirect(response);

		// [message body]
		PrintWriter writer = response.getWriter();
		writer.println("ok ");
	}

	private void content(HttpServletResponse response){
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		// response.setContentLength(2); // auto creation when omitted.
	}

	private void cookie(HttpServletResponse response) {
		// response.setHeader("Set-Cookie", "myCookie=good, Max-Age=600");
		Cookie cookie = new Cookie("myCookie", "good");
		cookie.setMaxAge(600); // 600 seconds
		response.addCookie(cookie);
	}


	private void redirect(HttpServletResponse response) throws IOException {
		/*response.setStatus(HttpServletResponse.SC_FOUND); // 302
		response.setHeader("Location", "/basic/hello-form.html");*/
		response.sendRedirect("/basic/hello-form.html");
	}
}

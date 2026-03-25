package web.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.LoginService;

/**
 * HTTP end-point to handle login service.
 */
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	        throws IOException, ServletException {

	    System.out.println("[LoginServlet] GET");

	    resp.setContentType("text/html");
	    PrintWriter writer = resp.getWriter();

	    // Show login form
	    writer.println("<html>");
	    writer.println("<head><title>Login Page</title></head>");
	    writer.println("<body>");

	    writer.println("<h2>Login Form</h2>");
	    writer.println("<form method='post' action='/login'>");

	    writer.println("Username: <input type='text' name='username'><br><br>");
	    writer.println("Password: <input type='password' name='passwd'><br><br>");
	    writer.println("DOB: <input type='text' name='dob' placeholder='yyyy-mm-dd'><br><br>");

	    writer.println("<input type='submit' value='Login'>");

	    writer.println("</form>");
	    writer.println("</body>");
	    writer.println("</html>");
	}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        System.out.println("[LoginServlet] POST");

        String username = req.getParameter("username");
        String password = req.getParameter("passwd");
        String dob = req.getParameter("dob");

        System.out.println("Username/password: " + username + ", " + password + ", DOB: " + dob);

        String loginStatus = "fail";

        if (LoginService.login(username, password, dob)) {
            loginStatus = "success";
        }

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        writer.println("<html>");
        writer.println("<head><title>" + loginStatus + "</title></head>");
        writer.println("<body>");
        writer.println("<h2 id='status'>Login status: " + loginStatus + "</h2>");
        writer.println("</body>");
        writer.println("</html>");
    }
}
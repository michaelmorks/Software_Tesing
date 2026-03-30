package web.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.service.RegistrationService;

/**
 * HTTP end-point to handle registration request.
 */
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        System.out.println("[RegistrationServlet] GET");
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head><title>Registration Page</title></head>");
        writer.println("<body>");
        writer.println("<h2>Registration Form</h2>");
        writer.println("<form method='post' action='/reg'>");
        writer.println("First Name: <input type='text' name='fname'><br><br>");
        writer.println("Last Name: <input type='text' name='lname'><br><br>");
        writer.println("Email: <input type='text' name='email'><br><br>");
        writer.println("DOB: <input type='text' name='dob' placeholder='yyyy-mm-dd'><br><br>");
        writer.println("Password: <input type='password' name='password'><br><br>");
        writer.println("Phone: <input type='text' name='phone'><br><br>");
        writer.println("<input type='submit' value='Register'>");
        writer.println("</form>");
        writer.println("</body>");
        writer.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        System.out.println("[RegistrationServlet] POST");

        String fName    = req.getParameter("fname");
        String lName    = req.getParameter("lname");
        String email    = req.getParameter("email");
        String dob      = req.getParameter("dob");
        String password = req.getParameter("password");
        String phone    = req.getParameter("phone");

        System.out.println("Registration attempt: " + fName + " " + lName +
                           ", Email: " + email + ", DOB: " + dob +
                           ", Phone: " + phone);

        String regStatus = "fail";
        if (RegistrationService.register(fName, lName, email, dob, password, phone)) {
            regStatus = "success";
        }

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head><title>" + regStatus + "</title></head>");
        writer.println("<body>");
        writer.println("<h2 id='status'>Registration status: " + regStatus + "</h2>");
        writer.println("</body>");
        writer.println("</html>");
    }
}
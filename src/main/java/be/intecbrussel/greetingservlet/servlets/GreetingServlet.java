package be.intecbrussel.greetingservlet.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GreetingServlet", value = "/greeting",initParams = @WebInitParam(name = "text",value = "Hi how are you?"))
public class GreetingServlet extends HttpServlet {
    private String text;


    @Override
    public void init() throws ServletException {
        log("init called");
        text = getInitParameter("text");
        if (text == null)
            throw new ServletException("Parameter text not found");


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log("doGet called");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title> Hello Servlet</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println(text);
        writer.println("</body>");
        writer.println("</html>");

    }

    @Override
    public void destroy() {
        log("destroy called");
    }
}

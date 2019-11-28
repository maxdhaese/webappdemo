package be.intecbrussel.greetingservlet.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/reverse")
public class ReverseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reverseText = req.getParameter("reverseText");
        StringBuilder sb = new StringBuilder(reverseText);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        writer.println("<html>");
        writer.println("<head>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>");
        writer.println("reversed text: ");
        writer.println("</h1>");
        writer.println("<h1>");
        writer.println(sb.reverse());
        writer.println("</body>");
        writer.println("</html>");
        writer.close();
    }
}

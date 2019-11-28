package be.intecbrussel.greetingservlet.servlets;

import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/square")
public class SquareServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String squareNumber  = req.getParameter("squareNumber");
        String outputText;
        if (NumberUtils.isCreatable(squareNumber)){
            //calculate the square
            int result = NumberUtils.createInteger(squareNumber);
            outputText = "The square of " + result + ": "+ (result *result);
        }else {
            //generate error message
            outputText = "Wrong input! you have to input a number!";
        }


        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        writer.println("<html>");
        writer.println("<head>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>");
        writer.println(outputText);
        writer.println("</h1>");
        writer.println("</body>");
        writer.println("</html>");
        writer.close();
}
}

package be.intecbrussel.greetingservlet.servlets;

import be.intecbrussel.greetingservlet.dao.MessageDao;
import be.intecbrussel.greetingservlet.model.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDateTime;

@WebServlet("/guestbook")
public class GuestBookServlet extends HttpServlet {

    private MessageDao messageDao;

    @Override
    public void init() throws ServletException {
        messageDao = new MessageDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //returns list of messages from guestbook
        // JDBC driver name and database URL
        String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
        String DB_URL = "jdbc:mariadb://noelvaes.eu/StudentDB";

        //  Database credentials
        String USER = "student";
        String PASS = "student123";

        // Set response content type
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String title = "Database Result";

        try {
            // Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            // Open a connection
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute SQL query
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT *  FROM GuestBook";
            ResultSet rs = stmt.executeQuery(sql);

            // Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                Date date = rs.getDate("Date");
                String name = rs.getString("Name");
                String message = rs.getString("Message");


                //Display values
                out.println( date + "   " + name + ":   " + message + "<br>" );



            }
            out.println("</body></html>");

            rs.close();
            stmt.close();
            conn.close();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //posts a message to the guestbook database
        try {
        Message message = new Message();

        message.setDate(LocalDateTime.now());
        message.setName(req.getParameter("name"));
        message.setMessage(req.getParameter("message"));

        messageDao.createMessage(message);

        PrintWriter out = resp.getWriter();
        out.println("<html><body><b>New message send!"
                    + "</b></body></html>");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}

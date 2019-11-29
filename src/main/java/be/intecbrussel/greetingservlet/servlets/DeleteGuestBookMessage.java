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
import java.sql.SQLException;
import java.time.LocalDateTime;

@WebServlet("/deletemessage")
public class DeleteGuestBookMessage extends HttpServlet {

    private MessageDao messageDao;

    @Override
    public void init() throws ServletException {
        messageDao = new MessageDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //delete a message
        try {
            Message message = new Message();

            message.setDate(LocalDateTime.now());
            message.setName(req.getParameter("name"));

            messageDao.deleteMessage(message);

            PrintWriter out = resp.getWriter();
            out.println("<html><body><b>Message Deleted"
                    + "</b></body></html>");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

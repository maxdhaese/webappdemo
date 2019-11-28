package be.intecbrussel.greetingservlet.dao;

import be.intecbrussel.greetingservlet.model.Message;

import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;

public class MessageDao {

    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
        public void createMessage(Message message) throws SQLException {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://noelvaes.eu/StudentDB","student","student123");
            PreparedStatement createStatement = connection.prepareStatement("insert into GuestBook (Date, Name, Message) values (?, ?, ?)");//enter sql statement

            createStatement.setDate(1, Date.valueOf(LocalDate.now()));
            createStatement.setString(2,message.getName());
            createStatement.setString(3,message.getMessage());

            createStatement.executeUpdate();

            createStatement.close();
            connection.close();

    }

}

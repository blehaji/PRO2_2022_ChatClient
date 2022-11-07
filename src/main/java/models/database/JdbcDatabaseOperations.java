package models.database;

import models.Message;

import java.sql.*;
import java.util.List;

public class JdbcDatabaseOperations implements DatabaseOperations {
    private final Connection connection;

    public JdbcDatabaseOperations(String driver, String url) throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        connection = DriverManager.getConnection(url);
    }

    @Override
    public void addMessage(Message message) {
        String sql =
                "INSERT INTO ChatMessages(author, text, created)" +
                " VALUES (" +
                        "'"+ message.getAuthor() +"'" +
                        "'"+ message.getText() +"'" +
                        "'"+ Timestamp.valueOf(message.getCreated()) +"'" +
                ")";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Message> getMessages() {
        return null;
    }
}

package am.itspace.myfriendservlet.service;

import am.itspace.myfriendservlet.db.DbConnectionProvider;
import am.itspace.myfriendservlet.model.Message;
import am.itspace.myfriendservlet.model.User;
import am.itspace.myfriendservlet.util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageService {
    Connection connection = DbConnectionProvider.getInstance().getConnection();

    public List<Message> getAllMessagesByUserAndFriendId(int user_id, int friend_id) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM message WHERE from_id = ? OR from_id = ? ORDER BY id ASC";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, friend_id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                messages.add(Message.builder()
                        .id(rs.getInt(1))
                        .fromId(User.builder().id(rs.getInt(2)).build())
                        .toId(User.builder().id(rs.getInt(3)).build())
                        .message(rs.getString(4))
                        .date(rs.getDate(5))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return messages;
    }
    public void add(Message message) {

        try {
            String sql = "INSERT INTO message(from_id,to_id,message,date,) VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, message.getFromId().getId());
            preparedStatement.setInt(2, message.getToId().getId());
            preparedStatement.setString(3, message.getMessage());
            preparedStatement.setString(4, DateUtil.fromDateToSqlString(message.getDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

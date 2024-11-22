package am.itspace.myfriendservlet.service;

import am.itspace.myfriendservlet.db.DbConnectionProvider;
import am.itspace.myfriendservlet.model.FriendRequest;
import am.itspace.myfriendservlet.model.User;
import am.itspace.myfriendservlet.util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendRequestService {
    Connection connection = DbConnectionProvider.getInstance().getConnection();

    public void add(FriendRequest friendRequest) {

        try {
            String sql = "INSERT INTO friend_request(from_id,to_id,date) VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, friendRequest.getFromId().getId());
            preparedStatement.setInt(2, friendRequest.getToId().getId());
            preparedStatement.setString(3, DateUtil.fromDateToSqlString(friendRequest.getDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<FriendRequest> getAllFriendRequestByFromId(int fromId) {
        List<FriendRequest> result = new ArrayList<>();
        String sql = "SELECT * FROM friend_request " +
                "INNER JOIN user u1 ON friend_request.from_id = u1.id " +
                "INNER JOIN user u2 ON friend_request.to_id =u2.id " +
                "WHERE from_id = " + fromId;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(FriendRequest.builder()
                        .id(resultSet.getInt(1))
                        .fromId(User.builder()
                                .id(resultSet.getInt(5))
                                .name(resultSet.getString(6))
                                .surname(resultSet.getString(7))
                                .email(resultSet.getString(8))
                                .password(resultSet.getString(9))
                                .imageName(resultSet.getString(10))
                                .build())
                        .toId(User.builder()
                                .id(resultSet.getInt(11))
                                .name(resultSet.getString(12))
                                .surname(resultSet.getString(13))
                                .email(resultSet.getString(14))
                                .password(resultSet.getString(15))
                                .imageName(resultSet.getString(16))
                                .build())
                        .date(resultSet.getDate(4))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<FriendRequest> getAllFriendRequestByToId(int toId) {
        List<FriendRequest> result = new ArrayList<>();
        String sql = "SELECT * FROM friend_request " +
                "INNER JOIN user u1 ON friend_request.from_id = u1.id " +
                "INNER JOIN user u2 ON friend_request.to_id =u2.id " +
                "WHERE to_id = " + toId;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(FriendRequest.builder()
                        .id(resultSet.getInt(1))
                        .fromId(User.builder()
                                .id(resultSet.getInt(5))
                                .name(resultSet.getString(6))
                                .surname(resultSet.getString(7))
                                .email(resultSet.getString(8))
                                .password(resultSet.getString(9))
                                .imageName(resultSet.getString(10))
                                .build())
                        .toId(User.builder()
                                .id(resultSet.getInt(11))
                                .name(resultSet.getString(12))
                                .surname(resultSet.getString(13))
                                .email(resultSet.getString(14))
                                .password(resultSet.getString(15))
                                .imageName(resultSet.getString(16))
                                .build())
                        .date(resultSet.getDate(4))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void deleteFriendRequest(int friendRequestId) {
        String sql = "DELETE FROM friend_request WHERE id = " + friendRequestId;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public FriendRequest getFriendRequestByFromId(int fromId) {
        FriendRequest friendRequest = null;
        String sql = "SELECT * FROM friend_request " +
                "INNER JOIN user u1 ON friend_request.from_id = u1.id " +
                "INNER JOIN user u2 ON friend_request.to_id =u2.id " +
                "WHERE to_id = " + fromId;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()) {
                friendRequest=(FriendRequest.builder()
                        .id(resultSet.getInt(1))
                        .fromId(User.builder()
                                .id(resultSet.getInt(5))
                                .name(resultSet.getString(6))
                                .surname(resultSet.getString(7))
                                .email(resultSet.getString(8))
                                .password(resultSet.getString(9))
                                .imageName(resultSet.getString(10))
                                .build())
                        .toId(User.builder()
                                .id(resultSet.getInt(11))
                                .name(resultSet.getString(12))
                                .surname(resultSet.getString(13))
                                .email(resultSet.getString(14))
                                .password(resultSet.getString(15))
                                .imageName(resultSet.getString(16))
                                .build())
                        .date(resultSet.getDate(4))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friendRequest;
    }
}

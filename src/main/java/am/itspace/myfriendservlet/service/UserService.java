package am.itspace.myfriendservlet.service;

import am.itspace.myfriendservlet.db.DbConnectionProvider;
import am.itspace.myfriendservlet.model.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private Connection connection = DbConnectionProvider.getInstance().getConnection();


    public void add(User user) {

        try {
            String sql = "INSERT INTO user(name,surname,email,password,image_name) VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getImageName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setImageName(resultSet.getString("image_name"));
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public User getUserByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setImageName(resultSet.getString("image_name"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setImageName(resultSet.getString("image_name"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void deleteUser(int userId) {
        String sql = "DELETE FROM user WHERE id = " + userId;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int id) {
        try {
            String sql = "SELECT * FROM user WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .imageName(resultSet.getString("image_name"))
                        .build();
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void acceptFriend(User user, User user2) {
        String sql = "INSERT INTO friends(user_id, friend_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getId());
            statement.setInt(2, user2.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user2.getId());
            statement.setInt(2, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<User> getFriends(int id) {
        List<User> result = new ArrayList<>();
        String sql = "SELECT * FROM friends " +
                "INNER JOIN user u1 ON friend_request.friend_id = u1.id " +
                "where user_id = " + id;

        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getInt(3))
                        .name(resultSet.getString(4))
                        .surname(resultSet.getString(5))
                        .email(resultSet.getString(6))
                        .password(resultSet.getString(7))
                        .imageName(resultSet.getString(8))
                        .build();
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


}

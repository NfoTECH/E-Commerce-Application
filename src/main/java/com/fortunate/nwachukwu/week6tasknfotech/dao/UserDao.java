package com.fortunate.nwachukwu.week6tasknfotech.dao;

import com.fortunate.nwachukwu.week6tasknfotech.connection.DBConnector;
import com.fortunate.nwachukwu.week6tasknfotech.model.User;

import java.sql.*;

public class UserDao {
    private Connection connection;

    private String query;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public User userLogin(String email, String password) {
        User user = null;
        try {
            query = "select * from users where email=? and password=?";
            statement = this.connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return user;
    }
}
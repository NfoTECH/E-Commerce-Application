package com.fortunate.nwachukwu.week6tasknfotech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


import com.fortunate.nwachukwu.week6tasknfotech.model.Order;
import com.fortunate.nwachukwu.week6tasknfotech.model.Product;

public class OrderDao {

    private Connection connection;

    private String query;
    private PreparedStatement statement;
    private ResultSet resultSet;



    public OrderDao(Connection connection) {
        super();
        this.connection = connection;
    }

    public boolean insertOrder(Order model) {
        boolean result = false;
        try {
            query = "INSERT INTO orders (productId, userId, orderQty, orderDate) VALUES(?,?,?,?)";
            statement = this.connection.prepareStatement(query);
            statement.setInt(1, model.getId());
            statement.setInt(2, model.getUid());
            statement.setInt(3, model.getQuantity());
            statement.setString(4, model.getDate());
            statement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }


    public List<Order> userOrders(int id) {
        List<Order> list = new ArrayList<>();
        try {
            query = "SELECT * FROM orders WHERE userId = ? ORDER BY orderId DESC";
            statement = this.connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                ProductDao productDao = new ProductDao(this.connection);
                int pId = resultSet.getInt("productId");
                Product product = productDao.getSingleProduct(pId);
                order.setOrderId(resultSet.getInt("orderId"));
                order.setId(pId);
                order.setName(product.getName());
                order.setCategory(product.getCategory());
                order.setPrice(product.getPrice() * resultSet.getInt("orderQty"));
                order.setQuantity(resultSet.getInt("orderQty"));
                order.setDate(resultSet.getString("orderDate"));
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void cancelOrder(int id) {
        try {
            query = "DELETE FROM orders WHERE orderId = ?";
            statement = this.connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            //result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
        //return result;
    }
}
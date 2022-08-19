package com.fortunate.nwachukwu.week6tasknfotech.dao;

import com.fortunate.nwachukwu.week6tasknfotech.connection.DBConnector;
import com.fortunate.nwachukwu.week6tasknfotech.model.Cart;
import com.fortunate.nwachukwu.week6tasknfotech.model.Product;

import java.sql.*;
import java.util.*;


public class ProductDao {
    private Connection connection;

    private int id;
    private String query;
    private PreparedStatement statement;
    private ResultSet resultSet;


    public ProductDao(Connection connection) {
        super();
        this.connection = connection;
    }



    public List<Product> getAllProducts() {
        List<Product> book = new ArrayList<>();
        try {

            query = "select * from products";
            statement = this.connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product row = new Product();
                row.setId(resultSet.getInt("id"));
                row.setName(resultSet.getString("name"));
                row.setCategory(resultSet.getString("category"));
                row.setPrice(resultSet.getDouble("price"));
                row.setImage(resultSet.getString("image"));
                row.setQuantity(resultSet.getInt("quantity"));

                book.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return book;
    }


    public Product getSingleProduct(int id) {
        Product row = null;
        try {
            query = "select * from products where id = ? ";

            statement = this.connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                row = new Product();
                row.setId(rs.getInt("id"));
                row.setName(rs.getString("name"));
                row.setCategory(rs.getString("category"));
                row.setPrice(rs.getDouble("price"));
                row.setImage(rs.getString("image"));
                row.setQuantity(rs.getInt("quantity"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return row;
    }

    public double getTotalCartPrice(ArrayList<Cart> cartList) {
        double sum = 0;
        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    query = "select price from products where id= ?";
                    statement = this.connection.prepareStatement(query);
                    statement.setInt(1, item.getId());
                    resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        sum += resultSet.getDouble("price")*item.getQuantity();
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return sum;
    }


    public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
        List<Cart> book = new ArrayList<>();
        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    query = "select * from products where id=?";
                    statement = this.connection.prepareStatement(query);
                    statement.setInt(1, item.getId());
                    resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        Cart row = new Cart();
                        row.setId(resultSet.getInt("id"));
                        row.setName(resultSet.getString("name"));
                        row.setCategory(resultSet.getString("category"));
                        row.setPrice(resultSet.getDouble("price") * item.getQuantity());
                        row.setQuantity(item.getQuantity());
                        book.add(row);
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return book;
    }

    public boolean addProduct(Product product) {
        try {
            String Query = "INSERT INTO products (name,category,price,quantity) VALUES" + "(?,?,?,?);";
            statement = DBConnector.getConnection().prepareStatement(Query);
            statement.setString(1, product.getName());
            statement.setString(2, product.getCategory());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getQuantity());

            int resultSet1 = statement.executeUpdate();
            if (resultSet1 > 0) return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    public boolean deleteProducts(int id) {
        try {
            String Query = "DELETE from products WHERE id = ?";
            statement = DBConnector.getConnection().prepareStatement(Query);
            statement.setInt(1,id);

            int resultSet1 = statement.executeUpdate();
            if (resultSet1 > 0) return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    public boolean updateProduct(Product product) {
        try {
            String Query = "Update products set name = ?,category = ?,price =?,quantity = ? where id = ?";
            statement = DBConnector.getConnection().prepareStatement(Query);
            statement.setString(1, product.getName());
            statement.setString(2, product.getCategory());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getQuantity());
            statement.setInt(5, product.getId());


            int resultSet1 = statement.executeUpdate();
            if (resultSet1 > 0) return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

}
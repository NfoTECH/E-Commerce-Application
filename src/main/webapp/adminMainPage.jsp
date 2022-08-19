<%@ page import="com.fortunate.nwachukwu.week6tasknfotech.model.User" %>
<%@ page import="com.fortunate.nwachukwu.week6tasknfotech.dao.ProductDao" %>
<%@ page import="com.fortunate.nwachukwu.week6tasknfotech.connection.DBConnector" %>
<%@ page import="com.fortunate.nwachukwu.week6tasknfotech.model.Product" %>
<%@ page import="com.fortunate.nwachukwu.week6tasknfotech.model.Cart" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: decagon
  Date: 10/08/2022
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%
  String auth = (String) request.getSession().getAttribute("username");
  if (auth == null) {
    response.sendRedirect("login.jsp");
  }
  ProductDao pd = new ProductDao(DBConnector.getConnection());
  List<Product> products = pd.getAllProducts();
  ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
  if (cart_list != null) {
    request.setAttribute("cart_list", cart_list);
  }
%>
<!DOCTYPE html>
<html lang="en">
<!-- Basic -->

<head>
  <meta name="viewport" content="width=device-width,initial-scale=1">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>

<body>
<!-- Start Main Top -->
<header class="main-header">
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
      <a class="navbar-brand" href="index.jsp">NfoTECH Jewelry Stores</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse"
              data-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent" aria-expanded="false"
              aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
            <li class="nav-item"><a class="nav-link" href="cart.jsp">Cart<span class="badge badge-danger">${cart_list.size()}</span> </a></li>

                    <%
                      if (auth != null) {
                    %>
          <li class="nav-item"><a class="nav-link" href="orders.jsp">Orders</a></li>
          <li class="nav-item"><a class="nav-link" href="Logout">Logout</a></li>
                    <%
                    } else {
                    %>
                    <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
                    <%
                      }
                    %>
        </ul>
      </div>
    </div>
  </nav>

</header>
<div class="all-title-box">
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <ul class="breadcrumb">
          <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
        </ul>
      </div>
    </div>
  </div>
</div>

<div class="my-account-box-main">
  <div class="container">
    <div class="my-account-page">
      <div class="row">
        <div class="col-lg-3 col-sm-auto m-5" style="background:gainsboro">
          <div class="account-box" >
            <div class="service-box">
              <div class="service-icon">
                <a href="addProduct.jsp"> <i class="fa fa-gift"></i> </a>
              </div>
              <div class="service-desc p-2">
                <h4 class="p-5" style="color: black"><a href="addProduct.jsp">Add Products</a></h4>
              </div>
            </div>
          </div>
        </div>
        </a>
        <br>
        <div class="col-lg-3 col-md-12 m-5" style="background:gainsboro">
          <div class="account-box">
            <div class="service-box">
              <div class="service-icon">
                <a href="AllProducts"><i class="fa fa-lock"></i> </a>
              </div>
              <div class="service-desc p-2">
                <h4 class="p-5" style="color: black"><a href="AllProducts">Update Product</a></h4>
              </div>
            </div>
          </div>
        </div>
        <br>
        <div class="col-lg-3 col-md-12 m-5" style="background:gainsboro">
          <div class="account-box">
            <div class="service-box">
              <div class="service-icon">
                <a href="AllProducts"> <i class="fa fa-location-arrow"></i> </a>
              </div>
              <div class="service-desc p-2">
                <h4 class="p-5" style="color: black"><a href="AllProducts">Delete Product</a></h4>
              </div>
            </div>
          </div>
        </div>
        <br>
        <div class="col-lg-3 col-md-12 m-5" style="background:gainsboro">
          <div class="account-box">
            <div class="service-box">
              <div class="service-icon">
                <a href="AllProducts"> <i class="fa fa-credit-card"></i> </a>
              </div>
              <div class="service-desc p-2">
                <h4 class="p-5" style="color: black"><a href="AllProducts">View All Product</a></h4>
              </div>
            </div>
          </div>
        </div>
        <br>
      </div>
    </div>
  </div>
</div>
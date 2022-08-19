<%@ page import="com.fortunate.nwachukwu.week6tasknfotech.dao.ProductDao" %>
<%@ page import="com.fortunate.nwachukwu.week6tasknfotech.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fortunate.nwachukwu.week6tasknfotech.model.User" %>
<%@ page import="com.fortunate.nwachukwu.week6tasknfotech.connection.DBConnector" %>
<%@ page import="com.fortunate.nwachukwu.week6tasknfotech.model.Cart" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: decagon
  Date: 11/08/2022
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 8/10/22
  Time: 2:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String auth = (String) request.getSession().getAttribute("username");
  if (auth == null) {
    response.sendRedirect("login.jsp");
  }
//  ProductDao pd = new ProductDao(DBConnector.getConnection());
//  List<Product> products = pd.getAllProducts();
//  ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
//  if (cart_list != null) {
//    request.setAttribute("cart_list", cart_list);
//  }
%>
<html>
<head>
  <head>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <title>NfoTECH stores</title>
  </head>
</head>
<body>
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
        <li class="nav-item"><a class="nav-link" href="cart.jsp">Cart <span class="badge badge-danger">${cart_list.size()}</span> </a></li>

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
<div>
  <table class="table table-striped">
    <thead>
    <tr>
      <th scope="col">S/N</th>
      <th scope="col">Name</th>
      <th scope="col">Category</th>
      <th scope="col">Price</th>
      <th scope="col">Quantity</th>
    </tr>
    </thead>
    <tbody>
      <%
      ProductDao productDao = new ProductDao(DBConnector.getConnection());
  List<Product> productList = productDao.getAllProducts();
  int index = 1;
  %>
      <%

  for(Product product : productList){%>
    <tr>
      <th scope="row"><%=index++%></th>
      <td><%=product.getName()%></td>
      <td><%=product.getCategory()%></td>
      <td><%=product.getPrice()%></td>
      <td><%=product.getQuantity()%></td>
      <td><a class="btn btn-info" href="UpdateProduct?id=<%=product.getId()%>">Edit</a>&nbsp;
        <a class="btn btn-danger" href="DeleteProduct?id=<%=product.getId()%>">Delete</a>
      </td>
    </tr><%} %>
</div>
<%--  <%@include file="includes/footer.jsp"%>--%>
</body>
</html>

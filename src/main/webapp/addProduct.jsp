<%@ page import="com.fortunate.nwachukwu.week6tasknfotech.model.User" %>
<%@ page import="com.fortunate.nwachukwu.week6tasknfotech.dao.ProductDao" %>
<%@ page import="com.fortunate.nwachukwu.week6tasknfotech.connection.DBConnector" %>
<%@ page import="com.fortunate.nwachukwu.week6tasknfotech.model.Product" %>
<%@ page import="com.fortunate.nwachukwu.week6tasknfotech.model.Cart" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%
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
  <title>NfoTECH stores</title>
</head>

<body>
<header>
<%--  <%@include file="includes/navbar.jsp"%>--%>
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
          <li class="nav-item"><a class="nav-link" href="cart.jsp">Cart <span class="badge badge-danger">${cart_list.size()}</span> </a></li>

          <%
            if (auth != null) {
          %>
          <li class="nav-item"><a class="nav-link" href="orders.jsp">Orders</a></li>
          <li class="nav-item"><a class="nav-link" href="Logout">Logout</a></li>
          <%
          } else {
          %>
          <li class="nav-item"><a class="nav-link" href="Logout">Logout</a></li>
          <%
            }
          %>
        </ul>
      </div>
    </div>
  </nav>
</header>

<!-- Start All Title Box -->
<div class="all-title-box">
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <ul class="breadcrumb">
          <li class="breadcrumb-item"><a href="adminMainPage.jsp">Back</a></li>
        </ul>
      </div>
    </div>
  </div>
</div>
<!-- End All Title Box -->

<div class="contact-box-main">
  <div class="container">
    <div class="row">
      <div class="col-lg-8 col-sm-12">
        <div class="contact-form-right">
          <h2>Add product</h2>
          <form method="post" action="AddProduct">
            <div class="row">
              <div class="col-md-12">
                <div class="form-group">
                  <input type="text" class="form-control" name="name" placeholder="Product" required>
                </div>
              </div>
              <div class="col-md-12">
                <div class="form-group">
                  <input type="text" class="form-control" name="category" placeholder="Category" required>
                </div>
              </div>
              <div class="col-md-12">
                <div class="form-group">
                  <input type="text" class="form-control" name="price" placeholder="Price" required>
                </div>
              </div>
              <div class="col-md-12">
                <div class="form-group">
                  <input type="text" class="form-control" name="quantity" placeholder="Quantity" required>
                </div>
              </div>
              <div class="col-md-12">
                <div class="submit-button text-center">
                  <button class="btn hvr-hover" id="submit" type="submit">Add</button>
                </div>
              </div>
              <div class="col-md-12 d-flex justify-content-center">
                <h1 style="color: Green">${Success}</h1><br>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
</div>
<!-- End Cart -->
<!-- Start Footer  -->
<footer>
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</footer>
<!-- End Footer  -->

<!-- Start copyright  -->
<div class="footer-copyright">
  <p class="footer-company">All Rights Reserved. &copy; 2022 <a href="#">Nfotech Stores</a></p>
</div>
<!-- End copyright  -->

<a href="#" id="back-to-top" title="Back to top" style="display: none;">&uarr;</a>

<!-- ALL JS FILES -->
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<!-- ALL PLUGINS -->
<script src="js/jquery.superslides.min.js"></script>
<script src="js/bootstrap-select.js"></script>
<script src="js/inewsticker.js"></script>
<script src="js/bootsnav.js."></script>
<script src="js/images-loded.min.js"></script>
<script src="js/isotope.min.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/baguetteBox.min.js"></script>
<script src="js/form-validator.min.js"></script>
<script src="js/contact-form-script.js"></script>
<script src="js/custom.js"></script>
</body>

</html>
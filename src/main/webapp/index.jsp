<%@ page import="com.fortunate.nwachukwu.week6tasknfotech.model.User" %>
<%@ page import="com.fortunate.nwachukwu.week6tasknfotech.dao.ProductDao" %>
<%@ page import="com.fortunate.nwachukwu.week6tasknfotech.model.Product" %>
<%@ page import="com.fortunate.nwachukwu.week6tasknfotech.connection.DBConnector" %>
<%@ page import="com.fortunate.nwachukwu.week6tasknfotech.model.Cart" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%
	User auth = (User) request.getSession().getAttribute("auth");
	if (auth != null) {
		request.setAttribute("person", auth);
	}
	ProductDao pd = new ProductDao(DBConnector.getConnection());
	List<Product> products = pd.getAllProducts();
	ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
	if (cart_list != null) {
		request.setAttribute("cart_list", cart_list);
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<title>NfoTECH stores</title>
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
				<li class="nav-item"><a class="nav-link" href="adminLogin.jsp">Admin</a></li>
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
<div class="container">
	<div class="card-header my-3">All Products</div>
	<div class="row">
		<%
			if(!(products.isEmpty())){
				for(Product p:products){%>
		<div class="col-md-3 my-3">
			<div class="card w-100" style="width:18rem;">
				<img src="images/<%=p.getImage()%>" class="card-imge-top" alt="Card images cap">
				<div class="card-body">
					<h5 class="card-title"><%=p.getName()%></h5>
					<h6 class="price">Price: $<%=p.getPrice()%></h6>
					<h6 class="category">Category: <%=p.getCategory()%></h6>
					<div class="mt-3 d-flex justify-content-xl-center">
						<a href="add-to cart?id=<%=p.getId()%>" class="btn btn-red" style= 'color: #d63384'>Add to Cart</a>
						<button type="button" class="btn btn-default btn-sm">
							<span class="glyphicon glyphicon-thumbs-up"></span> Like
						</button>
						<a href="cart.jsp" class="mx-3 btn btn-primary">Buy Now</a>
					</div>
				</div>

			</div>
		</div>

		<%}
		}
		%>

	</div>

</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</body>
</html>
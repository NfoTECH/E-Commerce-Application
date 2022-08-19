<%@ page import="com.fortunate.nwachukwu.week6tasknfotech.model.User" %><%--
  Created by IntelliJ IDEA.
  User: decagon
  Date: 10/08/2022
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
request.setAttribute("person", auth);
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up Form by Colorlib</title>

    <!-- Font Icon -->
    <link rel="stylesheet"
          href="fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="css/style.css">
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
<input type = "hidden" id="status" value = "<%= request.getAttribute("status")%>">
<div class="main">

    <!-- Sing in  Form -->
    <section class="sign-in">
        <div class="container">
            <div class="signin-content">
                <div class="signin-image">
                    <figure>
                        <img src="images/SignInImage.jpeg" alt="sing up image">
                    </figure>
                    <a href="#" class="signup-image-link">WELCOME ADMIN</a>
                </div>

                <div class="signin-form">
                    <h2 class="form-title">Sign in</h2>
                    <form method="post" action="Login" class="register-form"
                          id="login-form">
                        <div class="form-group">
                            <label for="username"><i
                                    class="zmdi zmdi-account material-icons-name"></i></label> <input
                                type="text" name="username" id="username"
                                placeholder="Email" required ="required"/>
                        </div>
                        <div class="form-group">
                            <label for="password"><i class="zmdi zmdi-lock"></i></label> <input
                                type="password" name="password" id="password"
                                placeholder="Password" required = "required"/>
                        </div>
                        <div class="form-group">
                            <input type="checkbox" name="remember-me" id="remember-me"
                                   class="agree-term" /> <label for="remember-me"
                                                                class="label-agree-term"><span><span></span></span>Remember
                            me</label>
                        </div>
                        <div class="form-group form-button">
                            <input type="submit" name="signin" id="signin"
                                   class="form-submit" value="Log in" />
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>

</div>

<!-- JS -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="js/main.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="alert/dist/sweetalert.css">

<script type = "text/javascript">
    let status = document.getElementById("status").value;
    if (status === "failed") {
        swal("Something went wrong. Try again.","error");
    }
    if (status === "invalidEmail") {
        swal("Invalid Email. Try again.","error");
    }
    if (status === "invalidPassword") {
        swal("Invalid Password. Try again","error");
    }
</script>

</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
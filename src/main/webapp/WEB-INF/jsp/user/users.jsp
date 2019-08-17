<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <%--    <link rel="stylesheet" href="theme.css" type="text/css">--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
</head>
<body>
<sec:authentication var="user" property="principal"/>
<c:if test="${user!='anonymousUser'}">
    You are logged as: <a href="/userProfile?username=${user.username}" class="btn btn-outline-info"> ${user.username}</a>
    <a href="/logout" class="btn btn-outline-info"><span class="glyphicon glyphicon-log-out" ></span></a>

</c:if>

<c:if test="${user=='anonymousUser'}">
    <%--    ${user.username}--%>



<div class="container bg-info text-light">
    <a href="/my-login" class="btn btn-outline-light">Login</a>
<h1>Create Account</h1>

<c:if test="${errorMessage!=null}">

    <div style="background-color: red;">${errorMessage}</div>

</c:if>

<form method="post" action="/users">

    <label>Login:</label><br/>
    <input type="text"  class="text-dark" name="login"><br/>
    <label>Email:</label><br/>
    <input type="email"  class="text-dark" name="email"><br/>
    <label>Password:</label><br/>
    <input type="password" class="text-dark" name="password"><br/>
    <label>User Name:</label><br/>
    <input type="text"  class="text-dark" name="username"><br/>
    <input type="submit" class="text-dark" value="Dodaj">


</form>
</div>

</c:if>
</body>
</html>
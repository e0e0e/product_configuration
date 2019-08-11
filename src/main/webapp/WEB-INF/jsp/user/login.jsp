<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project list</title>
    <%--    <style><%@include file="../css/style.css"%></style>--%>
    <%@include file="../css/boot.jsp" %>
</head>
<body>
<%--<%@include file="navigation.jsp" %>--%>

<%--<h1>Login or create account</h1>--%>
<%--<form method="post" action="/login">--%>
<%--    <label>User name</label>--%>
<%--    <input type="text" name="username"><br/>--%>
<%--    <label>Password</label>--%>
<%--    <input type="password" name="password">--%>
<%--    <br/>--%>
<%--    <input type="submit" value="Log in">--%>
<%--    <br/>--%>
<%--    <a href="/users">Create an account--%>
<%--    </a><br/>--%>
<%--</form>--%>

<div class="container">

    <div class="row justify-content-center">

        <div class="col-sm-4">

            <div class="card text-dark bg-warning mx-auto">

                <div class="card-header bg-info text-left text-light"><h4>Login</h4></div>

                <div class="card-body ">

                    <%--                    <h5 class="card-title">Services Title 1</h5>--%>

                    <form method="post" action="/login">
                        <label>User name</label>
                        <input type="text" name="username"><br/>
                        <label>Password</label>
                        <input type="password" name="password">
                        <br/>
                        <input type="submit" value="Login">
                        <br/>

                    </form>
                </div>

                <div class="card-footer bg-info text-right text-danger">

                    <a href="/users">Create an account</a>
                </div>

            </div>

        </div>


    </div>

</div>

</body>
</html>

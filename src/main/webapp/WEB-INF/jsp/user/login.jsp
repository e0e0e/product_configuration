<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project list</title>
    <style><%@include file="css/style.css"%></style>
</head>
<body>
<%--<%@include file="navigation.jsp" %>--%>

<h1>Login or create account</h1>
<form method="post" action="/login">
    <label>User name</label>
    <input type="text" name="userName"><br/>
    <label>Password</label>
    <input type="password" name="password">
    <br/>
    <input type="submit" value="Log in">
    <br/>
    <a href="/users">Create an account
    </a><br/>
</form>
</body>
</html>

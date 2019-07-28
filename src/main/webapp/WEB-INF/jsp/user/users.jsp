<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <style><%@include file="css/style.css"%></style>
</head>
<body>
<%@include file="navigation.jsp" %>
<h1>Create User</h1>

<c:if test="${errorMessage!=null}">

    <div style="background-color: red;">${errorMessage}</div>

</c:if>

<form method="post" action="/users">

    <label>Login:</label>
    <input type="text" name="login"><br/>
    <label>Email:</label>
    <input type="email" name="email"><br/>
    <label>Password:</label>
    <input type="password" name="password"><br/>
    <label>User Name:</label>
    <input type="text" name="userName"><br/>
    <input type="submit" value="Dodaj">


</form>



</body>
</html>

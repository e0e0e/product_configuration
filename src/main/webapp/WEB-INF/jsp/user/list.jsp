<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style><%@include file="../css/style.css"%></style>

</head>
<body>
<%@include file="navigation.jsp" %>

<c:if test="${createUserResult==true}">

    <h2>User has been successfully created</h2>

</c:if>
<c:if test="${deleteUserResults==true}">

    <h2>User has been successfully deleted</h2>

</c:if>


<table>
    <tr>
        <th>Id</th>
        <th>Email</th>
        <th>Login</th>
        <th>User Name</th>
        <th>Operations</th>
        <th>Admin in projects</th>
    </tr>
<c:forEach var="user" items="${users}">


            <tr>
                <td>${user.id}</td>
           <td> ${user.email} </td><td>${user.login} </td><td>${user.userName}</td>
                <td><a href="/users/delete?userId=${user.id}">Delete</a></td>
                    <td>${user.toString()}</td>
        </tr>



</c:forEach>
</table>


</body>
</html>

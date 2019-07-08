<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<c:if test="${createUserResult==true}">

    <h2>User has been successfully created</h2>

</c:if>
<c:if test="${deleteUserResults==true}">

    <h2>User has been successfully deleted</h2>

</c:if>

<a href="/users">Add User</a>

<c:forEach var="user" items="${users}">
    <div>
            ${user.email} ${user.dateOfBirth}
        <a href="/users/delete?userId=${user.id}">Delete</a>
    </div>


</c:forEach>


</body>
</html>

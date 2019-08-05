<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<select name="userId" class="text-dark" value="${users[0].id}">
    <%--    <option selected hidden >Choose here</option>--%>
    <c:forEach var="user" items="${users}">
        <option value="${user.id}">
                ${user.username}
        </option>
    </c:forEach>
</select><br/>
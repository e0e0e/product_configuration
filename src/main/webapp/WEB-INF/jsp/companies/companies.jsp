<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Companies</title>
</head>
<body>

<form method="post" action="/companies">
    <label>NIP: </label>
    <input type="text" name="NIP"></br>


    <label>Nazwa: </label>
    <input type="text" name="nazwa"></br>
    <input type="submit" value="wyszukaj">


</form>

<c:if test="${companies !=null}">
    <c:forEach var="comapny" items="${companies}">
        Nazwa: ${comapny.nazwa}<br/>
        NIP: ${comapny.NIP}<br/>
        Regon: ${comapny.regon}<br/>
        Ulica: ${comapny.address.street}<br/>
        Numer: ${comapny.address.number}<br/>
        Miasto: ${comapny.address.city}<bre/>

    </c:forEach>
</c:if>

</body>
</html>

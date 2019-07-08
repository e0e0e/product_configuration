<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search flights</title>
    <style>
        body {
            margin: 0;
            padding: 20px;
            background: url('https://www.pasazer.com/img/images/normal/wrightelectric.jpg');
        }

    </style>
</head>
<body>
<h1>Search Flights:</h1>
<form action="search" method="post">
    <label>From:</label>
    <input type="text" name="from"/>
    <label>To:</label>
    <input type="text" name="to"/>
    <input type="submit"/>
</form>
<c:if test="${flights != null}">
    <h2>Found flights:</h2>
    <c:forEach var="flight" items="${flights}">
        <div style="width:200px;background-color: blue;color:white;padding:10px;border-bottom: 2px solid white;">
            <div style="overflow:hidden;border-bottom:2px solid blue;">
                <div style="float:left;width:50%;">
                    <div style="background-color: cornflowerblue;border-right:1px solid blue;padding:5px;">${flight.from}</div>
                </div>
                <div style="float:left;width:50%;">
                    <div style="background-color: cornflowerblue;padding:5px;">${flight.to}</div>
                </div>
            </div>
            <c:if test="${!flight.connections.isEmpty()}">
                <div style="background-color: cornflowerblue;font-size:9px;padding:5px;">
                    <c:forEach var="conn" items="${flight.connections}">
                        ${conn}
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </c:forEach>
</c:if>
</body>
</html>

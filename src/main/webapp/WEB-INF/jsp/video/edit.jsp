<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Video Edit</title>
</head>
<body>
<c:if test="${error != null}">
    <div style="color:red;">!!! ${error} !!!</div>
</c:if>
<c:if test="${video.id == null}">
    <c:set value="/video" var="actionUrl"/>
</c:if>
<c:if test="${video.id != null}">
    <c:set value="/video/${video.id}/save" var="actionUrl"/>
</c:if>
<form method="POST" action="${actionUrl}">
    <input name="title" type="text" value="${video.title}"/>

    <select name="category">
        <c:forEach var="opt" items="${categories}">
            <option ${opt == video.category ? 'selected' : ''} value="${opt}">
                    ${opt}
            </option>
        </c:forEach>
    </select>

    <select name="rating">
        <option ${1 == video.rating ? 'selected' : ''} value="1">1</option>
        <option ${2 == video.rating ? 'selected' : ''} value="2">2</option>
        <option ${3 == video.rating ? 'selected' : ''} value="3">3</option>
        <option ${4 == video.rating ? 'selected' : ''} value="4">4</option>
        <option ${5 == video.rating ? 'selected' : ''} value="5">5</option>
        <option ${6 == video.rating ? 'selected' : ''} value="6">6</option>
        <option ${7 == video.rating ? 'selected' : ''} value="7">7</option>
        <option ${8 == video.rating ? 'selected' : ''} value="8">8</option>
        <option ${9 == video.rating ? 'selected' : ''} value="9">9</option>
        <option ${10 == video.rating ? 'selected' : ''} value="10">10</option>
    </select>
    <input type="submit" value="Save Video"/>
</form>
</body>
</html>

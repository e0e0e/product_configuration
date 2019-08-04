
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<c:set var="now" value="<%= new java.util.Date()%>" />

<div style="border-style: dotted;">
<form method="post" action="/sprint" >

    <h1>JSP   <c:out value="${now}"/></h1>
    <p>Formatted Date (2): <fmt:formatDate type="date" value="${now}" /></p>

    <label>From:</label>
        <input type="date" name="from" value=""><br/>
    <label>To:</label>
    <input type="date" name="to"><br/>
    <label>Story points:</label>
<%--    <input type="number" name="storyPoints" min="1" max="5" VALUE="1"><br/>--%>
    <%@include file="../pointsPicker.jsp" %>
<%--    <input type="submit" value="Dodaj">--%>


</form>
</div>

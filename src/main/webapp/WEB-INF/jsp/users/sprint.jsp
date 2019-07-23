
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sprint</title>
    <style><%@include file="css/style.css"%></style>
</head>
<body>
<%@include file="navigation.jsp" %>

<form method="post" action="/sprint">

    <label>From:</label>
    <input type="date" name="from"><br/>
    <label>To:</label>
    <input type="date" name="to"><br/>
    <label>Story points:</label>
    <input type="number" name="storyPoints"><br/>
    <input type="submit" value="Dodaj">


</form>

</body>
</html>

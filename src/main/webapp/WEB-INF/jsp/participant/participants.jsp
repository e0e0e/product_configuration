<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project Participants</title>

    <style>
        <%@include file="../css/style.css" %>
    </style>
    <%@include file="../css/boot.jsp" %>

</head>
<body>
<%@include file="../navigation.jsp" %>
<h1>For project <b>${projects.projectName}<b/> add user:</h1>
<h1>Search user by email: </h1><br/>
<input type="text" name="search">
<form method="post" action="/project/participant?projectId=${projects.id}">
    <%@include file="../userPicker.jsp" %>

    <input type="submit" value="Dodaj">
</form>

</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="resourcePath" scope="session" value="/resources/images/icons/png/"/>
<html>
<head>
    <title>${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
</head>
<body>

<%@include file="navigation.jsp" %>
<jsp:include page="${path}.jsp"></jsp:include>
<footer class="page-footer font-small blue pt-4 text-center mt-5" style=" position:initial; bottom:0;font-size:10px;">
    <p class="wysiwyg-text-align-center"><strong><em>Avatars icons made by </em></strong><a href="https://www.freepik.com/home"><strong><em>Freepik</em></strong></a><strong><em>
        from </em></strong><strong><em><a href="http://www.flaticon.com/">www.flaticon.com</a></em></strong></p>
</footer>
</body>
</html>

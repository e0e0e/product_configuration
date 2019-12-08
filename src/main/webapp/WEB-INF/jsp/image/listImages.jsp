<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
<c:forEach var="image" items="${imageList}">

<img class="img-circle bg-dark p-1 m-1" src="http://wielon.c0.pl/img/${image.name}">


</c:forEach>

</div>
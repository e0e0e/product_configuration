<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<%@include file="featureNavigation.jsp"%>
	<c:forEach var="feature" items="${features}">
name ${feature.name}

 </c:forEach>
</div>
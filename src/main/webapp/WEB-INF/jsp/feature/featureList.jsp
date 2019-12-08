<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<%@include file="featureNavigation.jsp"%>


	<c:forEach var="feature" items="${features}">
name ${feature.name}

 </c:forEach>
 <br>
	fields:
	<br>
	
	<c:forEach var="field" items="${fields}">
	<br>
 ${field.title} - ${field.value}
<br>
 </c:forEach>

</div>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<%@include file="featureNavigation.jsp"%>
	
	<form method="post" action="/saveChangedProductFeature">
	<c:forEach var="field" items="${fields}">
		<c:forEach var="row" items="${field}">
			<br><label>${row.title}</label><br>
			<input type="text"  class="text-dark" name="${row.paramName}" value=" ${row.value}"><br/>
			
		</c:forEach>
	</c:forEach>
	<input type="submit" class="text-dark mt-5" value="Save">
</form>
</div>
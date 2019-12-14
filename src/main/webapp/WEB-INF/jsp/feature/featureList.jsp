<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<%@include file="../featureNavigation.jsp"%>


		<c:forEach var="feature" items="${features}">
			<div><label>${feature.name}</label></div>
		</c:forEach>
<div><a href="/feature/add"
					style="float: left;"> <span
					class="glyphicon glyphicon-plus text-dark"></span></a></div>

</div>
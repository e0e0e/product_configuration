<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<%@include file="../featureNavigation.jsp"%>

	<form method="post" action="/saveChangedProductFeature">
		<c:forEach var="feature" items="${features}">
			<div class="card text-dark bg-warning m-1">
				<div class="card-header bg-info text-left text-light">
					${feature.name}</div>
				<div class="card-body ">
				<div>aa</div>
				<c:forEach var="feat" items="${feature.feature}">
						<div>>>${feat.name}--</div><br>
						</c:forEach>
					<div>aa</div>	
					<a href="/feature/edit?productFeatureId=${feature.id}"
						style="float: right;"> <span
						class="glyphicon glyphicon-edit text-dark"></span></a>
						
						
				</div>
			</div>
		</c:forEach>
		<input type="submit" class="text-dark mt-5" value="Save">
	</form>
</div>
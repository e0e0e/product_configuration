<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<%@include file="../featureNavigation.jsp"%>
	<div>
		<label>Product Features: </label> <a href="/feature/addProductFeature">
			<span class="glyphicon glyphicon-plus text-dark"></span>
		</a>
	</div>

	<c:forEach var="feature" items="${features}">
		<div class="card text-light bg-light border m-1">
			<div class="card-header bg-info text-left-light">${feature.name}
				<a href="/feature/edit?productFeatureId=${feature.id}"> <span
					class="glyphicon glyphicon-edit text-dark"></span></a>
			</div>
			<div class="card-body ">

				<c:forEach var="feat" items="${feature.feature}">
					<div>
						<a class="btn btn-outline-info text-dark"
							href="/feature/editFeature?featureId=${feat.id}"
							style="float: left;">${feat.name} <span
							class="glyphicon glyphicon-edit text-dark"></span></a>
					</div>
				</c:forEach>

			</div>
		</div>
	</c:forEach>

</div>
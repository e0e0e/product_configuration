<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<%@include file="../featureNavigation.jsp"%>
	<div>
		<div class="row">
			<div class="col-2 m-1">Name</div>
			<div class="col-2 m-1">Index</div>
			<div class="col-2"></div>
		</div>

		<c:forEach var="feature" items="${features}">


			<div class="row">
				<div class="col-2 border border-dark m-1">${feature.name}</div>
				<div class="col-2 border border-dark m-1">${feature.index}</div>
				<div class="col-2">
					<a class="btn btn-outline-info text-dark"
						href="/feature/editFeature?featureId=${feature.id}"><span
						class="glyphicon glyphicon-edit text-dark"></span></a>
				</div>



			</div>
		</c:forEach>
	</div>
	<div>
		<a href="/feature/add" style="float: left;"> <span
			class="glyphicon glyphicon-plus text-dark"></span></a>
	</div>

</div>
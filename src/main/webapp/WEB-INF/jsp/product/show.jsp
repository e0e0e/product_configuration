<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<%@include file="../featureNavigation.jsp"%>


	<h3>
		Products<a href="/product/add" class="btn btn-outline-dark btn-sm">
			<span class="glyphicon glyphicon-plus"></span>
		</a>
	</h3>

	<c:forEach var="configuration" items="${configurations}">
		<div class="card text-dark bg-warning m-1">


			<div class="card-header bg-info text-left text-light">
				<a href="/product/list?productId=${configuration.id}"
					class="btn btn-outline-warning text-dark">
					${configuration.name} <span
					class="glyphicon glyphicon-edit text-dark"></span>
				</a><a class="btn btn-outline-warning text-dark"
					href="/product/selection?productId=${configuration.id}"><span
					class="glyphicon glyphicon-briefcase text-dark"></span></a>
			</div>


		</div>

	</c:forEach>


</div>
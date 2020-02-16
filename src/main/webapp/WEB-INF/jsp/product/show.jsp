<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container" id="content">
	<%@include file="../featureNavigation.jsp"%>


	<h3>
		Products:
		<c:if test="${user.authorities=='[ADMIN]'}">
		<a
			href="/product/add"
			class="btn btn-outline-secondary btn-lg m-2">
			<span class="glyphicon glyphicon-plus text-dark"></span>
		</a>
		<a
					href="/product/showAll"
					class="btn btn-outline-dark text-light m-1">
					${configuration.name}
					<span class="glyphicon glyphicon-copy text-light"></span>
				</a>
		</c:if>
	</h3>

	<c:forEach
		var="configuration"
		items="${configurations}">
		<div class="card text-dark bg-dark m-1">


			<div class="card-header bg-info text-left text-light">
			
				<a
					href="/product/list?productId=${configuration.id}"
					class="btn btn-outline-dark text-light m-1">
					${configuration.name}
					<span class="glyphicon glyphicon-edit text-light"></span>
				</a>
				
				
				<c:if test="${user.authorities=='[ADMIN]'}">
				<a
					class="btn btn-outline-dark text-light m-1"
					href="/product/selection?productId=${configuration.id}">
					<span class="glyphicon glyphicon-briefcase text-light"></span>
				</a>
				<a
					class="btn btn-outline-dark text-light m-1"
					href="/product/copy?productId=${configuration.id}">
					<span class="glyphicon glyphicon-copy text-light"></span>
				</a>
				<a
					class="btn btn-outline-dark text-light m-1 float-right"
					href="/product/delete?productId=${configuration.id}">
					<span class="glyphicon glyphicon-trash text-light"></span>
				</a>
				</c:if>
			</div>


		</div>

	</c:forEach>


</div>


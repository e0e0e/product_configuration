<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<%@include file="../featureNavigation.jsp"%>




	<h3>
		Products<a href="/project" class="btn btn-outline-dark btn-sm"> <span
			class="glyphicon glyphicon-plus"></span></a>
	</h3>

	<c:forEach var="configuration" items="${configurations}">
		<div class="card text-dark bg-warning m-1">
	

			<div class="card-header bg-info text-left text-light">
				<a href="/product/show?productId=${configuration.id}"
					class="nav-link text-light font-weight-bold">
					${configuration.name}</a>
			</div>


			<div class="card-body ">
			<ul>
				<c:forEach var="configList"
					items="${configuration.configurationList}">
					
					<li><p class="card-text text-left">${configList.name}</p></li>
				</c:forEach>
				</ul>
			</div>
			<div class="card-footer bg-info text-right text-light">
				<div>created by:</div>

			</div>
		</div>
	</c:forEach>


</div>
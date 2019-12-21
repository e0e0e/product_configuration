<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<%@include file="../featureNavigation.jsp"%>


	<h3>
		Products<a href="/product/add" class="btn btn-outline-dark btn-sm"> <span
			class="glyphicon glyphicon-plus"></span></a>
	</h3>


		<div class="card text-dark bg-light m-1">
	

			<div class="card-header bg-info text-left text-light">
				<a href="/product/edit?productId=${configuration.id}"
					class="nav-link text-light font-weight-bold">
					${configuration.name}</a>
			</div>


			<div class="card-body ">
			<ul>
				<c:forEach var="configList"
					items="${configuration.configurationList}">
					<li><a class="btn btn-outline-info text-dark" href="/feature/edit?productFeatureId=${configList.id}">${configList.name} <span
					class="glyphicon glyphicon-edit text-dark"></span></a>
					<a class="btn btn-outline-info text-dark" href="/feature/removeProductFeature?productFeatureId=${configList.id}"><span
					class="glyphicon glyphicon-trash text-dark"></span></a>
					</li>
					
					<ul>
				<c:forEach var="feature"
					items="${configList.feature}">
					
					<li><a class="btn btn-outline-info text-dark" href="/feature/editFeature?featureId=${feature.id}">${feature.name} <span
					class="glyphicon glyphicon-edit text-dark"></span></a>
					<a class="btn btn-outline-info text-dark" href="/feature/removeFeature?featureId=${feature.id}&productFeatureId=${configList.id}"><span
					class="glyphicon glyphicon-trash text-dark"></span></a>
					</li>
				</c:forEach>
				</ul>
				
				</c:forEach>
				</ul>
			</div>
			<div class="card-footer bg-info text-right text-light">
				<div>created by:</div>

			</div>
		</div>



</div>
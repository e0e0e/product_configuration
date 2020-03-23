<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<%@include file="../featureNavigation.jsp"%>


	<h3>
		Move:
	</h3>


	<div class="card text-dark bg-light m-1">


		<div class="card-header bg-info text-left text-light">
		<c:choose>
					<c:when test="${user.authorities=='[ADMIN]'}">
			<a
				href="/product/edit?productId=${configuration.id}"
				class="nav-link text-light font-weight-bold">
				${configuration.name}</a>
				</c:when>
					<c:otherwise>
<div class="text-light font-weight-bold">${configuration.name}</div>
					</c:otherwise>
					</c:choose>
		</div>


		<div class="card-body ">
			<ul>
				<c:forEach
					var="configList"
					items="${configuration.configurationList}">
					<a name="anchor_${configList.id}"></a>
					<li class="greybat">
					<c:choose>
					<c:when test="${user.authorities=='[ADMIN]'}">
					<a
						class="btn btn-outline-info text-dark"
						href="/feature/edit?productFeatureId=${configList.id}">${configList.name}
							<span class="glyphicon glyphicon-edit text-dark"></span>
					</a> 
					<a
						href="/feature/moveDown?productFeatureId=${configList.id}&productId=${configuration.id}"
						class="btn btn-outline-info text-dark"> <span
							class="glyphicon glyphicon-triangle-bottom"></span></a> <a
						href="/feature/moveUp?productFeatureId=${configList.id}&productId=${configuration.id}"
						class="btn btn-outline-info text-dark"> <span
							class="glyphicon glyphicon-triangle-top"></span></a>
							<a class="btn btn-outline-info text-dark float-right kubel" href="/feature/removeProductFeature?productFeatureId=${configList.id}&productId=${configuration.id}"><span
					class="glyphicon glyphicon-trash text-dark"></span></a>
							</li>
					
					</c:when>
					<c:otherwise>
					<div class="btn btn-outline-info text-dark">${configList.name}</div>
					</c:otherwise>
					</c:choose>


				</c:forEach>
			</ul>
		</div>
	</div>



</div>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<%@include file="../featureNavigation.jsp"%>


	<h3>
		Orders:<a href="/product/add" class="btn btn-outline-dark btn-sm"> <span
			class="glyphicon glyphicon-plus"></span></a>
	</h3>
<c:forEach var="order"
					items="${orders}">

		<div class="card text-dark bg-warning m-1">
	

			<div class="card-header bg-info text-left text-light">
				<a href="/order/edit?orderId=${order.id}"
					class="nav-link text-light font-weight-bold">
					${order.orderName}</a>
					<a class="btn btn-outline-info text-dark" href="/order/delete?orderId=${order.id}"> <span
					class="glyphicon glyphicon-trash text-dark"></span></a>
			</div>


			<div class="card-body ">
			<ul>

			
					
			
				<c:forEach var="feature"
					items="${order.orderFeatures}">
					
					<li><a class="btn btn-outline-info text-dark" href="/feature/editFeature?featureId=${feature.id}">${feature.feature.name} <span
					class="glyphicon glyphicon-edit text-dark"></span></a>
					<a class="btn btn-outline-info text-dark" href="/feature/editFeature?featureId=${feature.id}">${feature.productFeature.name} <span
					class="glyphicon glyphicon-edit text-dark"></span></a>
					
					
				</li>
				</c:forEach>
				</ul>
	
			</div>
			<div class="card-footer bg-info text-right text-light">
				<div>created by:</div>

			</div>
		</div>
</c:forEach>


</div>
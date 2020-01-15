<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib
	prefix="fmt"
	uri="http://java.sun.com/jsp/jstl/fmt"%>
<div>
	<%@include file="../featureNavigation.jsp"%>
	<%@ taglib
		prefix="fmt"
		uri="http://java.sun.com/jsp/jstl/fmt"%>

		<c:set var = "imagesPath" scope = "session" value = "http://konfigurator.viberti.pl/images/Knorr.png"/>
	<div class="card text-dark bg-light m-1">


		<div class="card-header bg-info text-left text-light">
			<div>LP. ${order.id}, Order name: ${order.orderName}, Client Address: ${order.client}, Units:
				${order.unitsToProduce} Created: ${order.createdDate}, Modified:
				${order.lastModifiedDate}, Revision: ${order.revision}
				<div>Price : ${order.price}</div>
				</div>

			<a
				class="btn btn-outline-info text-dark"
				href="/order/delete?orderId=${order.id}">
				<span class="glyphicon glyphicon-trash text-light"></span>
			</a>
			<a
				class="btn btn-outline-info text-dark"
				href="/order/edit?orderId=${order.id}">
				<span class="glyphicon glyphicon-edit text-light"></span>
			</a>
				<a
				class="btn btn-outline-info text-light"
				href="/order/print?orderId=${order.id}">
				<span class="glyphicon glyphicon-print text-light"></span>
			</a>
			
		</div>


		<div class="card-body ">
			<ul>

				<c:forEach
					var="feature"
					items="${order.orderFeatures}">
					<div class="row border-bottom m-1">
						<div class="col-2 m-1">${feature.productFeature.name}:</div>
						<div class="col-4 m-1">${feature.feature.name}</div>
						<div class="col-2 m-1">${feature.feature.index}</div>
						<div class="col-1 m-1">${feature.feature.mIndex}</div>
						<div class="col-1 m-1"><img src=${imagesPath} alt="Flowers in Chania" width="20"></div>
						<c:if test="${feature.feature.price!='0.0'}">
							<div class="col-1 m-1">${feature.feature.price}</div>
						</c:if>
					</div>
				</c:forEach>
			</ul>

		</div>
		<div class="card-footer bg-info text-right text-light">
			<div>Modified by: ${order.lastModifiedBy}</div>
			
		</div>
	</div>



</div>

<div class="container p-5">
	<c:forEach
		var="order"
		items="${aud}">
		<div class="row border">
			<div class="col-8  text-secondary">
				<c:forEach
					var="listRow"
					items="${order[0].orderFeaturesStrings}">
					<div class="row">${listRow}</div>
				</c:forEach>
			</div>

			<div class="col-2">
				<fmt:formatDate
					type="date"
					value="${order[1].revisionDate}"
					pattern="yyyy-MM-dd, HH:mm" />
			</div>

			<div class="col-2">${order[1].username}</div>
		</div>
		<div>LP. ${order[0].id}, Order name: ${order[0].orderName},
			Price: ${order[0].price}, Client Address: ${order[0].client}, Units:
			${order[0].unitsToProduce}</div>
	</c:forEach>

</div>
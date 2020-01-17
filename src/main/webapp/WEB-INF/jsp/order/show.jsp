<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib
	prefix="fmt"
	uri="http://java.sun.com/jsp/jstl/fmt"%>

	<%@include file="../featureNavigation.jsp"%>
	<%@ taglib
		prefix="fmt"
		uri="http://java.sun.com/jsp/jstl/fmt"%>
		<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
		<%@ taglib
	prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

		<c:set var = "imagesPath" value = "http://konfigurator.viberti.pl/imagesLd/"/>
		<c:set var = "default" value = "default.png"/>
		<sec:authentication
	var="user"
	property="principal" />
			<script>
function imgError(es)
{
	
var something = "${imagesPath}";
var imagePath = "0.png";
console.log(something.concat(imagePath));
es.src=something.concat(imagePath);
es.parentElement.href=something.concat(imagePath);
es.width="1";

}
</script>
<div>

	<div class="card text-dark bg-light m-1">


		<div class="card-header bg-info text-left text-light">
			<div>LP. ${order.id}, Order name: ${order.orderName}, Client Address: ${order.client}, Units:
				${order.unitsToProduce} Created: ${order.createdDate}, Modified:
				${order.lastModifiedDate}, Revision: ${order.revision}
				<div>Price : ${order.price}</div>
				</div>
<c:if test="${user.authorities=='[ADMIN]'}">
			<a
				class="btn btn-outline-info text-dark"
				href="/order/delete?orderId=${order.id}">
				<span class="glyphicon glyphicon-trash text-light"></span>
			</a>
			</c:if>
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
				
						<div class="col-1 m-1">
						<a href=${imagesPath}${feature.feature.imagePath} target="_blank" class="rys">						
						<img src=${imagesPath}${feature.feature.imagePath} alt="Flowers in Chania" width="50" onerror="imgError(this)">
						</a>
						</div>
						
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


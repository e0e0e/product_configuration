<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib
	prefix="fmt"
	uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page
	contentType="text/html;charset=UTF-8"
	language="java"%>
<c:set var="resourcePath" scope="session" value="/resources/images/icons/png/" />
<html>

<head>
	<title>Print</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">


	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>

	<script>
		$(document).ready(function () {
			$('[data-toggle="popover"]').popover();
			$('.popover-dismiss').popover({
				trigger: 'focus'
			});
		});
	</script>
	<style>
		.popover {
			background-color: #9FC53B;
		}

		.box {
			background: rgb(238, 238, 240);
			box-shadow: 9px 9px 16px rgb(87, 87, 87, 0.6), -9px -9px 16px rgba(248, 249, 250, 0.5);
			/* For Neumorphism Effect */
		}

		.greyText {
			color: #a9cfee;
		}
	</style>
</head>

<body>

	<div class="container">

		<div>Order name:${order.orderName}, Price:
			${order.price}, Client Address: ${order.client}, Units:
			${order.unitsToProduce} Created: ${order.createdDate}, Modified:
			${order.lastModifiedDate}, Revision: ${order.revision}
			<c:if test="${order.noStandard=='true'}">
				<span>, NOT STANDARD</span>
			</c:if>
		</div>
	</div>
	<br />
	<div class="row border m-1">
		<div class="col-2 m-1"></div>
		<div class="col-5 m-1"></div>
		<div class="col-2 m-1">Indeks</div>
		<div class="col-1 m-1">Color</div>
		<div class="col-1 m-1">Price</div>
	</div>

	<c:forEach var="feature" items="${order.orderFeatures}">
		<div class="row border-bottom m-1">
			<div class="col-2 m-1">${feature.productFeature.name}:</div>
			<div class="col-5 m-1">
				<c:if test="${feature.feature.noStandard=='true'}">
					<span class="glyphicon glyphicon-exclamation-sign"
						style="color:${feature.color.hex};"></span>
				</c:if>
				${feature.feature.name}
			</div>
			<div class="col-2 m-1">${feature.feature.index}</div>
			<div class="col-1 m-1">
				<c:if test="${feature.productFeature.color=='true'}">
					${feature.color.type}<span class="glyphicon glyphicon-tint"
						style="color:${feature.color.hex};"></span>
				</c:if>
			</div>
			<c:if test="${feature.feature.price!='0.0'}">
				<div class="col-1 m-1">${feature.feature.price}</div>
			</c:if>
		</div>
	</c:forEach>



</body>

</html>
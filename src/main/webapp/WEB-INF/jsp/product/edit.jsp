<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<%@include file="../featureNavigation.jsp"%>
	<%@ taglib
		prefix="fn"
		uri="http://java.sun.com/jsp/jstl/functions"%>

	<form
		method="post"
		action="/saveChangedProduct?productId=${param.productId}">

		<br> <label>Product Name</label> <br> <input
			type="text"
			class="text-dark"
			name="name"
			value="${product.name}"> <br /> <label>Project
			Current Feature:</label><br />
		<ul>
			<c:forEach
				var="productFeature"
				items="${product.configurationList}">
				<li>${productFeature.name}</li>
			</c:forEach>
		</ul>


		<br> <label>To Change Feature:</label><br> <select
			size="${fn:length(newFeatures)}"
			name="feature"
			class="text-dark"
			multiple>

			<c:forEach
				var="newFeature"
				items="${newFeatures}">
				<option value="${newFeature.id}">${newFeature.name}</option>
			</c:forEach>
		</select> <br>
		<input
			type="submit"
			class="text-dark mt-5"
			value="Save">
	</form>

</div>
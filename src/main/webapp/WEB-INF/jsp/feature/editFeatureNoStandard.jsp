<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib
	prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="container">
	<%@include file="../featureNavigation.jsp"%>

	
	<sec:authentication
		var="loggedUser"
		property="principal" />
	<div class="row">
		<div class="col-6 bg-info">
		<h1>Edit no sandard  Feature</h1>
		<form
			method="post"
			action="/featureChange?userId=${loggedUser.id}&featureId=${param.featureId}&orderId=${orderId}">
			<c:if test="${errorMessage!=null}">

				<p style="background-color: red;">${errorMessage}</p>

			</c:if>

			<label>Feature Name:</label><br />
			<textarea
				rows="2"
				class="text-dark w-100 form-control"
				name="name" >${feature.name}</textarea>
			<br /> <label>Feature Description:</label><br />
			<textarea
				rows="4"
				class="text-dark"
				name="description">${feature.description}</textarea>
			<br /> <label>Feature Image:</label> <br /> <input
				type="text"
				class="text-dark"
				name="imagePath"
				value="${feature.imagePath}"><br /> <label>Price:</label><br />
			<input
				type="text"
				class="text-dark"
				name="price"
				value="${feature.price}"><br /> <label>Index:</label><br />
			<input
				type="text"
				class="text-dark"
				name="index"
				value="${feature.index}"><br />
				<label>Index M:</label><br />
			<input
				type="text"
				class="text-dark"
				name="mIndex"
				value="${feature.mIndex}"><br /> <br>
				<input
				type="submit"
				class="text-dark"
				value="Save">


		</form>
		</div>
		<div class="col-6 bg-secondary">
		<h1>Select existing Feature</h1>
		</div>
	</div>
</div>
<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib
	prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="container">
	<%@include file="../featureNavigation.jsp"%>

	<h1>Create Product Feature</h1>
	<sec:authentication
		var="loggedUser"
		property="principal" />

	<form
		method="post"
		action="/productFeatureCreate">

		<label>Product Feature Name:</label>
		<br />
		<input
			type="text"
			class="text-dark"
			name="name"
			value="${productFeature.name}">
		<br />
		<label>Project Description:</label>
		<br />
		<textarea
			rows="4"
			class="text-dark"
			name="description">${productFeature.description}</textarea>
		<br />
		<label>Feature Image:</label>
		<br />
		<input
			type="text"
			class="text-dark"
			name="imagePath"
			value="${productFeature.imagePath}">
		<br /> <%-- <br />
		<label>Current feature List:</label>
		<br />
		<c:forEach
			var="feat"
			items="${productFeature.feature}">
			<div>${feat.name}</div>
		</c:forEach>
		<br /> --%>
		<c:if test="${errorMessage!=null}">

			<p style="background-color: red;">${errorMessage}</p>

		</c:if>
		<label>Change feature List:</label>
		<br />
		<select
			name="featureList"
			multiple>
			<c:forEach
				var="feature"
				items="${featuresList}">
				<option value="${feature.id}">${feature.name}</option>
			</c:forEach>
		</select>
		<br>
		<input
			type="submit"
			class="text-dark"
			value="Save">


	</form>
</div>
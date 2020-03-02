<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib
	prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="container">
	<%@include file="../featureNavigation.jsp"%>

	<h1>Edit Feature</h1>
		<c:set var="imagesPath" value="http://konfigurator.viberti.pl/imagesLd/" />
		<c:set var="default" value="default.png" />
		<sec:authentication var="loggedUser"
		property="principal" />
	<form
		method="post"
		action="/featureChange?userId=${loggedUser.id}&featureId=${param.featureId}&orderId=${orderId}">
		<c:if test="${errorMessage!=null}">

			<p style="background-color: red;">${errorMessage}</p>

		</c:if>

		<label>Feature Name:</label><br />
		<textarea
			rows="2"
			class="text-dark"
			name="name" cols="100">${feature.name}</textarea>
		<br /> <label>Feature Description:</label><br />
		<textarea
			rows="4"
			class="text-dark"
			name="description">${feature.description}</textarea>
			<br>
				<input type="checkbox" name="noStandard" value="true" size="100"
				<c:if test="${feature.noStandard=='true'}">
				checked
				</c:if>
				>No standard
				<br />
		<br /> <label>Feature Image:</label> <br /> <input
			type="text"
			class="text-dark"
			name="imagePath"
			value="${feature.imagePath}"><br /> <label>Price:</label><br />
			<a href=${imagesPath}${feature.imagePath} target="_blank" class="rys">
		<img src=${imagesPath}${feature.imagePath} alt="Flowers in Chania" width="200" onerror="imgError(this)"></a><br><br>
		<input
			type="text"
			class="text-dark"
			name="price"
			value="${feature.price}"><br /> <label>Index:</label><br />
		<input
			type="text"
			class="text-dark"
			name="index"
			size="100"
			value="${feature.index}"><br />
			<label>Index M:</label><br />
		<input
			type="text"
			class="text-dark"
			name="mIndex"
			size="100"
			value="${feature.mIndex}"><br /> <br>
			<input
			type="submit"
			class="text-dark"
			value="Save">


	</form>
</div>
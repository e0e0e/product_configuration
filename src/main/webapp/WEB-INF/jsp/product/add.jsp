<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<%@include file="../featureNavigation.jsp"%>


	<form method="post" action="/saveProduct">

		<br> <label>Product Name</label> <br> <input type="text"
			class="text-dark" name="name" value=""> <br /> <label>Project
			Feature:</label><br /> <select name="feature" class="text-dark" value="" multiple>

			<c:forEach var="feature" items="${features}">
				<option value="${feature.id}">${feature.name}</option>
			</c:forEach>

		</select> <br> <input type="submit" class="text-dark mt-5" value="Save">
	</form>

</div>
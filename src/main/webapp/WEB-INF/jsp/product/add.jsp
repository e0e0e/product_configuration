<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<%@include file="featureNavigation.jsp"%>


	<form method="post" action="/saveProductConfiguration">

				<br>
				<label>Product Name</label>
				<br>
				<input type="text" class="text-dark" name="name"
					value="">
				<br />
				
				<label>Project Feature:</label><br/>
    	<select name="feature" class="text-dark" value="aa">
        <%--    <option selected hidden >Choose here</option>--%>
        <c:forEach var="feature" items="${features}">
            <option value="${feature.id}">
                    ${feature.name}
            </option>
        </c:forEach>

    </select>

		<input type="submit" class="text-dark mt-5" value="Save">
	</form>

</div>
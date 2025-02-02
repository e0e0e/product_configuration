<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="temp" value="1" />
<c:set var="default" value="default.png" />

<c:if test="${param.errorMessage!=null}">
	<p style="background-color: red;">${param.errorMessage}</p>
</c:if>
<div class="container">
	<%@include file="../featureNavigation.jsp"%>
	<%@ page import="pl.sda.pms.feature.Feature" %>
	<div>
		<a href="/feature/add" style="float: left;"> <span class="glyphicon glyphicon-plus text-dark"></span></a>
	</div>
	<hr style="border: 1px solid black;">
	<div>
		<div class="row">
			<div class="col-2 m-1">Name</div>
			<div class="col-2 m-1">Index</div>
			<div class="col-2"></div>
		</div>

		<c:forEach var="feature" items="${features}">

			<a name="anchor_${temp}"></a>
			<div class="row">
				<div class="col-2 border-bottom m-1">${feature.name}</div>
				<div class="col-2 border-bottom m-1">${feature.index} <%
				String headerNames = request.getHeader("host");

				Feature featureId =(Feature) pageContext.findAttribute("feature");

					if(headerNames.equals("nolocalhost:8081")){
						out.println("<a href='/feature/pdf?featureId="+featureId.getId()+"'> <i class='fa fa-file-pdf-o' aria-hidden='true'></i></a>");
					}
				%>
				
				</div>
				<div class="col-2 border-bottom m-1">
					<a href="/im/${feature.imagePath}" target="_blank" class="rys float-right">
						<img src="/im/${feature.imagePath}" alt="Flowers in Chania" width="150"
							onerror="imgError(this)"></a>

				</div>
				<div class="col-2 border-bottom m-1">
					<a class="btn btn-outline-info text-dark" href="/feature/editFeature?featureId=${feature.id}"><span
							class="glyphicon glyphicon-edit text-dark"></span></a>

							<a class="btn btn-outline-info text-dark" href="/feature/aud?featureId=${feature.id}"><span
							class="glyphicon glyphicon-time text-dark"></span></a>

												<a class="btn btn-outline-info text-dark"
						href="/delete/feature?featureId=${feature.id}&temp=${temp}"><span
							class="glyphicon glyphicon-trash text-dark"></span></a>
							
				</div>



			</div>
			<c:set var="temp" value="${feature.id}" />
		</c:forEach>
	</div>
	<div>
		<a href="/feature/add" style="float: left;"> <span class="glyphicon glyphicon-plus text-dark"></span></a>
	</div>

</div>
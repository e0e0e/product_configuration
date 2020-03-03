<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="temp" value="1" />
<c:set var="imagesPath" value="http://konfigurator.viberti.pl/imagesLd/" />
<c:set var="default" value="default.png" />

<c:if test="${param.errorMessage!=null}">
	<p style="background-color: red;">${param.errorMessage}</p>
</c:if>
<div class="container">

<c:forEach var="file" items="${files}">
<a href="file:///${file}"  target="_blank">${file}</a>
</c:forEach>

</div>
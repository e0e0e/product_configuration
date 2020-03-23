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


<div>
    <form id="myFilterForm" method="post" action="/order/newPf?orderId=${orderId}">
    
        <c:forEach var="productFeature" items="${productFeatures}">
        <label>${productFeature.name}</label>
            <select name="${productFeature.id}" class="text-dark" value="${configList.color.id}">
                <option value="" selected></option>
                <c:forEach var="feature" items="${productFeature.feature}">
                    <option value="${feature.id}"
                        style="text-shadow: -1px 0 white, 0 1px white, 1px 0 white, 0 -1px white;">${feature.name}
                    </option>
                </c:forEach>
            </select>


        </c:forEach>
        <br>
        <input id="saveButton" type="submit" value="Save">
    </form>

</div>
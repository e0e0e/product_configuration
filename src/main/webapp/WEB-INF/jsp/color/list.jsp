<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<div class="containers">
<h3>Colors</h3>

<div class="row text-dark p-2">
    <div class="col-2 ml-5 p-2">Color name</div>
    <div class="col-2 ml-5 p-2" >Ral</div>
</div>
<c:forEach
	var="color"
	items="${colors}">
<div class="row p-2" style="background-color:${color.hex};">
    <div class="col-2 text-light ml-5 p-2">${color.name}</div>
    <div class="col-2 text-light ml-5 p-2" >${color.ral}</div>
</div>
</c:forEach>

</div>
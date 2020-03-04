<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<div class="containers">
    <h3>Colors</h3>

    <div class="row text-dark p-2">
        <div class="col-1 ml-4 p-2">Sample</div>
        <div class="col-2 ml-4 p-2">Color name</div>
        <div class="col-2 ml-4 p-2">Type</div>
        <div class="col-1 ml-4 p-2">Ral</div>


    </div>
    <c:forEach var="color" items="${colors}">
        <div class="row line p-2 text-dark border-bottom">
            <div class="col-1 ml-4  rounded" style="background-color:${color.hex};"></div>
            <div class="col-2 ml-4  align-self-center">${color.name}</div>
            <div class="col-2 ml-4  align-self-center">${color.type}</div>
            <div class="col-1 ml-4  align-self-center">${color.ral}</div>
            <div class="col-1 ml-4  align-self-center">
                <a class="btn btn-outline-info text-dark" href="/color/edit?colorId=${color.id}" style="float: left;">
                    <span class="glyphicon glyphicon-edit text-dark"></span>
                </a></div>
        </div>
    </c:forEach>

</div>
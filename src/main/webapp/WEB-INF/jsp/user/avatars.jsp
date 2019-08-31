<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Choose your avatar</h1>
<div class="row">
<c:forEach var="file" items="${fileList}">
    <div class="col-2">
        <a href="/users/addAvatars?image=${file}&userId=${param.userId}">
            <img class="img-circle bg-dark p-1 m-1" src="${resourcePath}${file}" height="100" width="100"/>
        </a>
    </div>
</c:forEach>
</div>
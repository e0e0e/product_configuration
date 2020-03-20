<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${createUserResult==true}">

    <h2>User has been successfully created</h2>

</c:if>
<c:if test="${deleteUserResults==true}">

    <h2>User has been successfully deleted</h2>

</c:if>


<div class="container">

    <h3>Users</h3>

    <div class="row">
        <c:forEach var="user" items="${users}">
            <div class="col-sm-4">

                <div class="card text-dark bg-warning m-1">

                    <div class="card-header bg-info text-left text-light">
                        <img class="img-circle bg-dark p-1 m-1 d-inline-block" src="${resourcePath}${user.avatar}"
                            height="40" width="40" />
                        <div class="d-inline-block">${user.username}</div>
                    </div>

                    <div class="card-body ">

                         <br />
                        <p class="card-text text-left">email: ${user.email} </p>
                        <br />
                        <p class="card-text text-left">Authorities: ${user.authorities} </p>

                    </div>

                    <div class="card-footer bg-info text-right text-danger">

                        <a href="/user/delete?userId=${user.id}" class="btn btn-outline-light btn-sm">
                            <span class="glyphicon glyphicon-trash"></span></a>

                        <a href="/auth/user/edit?userId=${user.id}" class="btn btn-outline-light btn-sm">Give Authority
                            <span class="glyphicon glyphicon-user"></span></a>


                    </div>

                </div>

            </div>
        </c:forEach>


    </div>

</div>
<div class="card text-dark bg-warning m-1">

    <div class="card-header bg-info text-left text-light"><h4>User name: ${user.username}<br/>
        login:${user.login}</h4></div>

    <div class="card-body ">

        <%--                    <h5 class="card-title">Services Title 1</h5>--%>

        <p class="card-text text-left">email: ${user.email}  </p>
        <br/>
        <p class="card-text text-left">In projects: ${user.toString()}  </p>

    </div>

<%--    <div class="card-footer bg-info text-right text-danger">--%>

<%--        <a href="/project/user?projectId=${user.id}" class="btn btn-outline-light btn-sm">--%>
<%--            <span class="glyphicon glyphicon-trash"></span></a>--%>

<%--        <a href="/project/edit?projectId=${user.id}" class="btn btn-outline-light btn-sm">--%>
<%--            <span class="glyphicon glyphicon-edit"></span></a>--%>

<%--    </div>--%>

</div>
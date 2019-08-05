<div>

    <div class="card text-dark bg-warning m-1">

        <div class="card-header bg-info text-left text-light">
            <a href="/project/show?projectId=${project.id}" class="nav-link text-light font-weight-bold">
                ${project.projectName}</a>
        </div>

        <div class="card-body ">

            <%--                    <h5 class="card-title">Services Title 1</h5>--%>

            <p class="card-text text-left">${project.description}  </p>

        </div>

        <div class="card-footer bg-info text-right text-danger">

            <a href="/project/delete?projectId=${project.id}" class="btn btn-outline-light btn-sm">
                <span class="glyphicon glyphicon-trash"></span></a>
            <a href="/participant?projectId=${project.id}" class="btn btn-outline-light btn-sm">
                <span class="glyphicon glyphicon-user"></span></a>
            <a href="/tasks?projectId=${project.id}" class="btn btn-outline-light btn-sm">
                <span class="glyphicon glyphicon-time"></span></a>

            <a href="/project/edit?projectId=${project.id}" class="btn btn-outline-light btn-sm">
                <span class="glyphicon glyphicon-edit"></span></a>
            <div class="btn btn-outline-light btn-sm">
                ${project.showUsersInProject()}</div>

        </div>

    </div>

</div>
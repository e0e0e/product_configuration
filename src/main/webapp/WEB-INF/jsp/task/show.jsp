<div class="container">
    <table class="table table-dark">
        <tr>
            <%--        <th>Id</th>--%>
            <th>task Name</th>
            <th>Description</th>
            <%--        <th>User Name</th>--%>
            <th>Start Date</th>
            <th>Finish Date</th>
            <th>Story points</th>
            <th>Responsible user</th>
            <th>Progress</th>
            <th>Edit</th>
        </tr>


            <tr>
                <td>${task.name} </td>
                <td>${task.description} </td>
                <td>${task.sprint.startDate} </td>
                <td>${task.sprint.finishDate} </td>
                <td>${task.sprint.planedStoryPoints} </td>
                <td>${task.user.username} </td>
                <td>${task.progress}
                    <a href="/task/progressChange?taskId=${task.id}" class="btn btn-outline-light btn-sm text-right">
                        <span class="glyphicon glyphicon-edit"></span></a>


                </td>
                <td><a href="/task/delete?taskId=${task.id}&projectId=${param.projectId}" class="btn btn-outline-light btn-sm text-right">
                    <span class="glyphicon glyphicon-trash"></span></a></td>
            </tr>



    </table>
</div>
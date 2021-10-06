<%@include file="/common/taglib.jsp"%>
<c:url var="deletePost" value="/posts/delete"></c:url>
<c:url var="updatePost" value="/posts/edit"></c:url>
<br/>
<div class="col-8">
    <br />
    <table class="table table-bordered text-center">
        <thead>
        <tr>
            <th>Post name</th>
            <th>Content</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${posts}">
            <tr>
                <td><div class="form-group">${item.postName}</div></td>
                <td><div class="form-group">${item.content}</div></td>
                <td>
                    <c:if test="${customUser.userId == item.userId}">
                        <button id="btnUpdate" onclick="confirmUpdate(${item.postId})" class="btn btn-white btn-warning btn-bold">Update</button>
                        <button id="btnDelete" onclick="confirmDelete(${item.postId})" class="btn btn-white btn-danger btn-bold">Delete</button>
                    </c:if>
                    <button type="button" class="btn btn-bold" >View</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
    function confirmUpdate(postId){
        swal({
            title: "System response",
            text:"System is processing ...",
            icon: "success",
            buttons: false,
        });
        setTimeout(function () {
            window.location.href = "/posts/edit/"+postId;
        }, 600);
    }

    function confirmDelete(postId){
        swal({
            title: "Are you sure?",
            text: "If you delete it, you will not be able to recover this post",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal({
                        title: "System response",
                        text:"System is processing ...",
                        icon: "success",
                        buttons: false,
                    });
                    setTimeout(function () {
                        window.location.href = "/posts/delete/"+postId;
                    }, 600);
                } else {
                    swal({
                        title: "Notify",
                        text:"Your post is safe!",
                        icon: "success",
                        buttons: false,
                    });
                }
            })
    };
</script>

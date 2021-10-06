<%@include file="/common/taglib.jsp" %>
<c:url var="deletePost" value="/posts/delete"></c:url>
<c:url var="updatePost" value="/posts/edit"></c:url>
<br/>
<div class="col-8">
    <c:if test="${param.add_success != null}"><div class="alert alert-success" role="alert">Added a post successfully!</div></c:if>
    <c:if test="${param.update_success!= null}"><div class="alert alert-success" role="alert">Uploaded the post successfully!</div></c:if>
    <c:if test="${param.delete_success != null}"><div class="alert alert-success" role="alert">Deleted the post successfully!</div></c:if>
    <c:if test="${param.invalid_post != null}"><div class="alert alert-success" role="alert">Invalid name or content!</div></c:if>
    <c:if test="${param.post_empty != null}"><div  class="alert alert-warning" role="alert">This post isn't exists!</div></c:if>
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
                        <button id="btnUpdate" class="btn btn-white btn-warning btn-bold"onclick="confirmUpdate(${item.postId})">Update</button>
                        <button id="btnDelete" onclick="confirmDelete(${item.postId})" class="btn btn-white btn-danger btn-bold">Delete</button>
                    </c:if>
                    <button type="button" class="btn btn-bold" >View</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="paginate text-center">
        <c:forEach begin="1" end="${postDto.totalPage}" step="1" varStatus="loop">
            <c:url var="pagePost" value="${paging}">
                <c:param name="page" value="${loop.count}"></c:param>
            </c:url>
            <a href="${pagePost}">
                <c:if test="${loop.count == currentPage}">
                    <button class="btn btn-primary"  type="submit" id="btnPage" style="margin-right: 3px; text-align: center;">${currentPage}</button>
                </c:if>
                <c:if test="${loop.count != currentPage}">
                <button class="btn btn-light" type="submit" id="btnPage" style="margin-right: 3px; text-align: center;">${loop.count}</button>
                </c:if>
            </a>
        </c:forEach>
    </div>
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
                        title: "System Response",
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

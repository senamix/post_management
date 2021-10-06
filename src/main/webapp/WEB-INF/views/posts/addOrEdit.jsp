<%@include file="/common/taglib.jsp"%>
<br/>
<div class="col-4 edit-form">
    <div class="main-div">
        <br/>
        <c:if test="${param.addError != null}">
            <div class="alert alert-danger">
                Invalid postname and content!
            </div>
        </c:if>
        <c:if test="${param.updateError != null}">
            <div class="alert alert-danger">
                Invalid postname and content!
            </div>
        </c:if>
        <br />

        <form:form action="/posts/save" id="formSubmit" modelAttribute="model" method="post">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">Name</label>
                <div class="col-sm-9">
                    <input type="text" name="postName" value="${model.postName}"/>
                    <br/><form:errors path="postName"/><br/>
                </div>
            </div>
            <br/>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right">Content</label>
                <div class="col-sm-9">
                    <textarea name="content" style="width: 820px;height: 175px">${model.content}</textarea>
                    <br/><form:errors path="content"/><br/>
                </div>
            </div>
            <br/>
            <div class="form-group">
                <div class="col-sm-12">
                    <c:if test="${model.postId == null}"><button  type="submit"
                          class="btn btn-primary btn-bold" id="btnAddOrUpdate" onclick="process()">Add</button></c:if>
                    <c:if test="${model.postId  != null}"><button  type="submit"
                          class="btn btn-info btn-bold" id="btnAddOrUpdate" onclick="process()">Update</button></c:if>
                </div>
            </div>
            <input type="hidden" value="${model.postId}" name="postId" id="postId"/>
            <input type="hidden" value="${customUser.userId}" name="userId"/>
        </form:form>
    </div>
</div>
<script>
    function process(){
        swal({
            title: "System response",
            text:"System is processing ...",
            icon: "success",
            buttons: false,
        });
    };
</script>
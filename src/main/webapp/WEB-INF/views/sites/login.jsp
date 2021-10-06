<%@include file="/common/taglib.jsp"%>
<br/>
<div class="col-4 login-form">
    <div class="main-div">
        <br/>
        <c:if test="${param.error != null}">
            <div class="alert alert-danger">
                Invalid username and password.
            </div>
        </c:if>
        <c:if test="${param.logout != null}">
            <div class="alert alert-info">
                You have been logged out.
            </div>
        </c:if>
        <c:if test="${param.register_success != null}">
            <div class="alert alert-success">
                You registered successfully. Login now!
            </div>
        </c:if>
        <br/>
        <form:form action="/login" method="post">
            <div class="form-group">
                <input type="text" class="form-control" id="username" name="username" placeholder="UserName: ">
            </div>

            <div class="form-group">
                <input type="password" class="form-control" id="password" name="password" placeholder="Password: ">
            </div>
            <button type="submit" class="btn btn-primary" id="submit" >Login</button>
        </form:form>
    </div>
</div>
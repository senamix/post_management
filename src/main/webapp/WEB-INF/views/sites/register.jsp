<%@include file="/common/taglib.jsp"%>
<br/>
<div class="col-4 register-form">

    <c:if test="${param.account_existed != null}">
        <div class="alert alert-danger">
            <br /><h2>Account is already existed. Pls, check your username or email!</h2><br /><br />
        </div>
    </c:if>

    <form:form action="/register" method="post" modelAttribute="user">
        <br />
        <br />
        <div class="form-group">
            <input type="text" class="form-control" name="userName" placeholder="UserName: ">
            <br/><form:errors path="userName"/><br/>
        </div>
        <div class="form-group">
            <input type="password" class="form-control" name="password" placeholder="Password: ">
            <br/><form:errors path="password"/><br/>
        </div>
        <div class="form-group">
            <input type="email" class="form-control" name="email" placeholder="Email: ">
            <br/><form:errors path="email"/><br/>
        </div>
        <button type="submit" class="btn btn-primary">Register</button>
    </form:form>
</div>
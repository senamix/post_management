<%@ page import="com.example.su.security.SecurityUtil" %>
<%@include file="/common/taglib.jsp"%>
<br/>
<nav class="navbar navbar-light bg-light justify-content-between">
    <c:if test="${SecurityUtil.getCustomUser().getUserName() != null}">
        <a class="navbar-brand" href="#">Welcome, <b>${SecurityUtil.getCustomUser().getUserName()}</b></a>
    </c:if>
    <a class="navbar-brand active btn btn-light" href="/">Home</a>
    <c:if test="${SecurityUtil.getCustomUser().getUserName() != null}">
        <a class="navbar-brand active btn btn-danger" href="/logout">Logout</a>
        <a class="navbar-brand  active btn btn-success" href='/posts/form'>New Post</a>
    </c:if>
    <c:if test="${SecurityUtil.getCustomUser().getUserName() == null}">
        <a class="navbar-brand active btn btn-success" href="/login">Login</a>
        <a class="navbar-brand active btn btn-info" href="/register">Register</a>
    </c:if>
    <a class="navbar-brand btn active btn-primary" href='/posts/paging?page=1'>Page</a>
    <a class="navbar-brand btn active btn-warning" href='/posts/list'>AllPost</a>

    <div class="form-inline">
        <input class="form-control mr-sm-2" id="postName" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" onclick="searchPostByName()">Search</button>
    </div>
</nav>
<br/>

<script>
    function searchPostByName(){
        let postNameValue = document.querySelector('#postName').value;
        let searchUrl = "/posts/list?postName="+postNameValue;
        location.href= searchUrl;
    }
</script>
<%@include file="/common/taglib.jsp"%>
<br/>
<div class="col-8">
    <c:if test="${param.denied != null}">
        <div class="alert alert-warning">
            You can't access into here!
        </div>
        <br/>
    </c:if>
</div>
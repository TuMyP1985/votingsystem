<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<nav class="navbar navbar-dark bg-dark py-0">
    <div class="container">
        <a href="restaurants" class="navbar-brand"><img src="resources/images/icon-restaurant.png"> <spring:message code="app.title"/></a>
        <form class="form-inline my-2">
<%--            <a ${itIsAdmin ? '' : 'hidden'} class="btn btn-info mr-1" href="users"><spring:message code="user.title"/></a>--%>
            <a class="btn btn-info mr-1" href="users"><spring:message code="user.title"/></a>
            <a class="btn btn-primary" href="">
                <span class="fa fa-sign-in"></span>
            </a>
        </form>
    </div>
</nav>

<script type="text/javascript">
    const i18n = [];
    <c:forEach var="key" items='<%=new String[]{"common.deleted","common.saved","common.errorStatus","common.confirm"}%>'>
    i18n["${key}"] = "<spring:message code="${key}"/>";
    </c:forEach>
</script>
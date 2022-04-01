<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <hr/>
    <jsp:useBean id="restaurant" type="ru.java.votingsystem.model.Restaurant" scope="request"/>
    <h3><spring:message code="dish.title"/> (${restaurant.name}) </h3>
    <hr/>
    <a href="restaurants/dishes/create?id=${restaurant.id}"><spring:message code="dish.add"/></a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
<%--            <th><spring:message code="restaurant.name"/></th>--%>
            <th><spring:message code="dish.name"/></th>
            <th><spring:message code="dish.price"/></th>
            <th><spring:message code="dish.registered"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.dishes}" var="dish">
            <jsp:useBean id="dish" type="ru.java.votingsystem.model.Dish"/>
            <tr>
<%--                <td>${dish.restaurant.name}</td>--%>
                <td><c:out value="${dish.name}"/></td>
                <td>${dish.price}</td>
                <td><fmt:formatDate value="${dish.registered}" pattern="dd-MM-yyyy"/></td>
                <td><a href="restaurants/dishes/update?id=${dish.id}"><spring:message code="common.update"/></a></td>
                <td><a href="restaurants/dishes/delete?id=${dish.id}"><spring:message code="common.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
    <hr/>
    <a href="restaurants"><spring:message code="common.back"/>

</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
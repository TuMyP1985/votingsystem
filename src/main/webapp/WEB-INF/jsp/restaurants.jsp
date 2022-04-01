<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>

    <hr/>
    <h2><spring:message code="restaurant.title"/></h2>
    <hr/>
    <a href="restaurants/create"><spring:message code="restaurant.add"/></a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th><spring:message code="restaurant.name"/></th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.restaurants}" var="restaurant">
            <jsp:useBean id="restaurant" type="ru.java.votingsystem.model.Restaurant"/>
            <tr>
                <td>${restaurant.name}</td>
                <td><a href="restaurants/dishes?id=${restaurant.id}"><spring:message code="dish.title"/></a></td>
                <td><a href="restaurants/update?id=${restaurant.id}"><spring:message code="common.update"/></a></td>
                <td><a href="restaurants/delete?id=${restaurant.id}"><spring:message code="common.delete"/></a></td>
                <td><a href="restaurants/vote?id=${restaurant.id}"><spring:message code="vote.name"/></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
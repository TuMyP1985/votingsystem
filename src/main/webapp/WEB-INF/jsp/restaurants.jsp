<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="fragments/headTag.jsp"/>

    <style>
        .notSelect {
            color: green;
        }

        .select {
            color: red;
        }
    </style>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>

    <hr/>
<%--    ${canInputVote ? 'indianred' : ''}--%>

    <jsp:useBean id="canInputVote" type="java.lang.Boolean" scope="request"/>
    <h2><spring:message code="restaurant.title"/>
        <spring:message code= "${!canInputVote ? 'common.cannotadd' : 'common.empty'}"/>

    </h2>
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
        <jsp:useBean id="vote" type="ru.java.votingsystem.model.Vote" scope="request"/>
        <c:forEach items="${requestScope.restaurants}" var="restaurant">
            <jsp:useBean id="restaurant" type="ru.java.votingsystem.model.Restaurant"/>
<%--            <tr class="${restaurant.id == vote.id ? 'color: red' : 'color: green'}">--%>
            <tr style="background-color:${restaurant.id == vote.restaurant.id ? 'indianred' : 'white'}">
                <td>${restaurant.name}</td>
                <td><a href="restaurants/dishes?id=${restaurant.id}"><spring:message code="dish.title"/></a></td>
                <td><a href="restaurants/update?id=${restaurant.id}"><spring:message code="common.update"/></a></td>
                <td><a href="restaurants/delete?id=${restaurant.id}"><spring:message code="common.delete"/></a></td>
                <td><a href="restaurants/voteSelect?idRestaurant=${restaurant.id}&idVote=${vote.id}"><spring:message code="vote.name"/></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
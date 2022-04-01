<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <jsp:useBean id="dish" type="ru.java.votingsystem.model.Dish" scope="request"/>
    <%--    `restaurant.new` cause javax.el.ELException - bug tomcat --%>
    <h3><spring:message code="${dish.isNew() ? 'dish.add' : 'dish.edit'}"/></h3>
    <hr>
    <form method="post" action="restaurants/dishesUpdateOrCreate">
        <input type="hidden" name="id" value="${dish.id}">
        <input type="hidden" name="idRestaurant" value="${dish.restaurant.id}">
        <dl>
            <dt><spring:message code="dish.name"/>:</dt>
            <dd><input type="text" value="${dish.name}" name="name" required></dd>
        </dl>
        <dl>
            <dt><spring:message code="dish.price"/>:</dt>
            <dd><input type="number" value="${dish.price}" name="price" required></dd>
        </dl>
        <button type="submit"><spring:message code="common.save"/></button>
        <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>

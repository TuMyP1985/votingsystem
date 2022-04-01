<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <jsp:useBean id="restaurant" type="ru.java.votingsystem.model.Restaurant" scope="request"/>
    <%--    `restaurant.new` cause javax.el.ELException - bug tomcat --%>
    <h3><spring:message code="${restaurant.isNew() ? 'restaurant.add' : 'restaurant.edit'}"/></h3>
    <hr>
    <form method="post" action="restaurants">
        <input type="hidden" name="id" value="${restaurant.id}">
        <dl>
            <dt><spring:message code="restaurant.name"/>:</dt>
            <dd><input type="text" value="${restaurant.name}" name="name" required></dd>
        </dl>
        <button type="submit"><spring:message code="common.save"/></button>
        <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>

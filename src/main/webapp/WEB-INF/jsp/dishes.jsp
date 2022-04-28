<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script src="resources/js/votingsystem.common.js" defer></script>
<script src="resources/js/votingsystem.dishes.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <jsp:useBean id="restaurant" type="ru.java.votingsystem.model.Restaurant" scope="request"/>
        <h3 class="text-center">
            <spring:message code="dish.title"/> (${restaurant.name})
        </h3>

        <button visible-admin="${itIsAdmin}" class="btn btn-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            <spring:message code="common.add"/>
        </button>
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="dish.name"/></th>
                <th><spring:message code="dish.price"/></th>
                <th visible-admin="${itIsAdmin}"></th>
                <th visible-admin="${itIsAdmin}"></th>
            </tr>
            </thead>
            <c:forEach items="${requestScope.dishes}" var="dish">
                <jsp:useBean id="dish" type="ru.java.votingsystem.model.Dish"/>
                <tr id="${dish.id}">
                    <td><c:out value="${dish.name}"/></td>
                    <td><c:out value="${dish.price}"/></td>
                    <td visible-admin="${itIsAdmin}"><a onclick=updateRow(${dish.id})><span class="fa fa-pencil"></span></a></td>
                    <td visible-admin="${itIsAdmin}"><a onclick="deleteRow(${dish.id})"><span class="fa fa-remove"></span></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalTitle"></h4>
                <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">

                    <input type="hidden" id="id" name="id">
                    <input type="hidden" name="idRest" value="${restaurant.id}">

                    <div class="form-group">
                        <label for="name" class="col-form-label"><spring:message code="dish.name"/></label>
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="<spring:message code="dish.name"/>">
                    </div>

                    <div class="form-group">
                        <label for="price" class="col-form-label"><spring:message code="dish.price"/></label>
                        <input type="number" class="form-control" id="price" name="price"
                               placeholder="<spring:message code="dish.price"/>">
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="saveWithRest(${restaurant.id})">
                    <span class="fa fa-check"></span>
                    <spring:message code="common.save"/>
                </button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
<script type="text/javascript">
    i18n["addTitle"] = '<spring:message code="dish.add"/>';
    i18n["editTitle"] = '<spring:message code="dish.edit"/>';
</script>
</html>

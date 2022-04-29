<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<script src="resources/js/votingsystem.common.js" defer></script>
<script src="resources/js/votingsystem.restaurants.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<jsp:useBean id="vote" type="ru.java.votingsystem.model.Vote" scope="request"/>
<jsp:useBean id="canInputVote" type="java.lang.Boolean" scope="request"/>

<div class="jumbotron pt-4">
    <div class="container">

        <h3 class="text-center">

            <spring:message code="restaurant.title"/>
            <spring:message code= "${!canInputVote && vote.id!=null ? 'common.cannotadd' : 'common.empty'}"/>

        </h3>

        <button visible-admin="${itIsAdmin}" class="btn btn-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            <spring:message code="common.add"/>
        </button>
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="restaurant.name"/></th>
                <th></th>
                <th></th>
                <th visible-admin="${itIsAdmin}"></th>
                <th visible-admin="${itIsAdmin}"></th>
            </tr>
            </thead>
            <c:forEach items="${requestScope.restaurants}" var="restaurant">
                <jsp:useBean id="restaurant" type="ru.java.votingsystem.model.Restaurant"/>
                <tr id="${restaurant.id}">

                    <td><c:out value="${restaurant.name}"/></td>

                    <td><a href="restaurants/dishes?id=${restaurant.id}"><spring:message code="dish.title"/></a></td>

                    <td><a href="restaurants/voteSelect?idRestaurant=${restaurant.id}&idVote=${vote.id}"
                            style="${restaurant.id == vote.restaurant.id ? 'color: red' : 'color:blue'}">
                        <spring:message code="${restaurant.id == vote.restaurant.id ? 'vote.select' : 'vote.name'}"/>
                    </a></td>


                    <td visible-admin="${itIsAdmin}"><a onclick=updateRow(${restaurant.id})><span class="fa fa-pencil"></span></a></td>
                    <td visible-admin="${itIsAdmin}"><a onclick="deleteRow(${restaurant.id})"><span class="fa fa-remove"></span></a></td>
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

                    <div class="form-group">
                        <label for="name" class="col-form-label"><spring:message code="restaurant.name"/></label>
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="<spring:message code="restaurant.name"/>">
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
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
    i18n["addTitle"] = '<spring:message code="restaurant.add"/>';
    i18n["editTitle"] = '<spring:message code="restaurant.edit"/>';
</script>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../jsp/parts/head.jsp" %>


<html>
<body>
<%@ include file="../jsp/parts/navbar.jsp" %>
<div class="container">
    <div class="col-lg-3 col-md-3 col-xs-3" style="background-color:lightgray">
        <form action="<c:url value="/tour"/>" method="get" data-toggle="validator" role="form">
            <fieldset>
                <legend><fmt:message key="filter-legend"/>: </legend>
                <h4><fmt:message key="tour-type-label"/>:</h4>
                <c:forEach items="${type_map}" var="type">
                    <div>
                        <input type="checkbox" id="${type.key}" name="type_opt" value="${type.key}"
                                <c:if test="${type.value == true}">
                                    checked
                                </c:if>
                        />
                        <label for="${type.key}"><fmt:message key="${type.key}"/></label>
                    </div>
                </c:forEach>
            </fieldset>
            <div class="form-group">
                <button type="submit" class="btn btn-primary" id="filter_submit">Фильтровать</button>
            </div>
        </form>
    </div>
    <div class="col-lg-9 col-md-9 col-xs-9">
        <form action="<c:url value="/tour"/>" method="get" class="form-inline">
            <div class="form-group">
                <label for="sort"><fmt:message key="sort-label"/>: </label>
                <select class="form-control .form-inline" id="sort" name="sort_opt">
                    <c:forEach items="${sort_map}" var="sort">
                        <option value="${sort.key}"
                            <c:if test="${sort.value == true}">
                                selected
                            </c:if>
                        >
                            <fmt:message key="sort.${sort.key}"/>
                        </option>

                    </c:forEach>
                </select>
            </div>
            <input type="hidden" name="json_filter" value=${json_filter}>
            <div class="form-group">
                <button type="submit" class="btn btn-primary" id="sort_submit_asc" value="asc">
                    <fmt:message key="sort"/>
                </button>
            </div>
        </form>
        <br>
        <table class="table table-striped">
            <tr>
                <th><fmt:message key="place"/></th>
                <th><fmt:message key="tour-type"/></th>
                <th><fmt:message key="date"/></th>
                <th></th>
            </tr>
            <c:forEach items="${tours_list}" var="tour">
                <tr>
                    <td><c:out value="${tour.place}"/></td>
                    <td><fmt:message key="${tour.type}"/></td>
                    <td><c:out value="${tour.date}"/></td>
                    <td><a href="/tour_open?id=${tour.id}"><fmt:message key="buy"/></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
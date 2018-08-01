
<%@ include file="../jsp/parts/head.jsp" %>

<html>
<body>
<%@ include file="../jsp/parts/navbar.jsp" %>
<div class="container">

    <div class="col-lg-5 col-md-5 col-xs-5" >
        <h1><fmt:message key="tour"/>:</h1>
        <h3><fmt:message key="place"/>: ${tour.place}</h3>
        <h3><fmt:message key="tour-type"/>: <fmt:message key="${tour.type}"/></h3>
        <h3><fmt:message key="date"/>: ${tour.date}</h3>
    </div>

    <div class="col-lg-7 col-md-7 col-xs-7" >
        <h1><fmt:message key="options"/>:</h1>
        <form action="<c:url value="/tour_buy"/>" method="post" class="form-inline">
            <div class="form-group">
                <label for="meal"><fmt:message key="meal-label"/>: </label>
                <select class="form-control" id="meal" name="meal_opt">
                    <c:forEach items="${meal_set}" var="meal">
                        <option value="${meal}">
                            <fmt:message key="${meal}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <br><br>
            <div class="form-group">
                <label for="transport"><fmt:message key="transport-label"/>: </label>
                <select class="form-control" id="transport" name="transport_opt">
                    <c:forEach items="${transport_set}" var="transport">
                        <option value="${transport}">
                            <fmt:message key="${transport}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <input type="hidden" name="id" value=${tour_id}>
            <input type="hidden" name="tour_bean" value=${tour}>
            <br>
            <div class="form-group">
                <button type="submit" class="btn btn-primary" id="submit">
                    <fmt:message key="buy"/>
                </button>
            </div>
        </form>
    </div>




</div>
</body>
</html>
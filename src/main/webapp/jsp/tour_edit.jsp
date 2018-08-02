
<%@ include file="../jsp/parts/head.jsp" %>

<html>
<body>
<%@ include file="../jsp/parts/navbar.jsp" %>
<div class="container">

    <div class="col-lg-5 col-md-5 col-xs-5" >
        <h1><fmt:message key="tour"/>:</h1>
        <h3><fmt:message key="place"/>: ${tour.placeRepresentation}</h3>
        <h3><fmt:message key="tour-type"/>: <fmt:message key="${tour.type}"/></h3>
        <h3><fmt:message key="date"/>: ${tour.date}</h3>
    </div>

    <div class="col-lg-7 col-md-7 col-xs-7" >
        <h1><fmt:message key="options"/>:</h1>
        <form id = "meetingForm" action="<c:url value="/tour_buy"/>" method="post" class="form-inline">
            <div class="input-group">
                <label for="meal"><fmt:message key="meal-label"/>: </label>
                <select class="form-control" id="meal" name="meal_opt">
                    <c:forEach items="${meal_set}" var="meal">
                        <option value="${meal}">
                            <fmt:message key="${meal}"/>
                        </option>
                    </c:forEach>
                </select>
                <br>
                <label for="transport"><fmt:message key="transport-label"/>: </label>
                <select class="form-control" id="transport" name="transport_opt">
                    <c:forEach items="${transport_set}" var="transport">
                        <option value="${transport}">
                            <fmt:message key="${transport}"/>
                        </option>
                    </c:forEach>
                </select>
                <br>
                <label for="duration"><fmt:message key="duration-label"/>:</label>
                <input type="number" name="duration" class="form-control" id="duration" value="1">
            </div>
            <br>
            <input type="hidden" name="id" value=${tour_id}>
            <br>
            <div class="form-group">
                <button type="submit" class="btn btn-primary" id="submit">
                    <fmt:message key="buy"/>
                </button>
            </div>
        </form>
    </div>
    <script type="text/javascript">
        var duration = document.getElementById("duration");
        duration.onchange = validateDuration;

        function validateDuration() {
            duration.setCustomValidity('');
            var strongRegex = new RegExp("^[1-9][0-9]{0,2}$");
            if (!strongRegex.test(duration.value)) {
                duration.setCustomValidity('<fmt:message key="duration-incorrect"/>');
            }
        }
    </script>
</div>
</body>
</html>
<%@ include file="../jsp/parts/head.jsp" %>

<html>
<body>
<%@ include file="../jsp/parts/navbar.jsp" %>
<div class="container">

    <h1><fmt:message key="congratulation-buy-tour"/></h1>
    <br>
    <div class="col-lg-5 col-md-5 col-xs-5" >
        <h1><fmt:message key="tour"/>:</h1>
        <h3><fmt:message key="place"/>: ${voucher.tour.placeRepresentation}</h3>
        <h3><fmt:message key="tour-type"/>: <fmt:message key="${voucher.tour.type}"/></h3>
        <h3><fmt:message key="date"/>: ${voucher.tour.date}</h3>

        <h3><fmt:message key="meal-label"/>: <fmt:message key="${voucher.mealType}"/></h3>
        <h3><fmt:message key="transport-label"/>: <fmt:message key="${voucher.transportType}"/></h3>
        <h3><fmt:message key="duration-label"/>: ${voucher.duration}</h3>
    </div>
</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="container-fluid">
    <div class="row">
        <nav role="navigation" class="navbar navbar-inverse">
            <div class="container">
                <div class="navbar-header header">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12">
                                <h1><a href="<c:url value="/"/>"><fmt:message key="web-site-name"/></a></h1>
                                <ul class="nav navbar-nav navbar-right">
                                    <li><a href="<c:url value="/set_locale?loc=en_En"/>">Eng</a></li>
                                    <li><a href="<c:url value="/set_locale?loc=ru_Ru"/>">Rus</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
    </div>
</div>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />

<header>
    <div class="row align-items-center">
        <div class="col-md-8">
            <a href="/pharmacy/controller?command=GO_TO_GREETING_PAGE"><fmt:message bundle="${loc}" key="local.to.main" /></a>
            <a href="/pharmacy/controller?command=GO_TO_CATALOG"><fmt:message bundle="${loc}" key="local.catalog.name" /></a>
            <a href="/pharmacy/controller?command=SHOW_DOCTORS"><fmt:message bundle="${loc}" key="local.doctor.list" /></a>
        </div>

        <div class="col-md-2 d-inline-flex">
            <form action="/pharmacy/controller" method="post">
                <input type="hidden" name="command" value="localization" />
                <input type="hidden" name="local" value="ru" />
                <input class="btn btn-dark" type="submit" value="${ru_button}" />
            </form>
            <form action="/pharmacy/controller" method="post" >
                <input type="hidden" name="command" value="localization" />
                <input type="hidden" name="local" value="en" />
                 <input class="btn btn-dark" type="submit" value="${en_button}" />
            </form>
        </div>

        <div class="col-md-2">
            <c:if test="${not empty user}">
                <div class="btn-group">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        My account
                    </button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="/pharmacy/controller?command=GO_TO_PERSONAL_CABINET"><fmt:message bundle="${loc}" key="local.personal.cabinet.title" /></a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="/pharmacy/controller?command=Logout"><fmt:message bundle="${loc}" key="local.logout" /></a>
                    </div>
                </div>
            </c:if>

            <c:if test="${empty user}">
                <a href="/pharmacy/controller?command=GO_TO_REGISTRATION_PAGE"><fmt:message bundle="${loc}" key="local.registration.link" /></a>
                <a href="/pharmacy/controller?command=GO_TO_LOGIN_PAGE"><fmt:message bundle="${loc}" key="local.logination.link" /></a>
            </c:if>

            <c:if test="${user.userRole.name() eq 'CUSTOMER'}">
                <a href="/pharmacy/controller?command=GO_TO_CART"><fmt:message bundle="${loc}" key="local.cart" /></a>
            </c:if>
        </div>
    <div/>
</header>
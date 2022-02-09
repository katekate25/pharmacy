<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
<header>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark"> <a class="navbar-brand" href="#">OnlinePharmacy</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"> <span class="navbar-toggler-icon"></span> </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/pharmacy/controller?command=GO_TO_GREETING_PAGE">
                        <fmt:message bundle="${loc}" key="local.to.main" />
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/pharmacy/controller?command=GO_TO_CATALOG">
                        <fmt:message bundle="${loc}" key="local.catalog.name" />
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/pharmacy/controller?command=SHOW_DOCTORS">
                        <fmt:message bundle="${loc}" key="local.doctor.list" />
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/pharmacy/controller?command=GO_TO_NEWS">
                        <fmt:message bundle="${loc}" key="local.articles" />
                    </a>
                </li>
            </ul>
        </div>

        <form class="form-inline my-4 mr-5 my-lg-0" action="/pharmacy/controller" method="post">
            <input type="hidden" name="command" value="localization" />
            <select class="form-control btn-dark" name="local" onchange="this.form.submit()">
                <option value="en" ${local eq 'en' ? 'selected' : ''}>${en_button}</option>
                <option value="ru" ${local eq 'ru' ? 'selected' : ''}>${ru_button}</option>
            </select>
        </form>

        <div ${user.userRole.name() eq 'CUSTOMER' ? 'class="btn-group"' : ''}>

            <c:if test="${user.userRole.name() eq 'CUSTOMER'}">
                <a class="btn btn-outline-light btn-lg" href="/pharmacy/controller?command=GO_TO_CART">
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
                        <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
                    </svg>
                </a>
            </c:if>

            <c:if test="${not empty user}">
                <button type="button" class="btn btn-outline-light btn-lg mr-2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-list" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z" />
                    </svg>
                </button>
                <div class="dropdown-menu" style="left:auto">
                    <c:if test="${user.userRole.name() eq 'CUSTOMER'}">
                        <a class="dropdown-item" href="/pharmacy/controller?command=GO_TO_ORDERS">
                            <fmt:message bundle="${loc}" key="local.my.orders" />
                        </a>
                        <a class="dropdown-item" href="/pharmacy/controller?command=GO_TO_PRESCRIPTIONS_LIST">
                            <fmt:message bundle="${loc}" key="local.prescriptions.list" /> </a>
                    </c:if>
                    <c:if test="${user.userRole.name() eq 'DOCTOR'}">
                        <a class="dropdown-item" href="/pharmacy/controller?command=GO_TO_MESSAGES_LIST">
                            <fmt:message bundle="${loc}" key="local.my.messages" />
                        </a>
                    </c:if>
                    <c:if test="${user.userRole.name() eq 'PHARMACIST'}">
                        <a class="dropdown-item" href="/pharmacy/controller?command=GO_TO_ORDER_LIST">
                            <fmt:message bundle="${loc}" key="local.all.orders" /> </a>
                        <a class="dropdown-item" href="/pharmacy/controller?command=GO_TO_CUSTOMERS_LIST">
                            <fmt:message bundle="${loc}" key="local.customers.list" /> </a>
                        <a class="dropdown-item" href="/pharmacy/controller?command=GO_TO_PRESCRIPTIONS_LIST">
                            <fmt:message bundle="${loc}" key="local.prescriptions.list" /> </a>
                        <a class="dropdown-item" href="/pharmacy/controller?command=GO_TO_ACCOUNT_PAGE">
                            report</a>
                    </c:if>
                    <a class="dropdown-item" href="/pharmacy/controller?command=GO_TO_PERSONAL_CABINET">
                        <fmt:message bundle="${loc}" key="local.personal.cabinet.title" /> </a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/pharmacy/controller?command=Logout">
                        <fmt:message bundle="${loc}" key="local.logout" />
                    </a>
                </div>
            </c:if>
            <c:if test="${empty user}">
                <a class="text-light mr-2" href="/pharmacy/controller?command=GO_TO_REGISTRATION_PAGE">
                    <fmt:message bundle="${loc}" key="local.registration.link" />
                </a>
                <a class="text-light mr-2" href="/pharmacy/controller?command=GO_TO_LOGIN_PAGE">
                    <fmt:message bundle="${loc}" key="local.logination.link" />
                </a>
            </c:if>
        </div>
    </nav>
</header>

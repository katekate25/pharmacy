<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<html>
<common:head title="Welcome" />

<body>
    <div class="container">
        <common:header />

        <c:if test="${accessDenied}">
            <fmt:message bundle="${loc}" key="local.access.denied" />
        </c:if>

        <br />
        <h1>
            <fmt:message bundle="${loc}" key="local.greeting.main.page" />
        </h1>
        <h2>
            <fmt:message bundle="${loc}" key="local.sold.now" />
        </h2>
        <h2>
            <fmt:message bundle="${loc}" key="local.articles" />
        </h2>
    </div>
</body>

</html>
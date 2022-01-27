<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.enter" var="enter" />

<html>
<common:head title="Log in" />

<body>
    <div class="container">
        <common:header />

        <h2>
            <fmt:message bundle="${loc}" key="local.logination.link" />
        </h2>
        <br />
        <form action="/pharmacy/controller" method="post">
            <input type="hidden" name="command" value="Login">
            <fmt:message bundle="${loc}" key="local.login" />:
            <input type="text" name="login" value="" />
            <br />
            <fmt:message bundle="${loc}" key="local.password" />:
            <input type="password" name="password" value="" />

            <input type="submit" value="${enter}" />
        </form>
        <br />
        <c:if test="${param['wrongUser']}">
            <fmt:message bundle="${loc}" key="local.incorrect.data" />
        </c:if>

        <common:footer />
    </div>
</body>

</html>
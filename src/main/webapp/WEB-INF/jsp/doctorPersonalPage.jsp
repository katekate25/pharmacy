<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.send.button" var="send_button" />

<html>
<common:head title="Personal page" />

<body>
    <div class="container">
        <common:header />

        <c:if test="${user.userRole.name() eq 'CUSTOMER'}">
            <h2>
                <fmt:message bundle="${loc}" key="local.ask.prescription" />
            </h2>
            <br />
            <form action="/pharmacy/controller" method="post">
                <input type="hidden" name="command" value="CREATE_REQUEST_FOR_PRESCRIPTION">
                <fmt:message bundle="${loc}" key="local.message.message" />:
                <input type="text" name="message" value="" />
                <br />
                <input type="hidden" name="recipient" value="${recipient}" />

                <input type="submit" value="${send_button}" />
            </form>
            <br />

            <c:if test="${param['sendSuccessful']}">
                <fmt:message bundle="${loc}" key="local.send" />
            </c:if>

        </c:if>

    </div>
</body>

</html>
<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.add.button" var="add_button" />

<html>
<common:head title="Prescription" />

<body>
    <div class="container">
        <common:header />

        <h2>
            <fmt:message bundle="${loc}" key="local.prescription" />
        </h2>
        <form action="/pharmacy/controller" method="post">
            <input type="hidden" name="command" value="ADD_PRESCRIPTION_TO_DB" />

            <fmt:message bundle="${loc}" key="local.prescription.patient" />:
            <input type="text" name="client" value="" />
            <br />

            <input type="hidden" name="serialNumber" value="${serialNumber}">

            <input type="hidden" name="doctor" value="${login}" />

            <fmt:message bundle="${loc}" key="local.prescribed.amount" />:
            <input type="text" name="packageAmount" value="" />
            <br />
            <fmt:message bundle="${loc}" key="local.usage.instruction" />:
            <input type="text" name="usageInstruction" value="" />
            <br />
            <fmt:message bundle="${loc}" key="local.prescription.creation.date" />:
            <input type="text" name="creationDate" value="" />
            <br />
            <fmt:message bundle="${loc}" key="local.prescription.expiration.date" />:
            <input type="text" name="expirationDate" value="" />
            <br />
            <input type="submit" value="${add_button}" />

        </form>

        <common:footer />
    </div>
</body>

</html>
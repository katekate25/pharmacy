<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<html>
<common:head title="Medicine" />
 <style>
body { background: url(img/medical-supplies-placed-on-a-blue.jpg);
      background-size:cover;
      margin: 0;

      }
       </style>
<body>
    <common:header />
    <div class="container">

        <br>
        <fmt:message bundle="${loc}" key="local.commercialName" /> - ${medicineBySeries.commercialName}<br>
        <fmt:message bundle="${loc}" key="local.internationalName" /> -
        <c:out value="${medicineBySeries.internationalName}" /><br>
        <fmt:message bundle="${loc}" key="local.medicineForm" /> -
        <c:out value="${medicineBySeries.medicineForm}" /><br>
        <fmt:message bundle="${loc}" key="local.medicineDose" /> -
        <c:out value="${medicineBySeries.medicineDose}" />mg<br>
        <fmt:message bundle="${loc}" key="local.packagePrice" /> -
        <c:out value="${medicineBySeries.packagePrice}" />$<br>
        <fmt:message bundle="${loc}" key="local.expirationDate" /> -
        <c:out value="${medicineBySeries.medicineExpirationDate}" /><br>
        <fmt:message bundle="${loc}" key="local.productBalance" /> -
        <c:out value="${medicineBySeries.productBalance}" /><br>
        <fmt:message bundle="${loc}" key="local.prescriptionRequired" /> -
        <c:out value="${medicineBySeries.prescriptionRequired}" /><br>
        <fmt:message bundle="${loc}" key="local.producer" /> -
        <c:out value="${medicineBySeries.producer.producerFactoryName}" />,
        <c:out value="${medicineBySeries.producer.producerCountry}" /><br>
        <fmt:message bundle="${loc}" key="local.diseaseGroup" /> -
        <c:out value="${medicineBySeries.diseaseGroup}" /><br>


        <c:if test="${user.userRole.name() eq 'PHARMACIST'}">
            <fmt:message bundle="${loc}" key="local.serialNumber" /> -
            <c:out value="${medicineBySeries.serialNumber}" /><br>
            <fmt:message bundle="${loc}" key="local.invoiceNumber" /> -
            <c:out value="${medicineBySeries.invoiceNumber}" /><br>
            <fmt:message bundle="${loc}" key="local.arrivalDate" /> -
            <c:out value="${medicineBySeries.arrivalDate}" /><br>
            <fmt:message bundle="${loc}" key="local.productArrival" /> -
            <c:out value="${medicineBySeries.productArrival}" /><br>
        </c:if>

        <c:if test="${user.userRole.name() eq 'DOCTOR'}">
            <br>
            <fmt:message bundle="${loc}" key="local.add.prescription" />:<a href="/pharmacy/controller?command=GO_TO_PRESCRIPTION_PAGE&serialNumber=${medicineBySeries.serialNumber}">Prescribe</a><br>
        </c:if>


        <c:if test="${user.userRole.name() eq 'CUSTOMER'}">
            <form action="/pharmacy/controller" method="post">
                <input type="hidden" name="command" value="ADD_ENTRY_TO_CART">
                <fmt:message bundle="${loc}" key="local.amount.medicine" />:
                <input type="number" name="packageAmount" min="1" max="${medicineBySeries.productBalance}" value="1" />
                <input type="hidden" name="serialNumber" value="${medicineBySeries.serialNumber}">
                <br />
                <input type="submit" class="btn btn-dark" value="enter" />
            </form>
            <br />
        </c:if>

    </div>
    <common:footer />
</body>

</html>
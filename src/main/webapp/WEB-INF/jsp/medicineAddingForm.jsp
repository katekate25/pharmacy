<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<html>
<common:head title="Add medicine" />
 <style>
body { background: url(img/medical-supplies-placed-on-a-blue.jpg);
      background-size:cover;
      margin: 0;

      }
       </style>
<body>
    <div class="container">
        <common:header />


        <h2>
            <fmt:message bundle="${loc}" key="local.medicine.form" />
        </h2>
        <br />

        <form action="/pharmacy/controller" method="post">
            <input type="hidden" name="command" value="ADD_MEDICINE_TO_DB">
            <fmt:message bundle="${loc}" key="local.commercialName" />:
            <input type="text" name="commercialName" value="" />
            <br />
            <fmt:message bundle="${loc}" key="local.internationalName" />:
            <input type="text" name="internationalName" value="" />
            <br />
            <fmt:message bundle="${loc}" key="local.medicineForm" />:
            <input type="text" name="medicineForm" value="" />
            <br />
            <fmt:message bundle="${loc}" key="local.medicineDose" />:
            <input type="text" name="medicineDose" value="" />
            <br />
            <fmt:message bundle="${loc}" key="local.productArrival" />:
            <input type="text" name="productArrival" value="" />
            <br />
            <fmt:message bundle="${loc}" key="local.diseaseGroup" />:
            <input type="text" name="diseaseGroup" value="" />
            <br />
            <fmt:message bundle="${loc}" key="local.arrivalDate" />:
            <input type="text" name="arrivalDate" value="" />
            <br />
            <fmt:message bundle="${loc}" key="local.invoiceNumber" />:
            <input type="text" name="invoiceNumber" value="" />
            <br />
            <fmt:message bundle="${loc}" key="local.expirationDate" />:
            <input type="text" name="medicineExpirationDate" value="" />
            <br />
            <fmt:message bundle="${loc}" key="local.prescriptionRequired" />:
            <input type="text" name="prescriptionRequired" value="" />
            <br />
            <fmt:message bundle="${loc}" key="local.packagePrice" />:
            <input type="text" name="packagePrice" value="" />
            <br />
            <fmt:message bundle="${loc}" key="local.producer" />:
            <input type="text" name="producerName" value="" />
            <br />
            <fmt:message bundle="${loc}" key="local.serialNumber" />:
            <input type="text" name="serialNumber" value="" />
            <br />

            <input type="submit" value="enter" />
        </form>

        <c:if test="${param['successfulAdding']}">
            <fmt:message bundle="${loc}" key="local.adding.successfull" />
        </c:if>

        <common:footer />
    </div>
</body>

</html>